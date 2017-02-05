package com.jevalab.azure.group1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;
import com.google.gson.Gson;
import com.jevalab.azure.notifications.LikeNotification;
import com.jevalab.azure.notifications.Notification;
import com.jevalab.azure.persistence.AzureUser;
import com.jevalab.azure.persistence.Discussion;
import com.jevalab.azure.persistence.GeneralController;
import com.jevalab.helper.classes.DiscussionBean;
import com.jevalab.helper.classes.EntityConverter;
import com.jevalab.helper.classes.Util;
import com.jevalab.helper.classes.WelcomePageBean;

public class AddLike extends HttpServlet {
	private static final long serialVersionUID = -5985685432127198226L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String webKey = req.getParameter("webkey");
		Object o = null;
		Object o1 = null;
		HttpSession session = req.getSession();

		synchronized (session) {
			o = session.getAttribute("azureUser");
			o1 = session.getAttribute("welcomePage");
		}
		if ((Util.notNull(new String[] { webKey })) && (o != null)
				&& (o1 != null)) {
			AzureUser u = (AzureUser) o;
			Key key = KeyFactory.stringToKey(webKey);
			Entity e = GeneralController.findByKey(key);
			if (e != null) {
				Discussion d = EntityConverter.entityToDiscussion(e);
				List<Key> likers = d.getLikers();

				if (likers == null) {
					likers = new ArrayList<>();
				}
				Key userKey = u.getKey();

				if (likers.contains(userKey))
					likers.remove(userKey);
				else {
					likers.add(userKey);
				}

				d.setLikers(likers);

				WelcomePageBean wpb = (WelcomePageBean) o1;
				List<DiscussionBean> ds = wpb.getPosts();
				long likes = 0L;
				for (DiscussionBean b : ds) {
					if (b.getWebkey().equals(webKey)) {
						if (b.isLiked()) {
							b.setLiked(false);
							b.setLikes(b.getLikes() - 1L);
						} else {
							b.setLiked(true);
							b.setLikes(b.getLikes() + 1L);
						}
						likes = b.getLikes();
						break;
					}
				}

				wpb.setPosts(ds);
				synchronized (session) {
					session.setAttribute("welcomePage", wpb);
				}

				Notification not = new Notification();
				not.setDate(new Date());
				not.setMessage(new Text(KeyFactory.keyToString(d.getId())));
				not.setRecipient(d.getOwner());
				not.setSender(u.getKey());
				not.setType(LikeNotification.class.getSimpleName());

				AzureUser au = EntityConverter.entityToUser(GeneralController
						.findByKey(d.getOwner()));
				List<Key> keys = au.getNewNotifications();
				if (keys == null) {
					keys = new ArrayList<>();
				}
				keys.add(not.getId());
				au.setNewNotifications(keys);

				GeneralController.createWithCrossGroup(new Entity[] {
						EntityConverter.discussionToEntity(d),
						EntityConverter.userToEntity(au),
						EntityConverter.notificationToEntity(not) });

				resp.setContentType("application/json");
				resp.getWriter().write(new Gson().toJson(Long.valueOf(likes)));
			}
		}
	}
}
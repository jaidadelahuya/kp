package com.jevalab.azure.notifications;

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
import com.jevalab.azure.persistence.AzureUser;
import com.jevalab.azure.persistence.GeneralController;
import com.jevalab.azure.profile.UserProfile;
import com.jevalab.helper.classes.EntityConverter;
import com.jevalab.helper.classes.StringConstants;
import com.jevalab.helper.classes.Util;

public class SendFriendRequest extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7317762211249534305L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String webKey = req.getParameter("web-key");

		AzureUser u = null;
		Object o = null;
		HttpSession session = req.getSession();
		Object o2 = null;

		synchronized (session) {
			o = session.getAttribute(StringConstants.AZURE_USER);
			o2 = session.getAttribute("userProfile");
		}

		if (Util.notNull(webKey) && o != null) {
			UserProfile up = null;
			if(o2 != null) {
				 up = (UserProfile) o2;
				 up.setFriend(true);
				 synchronized (session) {
					session.setAttribute("userProfile", up);
				}
			}
			u = (AzureUser) o;
			Notification not = new Notification();
			not.setDate(new Date());
			not.setRecipient(KeyFactory.stringToKey(webKey));
			not.setSender(u.getKey());
			not.setType(FriendRequestNotification.class.getSimpleName());

			Entity e = GeneralController.findByKey(not.getRecipient());
			if (e != null) {
				AzureUser re = EntityConverter.entityToUser(e);
				List<Key> keys = re.getNewNotifications();
				if (keys == null) {
					keys = new ArrayList<>();
				}
				keys.add(not.getRecipient());
				re.setNewNotifications(keys);

				GeneralController.createWithCrossGroup(
						EntityConverter.userToEntity(re),
						EntityConverter.notificationToEntity(not));
			}
		}
	}

}

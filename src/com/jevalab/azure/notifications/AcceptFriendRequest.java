package com.jevalab.azure.notifications;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import com.jevalab.helper.classes.EntityConverter;
import com.jevalab.helper.classes.StringConstants;
import com.jevalab.helper.classes.Util;

public class AcceptFriendRequest extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5286224093793272492L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String webKey = req.getParameter("web-key");

		Object o = null;
		Object o1 = null;

		HttpSession session = req.getSession();
		synchronized (session) {
			o = session.getAttribute("notificationPage");
			o1 = session.getAttribute(StringConstants.AZURE_USER);
		}

		if (Util.notNull(webKey) && o != null && o1 != null) {
			NotificationPageBean npb = (NotificationPageBean) o;
			Iterator<NotificationBean> its = npb.getNotifications().iterator();
			while (its.hasNext()) {
				NotificationBean nb = its.next();
				if (nb.getNotificationKey().equals(webKey)) {
					its.remove();
					AzureUser u = (AzureUser) o1;
					List<Key> friends = u.getFriendsId();
					if (friends == null) {
						friends = new ArrayList<>();
					}
					friends.add(KeyFactory.stringToKey(nb.getSender()
							.getWebKey()));
					u.setFriendsId(friends);

					Notification n = new Notification();
					n.setDate(new Date());
					n.setRecipient(KeyFactory.stringToKey(nb.getSender()
							.getWebKey()));
					n.setSender(u.getKey());
					n.setType(FriendRequestAcceptedNotification.class
							.getSimpleName());
					AzureUser uu = EntityConverter
							.entityToUser(GeneralController
									.findByKey(KeyFactory.stringToKey(nb
											.getSender().getWebKey())));
					List<Key> f2 = uu.getFriendsId();
					if(f2==null) {
						f2 = new ArrayList<>();
					}
					f2.add(u.getKey());
					uu.setFriendsId(f2);
					List<Key> nots = uu.getNewNotifications();
					if(nots == null) {
						nots = new ArrayList<>();
					}
					nots.add(n.getId());
					uu.setNewNotifications(nots);
					Entity ee = EntityConverter.notificationToEntity(n);
					GeneralController.createWithCrossGroup(EntityConverter.userToEntity(u), ee,EntityConverter.userToEntity(uu) );

					GeneralController.delete(KeyFactory.stringToKey(nb
							.getNotificationKey()));
					break;
				}
			}
			synchronized (session) {
				session.setAttribute("notificationPage", npb);
			}

		}
	}

}

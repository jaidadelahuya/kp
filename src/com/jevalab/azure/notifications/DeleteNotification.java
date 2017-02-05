package com.jevalab.azure.notifications;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.KeyFactory;
import com.jevalab.azure.persistence.GeneralController;
import com.jevalab.helper.classes.Util;

public class DeleteNotification extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7147649721255622237L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String webKey = req.getParameter("web-key");
		
		Object o = null;
		
		HttpSession session = req.getSession();
		synchronized (session) {
			o = session.getAttribute("notificationPage");
		}
		
		if(Util.notNull(webKey) && o != null) {
			NotificationPageBean npb = (NotificationPageBean) o;
			Iterator<NotificationBean> its = npb.getNotifications().iterator();
			while(its.hasNext()) {
				NotificationBean nb = its.next();
				if(nb.getNotificationKey().equals(webKey)) {
					its.remove();
					break;
				}
			}
			synchronized (session) {
				session.setAttribute("notificationPage", npb);
			}
			
			GeneralController.delete(KeyFactory.stringToKey(webKey));
		}
	}

}

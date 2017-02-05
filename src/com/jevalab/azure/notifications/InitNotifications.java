/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.jevalab.azure.notifications;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jevalab.azure.persistence.AzureUser;
import com.jevalab.helper.classes.MainNav;
import com.jevalab.helper.classes.StringConstants;
import com.jevalab.helper.classes.Util;
import com.jevalab.helper.classes.WelcomePageBean;

public class InitNotifications extends HttpServlet {
	private static final long serialVersionUID = -5864947704712642857L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object o1 = null;
		Object o3 = null;
		synchronized (session) {
			session.removeAttribute("notificationsError");
			Object o = session.getAttribute("mainNav");
			if (o != null) {
				MainNav mn = (MainNav) o;
				mn.setNotification(Boolean.valueOf(true));
				session.setAttribute("mainNav", mn);
			}
			
			o1 = session.getAttribute(StringConstants.AZURE_USER);
			o3 = session.getAttribute("notificationPage");

		}
		
		if(o1!=null) {
			AzureUser u = (AzureUser) o1;
			if(u.getNewNotifications()!=null) {
				u.getNewNotifications().clear();
				Object o2 = null;
				synchronized (session) {
					o2 = session.getAttribute("welcomePage");
				}
				
				if(o2 == null) {
					resp.sendRedirect("/");
					return;
				}else {
					WelcomePageBean wp = (WelcomePageBean) o2;
					wp.setNewNotification(0);
					synchronized (session) {
						session.setAttribute("welcomePage", wp);
					}
				}
			}
			
			if(o3 == null) {
				NotificationPageBean npb = Util.initNotificationPageBean(u); 
				synchronized (session) {
					session.setAttribute("notificationPage", npb);
				}
			}
			req.getRequestDispatcher("/WEB-INF/notifications/index.jsp").include(
					req, resp);
		}else {
			resp.sendRedirect("/");
		
		}

		
	}
}
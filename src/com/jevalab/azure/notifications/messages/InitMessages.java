package com.jevalab.azure.notifications.messages;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jevalab.azure.notifications.NotificationPageBean;
import com.jevalab.azure.persistence.AzureUser;
import com.jevalab.helper.classes.MainNav;
import com.jevalab.helper.classes.StringConstants;
import com.jevalab.helper.classes.Util;
import com.jevalab.helper.classes.WelcomePageBean;

public class InitMessages extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2386358116121225793L;
	@Override
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
				mn.setMessages(Boolean.valueOf(true));
				session.setAttribute("mainNav", mn);
			}
			
			o1 = session.getAttribute(StringConstants.AZURE_USER);
			o3 = session.getAttribute("imessagePage");

		}
		
		if(o1!=null) {
			AzureUser u = (AzureUser) o1;
			if(u.getNewMessageNotifications()!=null) {
				u.getNewMessageNotifications().clear();
				Object o2 = null;
				synchronized (session) {
					o2 = session.getAttribute("welcomePage");
				}
				
				if(o2 == null) {
					resp.sendRedirect("/");
					return;
				}else {
					WelcomePageBean wp = (WelcomePageBean) o2;
					wp.setNewMessageNotification(0);
					synchronized (session) {
						session.setAttribute("welcomePage", wp);
					}
				}
			}
			
			if(o3 == null) {
				MPageBean npb = Util.initMessagePageBean(u); 
				synchronized (session) {
					session.setAttribute("imessagePage", npb);
					session.setAttribute(StringConstants.AZURE_USER, u);
				}
			}
			req.getRequestDispatcher("/WEB-INF/notifications/messages/index.jsp").include(
					req, resp);
		}else {
			resp.sendRedirect("/");
		
		}

	}

}

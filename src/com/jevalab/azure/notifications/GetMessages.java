package com.jevalab.azure.notifications;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.KeyFactory;
import com.jevalab.azure.notifications.messages.MPageBean;
import com.jevalab.azure.persistence.AzureUser;
import com.jevalab.helper.classes.StringConstants;
import com.jevalab.helper.classes.Util;

public class GetMessages extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2781326021308594233L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String webKey = req.getParameter("web-key");

		Object o = null;
		Object o1 = null;
		HttpSession session = req.getSession();
		synchronized (session) {
			o = session.getAttribute("imessagePage");
			o1 = session.getAttribute(StringConstants.AZURE_USER);
		}

		if (Util.notNull(webKey) && o != null && o1 != null) {
			AzureUser u = (AzureUser) o1;
			MPageBean npb = (MPageBean) o;
			Iterator<MessageNotification> its = npb.getNotifications().iterator();
			MessagePageBean mpb = null;
			MessageNotification bean = null;
			while (its.hasNext()) {
				MessageNotification nb = its.next();
				
				if (nb.getId().equals(webKey)) {
					bean = nb;
					mpb = Util.getMessages(KeyFactory
							.stringToKey(nb.getSender().getWebKey()),
							u);
					break;
				}
			}
			
			synchronized (session) {
				session.setAttribute("messagePage", mpb);
			}
			Util.updateMessageNotifications(bean);
			resp.sendRedirect(resp.encodeRedirectURL("/azure/notifications/p/messages#"+bean.getMessageId()));

		}
	}

}

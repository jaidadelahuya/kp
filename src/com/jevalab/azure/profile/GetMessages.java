package com.jevalab.azure.profile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.KeyFactory;
import com.jevalab.azure.notifications.Message;
import com.jevalab.azure.notifications.MessagePageBean;
import com.jevalab.azure.persistence.AzureUser;
import com.jevalab.helper.classes.StringConstants;
import com.jevalab.helper.classes.Util;

public class GetMessages extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7493240637925016999L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String webKey = req.getParameter("web-key");

		Object o1 = null;
		HttpSession session = req.getSession();
		synchronized (session) {

			o1 = session.getAttribute(StringConstants.AZURE_USER);

		}
		
		if (Util.notNull(webKey) && o1 != null) {
			AzureUser u = (AzureUser) o1;
			MessagePageBean mpb = Util.getMessages(KeyFactory
					.stringToKey(webKey),
					u);
			
			synchronized (session) {
				session.setAttribute("messagePage", mpb);
			}
			Message m = null;
			if(mpb.getMessages() != null && !mpb.getMessages().isEmpty() ) {
				for(Date d : mpb.getMessages().keySet()) {
					List<Message> msgs= mpb.getMessages().get(d);
					m = msgs.get(msgs.size()-1);
				}
				resp.sendRedirect(resp.encodeRedirectURL("/azure/notifications/p/messages#"+m.getWebKey()));
			}else {
				resp.sendRedirect(resp.encodeRedirectURL("/azure/notifications/p/messages"));
			}
			
			
		}

	}

}

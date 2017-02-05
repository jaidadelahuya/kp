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
import com.google.appengine.api.datastore.Text;
import com.jevalab.azure.notifications.messages.MessageN;
import com.jevalab.azure.persistence.AzureUser;
import com.jevalab.azure.persistence.GeneralController;
import com.jevalab.helper.classes.EntityConverter;
import com.jevalab.helper.classes.StringConstants;
import com.jevalab.helper.classes.Util;

public class SendMessage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3305735023906777958L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String webKey = req.getParameter("web-key");
		String message = req.getParameter("message");
		
		if (!Util.notNull(message)) {
			resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
					"Error: You can not send an empty message.");
			return;
		} else if (!Util.notNull(webKey)) {
			resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
					"Error: Your message has not been sent.");
			return;
		} else {
			AzureUser u = null;
			Object o = null;
			HttpSession session = req.getSession();

			synchronized (session) {
				o = session.getAttribute(StringConstants.AZURE_USER);
			}

			if (o != null) {
				u = (AzureUser) o;
				MessageN not = new MessageN();
				not.setDate(new Date());
				not.setRecipient(KeyFactory.stringToKey(webKey));
				not.setSender(u.getKey());
				not.setType(MessageNotification.class.getSimpleName());
				not.setMessage(new Text(message));
				Entity e = GeneralController.findByKey(not.getRecipient());
				if (e != null) {
					AzureUser re = EntityConverter.entityToUser(e);
					List<Key> keys = re.getNewMessageNotifications();
					if (keys == null) {
						keys = new ArrayList<>();
					}
					keys.add(not.getRecipient());
					re.setNewMessageNotifications(keys);

					GeneralController.createWithCrossGroup(
							EntityConverter.userToEntity(re),
							EntityConverter.MessageNToEntity(not));
				}
			} else {
				resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
						"Error: Your message has not been sent.");
				return;
			}
		}
	}

}

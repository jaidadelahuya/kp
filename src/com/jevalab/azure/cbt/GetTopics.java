package com.jevalab.azure.cbt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.KeyFactory;
import com.jevalab.helper.classes.Util;

public class GetTopics extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1485728540647418181L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String subject = req.getParameter("subject");

		HttpSession session = req.getSession();
		if (Util.notNull(subject)) {
			
			CustomTest2 ct2 = new CustomTest2();
			ct2.setSubject(subject);
			List<Topic> topics = Util.getSubjectTopics(KeyFactory.createKey(
					Subject.class.getSimpleName(), subject.toUpperCase()));
			ct2.setTopics(topics);

			synchronized (session) {
				session.setAttribute("ct2", ct2);
			}
			resp.sendRedirect(resp
					.encodeRedirectURL("/azure/cbt/utme/p2/custom/2"));
			return;

		} else {
			resp.sendRedirect("/azure/cbt/utme/p/custom/2");
		}
	}

}

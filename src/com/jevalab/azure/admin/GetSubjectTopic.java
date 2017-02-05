package com.jevalab.azure.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.jevalab.azure.cbt.Subject;
import com.jevalab.azure.cbt.Topic;
import com.jevalab.helper.classes.Util;

public class GetSubjectTopic extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8879726011315680212L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String sub = req.getParameter("subject");
		List<Topic> list = null;
		if (Util.notNull(sub)) {
			Key key = KeyFactory.createKey(Subject.class.getSimpleName(),
					sub.toUpperCase());
			list = Util.getSubjectTopics(key);
			HttpSession session = req.getSession();
			synchronized (session) {
				session.removeAttribute("question");
				session.setAttribute("subjectName", sub.toUpperCase());
				session.setAttribute("topics", list);
			}

			resp.sendRedirect(resp
					.encodeRedirectURL("/ca/admin/i/question/new"));
		}

	}

}

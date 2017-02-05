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

public class GetQuestion extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1586094968210235324L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String webKey = req.getParameter("web-key");

		HttpSession session = req.getSession();
		Object o = null;

		synchronized (session) {
			o = session.getAttribute("editQuestionsPage");
		}

		if (Util.notNull(webKey) && o != null) {
			EditQuestionsPage eqp = (EditQuestionsPage) o;
			List<com.jevalab.azure.persistence.Question> l = eqp.getoQ();
			for (com.jevalab.azure.persistence.Question q : l) {
				if (KeyFactory
						.createKey(
								com.jevalab.azure.persistence.Question.class
										.getSimpleName(),
								q.getId()).equals(
								KeyFactory.stringToKey(webKey))) {
					Key key = KeyFactory.createKey(Subject.class.getSimpleName(), q.getSubjectName().toUpperCase());
					List<Topic> topics = Util.getSubjectTopics(key);
					eqp.setTopics(topics);
					
					eqp.setCurrentQuestion(q);
					
					synchronized (session) {
						session.removeAttribute("formSuccess");
						session.setAttribute("editQuestionsPage", eqp);
					}
					resp.sendRedirect(resp.encodeRedirectURL("/ca/admin/cbt/p/question/update"));
					return;
				}
			}

		}
	}
}

package com.jevalab.azure.admin;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.KeyFactory;
import com.jevalab.azure.cbt.Question;
import com.jevalab.azure.persistence.GeneralController;
import com.jevalab.helper.classes.Util;

public class DeleteQuestion extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6381056514427956342L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String webKey = req.getParameter("web-key");
		
		HttpSession session = req.getSession();
		Object o = null;
		synchronized (session) {
			o = session.getAttribute("editQuestionsPage");
		}
		
		if(Util.notNull(webKey) && o!=null) {
			EditQuestionsPage eqp = (EditQuestionsPage) o;
			List<Question> q = eqp.getQuestions();
			Iterator<Question> it = q.iterator();
			
			while(it.hasNext()) {
				if(it.next().getWebKey().equals(webKey)) {
					it.remove();
					break;
				}
			}
			eqp.setQuestions(q);
			synchronized (session) {
				session.setAttribute("editQuestionsPage", eqp);
			}
			
			GeneralController.delete(KeyFactory.stringToKey(webKey));
			resp.sendRedirect(resp.encodeRedirectURL("/ca/admin/cbt/p2/questions/edit"));
		}
	}

}

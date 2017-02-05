package com.jevalab.azure.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InitEditQuestions extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7447559454718276154L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		synchronized (session) {
			session.removeAttribute("addQuestionError");
			session.removeAttribute("addQuestionSuccess");
		}
		
		req.getRequestDispatcher("/ca/admin/cbt/p1/questions/edit").include(req, resp);
	}

}

package com.jevalab.azure.admin;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jevalab.helper.classes.Util;

public class QuestionStatsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8740442866044258191L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String subject = req.getParameter("subject");
		HttpSession session = req.getSession();
		if(Util.notNull(subject)) {
			List<QuestionStats> lqs = Util.getQuestionStats(subject);
			Collections.sort(lqs);
			
			synchronized (session) {
				session.setAttribute("questionStats", lqs);
			}
			
			resp.sendRedirect(resp.encodeRedirectURL("/ca/admin/cbt/2/question/stats"));
			
		}
	}

}

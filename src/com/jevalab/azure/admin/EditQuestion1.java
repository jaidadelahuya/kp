package com.jevalab.azure.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jevalab.azure.cbt.Question;
import com.jevalab.helper.classes.Util;

public class EditQuestion1 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6012461964203164431L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String vendor = req.getParameter("vendor");
		String year = req.getParameter("year");
		String subject = req.getParameter("subject");
		
		HttpSession session = req.getSession();
		if(Util.notNull(vendor,year,subject)) {
			EditQuestionsPage q = Util.getQuestions(vendor,year,subject);
			synchronized (session) {
				
				session.setAttribute("editQuestionsPage", q);
			}
			resp.sendRedirect(resp.encodeRedirectURL("/ca/admin/cbt/p2/questions/edit"));
		}else{
			synchronized (session) {
				session.setAttribute("formError", "Vendor, year and subject are compulsory");
			}
			resp.sendRedirect(resp.encodeRedirectURL("/ca/admin/cbt/p1/questions/edit"));
		}
	}

}

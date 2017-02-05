package com.jevalab.azure.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InitAddDiscussion extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8574938706854452002L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		synchronized (session) {
			session.removeAttribute("formError");
			session.removeAttribute("formSuccess");
		}
		
		resp.sendRedirect(resp.encodeRedirectURL("/ca/admin/discussion/form/new"));
	}

}

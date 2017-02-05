package com.jevalab.azure.cbt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InitCustomUTME2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8444268766851093337L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		synchronized (session) {
			session.removeAttribute("formSuccess");
			session.removeAttribute("formError");
		}
		
		resp.sendRedirect(resp.encodeRedirectURL("/azure/cbt/utme/p/custom/2"));
	}

}

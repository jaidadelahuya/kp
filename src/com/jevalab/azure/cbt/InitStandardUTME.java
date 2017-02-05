package com.jevalab.azure.cbt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InitStandardUTME extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8895672468573004233L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		synchronized (session) {
			session.removeAttribute("formSuccess");
			session.removeAttribute("formError");
		}
		
		resp.sendRedirect(resp.encodeRedirectURL("/azure/cbt/utme/p/standard/default"));
	}

}

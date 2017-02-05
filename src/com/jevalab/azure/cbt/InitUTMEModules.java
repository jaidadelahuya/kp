package com.jevalab.azure.cbt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InitUTMEModules extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1532613476602135717L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		synchronized (session) {
			session.removeAttribute("formError");
			session.removeAttribute("formSuccess");
		}
		
		resp.sendRedirect(resp.encodeRedirectURL("/azure/cbt/p/utme/modules"));
	}

}

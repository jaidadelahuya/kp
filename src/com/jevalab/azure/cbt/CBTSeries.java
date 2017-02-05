package com.jevalab.azure.cbt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jevalab.helper.classes.Util;

public class CBTSeries extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2034206395987134850L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String vendor = req.getParameter("vendor");
		
		HttpSession session = req.getSession();
		
		if(Util.notNull(vendor)) {
			if(vendor.equalsIgnoreCase("utme")) {
				resp.sendRedirect(resp.encodeRedirectURL("/azure/cbt/i/utme/modules"));
			}
		}else {
			synchronized (session) {
				session.setAttribute("formError","You have to select and examination series.");
			}
			resp.sendRedirect(resp.encodeRedirectURL("/azure/p/cbt"));
			return;
		}
	}

}

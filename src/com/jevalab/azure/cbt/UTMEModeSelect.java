package com.jevalab.azure.cbt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jevalab.helper.classes.Util;

public class UTMEModeSelect extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6119959766575442754L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String mode = req.getParameter("mode");
		HttpSession session = req.getSession();

		if (Util.notNull(mode)) {
			switch (mode) {
			case "standard-utme":
				resp.sendRedirect(resp.encodeRedirectURL("/azure/cbt/utme/i/standard/default"));
				return;
			case "standard-subject":
				resp.sendRedirect(resp.encodeRedirectURL("/azure/cbt/utme/i/standard/subject"));
				return;
				
			case "custom-subject-1":
				resp.sendRedirect(resp.encodeRedirectURL("/azure/cbt/utme/i/custom/1"));
				return;
				
			case "custom-subject-2":
				resp.sendRedirect(resp.encodeRedirectURL("/azure/cbt/utme/i/custom/2"));
				return;
				

			}
		} else {
			synchronized (session) {
				session.setAttribute("formError",
						"You have to select an exam mode.");
			}
		}
	}

}

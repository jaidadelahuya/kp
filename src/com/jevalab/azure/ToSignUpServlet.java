package com.jevalab.azure;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jevalab.helper.classes.StringConstants;

public class ToSignUpServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -449120949345690069L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute(StringConstants.TO_SIGN_UP, true);
		resp.sendRedirect("/");
		
	}
}

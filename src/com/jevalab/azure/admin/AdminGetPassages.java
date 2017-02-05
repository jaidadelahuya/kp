package com.jevalab.azure.admin;

import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.jevalab.helper.classes.Util;

public class AdminGetPassages extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2168145414197389745L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String year = req.getParameter("year");
		String vendor = req.getParameter("vendor");
		
		resp.setContentType("application/json");
		if(Util.notNull(year,vendor)) {
			List<EnglishSnippet> l = Util.getPassageSnippet(year,vendor);
			
			
			
			String json = new Gson().toJson(l);
			
			resp.getWriter().write(json);
			
		}else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Year and Vendor should be selected");
		}
	}

}

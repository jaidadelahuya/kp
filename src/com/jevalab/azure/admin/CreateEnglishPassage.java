package com.jevalab.azure.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Text;
import com.jevalab.azure.cbt.EnglishPassage;
import com.jevalab.azure.persistence.GeneralController;
import com.jevalab.helper.classes.Util;

public class CreateEnglishPassage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3346469010961849766L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String vendor = req.getParameter("vendor");
		String year = req.getParameter("year");
		String topic = req.getParameter("topic");
		String passage = req.getParameter("passage");
		
		HttpSession session = req.getSession();
		if(Util.notNull(vendor,year,passage)) {
			EnglishPassage e = new EnglishPassage();
			e.setVendor(vendor);
			e.setYear(year);
			e.setPassage(new Text(passage));
			e.setTopic(topic);
			GeneralController.create(e.toEntity());
			synchronized (session) {
				session.setAttribute("formSuccess", "Passage saved successfully");
			}
			resp.sendRedirect(resp.encodeRedirectURL("/ca/admin/cbt/p1/english/passage/new"));
			
		}else {
			synchronized (session) {
				session.setAttribute("formError", "Fill out the form completely. Topic is optional");
			}
			resp.sendRedirect(resp.encodeRedirectURL("/ca/admin/cbt/p1/english/passage/new"));
		}
		
	}

}

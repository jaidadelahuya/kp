package com.jevalab.azure.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Entity;
import com.jevalab.azure.cbt.Subject;
import com.jevalab.azure.cbt.Topic;
import com.jevalab.azure.persistence.GeneralController;
import com.jevalab.helper.classes.Util;

public class SaveSubject extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4033399453358671472L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String sub = req.getParameter("subject");
		String top = req.getParameter("topics");
		
		
		HttpSession session = req.getSession();
		if(!Util.notNull(sub)) {
			synchronized (session) {
				session.setAttribute("formError","Select a subject");
				
			}
			resp.sendRedirect(resp.encodeRedirectURL("/ca/admin/cbt/p/subject/new"));
			return;
		}
		if(!Util.notNull(top)) {
			
			synchronized (session) {
				session.setAttribute("formError","Enter at least one topic");
				
			}
			resp.sendRedirect(resp.encodeRedirectURL("/ca/admin/cbt/p/subject/new"));
			return;
		}
		String[] topics = top.split(";");
		Subject s = new Subject(sub.toUpperCase());
		Entity[] ents = new Entity[topics.length+1];
		ents[0] = s.toEntity();
		for(int i=0; i < topics.length; i++ ) {
			Topic t = new Topic();
			t.setName(topics[i]);
			t.setSubject(s.getId());
			ents[i+1] = t.toEntity();
		}
		
		GeneralController.createWithCrossGroup(ents);
		
		synchronized (session) {
			session.setAttribute("formSuccess","Subject Saved Successfully");
		}
		
		resp.sendRedirect(resp.encodeRedirectURL("/ca/admin/cbt/p/subject/new"));
		return;
		
	}

}

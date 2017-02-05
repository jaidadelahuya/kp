/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.jevalab.azure.people;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jevalab.azure.persistence.AzureUser;
import com.jevalab.helper.classes.MainNav;
import com.jevalab.helper.classes.StringConstants;
import com.jevalab.helper.classes.Util;

public class InitPeople extends HttpServlet {
	private static final long serialVersionUID = -2838555889945911714L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		AzureUser u = null;
		Object o2 = null;
		Object o = null;
		Object o1 = null;
		synchronized (session) {
			o = session.getAttribute("mainNav");
			o2 = session.getAttribute(StringConstants.AZURE_USER);
			o1 = session.getAttribute("peoplePageBean");
		}
		if (o != null && o2 != null) {
			u = (AzureUser) o2;
			synchronized (session) {
				session.removeAttribute("peopleError");
				MainNav mn = (MainNav) o;
				mn.setPeople(Boolean.valueOf(true));
				session.setAttribute("mainNav", mn);
			}

			PeoplePageBean ppb = null;
			if(o1 == null) {
				ppb = new PeoplePageBean();
				ppb.setCategory("1");
				Map<String,Object> map = Util.getSuggestedPeople(ppb, u);
				ppb = (PeoplePageBean) map.get("peoplePageBean");
				synchronized (session) {
					session.setAttribute("peoplePageBean", ppb);
				}
			}
			
			
			req.getRequestDispatcher("/WEB-INF/people/index.jsp").include(req,
					resp);
		}

	}
}
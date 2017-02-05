package com.jevalab.azure.people;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.jevalab.azure.persistence.AzureUser;
import com.jevalab.helper.classes.StringConstants;
import com.jevalab.helper.classes.Util;

public class GetPeople extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 642986451647754377L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String category = req.getParameter("category");
		if (!Util.notNull(category)) {
			category = "1";
			//hello world
		}
		HttpSession session = req.getSession();
		AzureUser u = null;
		Object o2 = null;
		Object o1 = null;
		synchronized (session) {
			o2 = session.getAttribute(StringConstants.AZURE_USER);
			o1 = session.getAttribute("peoplePageBean");
		}
		if (o1 != null && o2 != null) {
			u = (AzureUser) o2;
			PeoplePageBean ppb = (PeoplePageBean) o1;
			ppb.setCategory(category);
			
			
			Map<String,Object> map = Util.getPeopleSet(ppb, u);
			ppb = (PeoplePageBean) map.get("peoplePageBean");
			List<Person> newSet = (List<Person>) map.get("newSet");
			
			
			synchronized (session) {
				session.setAttribute("peoplePageBean", ppb);
			}
			resp.setContentType("application/json");
			resp.getWriter().write(new Gson().toJson(newSet));
		}
	}

}

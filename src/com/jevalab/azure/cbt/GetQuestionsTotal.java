package com.jevalab.azure.cbt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultList;
import com.jevalab.helper.classes.Util;

public class GetQuestionsTotal extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6477169726836786875L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String[] topics = req.getParameterValues("topic");
		
		HttpSession session = req.getSession();
		Object o = null;
		CustomTest2 ct2 = null;
		
		synchronized (session) {
			o = session.getAttribute("ct2");
		}
		if(Util.notNull(topics) && o!=null) {
			ct2 = (CustomTest2) o;
			QueryResultList<Entity> ents = Util.getQuestionsTotal(topics, true);
			List<Entity> l = new ArrayList<>();
			l.addAll(ents);
			ct2.setqEnts(l);
			ct2.setNoQ(ents.size());
			
			synchronized (session) {
				session.setAttribute("ct2",ct2);
				resp.sendRedirect(resp.encodeRedirectURL("/azure/cbt/utme/p3/custom/2"));
				return;
			}
		}else {
			resp.sendRedirect("/azure/cbt/utme/p/custom/2");
		}
	}

}

package com.jevalab.azure.cbt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

public class GetCBT extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 438690410466880264L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String title = req.getParameter("title");
		String vendor = req.getParameter("vendor");
		String year = req.getParameter("year");
		String[] subjects = req.getParameterValues("subject");
		String time = req.getParameter("time");
		String noQ = req.getParameter("question");
		

		HttpSession session = req.getSession();
		resp.setContentType("application/json");
		if(Util.notNull(title) && title.contains("Standard") ) {
			if (!Util.notNull(year)) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"You have to select a year.");
				return;
			}
		}
		
		if (!Util.notNull(subjects)) {
			if(title.equalsIgnoreCase("Standard UTME Examination")) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"You have to select three subjects.");
				return;
			}else {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"You have to select a subject.");
				return;
			}
			
		}else {
			if(title.equalsIgnoreCase("Standard UTME Examination") && subjects.length != 3) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"You have to select three subjects.");
				return;
			}
		}

		Object o = null;

		synchronized (session) {
			o = session.getAttribute(StringConstants.AZURE_USER);
		}

		if (o != null) {
			CBT cbt = new CBT();
			AzureUser u = (AzureUser) o;
			cbt.setTitle(title);

			List<Test> tests = new  ArrayList<>();
			for (String s : subjects) {
				Test t = new Test();
				t.setSubject(s);
				t.setQuestions(new ArrayList<Question>());
				tests.add(t);
			}
			cbt.setTests(tests);

			cbt.setYear(year);

			cbt.setVendorName(vendor);
			
			cbt.setUsername(u.getFirstName()+" "+u.getLastName());
			
			

			if (vendor.equals("UTME")) {

				cbt.setVendorLogo("/images/utme-logo.png");
				if (title.equalsIgnoreCase("Standard UTME Subject Test")) {
					if(subjects[0].equalsIgnoreCase("english")) {
						cbt.setNoQ(100);
						cbt.setTime(90);
					}else {
						cbt.setNoQ(50);
						cbt.setTime(60);
					}
				} else if (title.equalsIgnoreCase("Standard UTME Examination")) {
					cbt.setTime(270);
					cbt.setNoQ(250);
					Test t = new Test();
					t.setSubject("English");
					t.setQuestions(new ArrayList<Question>());
					cbt.getTests().add(t);
				} else if (title.contains("Custom")) {
					if(!Util.notNull(time)) {
						cbt.setTime(60);
					}else {
						try {
						cbt.setTime(Integer.parseInt(time));
						
						}catch(NumberFormatException nfe) {
							resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Enter a valid time");
							return;
						}
					}
					
					if(!Util.notNull(noQ)) {
						resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Enter number of questions");
						return;
					}else {
						try {
						cbt.setNoQ(Integer.parseInt(noQ));
						
						}catch(NumberFormatException nfe) {
							resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Enter a valid number of questions");
							return;
						}
					}
				}
				
				CustomTest2 ct2 = null;
				synchronized (session) {
					ct2 = (CustomTest2) session.getAttribute("ct2");
				}
				
				cbt = Util.setCbtQuestions(cbt, ct2);
				synchronized (session) {
					session.setAttribute("cbt", cbt);
				}
				String json = new Gson().toJson(cbt);
				resp.getWriter().write(json);
			}
		}

	}

}

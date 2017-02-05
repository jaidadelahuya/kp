package com.jevalab.azure.admin;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jevalab.azure.persistence.AzureUser;
import com.jevalab.helper.classes.StringConstants;

public class AdminFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		HttpSession session = req.getSession();
		Object o = null;
		synchronized (session) {
			session.setMaxInactiveInterval(20*60);
			o = session.getAttribute(StringConstants.AZURE_USER);
		}
		
		if(o == null) {
			session.setAttribute("formError", "You dont have an active session. Please log in");
			resp.sendRedirect("/kp-admin");
		}else {
			AzureUser user = (AzureUser) o;
			if(user.getValidity().equalsIgnoreCase("admin")) {
				arg2.doFilter(arg0, arg1);
			}else {
				session.setAttribute("formError", "You are not a Kareer Plus admin");
				resp.sendRedirect("/kp-admin");
			}
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

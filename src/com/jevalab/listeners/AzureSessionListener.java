package com.jevalab.listeners;



import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


import com.jevalab.azure.persistence.AzureUser;
import com.jevalab.helper.classes.LoginHelper;
import com.jevalab.helper.classes.StringConstants;

public class AzureSessionListener implements HttpSessionListener {

	

	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		 
		HttpSession session = e.getSession();
		Object o = session.getAttribute(StringConstants.AZURE_USER);
		AzureUser user = null;
		if(o!=null) {
			user = (AzureUser) o;
			
			LoginHelper.persistUser(user, null);
		}	
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
		
	}

}

package com.jevalab.azure;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jevalab.helper.classes.RegistrationForm;
import com.jevalab.helper.classes.StringConstants;
import com.jevalab.helper.classes.Util;


public class ResendCodeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6404633083135806312L;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();
		if (!session.isNew()) {
			RegistrationForm rf = null;
			Object o = null;
			synchronized (session) {
				o = session.getAttribute(StringConstants.REGISTRATION_FORM);
			}
			if (o != null) {
				rf = (RegistrationForm) o;

				String code = Util.generateRandomCode(100000, 900000);
				rf.setConfirmationCode(code);

				resp.setContentType("text/html");

				if (rf.isUseMobile()) {
					
						Util.sendSMS(rf);
					
				} else if (rf.isUseEmail()) {
					String body = StringConstants.CONFIRMATION_EMAIL_BODY
							+ rf.getConfirmationCode();
					try {
						Util.sendEmail(rf.getUsername(),
								StringConstants.CONFIRMATION_EMAIL_SUBJECT,
								body);
					} catch (AddressException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						resp.sendError(
								HttpServletResponse.SC_EXPECTATION_FAILED,
								"We could not send an email your email address, try another email or use your mobile number.");
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						resp.sendError(
								HttpServletResponse.SC_EXPECTATION_FAILED,
								"We could not send an email your email address, try another email or use your mobile number.");
					}
				}

				synchronized (session) {
					session.setAttribute(StringConstants.REGISTRATION_FORM, rf);
				}

				resp.sendRedirect("/confirmation-code");
				// resp.getWriter().write("/confirmation-code");
			}

		}
	}
}

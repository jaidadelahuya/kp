package com.jevalab.helper.classes;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;
import com.jevalab.azure.persistence.AzureUser;
import com.jevalab.azure.persistence.UserJpaController;

public class LoginHelper {

	public synchronized static String getNextId() {

		long val = new Date().getTime();

		String randomString = Util.generateRandomCode(100000, 900000);
		return randomString + "@" + val;
	}

	public static void requestDispatcher(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, IOException {
		String uri = req.getRequestURL().toString();
		int a = uri.lastIndexOf('/');
		uri = uri.substring(a);
		System.out.println(uri);
		RequestDispatcher rd = req.getRequestDispatcher(uri);
		rd.forward(req, res);
	}

	public static WelcomePageBean initWelcomePageBean(AzureUser user) {
		WelcomePageBean wpb = new WelcomePageBean();
		wpb.setBackgroundImg(user.getCover());
		wpb.setFirstName(user.getFirstName());
		wpb.setLastName(user.getLastName());
		Map<String, Object> map = Util.getPreferredPosts(user, null);
		wpb.setCursor((String) map.get("cursor"));
		wpb.setPosts((List<DiscussionBean>) map.get("post"));
		wpb.setProfileImg(user.getPicture());
		wpb.setSchool(user.getSchool());
		wpb.setsClass(user.getsClass());
		if (user.getNewNotifications() != null) {
			wpb.setNewNotification(user.getNewNotifications().size());
		}
		if (user.getNewMessageNotifications() != null) {
			wpb.setNewMessageNotification(user.getNewMessageNotifications()
					.size());
		}
		return wpb;
	}

	public static Calendar getDateObject(String date) {
		if (date.contains("/")) {
			String[] vars = date.split("/");
			int mm = Integer.parseInt(vars[0]);
			mm = mm - 1;
			int dd = Integer.parseInt(vars[1]);
			int yy = Integer.parseInt(vars[2]);

			Calendar c = new GregorianCalendar(yy, mm, dd);

			return c;
		} else {
			return null;
		}

	}

	public static AzureUser persistUser(AzureUser user, PasswordRecovery pr) {

		Calendar millis = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

		Date d = millis.getTime();

		user.setLastSeenDate(d);

		UserJpaController cont = new UserJpaController();

		try {
			if (user.isNewUser()) {
				user.setNewUser(false);
				user = cont.create(user, pr);

			} else {

				cont.create(user, null);
			}
		} catch (Exception e) {
		}
		return user;
	}

	public static AzureUser setTakenTalentTest(AzureUser user) {
		String talents = ProfileHelper.getTalents(user.getUserID());
		if (talents == null || talents.isEmpty()) {
			user.setTakenTalentTest(false);
		} else {
			user.setTakenTalentTest(true);
		}
		return user;
	}

	public static Entity createEntityFromPasswordRecovery(PasswordRecovery pr) {
		Entity e = new Entity("PasswordRecovery", pr.getKey().getName());
		e.setUnindexedProperty("email", pr.isEmail());
		e.setUnindexedProperty("mobile", pr.isMobile());
		e.setUnindexedProperty("defaultRecovery", pr.isDefaultRecovery());
		e.setUnindexedProperty("verified", pr.isVerified());
		return e;
	}

}

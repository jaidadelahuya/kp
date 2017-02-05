package com.jevalab.azure.notifications;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3772408227818513103L;
	private String ownerKey;
	private String body;
	private Date date;
	private String webKey;
	@Override
	public String toString() {
		return "Message [ownerKey=" + ownerKey + ", body=" + body + ", date="
				+ date + "]";
	}
	
	
	public String getWebKey() {
		return webKey;
	}


	public void setWebKey(String webKey) {
		this.webKey = webKey;
	}


	public String getOwnerKey() {
		return ownerKey;
	}
	public void setOwnerKey(String ownerKey) {
		this.ownerKey = ownerKey;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getTruncatedDate () {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		Calendar c1 = new GregorianCalendar(c.get(Calendar.YEAR),
				c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		Date d = c1.getTime();
		return d;
	}

}

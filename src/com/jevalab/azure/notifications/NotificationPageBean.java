package com.jevalab.azure.notifications;

import java.io.Serializable;
import java.util.List;

public class NotificationPageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2264836648799304490L;
	
	private List<NotificationBean> notifications;
	private String cursor;
	@Override
	public String toString() {
		return "NotificationPageBean [notifications=" + notifications
				+ ", cursor=" + cursor + "]";
	}
	public List<NotificationBean> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<NotificationBean> notifications) {
		this.notifications = notifications;
	}
	public String getCursor() {
		return cursor;
	}
	public void setCursor(String cursor) {
		this.cursor = cursor;
	}
	
	

}

package com.jevalab.azure.notifications.messages;

import java.io.Serializable;
import java.util.List;

import com.jevalab.azure.notifications.MessageNotification;


public class MPageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7863844857232146511L;
	private List<MessageNotification> notifications;
	private String cursor;
	public List<MessageNotification> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<MessageNotification> notifications) {
		this.notifications = notifications;
	}
	public String getCursor() {
		return cursor;
	}
	public void setCursor(String cursor) {
		this.cursor = cursor;
	}
	
	
}

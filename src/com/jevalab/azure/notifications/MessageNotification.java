package com.jevalab.azure.notifications;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.KeyFactory;
import com.jevalab.azure.notifications.messages.MessageN;
import com.jevalab.azure.people.Person;
import com.jevalab.helper.classes.Util;

public class MessageNotification implements Serializable, Comparable<MessageNotification> {

	private static final long serialVersionUID = -6367829134895412276L;
	
	protected String type,display,id, notificationKey, messageId;
	protected Date date;
	protected Person sender;
	protected List<MessageN> notifications;
	
	@Override
	public int compareTo(MessageNotification o) {
		return date.compareTo(o.getDate());
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNotificationKey() {
		return notificationKey;
	}

	public void setNotificationKey(String notificationKey) {
		this.notificationKey = notificationKey;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Person getSender() {
		return sender;
	}

	public void setSender(Person sender) {
		this.sender = sender;
	}

	public List<MessageN> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<MessageN> notifications) {
		this.notifications = notifications;
	}

	public MessageNotification(MessageN firstNotification) {
		this.id = KeyFactory.keyToString(firstNotification.getSender());
		this.type = firstNotification.getType();
		this.date = firstNotification.getDate();
		this.sender = Util.getPersonFromIndex(firstNotification.getSender(),null);
		this.notifications = new ArrayList<>();
		this.notifications.add(firstNotification);
		this.notificationKey = KeyFactory.keyToString(firstNotification.getId());
		initDisplay();
	}
	
	


	public void initDisplay() {
		if (notifications == null) {
			display = "";
		} else if (notifications.size() == 1) {
			display = "";
			messageId = KeyFactory.keyToString(notifications.get(0).getId());
			display = notifications.get(0).getMessage().getValue().trim();
					
			display = display.replace("<div>", " ").replace("</div>", " ").replace("<p>"," ").replace("</p>", " ");
		} 

	}
	
	public void addNotification(MessageN n) {
		notifications.add(n);
		Collections.sort(notifications, new Comparator<MessageN>() {

			@Override
			public int compare(MessageN o1, MessageN o2) {
				return o2.getDate().compareTo(o1.getDate());
			}
		});
		date = notifications.get(0).getDate();
		initDisplay();
	}

}

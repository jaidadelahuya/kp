package com.jevalab.azure.notifications;

import java.io.Serializable;
import java.util.Date;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;
import com.jevalab.azure.persistence.GeneralController;

public class Notification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1229644486420640939L;
	private String type;
	private Text message;
	private boolean viewed;
	private Date date;
	private Key sender, recipient, id;
	private String webKey;

	@Override
	public String toString() {
		return "Notification [type=" + type + ", message=" + message
				+ ", viewed=" + viewed + ", date=" + date + ", sender="
				+ sender + ", recipient=" + recipient + ", id=" + id + "]";
	}
	
	

	public String getWebKey() {
		return webKey;
	}



	public void setWebKey(String webKey) {
		this.webKey = webKey;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Text getMessage() {
		return message;
	}

	public void setMessage(Text message) {
		this.message = message;
	}

	public boolean isViewed() {
		return viewed;
	}

	public void setViewed(boolean viewed) {
		this.viewed = viewed;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Key getSender() {
		return sender;
	}

	public void setSender(Key sender) {
		this.sender = sender;
	}

	public Key getRecipient() {
		return recipient;
	}

	public void setRecipient(Key recipient) {
		this.recipient = recipient;
	}

	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
		webKey = KeyFactory.keyToString(id);
	}

	public Notification() {
		id = GeneralController.ds.allocateIds(
				Notification.class.getSimpleName(), 1).getStart();
		webKey = KeyFactory.keyToString(id);
	}

	public Notification(Key id) {
		super();
		this.id = id;
		webKey = KeyFactory.keyToString(id);
	}
	
	

}

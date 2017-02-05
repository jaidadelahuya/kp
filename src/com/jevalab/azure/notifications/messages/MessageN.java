package com.jevalab.azure.notifications.messages;

import java.io.Serializable;
import java.util.Date;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;
import com.jevalab.azure.notifications.Notification;
import com.jevalab.azure.persistence.GeneralController;

public class MessageN implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -241125818136988280L;
	private String type;
	private Text message;
	private boolean viewed;
	private Date date;
	private Key sender, recipient, id;
	private String webKey;
	
	
	
	public String getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageN other = (MessageN) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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
	}

	public String getWebKey() {
		return webKey;
	}

	public void setWebKey(String webKey) {
		this.webKey = webKey;
	}

	@Override
	public String toString() {
		return "MessageN [type=" + type + ", message=" + message + ", viewed="
				+ viewed + ", date=" + date + ", sender=" + sender
				+ ", recipient=" + recipient + ", id=" + id + ", webKey="
				+ webKey + "]";
	}

	public MessageN(Key key) {
		this.id = key;
		webKey = KeyFactory.keyToString(key);
	}

	public MessageN() {
		id = GeneralController.ds.allocateIds(
				MessageN.class.getSimpleName(), 1).getStart();
		webKey = KeyFactory.keyToString(id);
	}
}

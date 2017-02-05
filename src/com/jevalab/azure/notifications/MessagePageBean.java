package com.jevalab.azure.notifications;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.jevalab.azure.people.Person;

public class MessagePageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5929731221058869869L;
	
	private Map<Date, List<Message>> messages;
	private int offset;
	private Person sender;
	private Date today;
	@Override
	public String toString() {
		return "MessagePageBean [messages=" + messages + ", offset=" + offset
				+ ", sender=" + sender + "]";
	}
	
	
	public MessagePageBean() {

		Calendar c = new GregorianCalendar();
		c = new GregorianCalendar(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE));
		today = c.getTime();
	}
	
	public boolean isToday (Date d) {
		if(d!=null && d.equals(today)) {
			return true;
		}else {
			return false;
		}
	}


	public Date getToday() {
		return today;
	}


	public void setToday(Date today) {
		this.today = today;
	}


	public Map<Date, List<Message>> getMessages() {
		return messages;
	}
	public void setMessages(Map<Date, List<Message>> messages) {
		this.messages = messages;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public Person getSender() {
		return sender;
	}
	public void setSender(Person sender) {
		this.sender = sender;
	}
	
	
	
	

}

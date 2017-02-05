package com.jevalab.azure.cbt;

import java.io.Serializable;
import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.QueryResultList;

public class CustomTest2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5031100458203862165L;
	
	
	private List<Topic> topics;
	private String subject;
	private List<Entity> qEnts;
	private int noQ;
	
	
	public int getNoQ() {
		return noQ;
	}
	public void setNoQ(int noQ) {
		this.noQ = noQ;
	}
	public List<Topic> getTopics() {
		return topics;
	}
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public List<Entity> getqEnts() {
		return qEnts;
	}
	public void setqEnts(List<Entity> qEnts) {
		this.qEnts = qEnts;
	}
	@Override
	public String toString() {
		return "CustomTest2 [topics=" + topics + ", subject=" + subject
				+ ", qEnts=" + qEnts + "]";
	}
	

	
	
		
	
	
	

}

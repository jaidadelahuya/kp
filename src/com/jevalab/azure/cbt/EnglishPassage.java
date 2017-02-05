package com.jevalab.azure.cbt;

import java.io.Serializable;
import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.jevalab.azure.persistence.GeneralController;

public class EnglishPassage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2648639823635617359L;
	
	private Key key;
	private String vendor,year,topic;
	private Text passage;
	private List<Key> questions;
	@Override
	public String toString() {
		return "EnglishPassage [key=" + key + ", vendor=" + vendor + ", year="
				+ year + ", topic=" + topic + ", passage=" + passage
				+ ", questions=" + questions + "]";
	}
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public Text getPassage() {
		return passage;
	}
	public void setPassage(Text passage) {
		this.passage = passage;
	}
	public List<Key> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Key> questions) {
		this.questions = questions;
	}
	public EnglishPassage( ) {
		this.key = GeneralController.ds.allocateIds(EnglishPassage.class.getSimpleName(),1).getStart();
	}
	
	@SuppressWarnings("unchecked")
	public EnglishPassage(Entity e) {
		this.key = e.getKey();
		this.passage = (Text) e.getProperty("passage");
		this.questions = (List<Key>) e.getProperty("questions");
		this.topic = (String) e.getProperty("topic");
		this.vendor = (String) e.getProperty("vendor");
		this.year = (String) e.getProperty("year");
	}
	
	public Entity toEntity() {
		Entity e = new Entity(key);
		e.setIndexedProperty("vendor", vendor);
		e.setIndexedProperty("year", year);
		e.setUnindexedProperty("topic", topic);
		e.setUnindexedProperty("passage", passage);
		e.setUnindexedProperty("questions", questions);
		return e;
	}

}

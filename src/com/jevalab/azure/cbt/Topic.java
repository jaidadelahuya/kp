package com.jevalab.azure.cbt;

import java.io.Serializable;
import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.jevalab.azure.persistence.GeneralController;

public class Topic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6603515507058293731L;
	
	private Key id, subject;
	private String name;
	private List<Key> tags;
	private List<Key> questions;
	private String webKey;
	
	
	public String getWebKey() {
		return webKey;
	}
	public void setWebKey(String webKey) {
		this.webKey = webKey;
	}
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
		webKey = KeyFactory.keyToString(id);
	}
	public Key getSubject() {
		return subject;
	}
	public void setSubject(Key subject) {
		this.subject = subject;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Key> getTags() {
		return tags;
	}
	public void setTags(List<Key> tags) {
		this.tags = tags;
	}
	public List<Key> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Key> questions) {
		this.questions = questions;
	}
	public Topic() {
		id= GeneralController.ds.allocateIds(Topic.class.getSimpleName(), 1).getStart();
		webKey = KeyFactory.keyToString(id);
	}
	
	public Topic(Entity e) {
		id = e.getKey();
		subject = (Key) e.getProperty("subject");
		name = (String) e.getProperty("name");
		tags = (List<Key>) e.getProperty("tags");
		questions = (List<Key>) e.getProperty("questions");
		webKey = KeyFactory.keyToString(id);
		
	}
	
	public Entity toEntity() {
		Entity e = new Entity(id);
		e.setIndexedProperty("subject", subject);
		e.setIndexedProperty("tags", tags);
		e.setUnindexedProperty("questions", questions);
		e.setUnindexedProperty("name", name);
		return e;
	}
	

}

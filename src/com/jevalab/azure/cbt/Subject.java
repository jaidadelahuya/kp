package com.jevalab.azure.cbt;

import java.io.Serializable;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class Subject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5207414069212916368L;
	
	private Key id;

	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

	public Subject(String name) {
		super();
		this.id = KeyFactory.createKey(Subject.class.getSimpleName(), name);
	}
	
	public String getName() {
		return id.getName();
	}
	
	public Entity toEntity() {
		Entity e = new Entity(id);
		return e;
	}
	
	public Subject (Entity e) {
		this(e.getKey().getName());
		
	}
	
	

}

package com.jevalab.azure.cbt;

import java.io.Serializable;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.jevalab.azure.persistence.GeneralController;

public class Tag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6603515507058293731L;

	private Key key;
	private String value;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Tag() {
		key = GeneralController.ds.allocateIds(Tag.class.getSimpleName(), 1)
				.getStart();
	}
	
	public Entity toEntity() {
		Entity e = new Entity(key);
		e.setIndexedProperty("value", this.value);
		return e;
	}
	
	public Tag(Entity e) {
		this.key = e.getKey();
		this.value = (String) e.getProperty("value");
	}

}

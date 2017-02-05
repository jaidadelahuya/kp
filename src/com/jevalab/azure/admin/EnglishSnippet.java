package com.jevalab.azure.admin;

import java.io.Serializable;

public class EnglishSnippet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5091672773236332743L;
	
	private String key, snippet;

	@Override
	public String toString() {
		return "EnglishSnippet [key=" + key + ", snippet=" + snippet + "]";
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSnippet() {
		return snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	
	

}

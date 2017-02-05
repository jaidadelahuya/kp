package com.jevalab.azure.cbt;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable, Comparable<Question> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4228495498192673667L;
	protected String webKey, body,image,userAns,extraInfo,category,passageKey,subject;
	protected List<String> alts;
	private int noTopics;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((webKey == null) ? 0 : webKey.hashCode());
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
		Question other = (Question) obj;
		if (webKey == null) {
			if (other.webKey != null)
				return false;
		} else if (!webKey.equals(other.webKey))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Question [webKey=" + webKey + ", body=" + body + ", image="
				+ image + ", userAns=" + userAns + ", extraInfo=" + extraInfo
				+ ", category=" + category + ", passageKey=" + passageKey
				+ ", subject=" + subject + ", alts=" + alts + "]";
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPassageKey() {
		return passageKey;
	}
	public void setPassageKey(String passageKey) {
		this.passageKey = passageKey;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getWebKey() {
		return webKey;
	}
	public void setWebKey(String webKey) {
		this.webKey = webKey;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUserAns() {
		return userAns;
	}
	public void setUserAns(String userAns) {
		this.userAns = userAns;
	}
	public String getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	public List<String> getAlts() {
		return alts;
	}
	public void setAlts(List<String> alts) {
		this.alts = alts;
	}
	@Override
	public int compareTo(Question o) {
		
		return this.body.compareToIgnoreCase(o.getBody());
	}
	public int getNoTopics() {
		return noTopics;
	}
	public void setNoTopics(int noTopics) {
		this.noTopics = noTopics;
	}
	
	

}

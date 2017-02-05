package com.jevalab.azure.cbt;

import java.io.Serializable;
import java.util.List;

public class Test implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3261239217075625399L;
	private String subject;
	private List<Question> questions;
	private List<JsonEnglishCategory> englishQuestions;
	
	
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "Test [subject=" + subject + ", questions=" + questions
				+ ", englishQuestions=" + englishQuestions + "]";
	}
	public List<JsonEnglishCategory> getEnglishQuestions() {
		return englishQuestions;
	}
	public void setEnglishQuestions(List<JsonEnglishCategory> englishQuestions) {
		this.englishQuestions = englishQuestions;
	}
	
	

}

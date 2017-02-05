package com.jevalab.azure.admin;

import java.io.Serializable;
import java.util.List;

import com.jevalab.azure.cbt.Question;
import com.jevalab.azure.cbt.Topic;

public class EditQuestionsPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7885086980718341103L;
	
	private String subject, year, vendor;
	List<Question> questions;
	List<com.jevalab.azure.persistence.Question> oQ;
	com.jevalab.azure.persistence.Question currentQuestion;
	List<Topic> topics;
	
	
	
	
	public List<Topic> getTopics() {
		return topics;
	}
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	public com.jevalab.azure.persistence.Question getCurrentQuestion() {
		return currentQuestion;
	}
	public void setCurrentQuestion(
			com.jevalab.azure.persistence.Question currentQuestion) {
		this.currentQuestion = currentQuestion;
	}
	public List<com.jevalab.azure.persistence.Question> getoQ() {
		return oQ;
	}
	public void setoQ(List<com.jevalab.azure.persistence.Question> oQ) {
		this.oQ = oQ;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	

}

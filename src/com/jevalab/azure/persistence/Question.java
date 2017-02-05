package com.jevalab.azure.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.datanucleus.api.jpa.annotations.Extension;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;



@Entity(name="question")
public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	public Question(com.google.appengine.api.datastore.Entity e) {
		id = e.getKey().getId();
		subjectName = (String) e.getProperty("subjectName");
		vendor = (String)e.getProperty("vendor");
		year = (String)e.getProperty("year");
		difficulty = (String)e.getProperty("difficulty");
		categoryName = (String)e.getProperty("categoryName");
		body = (String)e.getProperty("body");
		explanation = (String)e.getProperty("explanation");
		correctAlternative = (String)e.getProperty("correctAlternative");
		imageKey = (BlobKey)e.getProperty("imageKey");
		alternatives = (List<String>)e.getProperty("alternatives");
		topics = (List<Key>)e.getProperty("topics");
		passage = (Key)e.getProperty("passage");
	}
	
	

	public Question() {
		super();
	}



	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	private String subjectName,vendor,year,difficulty,categoryName;
	
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private String body,explanation,correctAlternative;
	private BlobKey imageKey;
	
	
	@Basic
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private List<String> alternatives;
	private List<Key> topics;
	
	@Transient
	private boolean isCorrect;
	
	
	Key passage;
	
	
	
	public Key getPassage() {
		return passage;
	}

	public void setPassage(Key passage) {
		this.passage = passage;
	}

	public String getSubjectName() {
		return subjectName;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public BlobKey getImageKey() {
		return imageKey;
	}
	public void setImageKey(BlobKey imageKey) {
		this.imageKey = imageKey;
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
	
	public List<String> getAlternatives() {
		return alternatives;
	}
	public void setAlternatives(List<String> alternatives) {
		this.alternatives = alternatives;
	}
	public List<Key> getTopics() {
		return topics;
	}
	public void setTopics(List<Key> topics) {
		this.topics = topics;
	}
	public String getCorrectAlternative() {
		return correctAlternative;
	}
	public void setCorrectAlternative(String correctAlternative) {
		this.correctAlternative = correctAlternative;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", subjectName=" + subjectName
				+ ", vendor=" + vendor + ", year=" + year + ", difficulty="
				+ difficulty + ", categoryName=" + categoryName + ", body="
				+ body + ", explanation=" + explanation
				+ ", correctAlternative=" + correctAlternative + ", imageKey="
				+ imageKey + ", alternatives=" + alternatives + ", topics="
				+ topics + ", isCorrect=" + isCorrect + ", passage=" + passage
				+ "]";
	}


	
	
	
}

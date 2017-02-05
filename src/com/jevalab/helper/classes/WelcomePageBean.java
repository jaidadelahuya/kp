package com.jevalab.helper.classes;

import java.io.Serializable;
import java.util.List;


public class WelcomePageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4377293919591287101L;
	
	private String firstName, lastName, profileImg, backgroundImg, school, sClass, cursor;
	private Integer offset,newNotification, newMessageNotification;
	private List<DiscussionBean> posts;
	@Override
	public String toString() {
		return "WelcomePageBean [firstName=" + firstName + ", lastName="
				+ lastName + ", profileImg=" + profileImg + ", backgroundImg="
				+ backgroundImg + ", school=" + school + ", sClass=" + sClass
				+ ", posts=" + posts + "]";
	}
	
	

	public String getCursor() {
		return cursor;
	}



	public void setCursor(String cursor) {
		this.cursor = cursor;
	}



	public Integer getNewMessageNotification() {
		return newMessageNotification;
	}



	public void setNewMessageNotification(Integer newMessageNotification) {
		this.newMessageNotification = newMessageNotification;
	}



	public Integer getNewNotification() {
		return newNotification;
	}

	public void setNewNotification(Integer newNotification) {
		this.newNotification = newNotification;
	}





	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}





	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getBackgroundImg() {
		return backgroundImg;
	}
	public void setBackgroundImg(String backgroundImg) {
		this.backgroundImg = backgroundImg;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getsClass() {
		return sClass;
	}
	public void setsClass(String sClass) {
		this.sClass = sClass;
	}
	public List<DiscussionBean> getPosts() {
		return posts;
	}
	public void setPosts(List<DiscussionBean> posts) {
		this.posts = posts;
	}
	public WelcomePageBean() {
		super();
	}
		

}

package com.jevalab.azure.people;

import java.io.Serializable;
import java.util.List;

public class PeoplePageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4850532635441322063L;
	
	private String category,suggestedCusor;
	private int followingMarker,friendsMarker;
	private List<Person> suggested,following,friends;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSuggestedCusor() {
		return suggestedCusor;
	}
	public void setSuggestedCusor(String suggestedCusor) {
		this.suggestedCusor = suggestedCusor;
	}
	public int getFollowingMarker() {
		return followingMarker;
	}
	public void setFollowingMarker(int followingMarker) {
		this.followingMarker = followingMarker;
	}
	public int getFriendsMarker() {
		return friendsMarker;
	}
	public void setFriendsMarker(int friendsMarker) {
		this.friendsMarker = friendsMarker;
	}
	public List<Person> getSuggested() {
		return suggested;
	}
	public void setSuggested(List<Person> suggested) {
		this.suggested = suggested;
	}
	public List<Person> getFollowing() {
		return following;
	}
	public void setFollowing(List<Person> following) {
		this.following = following;
	}
	public List<Person> getFriends() {
		return friends;
	}
	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}
	@Override
	public String toString() {
		return "PeoplePageBean [category=" + category + ", suggestedCusor="
				+ suggestedCusor + ", followingMarker=" + followingMarker
				+ ", friendsMarker=" + friendsMarker + ", suggested="
				+ suggested + ", following=" + following + ", friends="
				+ friends + "]";
	}
	
	

}

package com.jevalab.azure.notifications;

public class FriendRequestNotification extends NotificationBean {

	private static final long serialVersionUID = 7749233056626888430L;
	
	public FriendRequestNotification(Notification firstNotification) {
		super(firstNotification);
		initDisplay();
	}
	

	@Override
	public void initDisplay() {
		this.display = "wants to connect with you";
		
	}

}

package com.jevalab.azure.notifications;

public class FriendRequestAcceptedNotification extends NotificationBean {

	public FriendRequestAcceptedNotification(Notification firstNotification) {
		super(firstNotification);
		initDisplay();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5800624997465772195L;

	@Override
	public void initDisplay() {
		this.display="has accepted your friend request.";

	}

}

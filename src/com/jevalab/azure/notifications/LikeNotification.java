package com.jevalab.azure.notifications;

public class LikeNotification extends NotificationBean {

	
	public LikeNotification(Notification firstNotification) {
		super(firstNotification);
		initDisplay();
	}
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7375649464203565349L;

	@Override
	public void initDisplay() {
		this.display = "Likes your post";

	}

	
	
	

}

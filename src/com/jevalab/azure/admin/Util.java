package com.jevalab.azure.admin;

import com.google.appengine.api.datastore.Key;
import com.jevalab.azure.cbt.Topic;
import com.jevalab.azure.persistence.Question;

public class Util {

	public static Boolean containsTopic(Topic t, Question q) {
		Key k = t.getId();
		if(q.getTopics() != null && q.getTopics().contains(k)) {
			return true;
		}else {
			return false;
		}
		
	}
}

/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.jevalab.azure.group1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.jevalab.azure.persistence.AzureUser;
import com.jevalab.azure.persistence.Discussion;
import com.jevalab.azure.persistence.GeneralController;
import com.jevalab.helper.classes.DiscussionBean;
import com.jevalab.helper.classes.EntityConverter;
import com.jevalab.helper.classes.StringConstants;
import com.jevalab.helper.classes.Util;
import com.jevalab.helper.classes.WelcomePageBean;

public class SavePost extends HttpServlet {
	private static final long serialVersionUID = 8406644954146422900L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String p = req.getParameter("public");
		HttpSession session = req.getSession();
		Object o = null;
		Object o1 = null;
		AzureUser au = null;
		synchronized (session) {
			o = session.getAttribute("postImage");
			o1 = session.getAttribute("welcomePage");
			au = (AzureUser) session.getAttribute("azureUser");
		}
		String post = req.getParameter("post");
		BlobKey key = null;
		if (o != null) {
			key = (BlobKey) o;
		}
		if ((((Util.notNull(new String[] { post })) || (key != null)))
				&& (o1 != null)) {
			WelcomePageBean wpb = (WelcomePageBean) o1;
			List<DiscussionBean> dis = wpb.getPosts();
			Discussion d = new Discussion();
			if (Util.notNull(new String[] { post })) {
				d.setBody(new Text(post));
			}
			if(Util.notNull(p)) {
				d.set$private(false);
			}else {
				d.set$private(true);
			}
			d.setDateCreated(new Date());
			d.setFormat("1");
			d.setImage(key);
			d.setOwner(au.getKey());
			List<String> tags = new ArrayList<>();
			tags.add(au.getsClass());
			tags.addAll(Util.toInterestValues(au.getAreaOfInterest()));
			List<Key> keys = au.getFriendsId();
			Set<Key> ks = new HashSet<>();
			if(keys != null) {
				ks.addAll(keys);
			}
			keys = au.getFollowers();
			if(keys != null) {
				ks.addAll(keys);
			}
			keys = new ArrayList<>(ks);
			d.setSubscribers(keys);
			d.getSubscribers().add(au.getKey());
			d.setTags(tags);
			dis.add(0, Util.toDiscussionBean(d, au));
			wpb.setPosts(dis);
			if(!d.is$private()) {
				Util.addDiscussionToIndex(d);
			}
			synchronized (session) {
				session.setAttribute("welcomePage", wpb);
				session.setAttribute(StringConstants.AZURE_USER, au);
			}
			GeneralController.create(new Entity[] { EntityConverter
					.discussionToEntity(d) });
		}
	}
}
package com.jevalab.azure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.jevalab.azure.persistence.Question;
import com.jevalab.azure.persistence.QuestionJpaController;
import com.jevalab.exceptions.RollbackFailureException;
import com.jevalab.helper.classes.Util;

public class QuestionInputServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		BlobstoreService blobstoreService = BlobstoreServiceFactory
				.getBlobstoreService();
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		List<BlobKey> blobKeys = blobs.get("picture-url");
		BlobKey blobKey = null;
		if (blobKeys == null || blobKeys.isEmpty()) {
			// do nothing
		} else {
			blobKey = blobKeys.get(0);
		}

		HttpSession session = req.getSession();
		Question q = new Question();

		String subjectName = req.getParameter("subject-name");
		if (Util.notNull(subjectName)) {
			subjectName = subjectName.trim().toUpperCase();
			q.setSubjectName(subjectName);
		} else {
			session.setAttribute("addQuestionError", "Select a subject");
			resp.sendRedirect(resp.encodeRedirectURL("/ca/admin/question/new"));
			return;
		}

		String vendor = req.getParameter("vendor");
		if (Util.notNull(vendor)) {
			q.setVendor(vendor);
		} else {
			session.setAttribute("addQuestionError", "Select a Vendor");
			resp.sendRedirect(resp.encodeRedirectURL("/ca/admin/question/new"));
			return;
		}

		String year = req.getParameter("year");
		if (Util.notNull(year)) {
			q.setYear(year);
		} else {
			session.setAttribute("addQuestionError", "Select a year");
			resp.sendRedirect(resp.encodeRedirectURL("/ca/admin/question/new"));
			return;
		}

		String body = req.getParameter("body");
		if (Util.notNull(body)) {
			q.setBody(body);
		} else {
			session.setAttribute("addQuestionError", "Enter the question body");
			resp.sendRedirect(resp.encodeRedirectURL("/ca/admin/question/new"));
			return;
		}

		String altA = req.getParameter("alt-a");
		String altB = req.getParameter("alt-b");
		List<String> alts = new ArrayList<String>();
		if (Util.notNull(altA,altB)) {
			alts.add(altA);
			alts.add(altB);
		} else {
			session.setAttribute("addQuestionError", "Enter at least two alternatives");
			resp.sendRedirect(resp.encodeRedirectURL("/ca/admin/question/new"));
			return;
		}
		
		if (req.getParameter("alt-c") != null) {
			alts.add(req.getParameter("alt-c"));
		}

		if (req.getParameter("alt-d") != null) {
			alts.add(req.getParameter("alt-d"));
		}

		if (req.getParameter("alt-e") != null) {
			alts.add(req.getParameter("alt-e"));
		}

		q.setAlternatives(alts);
		
		q.setExplanation(req.getParameter("explanation"));

		String correctAlt = req.getParameter("correct-alt");
		
		if(Util.notNull(correctAlt)) {
			q.setCorrectAlternative(correctAlt);
		}else {
			session.setAttribute("addQuestionError", "Choose the correct alternative");
			resp.sendRedirect(resp.encodeRedirectURL("/ca/admin/question/new"));
			return;
		}
		
		if(blobKey!=null) {
			q.setImageKey(blobKey);
		}
		
		String pKey = req.getParameter("passage-key");
		
		if(Util.notNull(pKey) && subjectName.equalsIgnoreCase("english")) {
			q.setPassage(KeyFactory.stringToKey(pKey));
		}
		

		q.setCategoryName(req.getParameter("category-name"));
		
		String[] topics = req.getParameterValues("topics");
		List<Key> tKeys = new ArrayList<>();
		if(Util.notNull(topics)) {
			for(String s: topics) {
				tKeys.add(KeyFactory.stringToKey(s));
			}
			q.setTopics(tKeys);
		}

		QuestionJpaController cont = new QuestionJpaController();
		try {
			cont.create(q);
		} catch (RollbackFailureException e) {
			resp.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
			e.printStackTrace();
		}
		session.removeAttribute("addQuestionError");
		session.setAttribute("addQuestionSuccess", "Question saved successfully");
		resp.sendRedirect(resp.encodeRedirectURL("/ca/admin/question/new"));

	}
}

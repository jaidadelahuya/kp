package com.jevalab.azure.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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

public class UpdateQuestionServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7700170376779447520L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		BlobstoreService blobstoreService = BlobstoreServiceFactory
				.getBlobstoreService();
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		List<BlobKey> blobKeys = blobs.get("picture-url");
		BlobKey blobKey = null;
		HttpSession session = req.getSession();
		Object o = null;
		synchronized (session) {
			o = session.getAttribute("editQuestionsPage");
		}

		if (o != null) {
			EditQuestionsPage eqp = (EditQuestionsPage) o;
			Question q = eqp.getCurrentQuestion();
			com.jevalab.azure.cbt.Question qu = null;
			Iterator<com.jevalab.azure.cbt.Question> it = eqp.getQuestions().iterator();
			while(it.hasNext()) {
				if(q.getId().equals(KeyFactory.stringToKey(it.next().getWebKey()).getId())) {
					it.remove();
				}
			}
			if (blobKeys == null || blobKeys.isEmpty()) {
				// do nothing
			} else {
				blobKey = blobKeys.get(0);
				q.setImageKey(blobKey);
			}

			String subjectName = req.getParameter("subject-name");
			if (Util.notNull(subjectName)) {
				subjectName = subjectName.trim().toUpperCase();
				q.setSubjectName(subjectName);
			} else {
				session.setAttribute("formError", "Select a subject");
				resp.sendRedirect(resp
						.encodeRedirectURL("/ca/admin/cbt/p/question/update"));
				return;
			}

			String vendor = req.getParameter("vendor");
			if (Util.notNull(vendor)) {
				q.setVendor(vendor);
			} else {
				session.setAttribute("formError", "Select a Vendor");
				resp.sendRedirect(resp
						.encodeRedirectURL("/ca/admin/cbt/p/question/update"));
				return;
			}

			String year = req.getParameter("year");
			if (Util.notNull(year)) {
				q.setYear(year);
			} else {
				session.setAttribute("formError", "Select a year");
				resp.sendRedirect(resp
						.encodeRedirectURL("/ca/admin/cbt/p/question/update"));
				return;
			}

			String body = req.getParameter("body");
			if (Util.notNull(body)) {
				q.setBody(body);
			} else {
				session.setAttribute("formError",
						"Enter the question body");
				resp.sendRedirect(resp
						.encodeRedirectURL("/ca/admin/cbt/p/question/update"));
				return;
			}

			String altA = req.getParameter("alt-a");
			String altB = req.getParameter("alt-b");
			List<String> alts = new ArrayList<String>();
			if (Util.notNull(altA, altB)) {
				alts.add(altA);
				alts.add(altB);
			} else {
				session.setAttribute("formError",
						"Enter at least two alternatives");
				resp.sendRedirect(resp
						.encodeRedirectURL("/ca/admin/cbt/p/question/update"));
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

			if (Util.notNull(correctAlt)) {
				q.setCorrectAlternative(correctAlt);
			} else {
				session.setAttribute("formError",
						"Choose the correct alternative");
				resp.sendRedirect(resp
						.encodeRedirectURL("/ca/admin/cbt/p/question/update"));
				return;
			}

			

			String pKey = req.getParameter("passage-key");

			if (Util.notNull(pKey) && subjectName.equalsIgnoreCase("english")) {
				q.setPassage(KeyFactory.stringToKey(pKey));
			}

			q.setCategoryName(req.getParameter("category-name"));

			String[] topics = req.getParameterValues("topics");
			List<Key> tKeys = new ArrayList<>();
			if (Util.notNull(topics)) {
				for (String s : topics) {
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
			
			eqp.setCurrentQuestion(q);
			session.removeAttribute("formError");
			session.setAttribute("formSuccess",
					"Question updated successfully");
			resp.sendRedirect(resp.encodeRedirectURL("/ca/admin/cbt/p/question/update"));
		}

	}

}

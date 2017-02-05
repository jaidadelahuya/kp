package com.jevalab.azure.persistence;

//third commit
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;
import com.jevalab.azure.cbt.CBT;
import com.jevalab.azure.cbt.CustomTest2;
import com.jevalab.azure.cbt.EnglishPassage;
import com.jevalab.azure.cbt.Test;
import com.jevalab.azure.cbt.Topic;
import com.jevalab.azure.notifications.MessageNotification;
import com.jevalab.azure.notifications.Notification;
import com.jevalab.azure.notifications.messages.MessageN;
import com.jevalab.helper.classes.EntityConverter;

public class GeneralController {

	public static final DatastoreService ds = DatastoreServiceFactory
			.getDatastoreService();
	private static Transaction txn = null;

	public static List<Article> getLatestArticles(String categoryName, int i) {

		List<Article> articles = new ArrayList<>();
		Query q = new Query(Article.class.getSimpleName());
		q.setFilter(new FilterPredicate("category", FilterOperator.EQUAL,
				categoryName));
		q.addSort("date", SortDirection.DESCENDING);
		PreparedQuery pq = ds.prepare(q);
		List<Entity> ents = pq.asList(FetchOptions.Builder.withLimit(i));
		for (Entity e : ents) {
			articles.add(EntityConverter.entityToArticle(e));
		}
		return articles;
	}

	/*
	 * public static List<com.bestqualified.entities.Article>
	 * getNDiscussions(int i) { List<Article> articles = new ArrayList<>();
	 * Query q = new Query(Article.class.getSimpleName()); q.setFilter(new
	 * FilterPredicate("category", FilterOperator.EQUAL,
	 * ArticleCategory.DISCUSSION.name())); q.addSort("nComments",
	 * SortDirection.DESCENDING); PreparedQuery pq = ds.prepare(q); List<Entity>
	 * ents = pq.asList(FetchOptions.Builder.withLimit(i)); for (Entity e :
	 * ents) { articles.add(EntityConverter.entityToArticle(e)); } return
	 * articles; }
	 */

	public static List<Article> getNArticlesByDate(int no) {
		List<Article> articles = new ArrayList<>();
		Query q = new Query(Article.class.getSimpleName());
		q.addSort("date", SortDirection.DESCENDING);
		PreparedQuery pq = ds.prepare(q);
		List<Entity> ents = pq.asList(FetchOptions.Builder.withLimit(no));
		for (Entity e : ents) {
			articles.add(EntityConverter.entityToArticle(e));
		}
		return articles;
	}

	public static Iterator<Entity> findAll(String className) {
		Query q = new Query(className);
		PreparedQuery pq = ds.prepare(q);
		return pq.asIterator();
	}

	public static Entity findByKey(Key key) {
		try {
			return ds.get(key);
		} catch (EntityNotFoundException e) {

			return null;
		}
	}

	public static void delete(Key key) {
		txn = ds.beginTransaction();
		ds.delete(key);
		txn.commit();
	}

	public static void edit(Entity e) {
		txn = ds.beginTransaction();
		ds.put(e);
		txn.commit();
	}

	public static void create(Entity... e) {
		txn = ds.beginTransaction();
		List<Entity> entities = new ArrayList<>();
		for (Entity e1 : e) {
			entities.add(e1);
		}
		ds.put(entities);
		txn.commit();
	}

	public static void createWithCrossGroup(Entity... entities) {
		List<Entity> l = Arrays.asList(entities);
		txn = ds.beginTransaction(TransactionOptions.Builder.withXG(true));
		ds.put(l);
		txn.commitAsync();

	}

	public static Map<Key, Entity> findByKeys(List<Key> cKeys) {
		// TODO Auto-generated method stub
		return ds.get(cKeys);
	}

	public static Iterator<Entity> findAll(String simpleName, int i) {
		Query q = new Query(simpleName);
		PreparedQuery pq = ds.prepare(q);
		return pq.asIterator(FetchOptions.Builder.withLimit(i));
	}

	public static List<Community> findCACommunities() {
		Iterator<Entity> ents = findAll(Community.class.getSimpleName());
		List<Community> comms = new ArrayList<>();
		while (ents.hasNext()) {
			comms.add(EntityConverter.entityToCommunity(ents.next()));
		}
		return comms;
	}

	public static AzureUser findUserByLogin(String email, String password) {
		Query q = new Query(AzureUser.class.getSimpleName());
		Filter f = new FilterPredicate("EMail", FilterOperator.EQUAL, email
				.trim().toLowerCase());
		q.setFilter(f);
		PreparedQuery pq = ds.prepare(q);
		if (pq.countEntities(FetchOptions.Builder.withDefaults()) == 1) {
			AzureUser u = EntityConverter.entityToUser(pq.asSingleEntity());
			if (u.getPassword() != null && u.getPassword().equals(password)) {
				return u;
			} else {
				return null;
			}

		} else {
			return null;
		}
	}

	public static Map<String, Object> getPreferredPosts(AzureUser user,
			String cursor) {
		Query q = new Query(Discussion.class.getSimpleName());
		Filter f = new Query.FilterPredicate("subscribers",
				FilterOperator.EQUAL, user.getKey());
		q.setFilter(f);
		q.addSort("dateCreated", SortDirection.DESCENDING);
		PreparedQuery pq = ds.prepare(q);
		FetchOptions options = FetchOptions.Builder.withLimit(10);
		if (cursor != null) {
			options.startCursor(Cursor.fromWebSafeString(cursor));
		}
		List<Discussion> articles = new ArrayList<>();
		QueryResultList<Entity> rs = pq.asQueryResultList(options);
		Iterator<Entity> ents = rs.iterator();
		Cursor c = rs.getCursor();

		while (ents.hasNext()) {
			articles.add(EntityConverter.entityToDiscussion(ents.next()));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("post", articles);
		map.put("cursor", c.toWebSafeString());

		/*
		 * String clss = user.getsClass(); List<String> ints =
		 * user.getAreaOfInterest(); List<String> interest =
		 * Util.toInterestValues(ints); Filter f0 = new
		 * FilterPredicate("subscriber", FilterOperator.EQUAL,
		 * KeyFactory.createKey(AzureUser.class.getSimpleName(),
		 * user.getUserID())); Filter f4 = new FilterPredicate("owner",
		 * FilterOperator.EQUAL,
		 * KeyFactory.createKey(AzureUser.class.getSimpleName(),
		 * user.getUserID())); List<Filter> fs = new ArrayList<>(); fs.add(f0);
		 * fs.add(f4); for (String s : interest) { fs.add(new
		 * FilterPredicate("tags", FilterOperator.EQUAL, s)); } Filter f1 = new
		 * FilterPredicate("tags", FilterOperator.EQUAL, clss); Filter f2 = new
		 * Query.CompositeFilter(CompositeFilterOperator.OR, fs); List<Filter>
		 * fs1 = new ArrayList<>(); fs1.add(f1); fs1.add(f2); Filter f3 = new
		 * Query.CompositeFilter(CompositeFilterOperator.AND, fs1);
		 * q.setFilter(f3); q.addSort("dateCreated", SortDirection.DESCENDING);
		 * PreparedQuery pq = ds.prepare(q); FetchOptions options =
		 * FetchOptions.Builder.withLimit(10); options.offset(offset * 10);
		 * List<Discussion> articles = new ArrayList<>();
		 * QueryResultList<Entity> rs = pq.asQueryResultList(options);
		 * 
		 * Iterator<Entity> ents = rs.iterator(); if (rs.size() < 10) { offset =
		 * 0; } else { offset++; } while (ents.hasNext()) {
		 * articles.add(EntityConverter.entityToDiscussion(ents.next())); }
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("post", articles); map.put("offset", offset);
		 */
		return map;
	}

	public static QueryResultList<Entity> getComments(Key key, String cursor) {
		Query q = new Query(Discussion.class.getSimpleName());
		Cursor s = null;
		FetchOptions options = null;
		if (cursor != null) {
			s = Cursor.fromWebSafeString(cursor);
			options = FetchOptions.Builder.withStartCursor(s).limit(10);
		} else {
			options = FetchOptions.Builder.withLimit(10);
		}
		Query.Filter f = new Query.FilterPredicate("parent",
				Query.FilterOperator.EQUAL, key);
		q.setFilter(f);
		PreparedQuery pq = ds.prepare(q);
		QueryResultList<Entity> r = pq.asQueryResultList(options);

		return r;
	}

	public static QueryResultList<Entity> getNotifications(AzureUser u,
			String cursor) {
		Query q = new Query(Notification.class.getSimpleName());
		Query.Filter f = new Query.FilterPredicate("recipient",
				Query.FilterOperator.EQUAL, u.getKey());
		Query.Filter f1 = new Query.FilterPredicate("viewed",
				Query.FilterOperator.EQUAL, false);
		List<Filter> list = new ArrayList<>();
		list.add(f1);
		list.add(f);
		Query.Filter f2 = new Query.CompositeFilter(
				CompositeFilterOperator.AND, list);
		q.setFilter(f2);
		FetchOptions options = null;
		Cursor s = null;
		if (cursor != null) {
			s = Cursor.fromWebSafeString(cursor);
			options = FetchOptions.Builder.withStartCursor(s).limit(10);
		} else {
			options = FetchOptions.Builder.withLimit(10);
		}
		q.addSort("date", SortDirection.DESCENDING);
		PreparedQuery pq = ds.prepare(q);

		QueryResultList<Entity> r = pq.asQueryResultList(options);
		return r;

	}

	public static QueryResultList<Entity> getMessages(Key sender,
			Key recipient, int i) {
		Query q = new Query(MessageNotification.class.getSimpleName());
		Query.Filter f1 = new Query.FilterPredicate("recipient",
				Query.FilterOperator.EQUAL, recipient);
		Query.Filter f2 = new Query.FilterPredicate("sender",
				Query.FilterOperator.EQUAL, sender);
		List<Filter> first = new ArrayList<>();
		first.add(f1);
		first.add(f2);
		Query.Filter fx = new Query.CompositeFilter(
				CompositeFilterOperator.AND, first);
		Query.Filter f3 = new Query.FilterPredicate("recipient",
				Query.FilterOperator.EQUAL, sender);
		Query.Filter f4 = new Query.FilterPredicate("sender",
				Query.FilterOperator.EQUAL, recipient);
		List<Filter> second = new ArrayList<>();
		second.add(f3);
		second.add(f4);
		Query.Filter fy = new Query.CompositeFilter(
				CompositeFilterOperator.AND, second);
		List<Filter> third = new ArrayList<>();
		third.add(fx);
		third.add(fy);
		Query.Filter fz = new Query.CompositeFilter(CompositeFilterOperator.OR,
				third);
		Query.Filter f5 = new Query.FilterPredicate("type",
				Query.FilterOperator.EQUAL,
				MessageNotification.class.getSimpleName());
		List<Filter> fourth = new ArrayList<>();
		fourth.add(f5);
		fourth.add(fz);
		Query.Filter f = new Query.CompositeFilter(CompositeFilterOperator.AND,
				fourth);
		q.setFilter(f);
		q.addSort("date", SortDirection.DESCENDING);
		PreparedQuery pq = ds.prepare(q);
		FetchOptions options = FetchOptions.Builder.withOffset(i).limit(15);
		QueryResultList<Entity> r = pq.asQueryResultList(options);
		return r;
	}

	public static Map<Key, Entity> getEntities(List<Key> keys) {
		return ds.get(keys);
	}

	public static QueryResultList<Entity> getMessageNotifications(AzureUser u,
			String cursor) {
		Query q = new Query(MessageN.class.getSimpleName());
		Query.Filter f = new Query.FilterPredicate("recipient",
				Query.FilterOperator.EQUAL, u.getKey());
		Query.Filter f1 = new Query.FilterPredicate("type",
				Query.FilterOperator.EQUAL,
				MessageNotification.class.getSimpleName());
		List<Filter> list = new ArrayList<>();
		list.add(f1);
		list.add(f);
		Query.Filter f2 = new Query.CompositeFilter(
				CompositeFilterOperator.AND, list);
		q.setFilter(f2);
		FetchOptions options = null;
		Cursor s = null;
		if (cursor != null) {
			s = Cursor.fromWebSafeString(cursor);
			options = FetchOptions.Builder.withStartCursor(s).limit(10);
		} else {
			options = FetchOptions.Builder.withLimit(10);
		}
		q.addSort("date", SortDirection.DESCENDING);
		PreparedQuery pq = ds.prepare(q);

		QueryResultList<Entity> r = pq.asQueryResultList(options);
		return r;
	}

	public static List<Key> getSubscribers(List<Filter> filters) {
		Query q = new Query(AzureUser.class.getSimpleName());
		q.setKeysOnly();
		Query.Filter f2 = new Query.CompositeFilter(CompositeFilterOperator.OR,
				filters);
		q.setFilter(f2);

		List<Key> keys = new ArrayList<>();
		PreparedQuery pq = ds.prepare(q);
		List<Entity> list = null;
		int i = 0;
		do {
			list = pq.asList(FetchOptions.Builder.withLimit(1000).offset(i));
			for (Entity e : list) {
				keys.add(e.getKey());
			}
			i++;
		} while (list != null && list.size() == 1000);

		return keys;
	}

	public static QueryResultList<Entity> getStandardUTME(CBT cbt) {
		Query q = new Query(Question.class.getSimpleName());
		q.setKeysOnly();
		List<Filter> subjects = new ArrayList<>();
		for (Test t : cbt.getTests()) {
			String s = t.getSubject();
			subjects.add(new Query.FilterPredicate("subjectName",
					Query.FilterOperator.EQUAL, s.toUpperCase()));
		}

		Query.Filter f1 = new Query.CompositeFilter(CompositeFilterOperator.OR,
				subjects);
		Query.Filter f2 = new Query.FilterPredicate("year",
				Query.FilterOperator.EQUAL, cbt.getYear());
		Query.Filter f3 = new Query.FilterPredicate("vendor",
				Query.FilterOperator.EQUAL, cbt.getVendorName());
		List<Filter> filters = new ArrayList<>();
		filters.add(f1);
		filters.add(f2);
		filters.add(f3);

		Query.Filter f = new Query.CompositeFilter(CompositeFilterOperator.AND,
				filters);

		q.setFilter(f);

		PreparedQuery pq = ds.prepare(q);
		QueryResultList<Entity> qrl = pq.asQueryResultList(FetchOptions.Builder
				.withLimit(250));

		return qrl;

	}

	public static QueryResultList<Entity> getUTMESubject(CBT cbt) {
		Query q = new Query(Question.class.getSimpleName());
		q.setKeysOnly();
		Query.Filter f1 = null;
		FetchOptions options = null;
		for (Test t:cbt.getTests()) {
			String s = t.getSubject();
			f1 = new Query.FilterPredicate("subjectName",
					Query.FilterOperator.EQUAL, s.toUpperCase());
			if (s.equalsIgnoreCase("english")) {
				options = FetchOptions.Builder.withLimit(100);
			} else {
				options = FetchOptions.Builder.withLimit(50);
			}
		}
		Query.Filter f2 = new Query.FilterPredicate("year",
				Query.FilterOperator.EQUAL, cbt.getYear());
		Query.Filter f3 = new Query.FilterPredicate("vendor",
				Query.FilterOperator.EQUAL, cbt.getVendorName());
		List<Filter> filters = new ArrayList<>();
		filters.add(f1);
		filters.add(f2);
		filters.add(f3);

		Query.Filter f = new Query.CompositeFilter(CompositeFilterOperator.AND,
				filters);

		q.setFilter(f);

		PreparedQuery pq = ds.prepare(q);
		QueryResultList<Entity> qrl = pq.asQueryResultList(options);

		return qrl;

	}

	public static QueryResultList<Entity> getCustomUTME(CBT cbt) {
		Query q = new Query(Question.class.getSimpleName());
		q.setKeysOnly();
		Query.Filter f1 = null;

		for (Test t: cbt.getTests()) {
			String s = t.getSubject();
			f1 = new Query.FilterPredicate("subjectName",
					Query.FilterOperator.EQUAL, s.toUpperCase());

		}

		Query.Filter f3 = new Query.FilterPredicate("vendor",
				Query.FilterOperator.EQUAL, cbt.getVendorName());
		List<Filter> filters = new ArrayList<>();
		filters.add(f1);
		filters.add(f3);

		Query.Filter f = new Query.CompositeFilter(CompositeFilterOperator.AND,
				filters);

		q.setFilter(f);

		PreparedQuery pq = ds.prepare(q);
		QueryResultList<Entity> qrl = pq.asQueryResultList(FetchOptions.Builder
				.withLimit(1600));

		Collections.shuffle(qrl);

		List<Entity> list = new ArrayList<>(qrl);

		qrl.removeAll(list);

		for (int i = 0; i < cbt.getNoQ(); i++) {
			if (i < list.size()) {
				qrl.add(list.get(i));
			}

		}

		return qrl;
	}

	public static QueryResultList<Entity> getTopics(Key key) {
		Query q = new Query(Topic.class.getSimpleName());
		Query.Filter f3 = new Query.FilterPredicate("subject",
				Query.FilterOperator.EQUAL, key);
		q.setFilter(f3);
		PreparedQuery pq = ds.prepare(q);
		QueryResultList<Entity> qrl = pq.asQueryResultList(FetchOptions.Builder
				.withLimit(2000));

		return qrl;
	}

	public static QueryResultList<Entity> getQuestionsByTopics(String[] topics,
			boolean keysOnly) {

		QueryResultList<Entity> qrl = getQuestionsByTopics(topics, keysOnly,
				2000);
		return qrl;
	}

	public static QueryResultList<Entity> getQuestionsByTopics(String[] topics,
			boolean keysOnly, int noQ) {
		Query q = new Query(Question.class.getSimpleName());

		if (topics.length > 1) {
			List<Filter> list = new ArrayList<>();
			for (String s : topics) {
				list.add(new Query.FilterPredicate("topics",
						FilterOperator.EQUAL, KeyFactory.stringToKey(s)));
			}
			Query.Filter f = new Query.CompositeFilter(
					CompositeFilterOperator.OR, list);
			q.setFilter(f);
		} else {
			Query.Filter f = new Query.FilterPredicate("topics",
					FilterOperator.EQUAL, KeyFactory.stringToKey(topics[0]));
			q.setFilter(f);
		}

		if (keysOnly) {
			q.setKeysOnly();
		}
		PreparedQuery pq = ds.prepare(q);
		QueryResultList<Entity> qrl = pq.asQueryResultList(FetchOptions.Builder
				.withLimit(noQ));
		return qrl;
	}

	public static Map<Key, Entity> getCustomUTME(CBT cbt, CustomTest2 ct2) {
		List<Entity> l = ct2.getqEnts();
		Collections.shuffle(l);
		Set<Key> keys = new HashSet<>();
		for (Entity e : l) {
			if (keys.size() < cbt.getNoQ()) {
				keys.add(e.getKey());
			} else {
				break;
			}
		}
		List<Key> li = new ArrayList<>();
		li.addAll(keys);
		Map<Key, Entity> map = GeneralController.findByKeys(li);
		return map;
	}

	public static QueryResultList<Entity> getEnglishPassages(String year,
			String vendor) {
		Query q = new Query(EnglishPassage.class.getSimpleName());

		Query.Filter f1 = new Query.FilterPredicate("year",
				FilterOperator.EQUAL, year);
		Query.Filter f2 = new Query.FilterPredicate("vendor",
				FilterOperator.EQUAL, vendor);
		List<Filter> list = new ArrayList<>();
		list.add(f1);
		list.add(f2);
		Query.Filter f = new Query.CompositeFilter(CompositeFilterOperator.AND,
				list);
		q.setFilter(f);
		PreparedQuery pq = ds.prepare(q);
		QueryResultList<Entity> qrl = pq.asQueryResultList(FetchOptions.Builder
				.withDefaults());
		return qrl;

	}

	public static QueryResultList<Entity> getQuestions(String vendor,
			String year, String subject) {
		Query q = new Query(Question.class.getSimpleName());

		Query.Filter f1 = new Query.FilterPredicate("year",
				FilterOperator.EQUAL, year);
		Query.Filter f2 = new Query.FilterPredicate("vendor",
				FilterOperator.EQUAL, vendor);
		Query.Filter f3 = new Query.FilterPredicate("subjectName",
				FilterOperator.EQUAL, subject.toUpperCase());
		List<Filter> list = new ArrayList<>();
		list.add(f1);
		list.add(f2);
		list.add(f3);
		Query.Filter f = new Query.CompositeFilter(CompositeFilterOperator.AND,
				list);
		q.setFilter(f);
		PreparedQuery pq = ds.prepare(q);
		QueryResultList<Entity> qrl = pq.asQueryResultList(FetchOptions.Builder
				.withLimit(100));
		return qrl;
	}

	public static QueryResultList<Entity> getQuestions(String subject, String vendor) {
		Query q = new Query(Question.class.getSimpleName());
		Query.Filter f3 = new Query.FilterPredicate("subjectName",
				FilterOperator.EQUAL, subject.toUpperCase());
		Query.Filter f2 = new Query.FilterPredicate("vendor",
				FilterOperator.EQUAL, vendor);
		List<Filter> list = new ArrayList<>();
		list.add(f2);
		list.add(f3);
		
		Query.Filter f = new Query.CompositeFilter(CompositeFilterOperator.AND,
				list);
		q.setFilter(f);
		PreparedQuery pq = ds.prepare(q);
		QueryResultList<Entity> qrl = pq.asQueryResultList(FetchOptions.Builder
				.withLimit(5000));
		return qrl;
	
	}

}

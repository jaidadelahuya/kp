/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jevalab.azure.persistence;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;
import com.jevalab.exceptions.NonexistentEntityException;
import com.jevalab.exceptions.PreexistingEntityException;
import com.jevalab.exceptions.RollbackFailureException;
import com.jevalab.helper.classes.EntityConverter;
import com.jevalab.helper.classes.LoginHelper;
import com.jevalab.helper.classes.PasswordRecovery;

import com.jevalab.helper.classes.Util;

/**
 * 
 * @author JAIDA DE LAHUYA
 */
public class UserJpaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserJpaController() {

	}

	private static DatastoreService ds = EMF.getDs();
	private static EntityManagerFactory emf = EMF.get();
	private Transaction txn = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	/*
	 * public AzureUser create(AzureUser user, PasswordRecovery pr) throws
	 * PreexistingEntityException, RollbackFailureException, Exception {
	 * EntityManager em = null;
	 * 
	 * try { txn = ds.beginTransaction();
	 * 
	 * em = getEntityManager(); em.persist(user); if(pr!=null) { em.persist(pr);
	 * } txn.commit();
	 * 
	 * } catch (Exception ex) { try { txn.rollback(); } catch (Exception re) {
	 * throw new RollbackFailureException(
	 * "An error occurred attempting to roll back the transaction.", re); }
	 * 
	 * throw ex; } finally { if (em != null) { em.close(); } } return user; }
	 */

	public AzureUser create(AzureUser user, PasswordRecovery pr) {

		Entity e = EntityConverter.userToEntity(user);
		Entity e1 = null;
		Entity e2 = null;
		if (pr != null) {
			e1 = LoginHelper.createEntityFromPasswordRecovery(pr);
		}
		
		
		txn = ds.beginTransaction(TransactionOptions.Builder.withXG(true));
		ds.put(txn, e);
		if (e1 != null) {
			ds.put(txn, e1);
		}
		if(e2 != null) {
			ds.put(txn, e2);
		}
		txn.commit();
		return user;
		
	}
	
	

	

	public void destroy(String id) throws NonexistentEntityException,
			RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			txn = ds.beginTransaction();
			em = getEntityManager();
			AzureUser user;
			try {
				user = em.getReference(AzureUser.class, id);
				user.getUserID();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The user with key " + id
						+ " no longer exists.", enfe);
			}
			em.remove(user);
			txn.commit();
		} catch (Exception ex) {
			try {
				txn.rollback();
			} catch (Exception re) {
				throw new RollbackFailureException(
						"An error occurred attempting to roll back the transaction.",
						re);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	

	

	public AzureUser findUser(String id) {
		EntityManager em = getEntityManager();
		try {
			AzureUser user = em.find(AzureUser.class, id);
			
			if (user != null) {
				user.getFriendsId();
			}

			return user;
		} finally {
			em.close();
		}
	}

	

	public List<AzureUser> findUserBySchool(String sch, int fr, int mr) {

		EntityManager em = getEntityManager();
		Query q = em.createQuery("select a from user a");
		q.setFirstResult(fr);
		q.setMaxResults(mr);
		List<AzureUser> list = q.getResultList();
		for (AzureUser a : list) {
			a.getFriendsId();
		}

		return list;
	}

	public AzureUser findUserByUsername(String username, boolean keyOnly) {

		AzureUser user = null;

		com.google.appengine.api.datastore.Query q = new com.google.appengine.api.datastore.Query(
				"AzureUser");
		q.setFilter(new com.google.appengine.api.datastore.Query.FilterPredicate(
				"Username",
				com.google.appengine.api.datastore.Query.FilterOperator.EQUAL,
				username.toLowerCase()));
		if (keyOnly) {
			q.setKeysOnly();
			PreparedQuery pq = ds.prepare(q);
			Entity e = pq.asSingleEntity();
			if (e == null) {
				return user;
			} else {
				user = new AzureUser();
				return user;
			}
		} else {
			PreparedQuery pq = ds.prepare(q);
			Entity e = pq.asSingleEntity();
			if (e == null) {
				return user;
			} else {
				Map<String, Object> map = e.getProperties();
				user = EntityConverter.entityToUser(e);
			}
		}

		return user;
	}

	

}

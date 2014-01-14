package de.rwth.swc.teaching.forumsystem.simple.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.rwth.swc.teaching.forumsystem.simple.domain.Thread;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotFoundException;

@Stateless
public class ThreadDAOBean implements ThreadDAOLocal {

	private @PersistenceContext(name = "forumsystem")
	EntityManager em;

	/**
	 * Delete a thread
	 */
	public void deleteThread(Thread Thread) {
		em.remove(Thread);
	}

	/**
	 * Store a thread in a relational database
	 */
	public void storeThread(Thread Thread) {
		em.persist(Thread);
	}

	/**
	 * Update a thread that is already persisted
	 */
	public void updateThread(Thread Thread) {
	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Thread getThreadByThreadId(int threadId) throws NotFoundException {
		Query query = em.createNamedQuery("getThreadByThreadId");

		query.setParameter("threadId", threadId);

		try {
			return (Thread) query.getSingleResult();
		} catch (NoResultException e) {
			throw new NotFoundException("Thread", "ThreadId" + threadId);
		}
	}

	/**
	 * TODO Add a comment to this method.
	 */
	public boolean existsThread(int threadId) {

		try {
			if (this.getThreadByThreadId(threadId) != null) {
				return true;
			} else {
				return false;
			}
		} catch (NotFoundException e) {
			return false;
		}

	}

}

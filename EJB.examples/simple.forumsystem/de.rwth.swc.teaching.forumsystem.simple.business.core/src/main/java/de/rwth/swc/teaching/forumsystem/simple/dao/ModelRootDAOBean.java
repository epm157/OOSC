package de.rwth.swc.teaching.forumsystem.simple.dao;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.rwth.swc.teaching.forumsystem.simple.domain.Thread;

@Stateless
public class ModelRootDAOBean implements ModelRootDAOLocal {

	private @PersistenceContext(name = "forumsystem")
	EntityManager em;

	/**
	 * TODO Add a comment to this method.
	 */
	public Set<Thread> getAllThread() {

		Query query = em.createNamedQuery("getAllThread");

		@SuppressWarnings("unchecked")
		Set<Thread> result = new HashSet<Thread>(query.getResultList());
		return result;

	}

}

package de.rwth.swc.oosc.carcass.material.core.dao;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.rwt.swc.oosc.customer.domain.Customer;
import de.rwt.swc.oosc.customer.domain.Farmer;
import de.rwt.swc.oosc.customer.domain.ResearchLaboratory;
import de.rwth.swc.oosc.carcass.material.core.domain.Goat;
import de.rwth.swc.oosc.carcass.material.core.domain.Pig;

@Stateless
public class ModelRootDAOBean implements ModelRootDAOLocal {

	private @PersistenceContext(name = "carcass")
	EntityManager em;

	/**
	 * TODO Add a comment to this method.
	 */
	public Set<Customer> getAllCustomer() {

		Query query = em.createNamedQuery("getAllCustomer");

		@SuppressWarnings("unchecked")
		Set<Customer> result = new HashSet<Customer>(query.getResultList());
		return result;

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Set<Farmer> getAllFarmer() {

		Query query = em.createNamedQuery("getAllFarmer");

		@SuppressWarnings("unchecked")
		Set<Farmer> result = new HashSet<Farmer>(query.getResultList());
		return result;

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Set<ResearchLaboratory> getAllResearchLaboratory() {

		Query query = em.createNamedQuery("getAllResearchLaboratory");

		@SuppressWarnings("unchecked")
		Set<ResearchLaboratory> result = new HashSet<ResearchLaboratory>(
				query.getResultList());
		return result;

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Set<Pig> getAllPig() {

		Query query = em.createNamedQuery("getAllPig");

		@SuppressWarnings("unchecked")
		Set<Pig> result = new HashSet<Pig>(query.getResultList());
		return result;

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Set<Goat> getAllGoat() {

		Query query = em.createNamedQuery("getAllGoat");

		@SuppressWarnings("unchecked")
		Set<Goat> result = new HashSet<Goat>(query.getResultList());
		return result;

	}

}

package de.rwth.swc.oosc.carcass.crud.complete.dao;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.crud.complete.domain.Customer;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Farmer;
import de.rwth.swc.oosc.carcass.crud.complete.domain.ResearchLaboratory;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Pig;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Goat;

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

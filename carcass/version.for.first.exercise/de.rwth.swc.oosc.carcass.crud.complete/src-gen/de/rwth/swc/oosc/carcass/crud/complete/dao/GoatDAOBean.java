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

import de.rwth.swc.oosc.carcass.crud.complete.domain.Goat;

@Stateless
public class GoatDAOBean implements GoatDAOLocal {

	private @PersistenceContext(name = "carcass")
	EntityManager em;

	/**
	 * TODO Add a comment to this method.
	 */
	public void storeGoat(Goat Goat) {

		em.persist(Goat);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateGoat(Goat Goat) {

		em.merge(Goat);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteGoat(Goat Goat) {

		em.remove(Goat);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Goat getGoatByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException {

		Query query = em
				.createNamedQuery("getGoatByIdentificationAndPointIdentifierAndCustomerNumber");

		query.setParameter("identification", identification);

		query.setParameter("pointIdentifier", pointIdentifier);

		query.setParameter("customerNumber", customerNumber);

		try {
			return (Goat) query.getSingleResult();
		} catch (NoResultException e) {
			throw new NotFoundException("Goat", "Identification"
					+ identification + "PointIdentifier" + pointIdentifier
					+ "CustomerNumber" + customerNumber);
		}

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public boolean existsGoat(String identification, String pointIdentifier,
			String customerNumber) {

		try {
			if (this.getGoatByIdentificationAndPointIdentifierAndCustomerNumber(
					identification, pointIdentifier, customerNumber) != null) {
				return true;
			} else {
				return false;
			}
		} catch (NotFoundException e) {
			return false;
		}

	}

}

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

import de.rwth.swc.oosc.carcass.crud.complete.domain.Pig;

@Stateless
public class PigDAOBean implements PigDAOLocal {

	private @PersistenceContext(name = "carcass")
	EntityManager em;

	/**
	 * TODO Add a comment to this method.
	 */
	public void storePig(Pig Pig) {

		em.persist(Pig);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updatePig(Pig Pig) {

		em.merge(Pig);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deletePig(Pig Pig) {

		em.remove(Pig);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Pig getPigByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException {

		Query query = em
				.createNamedQuery("getPigByIdentificationAndPointIdentifierAndCustomerNumber");

		query.setParameter("identification", identification);

		query.setParameter("pointIdentifier", pointIdentifier);

		query.setParameter("customerNumber", customerNumber);

		try {
			return (Pig) query.getSingleResult();
		} catch (NoResultException e) {
			throw new NotFoundException("Pig", "Identification"
					+ identification + "PointIdentifier" + pointIdentifier
					+ "CustomerNumber" + customerNumber);
		}

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public boolean existsPig(String identification, String pointIdentifier,
			String customerNumber) {

		try {
			if (this.getPigByIdentificationAndPointIdentifierAndCustomerNumber(
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

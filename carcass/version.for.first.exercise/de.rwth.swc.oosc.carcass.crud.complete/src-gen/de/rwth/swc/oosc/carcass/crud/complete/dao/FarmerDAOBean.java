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

import de.rwth.swc.oosc.carcass.crud.complete.domain.Farmer;

@Stateless
public class FarmerDAOBean implements FarmerDAOLocal {

	private @PersistenceContext(name = "carcass")
	EntityManager em;

	/**
	 * TODO Add a comment to this method.
	 */
	public void storeFarmer(Farmer Farmer) {

		em.persist(Farmer);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateFarmer(Farmer Farmer) {

		em.merge(Farmer);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteFarmer(Farmer Farmer) {

		em.remove(Farmer);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Farmer getFarmerByCustomerNumber(String customerNumber)
			throws NotFoundException {

		Query query = em.createNamedQuery("getFarmerByCustomerNumber");

		query.setParameter("customerNumber", customerNumber);

		try {
			return (Farmer) query.getSingleResult();
		} catch (NoResultException e) {
			throw new NotFoundException("Farmer", "CustomerNumber"
					+ customerNumber);
		}

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public boolean existsFarmer(String customerNumber) {

		try {
			if (this.getFarmerByCustomerNumber(customerNumber) != null) {
				return true;
			} else {
				return false;
			}
		} catch (NotFoundException e) {
			return false;
		}

	}

}

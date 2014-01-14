package de.rwt.swc.oosc.customer.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.rwt.swc.oosc.customer.domain.Farmer;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;

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

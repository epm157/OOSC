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

@Stateless
public class CustomerDAOBean implements CustomerDAOLocal {

	private @PersistenceContext(name = "carcass")
	EntityManager em;

	/**
	 * TODO Add a comment to this method.
	 */
	public void storeCustomer(Customer Customer) {

		em.persist(Customer);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateCustomer(Customer Customer) {

		em.merge(Customer);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteCustomer(Customer Customer) {

		em.remove(Customer);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Customer getCustomerByCustomerNumber(String customerNumber)
			throws NotFoundException {

		Query query = em.createNamedQuery("getCustomerByCustomerNumber");

		query.setParameter("customerNumber", customerNumber);

		try {
			return (Customer) query.getSingleResult();
		} catch (NoResultException e) {
			throw new NotFoundException("Customer", "CustomerNumber"
					+ customerNumber);
		}

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public boolean existsCustomer(String customerNumber) {

		try {
			if (this.getCustomerByCustomerNumber(customerNumber) != null) {
				return true;
			} else {
				return false;
			}
		} catch (NotFoundException e) {
			return false;
		}

	}

}

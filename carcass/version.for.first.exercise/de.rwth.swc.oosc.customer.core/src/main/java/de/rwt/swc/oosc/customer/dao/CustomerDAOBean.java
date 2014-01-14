package de.rwt.swc.oosc.customer.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.rwt.swc.oosc.customer.domain.Customer;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;

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

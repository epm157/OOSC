package de.rwth.swc.oosc.carcass.core.dao;

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

import de.rwth.swc.oosc.carcass.core.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.core.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.core.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.core.domain.CustomerDTO;

@Stateless
public class CustomerDTODAOBean implements CustomerDTODAOLocal {

	private @PersistenceContext(name = "carcass")
	EntityManager em;

	/**
	 * TODO Add a comment to this method.
	 */
	public void storeCustomerDTO(CustomerDTO CustomerDTO) {

		em.persist(CustomerDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateCustomerDTO(CustomerDTO CustomerDTO) {

		em.merge(CustomerDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteCustomerDTO(CustomerDTO CustomerDTO) {

		em.remove(CustomerDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public CustomerDTO getCustomerDTOByCustomerNumber(String customerNumber)
			throws NotFoundException {

		Query query = em.createNamedQuery("getCustomerDTOByCustomerNumber");

		query.setParameter("customerNumber", customerNumber);

		try {
			return (CustomerDTO) query.getSingleResult();
		} catch (NoResultException e) {
			throw new NotFoundException("CustomerDTO", "CustomerNumber"
					+ customerNumber);
		}

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public boolean existsCustomerDTO(String customerNumber) {

		try {
			if (this.getCustomerDTOByCustomerNumber(customerNumber) != null) {
				return true;
			} else {
				return false;
			}
		} catch (NotFoundException e) {
			return false;
		}

	}

}

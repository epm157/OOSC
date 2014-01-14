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

import de.rwth.swc.oosc.carcass.crud.complete.domain.ResearchLaboratory;

@Stateless
public class ResearchLaboratoryDAOBean implements ResearchLaboratoryDAOLocal {

	private @PersistenceContext(name = "carcass")
	EntityManager em;

	/**
	 * TODO Add a comment to this method.
	 */
	public void storeResearchLaboratory(ResearchLaboratory ResearchLaboratory) {

		em.persist(ResearchLaboratory);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateResearchLaboratory(ResearchLaboratory ResearchLaboratory) {

		em.merge(ResearchLaboratory);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteResearchLaboratory(ResearchLaboratory ResearchLaboratory) {

		em.remove(ResearchLaboratory);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public ResearchLaboratory getResearchLaboratoryByCustomerNumber(
			String customerNumber) throws NotFoundException {

		Query query = em
				.createNamedQuery("getResearchLaboratoryByCustomerNumber");

		query.setParameter("customerNumber", customerNumber);

		try {
			return (ResearchLaboratory) query.getSingleResult();
		} catch (NoResultException e) {
			throw new NotFoundException("ResearchLaboratory", "CustomerNumber"
					+ customerNumber);
		}

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public boolean existsResearchLaboratory(String customerNumber) {

		try {
			if (this.getResearchLaboratoryByCustomerNumber(customerNumber) != null) {
				return true;
			} else {
				return false;
			}
		} catch (NotFoundException e) {
			return false;
		}

	}

}

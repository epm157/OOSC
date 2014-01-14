package de.rwt.swc.oosc.customer.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.rwt.swc.oosc.customer.domain.ResearchLaboratory;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;

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

package de.rwth.swc.oosc.carcass.material.core.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.material.core.domain.Material;

@Stateless
public class MaterialDAOBean implements MaterialDAOLocal {

	private @PersistenceContext(name = "carcass")
	EntityManager em;

	/**
	 * TODO Add a comment to this method.
	 */
	public void storeMaterial(Material Material) {

		em.persist(Material);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateMaterial(Material Material) {

		em.merge(Material);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteMaterial(Material Material) {

		em.remove(Material);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Material getMaterialByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException {

		Query query = em
				.createNamedQuery("getMaterialByIdentificationAndPointIdentifierAndCustomerNumber");

		query.setParameter("identification", identification);

		query.setParameter("pointIdentifier", pointIdentifier);

		query.setParameter("customerNumber", customerNumber);

		try {
			return (Material) query.getSingleResult();
		} catch (NoResultException e) {
			throw new NotFoundException("Material", "Identification"
					+ identification + "PointIdentifier" + pointIdentifier
					+ "CustomerNumber" + customerNumber);
		}

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public boolean existsMaterial(String identification,
			String pointIdentifier, String customerNumber) {

		try {
			if (this.getMaterialByIdentificationAndPointIdentifierAndCustomerNumber(
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

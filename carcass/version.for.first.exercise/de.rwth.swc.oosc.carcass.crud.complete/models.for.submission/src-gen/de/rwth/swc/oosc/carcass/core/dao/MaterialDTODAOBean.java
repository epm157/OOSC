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

import de.rwth.swc.oosc.carcass.core.domain.MaterialDTO;

@Stateless
public class MaterialDTODAOBean implements MaterialDTODAOLocal {

	private @PersistenceContext(name = "carcass")
	EntityManager em;

	/**
	 * TODO Add a comment to this method.
	 */
	public void storeMaterialDTO(MaterialDTO MaterialDTO) {

		em.persist(MaterialDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateMaterialDTO(MaterialDTO MaterialDTO) {

		em.merge(MaterialDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteMaterialDTO(MaterialDTO MaterialDTO) {

		em.remove(MaterialDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public MaterialDTO getMaterialDTOByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException {

		Query query = em
				.createNamedQuery("getMaterialDTOByIdentificationAndPointIdentifierAndCustomerNumber");

		query.setParameter("identification", identification);

		query.setParameter("pointIdentifier", pointIdentifier);

		query.setParameter("customerNumber", customerNumber);

		try {
			return (MaterialDTO) query.getSingleResult();
		} catch (NoResultException e) {
			throw new NotFoundException("MaterialDTO", "Identification"
					+ identification + "PointIdentifier" + pointIdentifier
					+ "CustomerNumber" + customerNumber);
		}

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public boolean existsMaterialDTO(String identification,
			String pointIdentifier, String customerNumber) {

		try {
			if (this.getMaterialDTOByIdentificationAndPointIdentifierAndCustomerNumber(
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

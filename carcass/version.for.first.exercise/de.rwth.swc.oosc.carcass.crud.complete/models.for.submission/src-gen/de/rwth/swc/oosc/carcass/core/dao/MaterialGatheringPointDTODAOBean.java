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

import de.rwth.swc.oosc.carcass.core.domain.MaterialGatheringPointDTO;

@Stateless
public class MaterialGatheringPointDTODAOBean
		implements
			MaterialGatheringPointDTODAOLocal {

	private @PersistenceContext(name = "carcass")
	EntityManager em;

	/**
	 * TODO Add a comment to this method.
	 */
	public void storeMaterialGatheringPointDTO(
			MaterialGatheringPointDTO MaterialGatheringPointDTO) {

		em.persist(MaterialGatheringPointDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateMaterialGatheringPointDTO(
			MaterialGatheringPointDTO MaterialGatheringPointDTO) {

		em.merge(MaterialGatheringPointDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteMaterialGatheringPointDTO(
			MaterialGatheringPointDTO MaterialGatheringPointDTO) {

		em.remove(MaterialGatheringPointDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public MaterialGatheringPointDTO getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber(
			String pointIdentifier, String customerNumber)
			throws NotFoundException {

		Query query = em
				.createNamedQuery("getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber");

		query.setParameter("pointIdentifier", pointIdentifier);

		query.setParameter("customerNumber", customerNumber);

		try {
			return (MaterialGatheringPointDTO) query.getSingleResult();
		} catch (NoResultException e) {
			throw new NotFoundException("MaterialGatheringPointDTO",
					"PointIdentifier" + pointIdentifier + "CustomerNumber"
							+ customerNumber);
		}

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public boolean existsMaterialGatheringPointDTO(String pointIdentifier,
			String customerNumber) {

		try {
			if (this.getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber(
					pointIdentifier, customerNumber) != null) {
				return true;
			} else {
				return false;
			}
		} catch (NotFoundException e) {
			return false;
		}

	}

}

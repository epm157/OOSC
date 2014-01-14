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

import de.rwth.swc.oosc.carcass.crud.complete.domain.MaterialGatheringPoint;

@Stateless
public class MaterialGatheringPointDAOBean
		implements
			MaterialGatheringPointDAOLocal {

	private @PersistenceContext(name = "carcass")
	EntityManager em;

	/**
	 * TODO Add a comment to this method.
	 */
	public void storeMaterialGatheringPoint(
			MaterialGatheringPoint MaterialGatheringPoint) {

		em.persist(MaterialGatheringPoint);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateMaterialGatheringPoint(
			MaterialGatheringPoint MaterialGatheringPoint) {

		em.merge(MaterialGatheringPoint);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteMaterialGatheringPoint(
			MaterialGatheringPoint MaterialGatheringPoint) {

		em.remove(MaterialGatheringPoint);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public MaterialGatheringPoint getMaterialGatheringPointByPointIdentifierAndCustomerNumber(
			String pointIdentifier, String customerNumber)
			throws NotFoundException {

		Query query = em
				.createNamedQuery("getMaterialGatheringPointByPointIdentifierAndCustomerNumber");

		query.setParameter("pointIdentifier", pointIdentifier);

		query.setParameter("customerNumber", customerNumber);

		try {
			return (MaterialGatheringPoint) query.getSingleResult();
		} catch (NoResultException e) {
			throw new NotFoundException("MaterialGatheringPoint",
					"PointIdentifier" + pointIdentifier + "CustomerNumber"
							+ customerNumber);
		}

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public boolean existsMaterialGatheringPoint(String pointIdentifier,
			String customerNumber) {

		try {
			if (this.getMaterialGatheringPointByPointIdentifierAndCustomerNumber(
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

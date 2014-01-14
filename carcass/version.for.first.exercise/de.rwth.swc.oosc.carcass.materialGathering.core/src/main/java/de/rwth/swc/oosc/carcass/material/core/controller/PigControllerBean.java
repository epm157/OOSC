package de.rwth.swc.oosc.carcass.material.core.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwth.swc.oosc.carcase.common.domain.DeadType;
import de.rwth.swc.oosc.carcass.common.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.common.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.common.exceptions.UnassignException;
import de.rwth.swc.oosc.carcass.material.core.dao.MaterialGatheringPointDAOLocal;
import de.rwth.swc.oosc.carcass.material.core.dao.PigDAOLocal;
import de.rwth.swc.oosc.carcass.material.core.domain.EntityFactoryLocal;
import de.rwth.swc.oosc.carcass.material.core.domain.MaterialGatheringPoint;
import de.rwth.swc.oosc.carcass.material.core.domain.Pig;
import de.rwth.swc.oosc.carcass.material.core.facade.CarcassMaterialGatheringFacadeLocal;

@Stateless
public class PigControllerBean implements PigControllerLocal {
	private @EJB
	PigDAOLocal pigDAO;
	private @EJB
	CarcassMaterialGatheringFacadeLocal carcassFacade;
	private @EJB
	MaterialGatheringPointDAOLocal materialGatheringPointDAO;

	private @EJB
	EntityFactoryLocal entityFactory;

	/**
	 * TODO Add a comment to this method.
	 */
	public Pig getPigByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException

	{

		// Get the entity out of the database

		return pigDAO
				.getPigByIdentificationAndPointIdentifierAndCustomerNumber(
						identification, pointIdentifier, customerNumber);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void createPig(String identification, String pointIdentifier,
			String customerNumber, double weight, DeadType deadType)
			throws AlreadyInDBException, NotNullableException, AssignException,
			NotFoundException

	{

		// Not null checks:
		if (identification == null || identification.trim().length() == 0) {
			throw new NotNullableException("identification");
		}
		if (pointIdentifier == null || pointIdentifier.trim().length() == 0) {
			throw new NotNullableException("pointIdentifier");
		}
		if (customerNumber == null || customerNumber.trim().length() == 0) {
			throw new NotNullableException("customerNumber");
		}

		// Check if this entity allready exists in the database
		if (pigDAO.existsPig(identification, pointIdentifier, customerNumber)) {
			throw new AlreadyInDBException("Pig");
		}

		// Create a new entity using the Entity Factory

		Pig pig = entityFactory.createPig(identification);

		// Set attributes

		pig.setWeight(weight);

		pig.setDeadType(deadType);

		// --------------------------------------------------------------------------
		// |     Begin Assigning Containment                                           |
		// --------------------------------------------------------------------------
		// Get the entity out of the database

		MaterialGatheringPoint materialGatheringPoint = carcassFacade
				.getMaterialGatheringPointByPointIdentifierAndCustomerNumber(
						pointIdentifier, customerNumber);

		// Set the inverse attribute if not already another one exists
		if (pig.getMaterialGatheringPoint() != null)
			throw new AssignException("gatheredMaterial");
		pig.setMaterialGatheringPoint(materialGatheringPoint);

		// Persist the entity into the database

		pigDAO.storePig(pig);

		// Add the attribute
		materialGatheringPoint.addMaterialToGatheredMaterial(pig);

		// Persist the entity back into the database

		materialGatheringPointDAO
				.updateMaterialGatheringPoint(materialGatheringPoint);

		// --------------------------------------------------------------------------
		// |     End Assigning Containment                                             |
		// --------------------------------------------------------------------------

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updatePig(String newIdentification, String oldIdentification,
			String pointIdentifier, String customerNumber, double weight,
			DeadType deadType) throws NotFoundException, AlreadyInDBException,
			NotNullableException

	{

		// Not null checks:
		if (newIdentification == null || newIdentification.trim().length() == 0) {
			throw new NotNullableException("newIdentification");
		}
		if (oldIdentification == null || oldIdentification.trim().length() == 0) {
			throw new NotNullableException("oldIdentification");
		}
		if (pointIdentifier == null || pointIdentifier.trim().length() == 0) {
			throw new NotNullableException("pointIdentifier");
		}
		if (customerNumber == null || customerNumber.trim().length() == 0) {
			throw new NotNullableException("customerNumber");
		}

		// Only check dupplication if old != new
		if (!(oldIdentification.equals(newIdentification))) {

			// Check if this entity allready exists in the database
			if (pigDAO.existsPig(newIdentification, pointIdentifier,
					customerNumber)) {
				throw new AlreadyInDBException("Pig");
			}

		}

		// Get the entity out of the database

		Pig pig = this
				.getPigByIdentificationAndPointIdentifierAndCustomerNumber(
						oldIdentification, pointIdentifier, customerNumber);

		// Set the attributes to the new values

		pig.setIdentification(newIdentification);

		pig.setWeight(weight);

		pig.setDeadType(deadType);

		// Persist the entity back into the database

		pigDAO.updatePig(pig);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deletePig(String identification, String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	{

		// Get the entit(y/ies) out of the database

		Pig pig = this
				.getPigByIdentificationAndPointIdentifierAndCustomerNumber(
						identification, pointIdentifier, customerNumber);

		MaterialGatheringPoint materialGatheringPoint = carcassFacade
				.getMaterialGatheringPointByPointIdentifierAndCustomerNumber(
						pointIdentifier, customerNumber);

		// --------------------------------------------------------------------------
		// |     Begin Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------

		if (pig.getMaterialGatheringPoint() != null) {
			carcassFacade
					.unassignMaterialGatheringPointFromMaterialMaterialGatheringPoint(
							identification, pointIdentifier, customerNumber);
		}

		// Remove the inverse attribute
		materialGatheringPoint.removeMaterialFromGatheredMaterial(pig);

		// Set the attribute null if not set to another attribute
		if (!pig.getMaterialGatheringPoint().equals(materialGatheringPoint))
			throw new UnassignException("materialGatheringPoint");
		pig.setMaterialGatheringPoint(null);

		// Persist the entity back into the database

		materialGatheringPointDAO
				.updateMaterialGatheringPoint(materialGatheringPoint);

		// --------------------------------------------------------------------------
		// |      End Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------			

		// Delete the entity
		pigDAO.deletePig(pig);

	}

}

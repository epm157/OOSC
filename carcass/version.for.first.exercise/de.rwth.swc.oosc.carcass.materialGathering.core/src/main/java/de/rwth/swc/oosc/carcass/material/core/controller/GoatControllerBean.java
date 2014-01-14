package de.rwth.swc.oosc.carcass.material.core.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwth.swc.oosc.carcase.common.domain.DeadType;
import de.rwth.swc.oosc.carcass.common.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.common.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.common.exceptions.UnassignException;
import de.rwth.swc.oosc.carcass.material.core.dao.GoatDAOLocal;
import de.rwth.swc.oosc.carcass.material.core.dao.MaterialGatheringPointDAOLocal;
import de.rwth.swc.oosc.carcass.material.core.domain.EntityFactoryLocal;
import de.rwth.swc.oosc.carcass.material.core.domain.Goat;
import de.rwth.swc.oosc.carcass.material.core.domain.MaterialGatheringPoint;
import de.rwth.swc.oosc.carcass.material.core.facade.CarcassMaterialGatheringFacadeLocal;

@Stateless
public class GoatControllerBean implements GoatControllerLocal {
	private @EJB
	GoatDAOLocal goatDAO;
	private @EJB
	CarcassMaterialGatheringFacadeLocal carcassFacade;
	private @EJB
	MaterialGatheringPointDAOLocal materialGatheringPointDAO;

	private @EJB
	EntityFactoryLocal entityFactory;

	/**
	 * TODO Add a comment to this method.
	 */
	public Goat getGoatByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException

	{

		// Get the entity out of the database

		return goatDAO
				.getGoatByIdentificationAndPointIdentifierAndCustomerNumber(
						identification, pointIdentifier, customerNumber);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void createGoat(String identification, String pointIdentifier,
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
		if (goatDAO.existsGoat(identification, pointIdentifier, customerNumber)) {
			throw new AlreadyInDBException("Goat");
		}

		// Create a new entity using the Entity Factory

		Goat goat = entityFactory.createGoat(identification);

		// Set attributes

		goat.setWeight(weight);

		goat.setDeadType(deadType);

		// --------------------------------------------------------------------------
		// |     Begin Assigning Containment                                           |
		// --------------------------------------------------------------------------
		// Get the entity out of the database

		MaterialGatheringPoint materialGatheringPoint = carcassFacade
				.getMaterialGatheringPointByPointIdentifierAndCustomerNumber(
						pointIdentifier, customerNumber);

		// Set the inverse attribute if not already another one exists
		if (goat.getMaterialGatheringPoint() != null)
			throw new AssignException("gatheredMaterial");
		goat.setMaterialGatheringPoint(materialGatheringPoint);

		// Persist the entity into the database

		goatDAO.storeGoat(goat);

		// Add the attribute
		materialGatheringPoint.addMaterialToGatheredMaterial(goat);

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
	public void updateGoat(String newIdentification, String oldIdentification,
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
			if (goatDAO.existsGoat(newIdentification, pointIdentifier,
					customerNumber)) {
				throw new AlreadyInDBException("Goat");
			}

		}

		// Get the entity out of the database

		Goat goat = this
				.getGoatByIdentificationAndPointIdentifierAndCustomerNumber(
						oldIdentification, pointIdentifier, customerNumber);

		// Set the attributes to the new values

		goat.setIdentification(newIdentification);

		goat.setWeight(weight);

		goat.setDeadType(deadType);

		// Persist the entity back into the database

		goatDAO.updateGoat(goat);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteGoat(String identification, String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	{

		// Get the entit(y/ies) out of the database

		Goat goat = this
				.getGoatByIdentificationAndPointIdentifierAndCustomerNumber(
						identification, pointIdentifier, customerNumber);

		MaterialGatheringPoint materialGatheringPoint = carcassFacade
				.getMaterialGatheringPointByPointIdentifierAndCustomerNumber(
						pointIdentifier, customerNumber);

		// --------------------------------------------------------------------------
		// |     Begin Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------

		if (goat.getMaterialGatheringPoint() != null) {
			carcassFacade
					.unassignMaterialGatheringPointFromMaterialMaterialGatheringPoint(
							identification, pointIdentifier, customerNumber);
		}

		// Remove the inverse attribute
		materialGatheringPoint.removeMaterialFromGatheredMaterial(goat);

		// Set the attribute null if not set to another attribute
		if (!goat.getMaterialGatheringPoint().equals(materialGatheringPoint))
			throw new UnassignException("materialGatheringPoint");
		goat.setMaterialGatheringPoint(null);

		// Persist the entity back into the database

		materialGatheringPointDAO
				.updateMaterialGatheringPoint(materialGatheringPoint);

		// --------------------------------------------------------------------------
		// |      End Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------			

		// Delete the entity
		goatDAO.deleteGoat(goat);

	}

}

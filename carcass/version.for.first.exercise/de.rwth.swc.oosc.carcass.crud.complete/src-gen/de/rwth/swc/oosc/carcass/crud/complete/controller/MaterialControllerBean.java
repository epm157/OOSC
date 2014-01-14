package de.rwth.swc.oosc.carcass.crud.complete.controller;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.crud.complete.domain.Material;
import de.rwth.swc.oosc.carcass.crud.complete.domain.MaterialGatheringPoint;

import de.rwth.swc.oosc.carcass.crud.complete.facade.CarcassFacadeLocal;
import de.rwth.swc.oosc.carcass.crud.complete.dao.MaterialDAOLocal;
import de.rwth.swc.oosc.carcass.crud.complete.dao.MaterialGatheringPointDAOLocal;

import de.rwth.swc.oosc.carcass.crud.complete.domain.EntityFactoryLocal;

@Stateless
public class MaterialControllerBean implements MaterialControllerLocal {
	private @EJB
	CarcassFacadeLocal carcassFacade;
	private @EJB
	MaterialDAOLocal materialDAO;
	private @EJB
	MaterialGatheringPointDAOLocal materialGatheringPointDAO;

	private @EJB
	EntityFactoryLocal entityFactory;

	/**
	 * TODO Add a comment to this method.
	 */
	public Material getMaterialByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException

	{

		// Get the entity out of the database

		return materialDAO
				.getMaterialByIdentificationAndPointIdentifierAndCustomerNumber(
						identification, pointIdentifier, customerNumber);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void createMaterial(String identification, String pointIdentifier,
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
		if (materialDAO.existsMaterial(identification, pointIdentifier,
				customerNumber)) {
			throw new AlreadyInDBException("Material");
		}

		// Create a new entity using the Entity Factory

		Material material = entityFactory.createMaterial(identification);

		// Set attributes

		material.setWeight(weight);

		material.setDeadType(deadType);

		// --------------------------------------------------------------------------
		// |     Begin Assigning Containment                                           |
		// --------------------------------------------------------------------------
		// Get the entity out of the database

		MaterialGatheringPoint materialGatheringPoint = carcassFacade
				.getMaterialGatheringPointByPointIdentifierAndCustomerNumber(
						pointIdentifier, customerNumber);

		// Set the inverse attribute if not already another one exists
		if (material.getMaterialGatheringPoint() != null)
			throw new AssignException("gatheredMaterial");
		material.setMaterialGatheringPoint(materialGatheringPoint);

		// Persist the entity into the database

		materialDAO.storeMaterial(material);

		// Add the attribute
		materialGatheringPoint.addMaterialToGatheredMaterial(material);

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
	public void updateMaterial(String newIdentification,
			String oldIdentification, String pointIdentifier,
			String customerNumber, double weight, DeadType deadType)
			throws NotFoundException, AlreadyInDBException,
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
			if (materialDAO.existsMaterial(newIdentification, pointIdentifier,
					customerNumber)) {
				throw new AlreadyInDBException("Material");
			}

		}

		// Get the entity out of the database

		Material material = this
				.getMaterialByIdentificationAndPointIdentifierAndCustomerNumber(
						oldIdentification, pointIdentifier, customerNumber);

		// Set the attributes to the new values

		material.setIdentification(newIdentification);

		material.setWeight(weight);

		material.setDeadType(deadType);

		// Persist the entity back into the database

		materialDAO.updateMaterial(material);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteMaterial(String identification, String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	{

		// Get the entit(y/ies) out of the database

		Material material = this
				.getMaterialByIdentificationAndPointIdentifierAndCustomerNumber(
						identification, pointIdentifier, customerNumber);

		MaterialGatheringPoint materialGatheringPoint = carcassFacade
				.getMaterialGatheringPointByPointIdentifierAndCustomerNumber(
						pointIdentifier, customerNumber);

		// --------------------------------------------------------------------------
		// |     Begin Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------

		// Remove the inverse attribute
		materialGatheringPoint.removeMaterialFromGatheredMaterial(material);

		// Set the attribute null if not set to another attribute
		if (!material.getMaterialGatheringPoint()
				.equals(materialGatheringPoint))
			throw new UnassignException("materialGatheringPoint");
		material.setMaterialGatheringPoint(null);

		// Persist the entity back into the database

		materialGatheringPointDAO
				.updateMaterialGatheringPoint(materialGatheringPoint);

		// --------------------------------------------------------------------------
		// |      End Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------			

		// Delete the entity
		materialDAO.deleteMaterial(material);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void unassignMaterialGatheringPointFromMaterialMaterialGatheringPoint(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException, NotNullableException, UnassignException

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

		//Delegate Unassign

		carcassFacade
				.unassignMaterialFromMaterialGatheringPointGatheredMaterial(
						pointIdentifier, customerNumber, identification);

	}

}

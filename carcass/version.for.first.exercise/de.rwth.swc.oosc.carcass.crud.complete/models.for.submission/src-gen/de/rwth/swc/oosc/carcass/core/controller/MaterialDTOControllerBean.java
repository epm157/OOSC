package de.rwth.swc.oosc.carcass.core.controller;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwth.swc.oosc.carcass.core.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.core.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.core.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.core.domain.MaterialDTO;
import de.rwth.swc.oosc.carcass.core.domain.MaterialGatheringPointDTO;

import de.rwth.swc.oosc.carcass.core.facade.CarcassFacadeLocal;
import de.rwth.swc.oosc.carcass.core.dao.MaterialGatheringPointDTODAOLocal;
import de.rwth.swc.oosc.carcass.core.dao.MaterialDTODAOLocal;

import de.rwth.swc.oosc.carcass.core.domain.EntityFactoryLocal;

@Stateless
public class MaterialDTOControllerBean implements MaterialDTOControllerLocal {
	private @EJB
	CarcassFacadeLocal carcassFacade;
	private @EJB
	MaterialGatheringPointDTODAOLocal materialGatheringPointDTODAO;
	private @EJB
	MaterialDTODAOLocal materialDTODAO;

	private @EJB
	EntityFactoryLocal entityFactory;

	/**
	 * TODO Add a comment to this method.
	 */
	public MaterialDTO getMaterialDTOByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException

	{

		// Get the entity out of the database

		return materialDTODAO
				.getMaterialDTOByIdentificationAndPointIdentifierAndCustomerNumber(
						identification, pointIdentifier, customerNumber);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void createMaterialDTO(String identification,
			String pointIdentifier, String customerNumber,
			MaterialType materialType, double weight, DeadType deadType,
			String cowEarNumber) throws AlreadyInDBException,
			NotNullableException, AssignException, NotFoundException

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
		if (materialDTODAO.existsMaterialDTO(identification, pointIdentifier,
				customerNumber)) {
			throw new AlreadyInDBException("MaterialDTO");
		}

		// Create a new entity using the Entity Factory

		MaterialDTO materialDTO = entityFactory
				.createMaterialDTO(identification);

		// Set attributes

		materialDTO.setMaterialType(materialType);

		materialDTO.setWeight(weight);

		materialDTO.setDeadType(deadType);

		materialDTO.setCowEarNumber(cowEarNumber);

		// --------------------------------------------------------------------------
		// |     Begin Assigning Containment                                           |
		// --------------------------------------------------------------------------
		// Get the entity out of the database

		MaterialGatheringPointDTO materialGatheringPointDTO = carcassFacade
				.getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber(
						pointIdentifier, customerNumber);

		// Set the inverse attribute if not already another one exists
		if (materialDTO.getMaterialGatheringPoint() != null)
			throw new AssignException("gatheredMaterial");
		materialDTO.setMaterialGatheringPoint(materialGatheringPointDTO);

		// Persist the entity into the database

		materialDTODAO.storeMaterialDTO(materialDTO);

		// Add the attribute
		materialGatheringPointDTO.addMaterialDTOToGatheredMaterial(materialDTO);

		// Persist the entity back into the database

		materialGatheringPointDTODAO
				.updateMaterialGatheringPointDTO(materialGatheringPointDTO);

		// --------------------------------------------------------------------------
		// |     End Assigning Containment                                             |
		// --------------------------------------------------------------------------

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateMaterialDTO(String newIdentification,
			String oldIdentification, String pointIdentifier,
			String customerNumber, MaterialType materialType, double weight,
			DeadType deadType, String cowEarNumber) throws NotFoundException,
			AlreadyInDBException, NotNullableException

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
			if (materialDTODAO.existsMaterialDTO(newIdentification,
					pointIdentifier, customerNumber)) {
				throw new AlreadyInDBException("MaterialDTO");
			}

		}

		// Get the entity out of the database

		MaterialDTO materialDTO = this
				.getMaterialDTOByIdentificationAndPointIdentifierAndCustomerNumber(
						oldIdentification, pointIdentifier, customerNumber);

		// Set the attributes to the new values

		materialDTO.setIdentification(newIdentification);

		materialDTO.setMaterialType(materialType);

		materialDTO.setWeight(weight);

		materialDTO.setDeadType(deadType);

		materialDTO.setCowEarNumber(cowEarNumber);

		// Persist the entity back into the database

		materialDTODAO.updateMaterialDTO(materialDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteMaterialDTO(String identification,
			String pointIdentifier, String customerNumber)
			throws NotFoundException, UnassignException, NotNullableException

	{

		// Get the entit(y/ies) out of the database

		MaterialDTO materialDTO = this
				.getMaterialDTOByIdentificationAndPointIdentifierAndCustomerNumber(
						identification, pointIdentifier, customerNumber);

		MaterialGatheringPointDTO materialGatheringPointDTO = carcassFacade
				.getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber(
						pointIdentifier, customerNumber);

		// --------------------------------------------------------------------------
		// |     Begin Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------

		// Remove the inverse attribute
		materialGatheringPointDTO
				.removeMaterialDTOFromGatheredMaterial(materialDTO);

		// Set the attribute null if not set to another attribute
		if (!materialDTO.getMaterialGatheringPoint().equals(
				materialGatheringPointDTO))
			throw new UnassignException("materialGatheringPoint");
		materialDTO.setMaterialGatheringPoint(null);

		// Persist the entity back into the database

		materialGatheringPointDTODAO
				.updateMaterialGatheringPointDTO(materialGatheringPointDTO);

		// --------------------------------------------------------------------------
		// |      End Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------			

		// Delete the entity
		materialDTODAO.deleteMaterialDTO(materialDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void unassignMaterialGatheringPointDTOFromMaterialDTOMaterialGatheringPoint(
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

		// Get the entities out of the database

		MaterialDTO materialDTO = carcassFacade
				.getMaterialDTOByIdentificationAndPointIdentifierAndCustomerNumber(
						identification, pointIdentifier, customerNumber);

		MaterialGatheringPointDTO materialGatheringPointDTO = carcassFacade
				.getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber(
						pointIdentifier, customerNumber);

		// Set the attribute null if not set to another attribute
		if (!materialDTO.getMaterialGatheringPoint().equals(
				materialGatheringPointDTO))
			throw new UnassignException("materialGatheringPoint");
		materialDTO.setMaterialGatheringPoint(null);

		// Remove the inverse attribute
		materialGatheringPointDTO
				.removeMaterialDTOFromGatheredMaterial(materialDTO);

		// Persist the entit(y/ies) back into the database

		materialGatheringPointDTODAO
				.updateMaterialGatheringPointDTO(materialGatheringPointDTO);

	}

}

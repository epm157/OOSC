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

import de.rwth.swc.oosc.carcass.core.domain.MaterialGatheringPointDTO;
import de.rwth.swc.oosc.carcass.core.domain.MaterialDTO;

import de.rwth.swc.oosc.carcass.core.facade.CarcassFacadeLocal;
import de.rwth.swc.oosc.carcass.core.dao.MaterialGatheringPointDTODAOLocal;

import de.rwth.swc.oosc.carcass.core.domain.EntityFactoryLocal;

@Stateless
public class MaterialGatheringPointDTOControllerBean
		implements
			MaterialGatheringPointDTOControllerLocal {
	private @EJB
	CarcassFacadeLocal carcassFacade;
	private @EJB
	MaterialGatheringPointDTODAOLocal materialGatheringPointDTODAO;

	private @EJB
	EntityFactoryLocal entityFactory;

	/**
	 * TODO Add a comment to this method.
	 */
	public MaterialGatheringPointDTO getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber(
			String pointIdentifier, String customerNumber)
			throws NotFoundException

	{

		// Get the entity out of the database

		return materialGatheringPointDTODAO
				.getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber(
						pointIdentifier, customerNumber);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void createMaterialGatheringPointDTO(String pointIdentifier,
			String customerNumber) throws AlreadyInDBException,
			NotNullableException

	{

		// Not null checks:
		if (pointIdentifier == null || pointIdentifier.trim().length() == 0) {
			throw new NotNullableException("pointIdentifier");
		}
		if (customerNumber == null || customerNumber.trim().length() == 0) {
			throw new NotNullableException("customerNumber");
		}

		// Check if this entity allready exists in the database
		if (materialGatheringPointDTODAO.existsMaterialGatheringPointDTO(
				pointIdentifier, customerNumber)) {
			throw new AlreadyInDBException("MaterialGatheringPointDTO");
		}

		// Create a new entity using the Entity Factory

		MaterialGatheringPointDTO materialGatheringPointDTO = entityFactory
				.createMaterialGatheringPointDTO(pointIdentifier,
						customerNumber);

		// Persist the entity into the database

		materialGatheringPointDTODAO
				.storeMaterialGatheringPointDTO(materialGatheringPointDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateMaterialGatheringPointDTO(String newPointIdentifier,
			String oldPointIdentifier, String newCustomerNumber,
			String oldCustomerNumber) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	{

		// Not null checks:
		if (newPointIdentifier == null
				|| newPointIdentifier.trim().length() == 0) {
			throw new NotNullableException("newPointIdentifier");
		}
		if (oldPointIdentifier == null
				|| oldPointIdentifier.trim().length() == 0) {
			throw new NotNullableException("oldPointIdentifier");
		}
		if (newCustomerNumber == null || newCustomerNumber.trim().length() == 0) {
			throw new NotNullableException("newCustomerNumber");
		}
		if (oldCustomerNumber == null || oldCustomerNumber.trim().length() == 0) {
			throw new NotNullableException("oldCustomerNumber");
		}

		// Only check dupplication if old != new
		if (!(oldPointIdentifier.equals(newPointIdentifier))
				|| !(oldCustomerNumber.equals(newCustomerNumber))) {

			// Check if this entity allready exists in the database
			if (materialGatheringPointDTODAO.existsMaterialGatheringPointDTO(
					newPointIdentifier, newCustomerNumber)) {
				throw new AlreadyInDBException("MaterialGatheringPointDTO");
			}

		}

		// Get the entity out of the database

		MaterialGatheringPointDTO materialGatheringPointDTO = this
				.getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber(
						oldPointIdentifier, oldCustomerNumber);

		// Set the attributes to the new values

		materialGatheringPointDTO.setPointIdentifier(newPointIdentifier);

		materialGatheringPointDTO.setCustomerNumber(newCustomerNumber);

		// Persist the entity back into the database

		materialGatheringPointDTODAO
				.updateMaterialGatheringPointDTO(materialGatheringPointDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteMaterialGatheringPointDTO(String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	{

		// Get the entit(y/ies) out of the database

		MaterialGatheringPointDTO materialGatheringPointDTO = this
				.getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber(
						pointIdentifier, customerNumber);

		// --------------------------------------------------------------------------
		// |     Begin Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------

		// Copy the child entities to a new set to avoid updade conflicts while deleting.			
		Set<MaterialDTO> _temp_GatheredMaterial = new HashSet<MaterialDTO>();
		_temp_GatheredMaterial.addAll(materialGatheringPointDTO
				.getGatheredMaterial());
		for (MaterialDTO gatheredMaterial : _temp_GatheredMaterial) {
			carcassFacade
					.unassignMaterialDTOFromMaterialGatheringPointDTOGatheredMaterial(
							pointIdentifier, customerNumber,
							gatheredMaterial.getIdentification());
			Logger.getLogger(this.getClass().getName()).log(
					Level.INFO,
					"Unassigned children MaterialDTO from MaterialGatheringPointDTO - Values: "
							+ pointIdentifier + ", " + customerNumber + ", "
							+ gatheredMaterial.getIdentification());
		}

		// --------------------------------------------------------------------------
		// |      End Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------			

		// Delete the entity
		materialGatheringPointDTODAO
				.deleteMaterialGatheringPointDTO(materialGatheringPointDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void unassignMaterialDTOFromMaterialGatheringPointDTOGatheredMaterial(
			String pointIdentifier, String customerNumber, String identification)
			throws NotFoundException, NotNullableException, UnassignException

	{

		// Not null checks:
		if (pointIdentifier == null || pointIdentifier.trim().length() == 0) {
			throw new NotNullableException("pointIdentifier");
		}
		if (customerNumber == null || customerNumber.trim().length() == 0) {
			throw new NotNullableException("customerNumber");
		}
		if (identification == null || identification.trim().length() == 0) {
			throw new NotNullableException("identification");
		}

		// Delete contained Entity	

		carcassFacade.deleteMaterialDTO(identification, pointIdentifier,
				customerNumber);

	}

}

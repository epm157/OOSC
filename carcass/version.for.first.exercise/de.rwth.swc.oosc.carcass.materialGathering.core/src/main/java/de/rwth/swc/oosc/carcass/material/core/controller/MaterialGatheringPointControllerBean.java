package de.rwth.swc.oosc.carcass.material.core.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwt.swc.oosc.customer.dao.CustomerDAOLocal;
import de.rwt.swc.oosc.customer.domain.Customer;
import de.rwth.swc.oosc.carcass.common.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.common.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.common.exceptions.UnassignException;
import de.rwth.swc.oosc.carcass.material.core.dao.MaterialGatheringPointDAOLocal;
import de.rwth.swc.oosc.carcass.material.core.domain.EntityFactoryLocal;
import de.rwth.swc.oosc.carcass.material.core.domain.Material;
import de.rwth.swc.oosc.carcass.material.core.domain.MaterialGatheringPoint;
import de.rwth.swc.oosc.carcass.material.core.facade.CarcassMaterialGatheringFacadeLocal;

@Stateless
public class MaterialGatheringPointControllerBean
		implements
			MaterialGatheringPointControllerLocal {
	private @EJB
	CarcassMaterialGatheringFacadeLocal carcassFacade;
	private @EJB
	MaterialGatheringPointDAOLocal materialGatheringPointDAO;
	private @EJB
	CustomerDAOLocal customerDAO;

	private @EJB
	EntityFactoryLocal entityFactory;

	/**
	 * TODO Add a comment to this method.
	 */
	public MaterialGatheringPoint getMaterialGatheringPointByPointIdentifierAndCustomerNumber(
			String pointIdentifier, String customerNumber)
			throws NotFoundException

	{

		// Get the entity out of the database

		return materialGatheringPointDAO
				.getMaterialGatheringPointByPointIdentifierAndCustomerNumber(
						pointIdentifier, customerNumber);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void createMaterialGatheringPoint(String pointIdentifier,
			String customerNumber) throws AlreadyInDBException,
			NotNullableException, AssignException, NotFoundException

	{

		// Not null checks:
		if (pointIdentifier == null || pointIdentifier.trim().length() == 0) {
			throw new NotNullableException("pointIdentifier");
		}
		if (customerNumber == null || customerNumber.trim().length() == 0) {
			throw new NotNullableException("customerNumber");
		}

		// Check if this entity allready exists in the database
		if (materialGatheringPointDAO.existsMaterialGatheringPoint(
				pointIdentifier, customerNumber)) {
			throw new AlreadyInDBException("MaterialGatheringPoint");
		}

		// Create a new entity using the Entity Factory

		MaterialGatheringPoint materialGatheringPoint = entityFactory
				.createMaterialGatheringPoint(pointIdentifier);

		// --------------------------------------------------------------------------
		// |     Begin Assigning Containment                                           |
		// --------------------------------------------------------------------------
		// Get the entity out of the database

		Customer customer = carcassFacade
				.getCustomerByCustomerNumber(customerNumber);

		// Set the inverse attribute if not already another one exists
		if (materialGatheringPoint.getCustomer() != null)
			throw new AssignException("materialGatheringPoints");
		materialGatheringPoint.setCustomer(customer);

		// Persist the entity into the database

		materialGatheringPointDAO
				.storeMaterialGatheringPoint(materialGatheringPoint);

		// Persist the entity back into the database

		customerDAO.updateCustomer(customer);

		// --------------------------------------------------------------------------
		// |     End Assigning Containment                                             |
		// --------------------------------------------------------------------------

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateMaterialGatheringPoint(String newPointIdentifier,
			String oldPointIdentifier, String customerNumber)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException

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
		if (customerNumber == null || customerNumber.trim().length() == 0) {
			throw new NotNullableException("customerNumber");
		}

		// Only check dupplication if old != new
		if (!(oldPointIdentifier.equals(newPointIdentifier))) {

			// Check if this entity allready exists in the database
			if (materialGatheringPointDAO.existsMaterialGatheringPoint(
					newPointIdentifier, customerNumber)) {
				throw new AlreadyInDBException("MaterialGatheringPoint");
			}

		}

		// Get the entity out of the database

		MaterialGatheringPoint materialGatheringPoint = this
				.getMaterialGatheringPointByPointIdentifierAndCustomerNumber(
						oldPointIdentifier, customerNumber);

		// Set the attributes to the new values

		materialGatheringPoint.setPointIdentifier(newPointIdentifier);

		// Persist the entity back into the database

		materialGatheringPointDAO
				.updateMaterialGatheringPoint(materialGatheringPoint);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteMaterialGatheringPoint(String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	{

		// Get the entit(y/ies) out of the database

		MaterialGatheringPoint materialGatheringPoint = this
				.getMaterialGatheringPointByPointIdentifierAndCustomerNumber(
						pointIdentifier, customerNumber);

		Customer customer = carcassFacade
				.getCustomerByCustomerNumber(customerNumber);

		// --------------------------------------------------------------------------
		// |     Begin Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------

		// Copy the child entities to a new set to avoid updade conflicts while deleting.			
		Set<Material> _temp_GatheredMaterial = new HashSet<Material>();
		_temp_GatheredMaterial.addAll(materialGatheringPoint
				.getGatheredMaterial());
		for (Material gatheredMaterial : _temp_GatheredMaterial) {
			carcassFacade
					.unassignMaterialFromMaterialGatheringPointGatheredMaterial(
							pointIdentifier, customerNumber,
							gatheredMaterial.getIdentification());
			Logger.getLogger(this.getClass().getName()).log(
					Level.INFO,
					"Unassigned children Material from MaterialGatheringPoint - Values: "
							+ pointIdentifier + ", " + customerNumber + ", "
							+ gatheredMaterial.getIdentification());
		}

		// Set the attribute null if not set to another attribute
		if (!materialGatheringPoint.getCustomer().equals(customer))
			throw new UnassignException("customer");
		materialGatheringPoint.setCustomer(null);

		// Persist the entity back into the database

		customerDAO.updateCustomer(customer);

		// --------------------------------------------------------------------------
		// |      End Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------			

		// Delete the entity
		materialGatheringPointDAO
				.deleteMaterialGatheringPoint(materialGatheringPoint);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void unassignMaterialFromMaterialGatheringPointGatheredMaterial(
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

		carcassFacade.deleteMaterial(identification, pointIdentifier,
				customerNumber);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void unassignCustomerFromMaterialGatheringPointCustomer(
			String pointIdentifier, String customerNumber)
			throws NotFoundException, NotNullableException, UnassignException

	{

		// Not null checks:
		if (pointIdentifier == null || pointIdentifier.trim().length() == 0) {
			throw new NotNullableException("pointIdentifier");
		}
		if (customerNumber == null || customerNumber.trim().length() == 0) {
			throw new NotNullableException("customerNumber");
		}

		//Delegate Unassign

		carcassFacade
				.unassignMaterialGatheringPointFromCustomerMaterialGatheringPoints(
						customerNumber, pointIdentifier);

	}

}

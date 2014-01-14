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

import de.rwth.swc.oosc.carcass.crud.complete.domain.Customer;
import de.rwth.swc.oosc.carcass.crud.complete.domain.MaterialGatheringPoint;

import de.rwth.swc.oosc.carcass.crud.complete.facade.CarcassFacadeLocal;
import de.rwth.swc.oosc.carcass.crud.complete.dao.CustomerDAOLocal;

import de.rwth.swc.oosc.carcass.crud.complete.domain.EntityFactoryLocal;

@Stateless
public class CustomerControllerBean implements CustomerControllerLocal {
	private @EJB
	CarcassFacadeLocal carcassFacade;
	private @EJB
	CustomerDAOLocal customerDAO;

	private @EJB
	EntityFactoryLocal entityFactory;

	/**
	 * TODO Add a comment to this method.
	 */
	public Customer getCustomerByCustomerNumber(String customerNumber)
			throws NotFoundException

	{

		// Get the entity out of the database

		return customerDAO.getCustomerByCustomerNumber(customerNumber);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void createCustomer(String customerNumber, String customerName,
			String zip, double lat, double lng) throws AlreadyInDBException,
			NotNullableException

	{

		// Not null checks:
		if (customerNumber == null || customerNumber.trim().length() == 0) {
			throw new NotNullableException("customerNumber");
		}

		// Check if this entity allready exists in the database
		if (customerDAO.existsCustomer(customerNumber)) {
			throw new AlreadyInDBException("Customer");
		}

		// Create a new entity using the Entity Factory

		Customer customer = entityFactory.createCustomer(customerNumber);

		// Set attributes

		customer.setCustomerName(customerName);

		customer.setZip(zip);

		customer.setLat(lat);

		customer.setLng(lng);

		// Persist the entity into the database

		customerDAO.storeCustomer(customer);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateCustomer(String newCustomerNumber,
			String oldCustomerNumber, String customerName, String zip,
			double lat, double lng) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	{

		// Not null checks:
		if (newCustomerNumber == null || newCustomerNumber.trim().length() == 0) {
			throw new NotNullableException("newCustomerNumber");
		}
		if (oldCustomerNumber == null || oldCustomerNumber.trim().length() == 0) {
			throw new NotNullableException("oldCustomerNumber");
		}

		// Only check dupplication if old != new
		if (!(oldCustomerNumber.equals(newCustomerNumber))) {

			// Check if this entity allready exists in the database
			if (customerDAO.existsCustomer(newCustomerNumber)) {
				throw new AlreadyInDBException("Customer");
			}

		}

		// Get the entity out of the database

		Customer customer = this.getCustomerByCustomerNumber(oldCustomerNumber);

		// Set the attributes to the new values

		customer.setCustomerNumber(newCustomerNumber);

		customer.setCustomerName(customerName);

		customer.setZip(zip);

		customer.setLat(lat);

		customer.setLng(lng);

		// Persist the entity back into the database

		customerDAO.updateCustomer(customer);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteCustomer(String customerNumber) throws NotFoundException,
			UnassignException, NotNullableException

	{

		// Get the entit(y/ies) out of the database

		Customer customer = this.getCustomerByCustomerNumber(customerNumber);

		// --------------------------------------------------------------------------
		// |     Begin Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------

		// Copy the child entities to a new set to avoid updade conflicts while deleting.			
		Set<MaterialGatheringPoint> _temp_MaterialGatheringPoints = new HashSet<MaterialGatheringPoint>();
		_temp_MaterialGatheringPoints.addAll(customer
				.getMaterialGatheringPoints());
		for (MaterialGatheringPoint materialGatheringPoints : _temp_MaterialGatheringPoints) {
			carcassFacade
					.unassignMaterialGatheringPointFromCustomerMaterialGatheringPoints(
							customerNumber,
							materialGatheringPoints.getPointIdentifier());
			Logger.getLogger(this.getClass().getName()).log(
					Level.INFO,
					"Unassigned children MaterialGatheringPoint from Customer - Values: "
							+ customerNumber + ", "
							+ materialGatheringPoints.getPointIdentifier());
		}

		// --------------------------------------------------------------------------
		// |      End Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------			

		// Delete the entity
		customerDAO.deleteCustomer(customer);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void unassignMaterialGatheringPointFromCustomerMaterialGatheringPoints(
			String customerNumber, String pointIdentifier)
			throws NotFoundException, NotNullableException, UnassignException

	{

		// Not null checks:
		if (customerNumber == null || customerNumber.trim().length() == 0) {
			throw new NotNullableException("customerNumber");
		}
		if (pointIdentifier == null || pointIdentifier.trim().length() == 0) {
			throw new NotNullableException("pointIdentifier");
		}

		// Delete contained Entity	

		carcassFacade.deleteMaterialGatheringPoint(pointIdentifier,
				customerNumber);

	}

}

package de.rwt.swc.oosc.customer.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwt.swc.oosc.customer.dao.FarmerDAOLocal;
import de.rwt.swc.oosc.customer.domain.EntityFactoryLocal;
import de.rwt.swc.oosc.customer.domain.Farmer;
import de.rwth.swc.oosc.carcass.common.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.common.exceptions.UnassignException;

@Stateless
public class FarmerControllerBean implements FarmerControllerLocal {
	private @EJB
	FarmerDAOLocal farmerDAO;

	private @EJB
	EntityFactoryLocal entityFactory;

	/**
	 * TODO Add a comment to this method.
	 */
	public Farmer getFarmerByCustomerNumber(String customerNumber)
			throws NotFoundException

	{

		// Get the entity out of the database

		return farmerDAO.getFarmerByCustomerNumber(customerNumber);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void createFarmer(String customerNumber, String customerName,
			String zip, double lat, double lng) throws AlreadyInDBException,
			NotNullableException

	{

		// Not null checks:
		if (customerNumber == null || customerNumber.trim().length() == 0) {
			throw new NotNullableException("customerNumber");
		}

		// Check if this entity allready exists in the database
		if (farmerDAO.existsFarmer(customerNumber)) {
			throw new AlreadyInDBException("Farmer");
		}

		// Create a new entity using the Entity Factory

		Farmer farmer = entityFactory.createFarmer(customerNumber);

		// Set attributes

		farmer.setCustomerName(customerName);

		farmer.setZip(zip);

		farmer.setLat(lat);

		farmer.setLng(lng);

		// Persist the entity into the database

		farmerDAO.storeFarmer(farmer);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateFarmer(String newCustomerNumber,
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
			if (farmerDAO.existsFarmer(newCustomerNumber)) {
				throw new AlreadyInDBException("Farmer");
			}

		}

		// Get the entity out of the database

		Farmer farmer = this.getFarmerByCustomerNumber(oldCustomerNumber);

		// Set the attributes to the new values

		farmer.setCustomerNumber(newCustomerNumber);

		farmer.setCustomerName(customerName);

		farmer.setZip(zip);

		farmer.setLat(lat);

		farmer.setLng(lng);

		// Persist the entity back into the database

		farmerDAO.updateFarmer(farmer);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteFarmer(String customerNumber) throws NotFoundException,
			UnassignException, NotNullableException

	{

		// Get the entit(y/ies) out of the database

		Farmer farmer = this.getFarmerByCustomerNumber(customerNumber);

		// --------------------------------------------------------------------------
		// | Begin Unassigning Associated Entities |
		// --------------------------------------------------------------------------

		// --------------------------------------------------------------------------
		// | End Unassigning Associated Entities |
		// --------------------------------------------------------------------------

		// Delete the entity
		farmerDAO.deleteFarmer(farmer);

	}

}

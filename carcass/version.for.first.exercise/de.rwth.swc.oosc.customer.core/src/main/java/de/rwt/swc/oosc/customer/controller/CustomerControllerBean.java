package de.rwt.swc.oosc.customer.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwt.swc.oosc.customer.dao.CustomerDAOLocal;
import de.rwt.swc.oosc.customer.domain.Customer;
import de.rwt.swc.oosc.customer.domain.EntityFactoryLocal;
import de.rwth.swc.oosc.carcass.common.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.common.exceptions.UnassignException;

@Stateless
public class CustomerControllerBean implements CustomerControllerLocal {
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

		// Delete the entity
		customerDAO.deleteCustomer(customer);
	}

}

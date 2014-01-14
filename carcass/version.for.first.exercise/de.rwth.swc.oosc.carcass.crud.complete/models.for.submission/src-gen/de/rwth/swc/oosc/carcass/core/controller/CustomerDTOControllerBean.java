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

import de.rwth.swc.oosc.carcass.core.domain.CustomerDTO;

import de.rwth.swc.oosc.carcass.core.dao.CustomerDTODAOLocal;

import de.rwth.swc.oosc.carcass.core.domain.EntityFactoryLocal;

@Stateless
public class CustomerDTOControllerBean implements CustomerDTOControllerLocal {
	private @EJB
	CustomerDTODAOLocal customerDTODAO;

	private @EJB
	EntityFactoryLocal entityFactory;

	/**
	 * TODO Add a comment to this method.
	 */
	public CustomerDTO getCustomerDTOByCustomerNumber(String customerNumber)
			throws NotFoundException

	{

		// Get the entity out of the database

		return customerDTODAO.getCustomerDTOByCustomerNumber(customerNumber);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void createCustomerDTO(String customerNumber,
			CustomerType customerType, String customerName, String zip,
			double lat, double lng) throws AlreadyInDBException,
			NotNullableException

	{

		// Not null checks:
		if (customerNumber == null || customerNumber.trim().length() == 0) {
			throw new NotNullableException("customerNumber");
		}

		// Check if this entity allready exists in the database
		if (customerDTODAO.existsCustomerDTO(customerNumber)) {
			throw new AlreadyInDBException("CustomerDTO");
		}

		// Create a new entity using the Entity Factory

		CustomerDTO customerDTO = entityFactory
				.createCustomerDTO(customerNumber);

		// Set attributes

		customerDTO.setCustomerType(customerType);

		customerDTO.setCustomerName(customerName);

		customerDTO.setZip(zip);

		customerDTO.setLat(lat);

		customerDTO.setLng(lng);

		// Persist the entity into the database

		customerDTODAO.storeCustomerDTO(customerDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateCustomerDTO(String newCustomerNumber,
			String oldCustomerNumber, CustomerType customerType,
			String customerName, String zip, double lat, double lng)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException

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
			if (customerDTODAO.existsCustomerDTO(newCustomerNumber)) {
				throw new AlreadyInDBException("CustomerDTO");
			}

		}

		// Get the entity out of the database

		CustomerDTO customerDTO = this
				.getCustomerDTOByCustomerNumber(oldCustomerNumber);

		// Set the attributes to the new values

		customerDTO.setCustomerNumber(newCustomerNumber);

		customerDTO.setCustomerType(customerType);

		customerDTO.setCustomerName(customerName);

		customerDTO.setZip(zip);

		customerDTO.setLat(lat);

		customerDTO.setLng(lng);

		// Persist the entity back into the database

		customerDTODAO.updateCustomerDTO(customerDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteCustomerDTO(String customerNumber)
			throws NotFoundException

	{

		// Get the entit(y/ies) out of the database

		CustomerDTO customerDTO = this
				.getCustomerDTOByCustomerNumber(customerNumber);

		// Delete the entity
		customerDTODAO.deleteCustomerDTO(customerDTO);

	}

}

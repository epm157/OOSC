package de.rwt.swc.oosc.customer.controller;

import javax.ejb.Local;

import de.rwt.swc.oosc.customer.domain.Customer;
import de.rwth.swc.oosc.carcass.common.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.common.exceptions.UnassignException;

@Local
public interface CustomerControllerLocal {

	Customer getCustomerByCustomerNumber(String customerNumber)
			throws NotFoundException

	;

	void createCustomer(String customerNumber, String customerName, String zip,
			double lat, double lng) throws AlreadyInDBException,
			NotNullableException

	;

	void updateCustomer(String newCustomerNumber, String oldCustomerNumber,
			String customerName, String zip, double lat, double lng)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException

	;

	void deleteCustomer(String customerNumber) throws NotFoundException,
			UnassignException, NotNullableException

	;

}

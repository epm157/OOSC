package de.rwth.swc.oosc.carcass.core.controller;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.core.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.core.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.core.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.core.domain.CustomerDTO;

@Local
public interface CustomerDTOControllerLocal {

	CustomerDTO getCustomerDTOByCustomerNumber(String customerNumber)
			throws NotFoundException

	;

	void createCustomerDTO(String customerNumber, CustomerType customerType,
			String customerName, String zip, double lat, double lng)
			throws AlreadyInDBException, NotNullableException

	;

	void updateCustomerDTO(String newCustomerNumber, String oldCustomerNumber,
			CustomerType customerType, String customerName, String zip,
			double lat, double lng) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	;

	void deleteCustomerDTO(String customerNumber) throws NotFoundException

	;

}

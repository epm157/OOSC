package de.rwt.swc.oosc.customer.controller;

import javax.ejb.Local;

import de.rwt.swc.oosc.customer.domain.Farmer;
import de.rwth.swc.oosc.carcass.common.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.common.exceptions.UnassignException;

@Local
public interface FarmerControllerLocal {

	Farmer getFarmerByCustomerNumber(String customerNumber)
			throws NotFoundException

	;

	void createFarmer(String customerNumber, String customerName, String zip,
			double lat, double lng) throws AlreadyInDBException,
			NotNullableException

	;

	void updateFarmer(String newCustomerNumber, String oldCustomerNumber,
			String customerName, String zip, double lat, double lng)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException

	;

	void deleteFarmer(String customerNumber) throws NotFoundException,
			UnassignException, NotNullableException

	;

}

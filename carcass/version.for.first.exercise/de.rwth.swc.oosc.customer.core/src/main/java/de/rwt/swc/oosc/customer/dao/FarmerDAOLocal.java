package de.rwt.swc.oosc.customer.dao;

import javax.ejb.Local;

import de.rwt.swc.oosc.customer.domain.Farmer;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;

@Local
public interface FarmerDAOLocal {

	void storeFarmer(Farmer Farmer);

	void updateFarmer(Farmer Farmer);

	void deleteFarmer(Farmer Farmer);

	Farmer getFarmerByCustomerNumber(String customerNumber)
			throws NotFoundException;

	boolean existsFarmer(String customerNumber);

}

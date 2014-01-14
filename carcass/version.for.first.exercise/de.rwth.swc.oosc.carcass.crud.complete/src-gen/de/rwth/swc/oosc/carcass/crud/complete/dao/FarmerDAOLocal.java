package de.rwth.swc.oosc.carcass.crud.complete.dao;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.crud.complete.domain.Farmer;

@Local
public interface FarmerDAOLocal {

	void storeFarmer(Farmer Farmer);

	void updateFarmer(Farmer Farmer);

	void deleteFarmer(Farmer Farmer);

	Farmer getFarmerByCustomerNumber(String customerNumber)
			throws NotFoundException;

	boolean existsFarmer(String customerNumber);

}

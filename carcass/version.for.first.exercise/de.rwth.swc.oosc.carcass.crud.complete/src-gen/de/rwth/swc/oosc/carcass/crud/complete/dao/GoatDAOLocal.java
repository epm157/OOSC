package de.rwth.swc.oosc.carcass.crud.complete.dao;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.crud.complete.domain.Goat;

@Local
public interface GoatDAOLocal {

	void storeGoat(Goat Goat);

	void updateGoat(Goat Goat);

	void deleteGoat(Goat Goat);

	Goat getGoatByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException;

	boolean existsGoat(String identification, String pointIdentifier,
			String customerNumber);

}

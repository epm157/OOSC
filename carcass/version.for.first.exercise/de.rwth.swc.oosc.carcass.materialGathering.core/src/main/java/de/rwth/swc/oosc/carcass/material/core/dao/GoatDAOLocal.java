package de.rwth.swc.oosc.carcass.material.core.dao;

import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.material.core.domain.Goat;

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

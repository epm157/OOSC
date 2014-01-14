package de.rwth.swc.oosc.carcass.material.core.dao;

import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.material.core.domain.Pig;

@Local
public interface PigDAOLocal {

	void storePig(Pig Pig);

	void updatePig(Pig Pig);

	void deletePig(Pig Pig);

	Pig getPigByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException;

	boolean existsPig(String identification, String pointIdentifier,
			String customerNumber);

}

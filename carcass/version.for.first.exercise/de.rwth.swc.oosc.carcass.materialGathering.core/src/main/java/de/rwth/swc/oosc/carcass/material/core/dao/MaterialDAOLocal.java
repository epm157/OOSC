package de.rwth.swc.oosc.carcass.material.core.dao;

import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.material.core.domain.Material;

@Local
public interface MaterialDAOLocal {

	void storeMaterial(Material Material);

	void updateMaterial(Material Material);

	void deleteMaterial(Material Material);

	Material getMaterialByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException;

	boolean existsMaterial(String identification, String pointIdentifier,
			String customerNumber);

}

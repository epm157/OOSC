package de.rwth.swc.oosc.carcass.core.dao;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.core.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.core.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.core.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.core.domain.MaterialDTO;

@Local
public interface MaterialDTODAOLocal {

	void storeMaterialDTO(MaterialDTO MaterialDTO);

	void updateMaterialDTO(MaterialDTO MaterialDTO);

	void deleteMaterialDTO(MaterialDTO MaterialDTO);

	MaterialDTO getMaterialDTOByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException;

	boolean existsMaterialDTO(String identification, String pointIdentifier,
			String customerNumber);

}

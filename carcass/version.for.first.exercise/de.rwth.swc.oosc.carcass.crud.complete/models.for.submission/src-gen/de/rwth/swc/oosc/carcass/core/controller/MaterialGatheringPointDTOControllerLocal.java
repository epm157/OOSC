package de.rwth.swc.oosc.carcass.core.controller;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.core.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.core.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.core.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.core.domain.MaterialGatheringPointDTO;
import de.rwth.swc.oosc.carcass.core.domain.MaterialDTO;

@Local
public interface MaterialGatheringPointDTOControllerLocal {

	MaterialGatheringPointDTO getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber(
			String pointIdentifier, String customerNumber)
			throws NotFoundException

	;

	void createMaterialGatheringPointDTO(String pointIdentifier,
			String customerNumber) throws AlreadyInDBException,
			NotNullableException

	;

	void updateMaterialGatheringPointDTO(String newPointIdentifier,
			String oldPointIdentifier, String newCustomerNumber,
			String oldCustomerNumber) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	;

	void deleteMaterialGatheringPointDTO(String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	;

	void unassignMaterialDTOFromMaterialGatheringPointDTOGatheredMaterial(
			String pointIdentifier, String customerNumber, String identification)
			throws NotFoundException, NotNullableException, UnassignException

	;

}

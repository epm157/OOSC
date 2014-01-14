package de.rwth.swc.oosc.carcass.core.controller;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.core.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.core.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.core.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.core.domain.MaterialDTO;
import de.rwth.swc.oosc.carcass.core.domain.MaterialGatheringPointDTO;

@Local
public interface MaterialDTOControllerLocal {

	MaterialDTO getMaterialDTOByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException

	;

	void createMaterialDTO(String identification, String pointIdentifier,
			String customerNumber, MaterialType materialType, double weight,
			DeadType deadType, String cowEarNumber)
			throws AlreadyInDBException, NotNullableException, AssignException,
			NotFoundException

	;

	void updateMaterialDTO(String newIdentification, String oldIdentification,
			String pointIdentifier, String customerNumber,
			MaterialType materialType, double weight, DeadType deadType,
			String cowEarNumber) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	;

	void deleteMaterialDTO(String identification, String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	;

	void unassignMaterialGatheringPointDTOFromMaterialDTOMaterialGatheringPoint(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException, NotNullableException, UnassignException

	;

}

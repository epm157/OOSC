package de.rwth.swc.oosc.carcass.material.core.controller;

import javax.ejb.Local;

import de.rwth.swc.oosc.carcase.common.domain.DeadType;
import de.rwth.swc.oosc.carcass.common.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.common.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.common.exceptions.UnassignException;
import de.rwth.swc.oosc.carcass.material.core.domain.Material;

@Local
public interface MaterialControllerLocal {

	Material getMaterialByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException

	;

	void createMaterial(String identification, String pointIdentifier,
			String customerNumber, double weight, DeadType deadType)
			throws AlreadyInDBException, NotNullableException, AssignException,
			NotFoundException

	;

	void updateMaterial(String newIdentification, String oldIdentification,
			String pointIdentifier, String customerNumber, double weight,
			DeadType deadType) throws NotFoundException, AlreadyInDBException,
			NotNullableException

	;

	void deleteMaterial(String identification, String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	;

	void unassignMaterialGatheringPointFromMaterialMaterialGatheringPoint(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException, NotNullableException, UnassignException

	;

}

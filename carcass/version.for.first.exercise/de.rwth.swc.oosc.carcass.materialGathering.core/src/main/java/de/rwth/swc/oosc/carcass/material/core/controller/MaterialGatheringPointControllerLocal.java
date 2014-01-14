package de.rwth.swc.oosc.carcass.material.core.controller;

import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.common.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.common.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.common.exceptions.UnassignException;
import de.rwth.swc.oosc.carcass.material.core.domain.MaterialGatheringPoint;

@Local
public interface MaterialGatheringPointControllerLocal {

	MaterialGatheringPoint getMaterialGatheringPointByPointIdentifierAndCustomerNumber(
			String pointIdentifier, String customerNumber)
			throws NotFoundException

	;

	void createMaterialGatheringPoint(String pointIdentifier,
			String customerNumber) throws AlreadyInDBException,
			NotNullableException, AssignException, NotFoundException

	;

	void updateMaterialGatheringPoint(String newPointIdentifier,
			String oldPointIdentifier, String customerNumber)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException

	;

	void deleteMaterialGatheringPoint(String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	;

	void unassignMaterialFromMaterialGatheringPointGatheredMaterial(
			String pointIdentifier, String customerNumber, String identification)
			throws NotFoundException, NotNullableException, UnassignException

	;

	void unassignCustomerFromMaterialGatheringPointCustomer(
			String pointIdentifier, String customerNumber)
			throws NotFoundException, NotNullableException, UnassignException

	;

}

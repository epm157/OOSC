package de.rwth.swc.oosc.carcass.crud.complete.controller;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.crud.complete.domain.MaterialGatheringPoint;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Material;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Customer;

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

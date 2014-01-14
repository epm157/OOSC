package de.rwth.swc.oosc.carcass.crud.complete.dao;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.crud.complete.domain.MaterialGatheringPoint;

@Local
public interface MaterialGatheringPointDAOLocal {

	void storeMaterialGatheringPoint(
			MaterialGatheringPoint MaterialGatheringPoint);

	void updateMaterialGatheringPoint(
			MaterialGatheringPoint MaterialGatheringPoint);

	void deleteMaterialGatheringPoint(
			MaterialGatheringPoint MaterialGatheringPoint);

	MaterialGatheringPoint getMaterialGatheringPointByPointIdentifierAndCustomerNumber(
			String pointIdentifier, String customerNumber)
			throws NotFoundException;

	boolean existsMaterialGatheringPoint(String pointIdentifier,
			String customerNumber);

}

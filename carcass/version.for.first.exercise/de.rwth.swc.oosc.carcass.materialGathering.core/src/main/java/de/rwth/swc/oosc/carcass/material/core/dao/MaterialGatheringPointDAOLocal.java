package de.rwth.swc.oosc.carcass.material.core.dao;

import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.material.core.domain.MaterialGatheringPoint;

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

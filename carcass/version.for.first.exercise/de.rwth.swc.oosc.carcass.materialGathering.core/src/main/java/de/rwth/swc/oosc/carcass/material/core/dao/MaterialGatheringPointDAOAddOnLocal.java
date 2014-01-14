package de.rwth.swc.oosc.carcass.material.core.dao;

import java.util.Set;

import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.material.core.domain.MaterialGatheringPoint;

@Local
public interface MaterialGatheringPointDAOAddOnLocal {

	public abstract Set<MaterialGatheringPoint> getMaterialGatheringPointsForCustomerNumber(
			String customerNumber);

	public abstract Set<MaterialGatheringPoint> getAllMaterialGatheringPoints();

}
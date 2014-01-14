package de.rwth.swc.oosc.carcass.material.core.controller;

import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwth.swc.oosc.carcass.material.core.dao.MaterialGatheringPointDAOAddOnLocal;
import de.rwth.swc.oosc.carcass.material.core.domain.MaterialGatheringPoint;

@Stateless
public class MaterialGatheringPointControllerAddOn {
	
	private @EJB MaterialGatheringPointDAOAddOnLocal mgpDAO;
	
	public Set<MaterialGatheringPoint> getMaterialGatheringPointsForCustomerNumber(
			String customerNumber) {
		return mgpDAO.getMaterialGatheringPointsForCustomerNumber(customerNumber);
	}

	public Set<MaterialGatheringPoint> getAllMaterialGatheringPoints() {
		return mgpDAO.getAllMaterialGatheringPoints();
	}
	
}

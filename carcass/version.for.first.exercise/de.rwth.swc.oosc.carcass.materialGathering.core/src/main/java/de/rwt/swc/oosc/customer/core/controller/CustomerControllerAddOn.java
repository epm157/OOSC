package de.rwt.swc.oosc.customer.core.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.common.exceptions.UnassignException;
import de.rwth.swc.oosc.carcass.material.core.domain.MaterialGatheringPoint;
import de.rwth.swc.oosc.carcass.material.core.facade.CarcassMaterialGatheringFacadeLocal;

@Stateless
public class CustomerControllerAddOn {

	private @EJB
	CarcassMaterialGatheringFacadeLocal carcassFacade;

	public void deleteCustomerAddOn(String customerNumber) throws NotFoundException, NotNullableException, UnassignException {
		// --------------------------------------------------------------------------
		// | Begin Unassigning Associated Entities |
		// --------------------------------------------------------------------------

		// Copy the child entities to a new set to avoid updade conflicts while
		// deleting.
		Set<MaterialGatheringPoint> _temp_MaterialGatheringPoints = new HashSet<MaterialGatheringPoint>();
		_temp_MaterialGatheringPoints.addAll(carcassFacade.getAllMaterialGatheringPointsForCustomer(customerNumber));
		for (MaterialGatheringPoint materialGatheringPoints : _temp_MaterialGatheringPoints) {
			carcassFacade
					.unassignMaterialGatheringPointFromCustomerMaterialGatheringPoints(
							customerNumber,
							materialGatheringPoints.getPointIdentifier());
			Logger.getLogger(this.getClass().getName()).log(
					Level.INFO,
					"Unassigned children MaterialGatheringPoint from Customer - Values: "
							+ customerNumber + ", "
							+ materialGatheringPoints.getPointIdentifier());
		}

		// --------------------------------------------------------------------------
		// | End Unassigning Associated Entities |
		// --------------------------------------------------------------------------
	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void unassignMaterialGatheringPointFromCustomerMaterialGatheringPoints(
			String customerNumber, String pointIdentifier)
			throws NotFoundException, NotNullableException, UnassignException {
		// Not null checks:
		if (customerNumber == null || customerNumber.trim().length() == 0) {
			throw new NotNullableException("customerNumber");
		}
		if (pointIdentifier == null || pointIdentifier.trim().length() == 0) {
			throw new NotNullableException("pointIdentifier");
		}

		// Delete contained Entity
		carcassFacade.deleteMaterialGatheringPoint(pointIdentifier,
				customerNumber);
	}

}

package Route.controller;

import javax.ejb.Local;

import de.rwth.swc.oosc.carcase.common.domain.DeadType;
import de.rwth.swc.oosc.carcass.common.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.common.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.common.exceptions.UnassignException;
import de.rwth.swc.oosc.carcass.material.core.domain.Route;

@Local
public interface RouteControllerLocal {

	Route getRouteByIdentification(
			String identification)
			throws NotFoundException

	;

	void createRoute(String identification)
			throws AlreadyInDBException, NotNullableException

	;

	void updateRoute(String newIdentification, String oldIdentification) throws NotFoundException, AlreadyInDBException,
			NotNullableException

	;

	void deleteRoute(String identification) throws NotFoundException, UnassignException,
			NotNullableException

	;
	

	/*
	void unassignCustomerFromMaterialGatheringPointCustomer(
			String pointIdentifier, String customerNumber)
			throws NotFoundException, NotNullableException, UnassignException

	;
*/



	
}

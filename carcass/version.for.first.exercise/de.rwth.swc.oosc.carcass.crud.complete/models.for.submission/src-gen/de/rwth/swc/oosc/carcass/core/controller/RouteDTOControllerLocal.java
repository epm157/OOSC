package de.rwth.swc.oosc.carcass.core.controller;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.core.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.core.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.core.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.core.domain.RouteDTO;
import de.rwth.swc.oosc.carcass.core.domain.MaterialGatheringPointDTO;

@Local
public interface RouteDTOControllerLocal {

	RouteDTO getRouteDTOByRouteName(String routeName) throws NotFoundException

	;

	void createRouteDTO(String routeName) throws AlreadyInDBException,
			NotNullableException

	;

	void updateRouteDTO(String newRouteName, String oldRouteName)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException

	;

	void deleteRouteDTO(String routeName) throws NotFoundException,
			UnassignException, NotNullableException

	;

	void assignMaterialGatheringPointDTOToRouteDTOMaterialGatheringPoints(
			String routeName, String targetPointIdentifier,
			String targetCustomerNumber) throws NotFoundException,
			NotNullableException, AssignException

	;

	void unassignMaterialGatheringPointDTOFromRouteDTOMaterialGatheringPoints(
			String routeName, String targetPointIdentifier,
			String targetCustomerNumber) throws NotFoundException,
			NotNullableException, UnassignException

	;

}

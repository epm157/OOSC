package de.rwth.swc.oosc.carcass.core.dao;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.core.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.core.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.core.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.core.domain.RouteDTO;

@Local
public interface RouteDTODAOLocal {

	void storeRouteDTO(RouteDTO RouteDTO);

	void updateRouteDTO(RouteDTO RouteDTO);

	void deleteRouteDTO(RouteDTO RouteDTO);

	RouteDTO getRouteDTOByRouteName(String routeName) throws NotFoundException;

	boolean existsRouteDTO(String routeName);

}

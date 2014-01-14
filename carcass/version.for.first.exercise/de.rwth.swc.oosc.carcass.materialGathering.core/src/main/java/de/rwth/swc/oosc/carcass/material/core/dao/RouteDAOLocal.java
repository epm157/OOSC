package de.rwth.swc.oosc.carcass.material.core.dao;

import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.material.core.domain.Route;

@Local
public interface RouteDAOLocal {

	void storeRoute(Route Route);

	void updateRoute(Route Route);

	void deleteRoute(Route Route);

	Route getRouteByIdentification(
			String identification)
			throws NotFoundException;

	boolean existsRoute(String identification);



}

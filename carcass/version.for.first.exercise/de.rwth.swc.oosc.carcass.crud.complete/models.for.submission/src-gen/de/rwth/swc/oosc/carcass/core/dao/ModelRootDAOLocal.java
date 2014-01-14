package de.rwth.swc.oosc.carcass.core.dao;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.core.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.core.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.core.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.core.domain.CustomerDTO;
import de.rwth.swc.oosc.carcass.core.domain.RouteDTO;
import de.rwth.swc.oosc.carcass.core.domain.MaterialGatheringPointDTO;

@Local
public interface ModelRootDAOLocal {

	Set<CustomerDTO> getAllCustomerDTO();

	Set<RouteDTO> getAllRouteDTO();

	Set<MaterialGatheringPointDTO> getAllMaterialGatheringPointDTO();

}

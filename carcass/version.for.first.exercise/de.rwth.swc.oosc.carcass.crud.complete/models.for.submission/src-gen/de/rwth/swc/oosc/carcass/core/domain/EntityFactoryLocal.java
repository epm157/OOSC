package de.rwth.swc.oosc.carcass.core.domain;

import javax.ejb.Local;
import de.rwth.swc.oosc.carcass.core.domain.CustomerDTO;
import de.rwth.swc.oosc.carcass.core.domain.MaterialDTO;
import de.rwth.swc.oosc.carcass.core.domain.RouteDTO;
import de.rwth.swc.oosc.carcass.core.domain.MaterialGatheringPointDTO;

@Local
public interface EntityFactoryLocal {

	CustomerDTO createCustomerDTO(String customerNumber);

	MaterialDTO createMaterialDTO(String identification);

	RouteDTO createRouteDTO(String routeName);

	MaterialGatheringPointDTO createMaterialGatheringPointDTO(
			String pointIdentifier, String customerNumber);

}

package de.rwth.swc.oosc.carcass.core.domain;

import javax.ejb.Stateless;
import de.rwth.swc.oosc.carcass.core.domain.CustomerDTO;
import de.rwth.swc.oosc.carcass.core.domain.MaterialDTO;
import de.rwth.swc.oosc.carcass.core.domain.RouteDTO;
import de.rwth.swc.oosc.carcass.core.domain.MaterialGatheringPointDTO;

/**
 * Session Bean implementation class EntityFactory
 */
@Stateless
public class EntityFactoryBean implements EntityFactoryLocal {

	public CustomerDTO createCustomerDTO(String customerNumber) {
		CustomerDTO result = new CustomerDTO();

		result.setCustomerNumber(customerNumber);

		return result;
	}

	public MaterialDTO createMaterialDTO(String identification) {
		MaterialDTO result = new MaterialDTO();

		result.setIdentification(identification);

		return result;
	}

	public RouteDTO createRouteDTO(String routeName) {
		RouteDTO result = new RouteDTO();

		result.setRouteName(routeName);

		return result;
	}

	public MaterialGatheringPointDTO createMaterialGatheringPointDTO(
			String pointIdentifier, String customerNumber) {
		MaterialGatheringPointDTO result = new MaterialGatheringPointDTO();

		result.setPointIdentifier(pointIdentifier);

		result.setCustomerNumber(customerNumber);

		return result;
	}

}

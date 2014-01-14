package de.rwth.swc.oosc.carcass.simple.application.dto.transform;

import javax.ejb.Stateless;

import de.rwth.swc.oosc.carcass.client.dtos.RouteDTO;
import de.rwth.swc.oosc.carcass.material.core.domain.MaterialGatheringPoint;
import de.rwth.swc.oosc.carcass.material.core.domain.Route;



@Stateless
public class RouteDTOTransformBean {
	@EJB
	private MaterialGatheringPointDTOTransformBean transform;
	public RouteDTO transformRoute(Route route) {
		RouteDTO routeDTO = new RouteDTO();
		
		routeDTO.setRouteName(route.getIdentification());
		
		for(MaterialGatheringPoint m: route.getGatheringPoints()){
			routeDTO.addMaterialGatheringPointDTO(transform.transormMaterialGatheringPoint(m));
			
		}
		return routeDTO;
	}
}

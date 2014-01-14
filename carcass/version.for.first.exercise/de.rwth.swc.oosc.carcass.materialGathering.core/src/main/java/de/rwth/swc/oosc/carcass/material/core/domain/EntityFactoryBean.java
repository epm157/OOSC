package de.rwth.swc.oosc.carcass.material.core.domain;

import javax.ejb.Stateless;


/**
 * Session Bean implementation class EntityFactory
 */
@Stateless
public class EntityFactoryBean implements EntityFactoryLocal {

	public MaterialGatheringPoint createMaterialGatheringPoint(
			String pointIdentifier) {
		MaterialGatheringPoint result = new MaterialGatheringPoint();

		result.setPointIdentifier(pointIdentifier);

		return result;
	}

	public Material createMaterial(String identification) {
		Material result = new Material();

		result.setIdentification(identification);

		return result;
	}

	public Pig createPig(String identification) {
		Pig result = new Pig();

		result.setIdentification(identification);

		return result;
	}

	public Goat createGoat(String identification) {
		Goat result = new Goat();

		result.setIdentification(identification);

		return result;
	}

	public Route createRoute(String identification) {
		Route result = new Route();

		result.setIdentification(identification);

		return result;
	}

}

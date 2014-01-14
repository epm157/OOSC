package de.rwth.swc.oosc.carcass.material.core.domain;

import javax.ejb.Local;


@Local
public interface EntityFactoryLocal {

	MaterialGatheringPoint createMaterialGatheringPoint(String pointIdentifier);

	Material createMaterial(String identification);

	Pig createPig(String identification);

	Goat createGoat(String identification);

}

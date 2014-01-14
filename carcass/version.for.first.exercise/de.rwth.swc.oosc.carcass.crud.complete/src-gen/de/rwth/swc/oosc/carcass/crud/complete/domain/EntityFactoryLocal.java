package de.rwth.swc.oosc.carcass.crud.complete.domain;

import javax.ejb.Local;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Customer;
import de.rwth.swc.oosc.carcass.crud.complete.domain.MaterialGatheringPoint;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Material;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Farmer;
import de.rwth.swc.oosc.carcass.crud.complete.domain.ResearchLaboratory;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Pig;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Goat;

@Local
public interface EntityFactoryLocal {

	Customer createCustomer(String customerNumber);

	MaterialGatheringPoint createMaterialGatheringPoint(String pointIdentifier);

	Material createMaterial(String identification);

	Farmer createFarmer(String customerNumber);

	ResearchLaboratory createResearchLaboratory(String customerNumber);

	Pig createPig(String identification);

	Goat createGoat(String identification);

}

package de.rwth.swc.oosc.carcass.crud.complete.domain;

import javax.ejb.Stateless;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Customer;
import de.rwth.swc.oosc.carcass.crud.complete.domain.MaterialGatheringPoint;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Material;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Farmer;
import de.rwth.swc.oosc.carcass.crud.complete.domain.ResearchLaboratory;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Pig;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Goat;

/**
 * Session Bean implementation class EntityFactory
 */
@Stateless
public class EntityFactoryBean implements EntityFactoryLocal {

	public Customer createCustomer(String customerNumber) {
		Customer result = new Customer();

		result.setCustomerNumber(customerNumber);

		return result;
	}

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

	public Farmer createFarmer(String customerNumber) {
		Farmer result = new Farmer();

		result.setCustomerNumber(customerNumber);

		return result;
	}

	public ResearchLaboratory createResearchLaboratory(String customerNumber) {
		ResearchLaboratory result = new ResearchLaboratory();

		result.setCustomerNumber(customerNumber);

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

}

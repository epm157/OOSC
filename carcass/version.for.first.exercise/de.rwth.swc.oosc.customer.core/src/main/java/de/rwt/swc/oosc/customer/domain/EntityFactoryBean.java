package de.rwt.swc.oosc.customer.domain;

import javax.ejb.Stateless;


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

}

package de.rwt.swc.oosc.customer.domain;

import javax.ejb.Local;


@Local
public interface EntityFactoryLocal {

	Customer createCustomer(String customerNumber);

	Farmer createFarmer(String customerNumber);

	ResearchLaboratory createResearchLaboratory(String customerNumber);

}

package de.rwth.swc.oosc.carcass.material.core.dao;

import java.util.Set;

import javax.ejb.Local;

import de.rwt.swc.oosc.customer.domain.Customer;
import de.rwt.swc.oosc.customer.domain.Farmer;
import de.rwt.swc.oosc.customer.domain.ResearchLaboratory;
import de.rwth.swc.oosc.carcass.material.core.domain.Goat;
import de.rwth.swc.oosc.carcass.material.core.domain.Pig;
import de.rwth.swc.oosc.carcass.material.core.domain.Route;

@Local
public interface ModelRootDAOLocal {

	Set<Customer> getAllCustomer();

	Set<Farmer> getAllFarmer();

	Set<ResearchLaboratory> getAllResearchLaboratory();

	Set<Pig> getAllPig();

	Set<Goat> getAllGoat();

	Set<Route> getAllRoute();

}

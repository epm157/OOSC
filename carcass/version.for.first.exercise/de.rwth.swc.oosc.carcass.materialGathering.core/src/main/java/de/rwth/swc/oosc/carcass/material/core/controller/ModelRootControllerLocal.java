package de.rwth.swc.oosc.carcass.material.core.controller;

import java.util.Set;

import javax.ejb.Local;

import de.rwt.swc.oosc.customer.domain.Customer;
import de.rwt.swc.oosc.customer.domain.Farmer;
import de.rwt.swc.oosc.customer.domain.ResearchLaboratory;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.material.core.domain.Goat;
import de.rwth.swc.oosc.carcass.material.core.domain.Pig;
import de.rwth.swc.oosc.carcass.material.core.domain.Route;

@Local
public interface ModelRootControllerLocal {

	Set<Customer> getAllCustomer()

	;

	Set<Farmer> getAllFarmer() throws NotFoundException

	;

	Set<ResearchLaboratory> getAllResearchLaboratory() throws NotFoundException

	;

	Set<Pig> getAllPig() throws NotFoundException

	;

	Set<Goat> getAllGoat() throws NotFoundException

	;
	
	Set<Route> getAllRoute() throws NotFoundException

	;

}

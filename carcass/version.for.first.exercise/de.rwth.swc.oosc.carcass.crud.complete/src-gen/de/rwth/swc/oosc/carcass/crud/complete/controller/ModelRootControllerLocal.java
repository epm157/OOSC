package de.rwth.swc.oosc.carcass.crud.complete.controller;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.crud.complete.domain.Customer;
import de.rwth.swc.oosc.carcass.crud.complete.domain.MaterialGatheringPoint;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Farmer;
import de.rwth.swc.oosc.carcass.crud.complete.domain.ResearchLaboratory;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Pig;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Goat;

@Local
public interface ModelRootControllerLocal {

	Set<Customer> getAllCustomer() throws NotFoundException

	;

	Set<Farmer> getAllFarmer() throws NotFoundException

	;

	Set<ResearchLaboratory> getAllResearchLaboratory() throws NotFoundException

	;

	Set<Pig> getAllPig() throws NotFoundException

	;

	Set<Goat> getAllGoat() throws NotFoundException

	;

}

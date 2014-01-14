package de.rwth.swc.oosc.carcass.material.core.controller;

import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwt.swc.oosc.customer.domain.Customer;
import de.rwt.swc.oosc.customer.domain.Farmer;
import de.rwt.swc.oosc.customer.domain.ResearchLaboratory;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.material.core.dao.ModelRootDAOLocal;
import de.rwth.swc.oosc.carcass.material.core.domain.Goat;
import de.rwth.swc.oosc.carcass.material.core.domain.Pig;
import de.rwth.swc.oosc.carcass.material.core.domain.Route;

@Stateless
public class ModelRootControllerBean implements ModelRootControllerLocal {
	private @EJB
	ModelRootDAOLocal modelRootDAO;

	/**
	 * TODO Add a comment to this method.
	 */
	public Set<Customer> getAllCustomer() 
	{
		// Use the Management-Method to get the entities from the database
		return modelRootDAO.getAllCustomer();

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Set<Farmer> getAllFarmer() throws NotFoundException

	{

		// Get the entity out of the database

		// Use the Management-Method to get the entities from the database
		return modelRootDAO.getAllFarmer();

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Set<ResearchLaboratory> getAllResearchLaboratory()
			throws NotFoundException

	{

		// Get the entity out of the database

		// Use the Management-Method to get the entities from the database
		return modelRootDAO.getAllResearchLaboratory();

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Set<Pig> getAllPig() throws NotFoundException

	{

		// Get the entity out of the database

		// Use the Management-Method to get the entities from the database
		return modelRootDAO.getAllPig();

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Set<Goat> getAllGoat() throws NotFoundException

	{

		// Get the entity out of the database

		// Use the Management-Method to get the entities from the database
		return modelRootDAO.getAllGoat();

	}

	public Set<Route> getAllRoute() throws NotFoundException {
		// TODO Auto-generated method stub
		return modelRootDAO.getAllRoute();
	}

}

package de.rwth.swc.oosc.carcass.crud.complete.controller;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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

import de.rwth.swc.oosc.carcass.crud.complete.dao.ModelRootDAOLocal;

@Stateless
public class ModelRootControllerBean implements ModelRootControllerLocal {
	private @EJB
	ModelRootDAOLocal modelRootDAO;

	/**
	 * TODO Add a comment to this method.
	 */
	public Set<Customer> getAllCustomer() throws NotFoundException

	{

		// Get the entity out of the database

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

}

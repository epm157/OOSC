package de.rwth.swc.oosc.carcass.core.controller;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwth.swc.oosc.carcass.core.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.core.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.core.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.core.domain.CustomerDTO;
import de.rwth.swc.oosc.carcass.core.domain.RouteDTO;
import de.rwth.swc.oosc.carcass.core.domain.MaterialGatheringPointDTO;
import de.rwth.swc.oosc.carcass.core.domain.MaterialDTO;

import de.rwth.swc.oosc.carcass.core.dao.ModelRootDAOLocal;

@Stateless
public class ModelRootControllerBean implements ModelRootControllerLocal {
	private @EJB
	ModelRootDAOLocal modelRootDAO;

	/**
	 * TODO Add a comment to this method.
	 */
	public Set<CustomerDTO> getAllCustomerDTO() throws NotFoundException

	{

		// Get the entity out of the database

		// Use the Management-Method to get the entities from the database
		return modelRootDAO.getAllCustomerDTO();

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Set<RouteDTO> getAllRouteDTO() throws NotFoundException

	{

		// Get the entity out of the database

		// Use the Management-Method to get the entities from the database
		return modelRootDAO.getAllRouteDTO();

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Set<MaterialGatheringPointDTO> getAllMaterialGatheringPointDTO()
			throws NotFoundException

	{

		// Get the entity out of the database

		// Use the Management-Method to get the entities from the database
		return modelRootDAO.getAllMaterialGatheringPointDTO();

	}

}

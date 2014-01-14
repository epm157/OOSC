package de.rwth.swc.teaching.forumsystem.simple.controller;

import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwth.swc.teaching.forumsystem.simple.dao.ModelRootDAOLocal;
import de.rwth.swc.teaching.forumsystem.simple.domain.Thread;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotFoundException;

@Stateless
public class ModelRootControllerBean implements ModelRootControllerLocal {
	private @EJB
	ModelRootDAOLocal modelRootDAO;

	/**
	 * TODO Add a comment to this method.
	 */
	public Set<Thread> getAllThread() throws NotFoundException

	{

		// Get the entity out of the database

		// Use the Management-Method to get the entities from the database
		return modelRootDAO.getAllThread();

	}

}

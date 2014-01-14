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

import de.rwth.swc.oosc.carcass.core.domain.RouteDTO;
import de.rwth.swc.oosc.carcass.core.domain.MaterialGatheringPointDTO;

import de.rwth.swc.oosc.carcass.core.facade.CarcassFacadeLocal;
import de.rwth.swc.oosc.carcass.core.dao.RouteDTODAOLocal;

import de.rwth.swc.oosc.carcass.core.domain.EntityFactoryLocal;

@Stateless
public class RouteDTOControllerBean implements RouteDTOControllerLocal {
	private @EJB
	CarcassFacadeLocal carcassFacade;
	private @EJB
	RouteDTODAOLocal routeDTODAO;

	private @EJB
	EntityFactoryLocal entityFactory;

	/**
	 * TODO Add a comment to this method.
	 */
	public RouteDTO getRouteDTOByRouteName(String routeName)
			throws NotFoundException

	{

		// Get the entity out of the database

		return routeDTODAO.getRouteDTOByRouteName(routeName);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void createRouteDTO(String routeName) throws AlreadyInDBException,
			NotNullableException

	{

		// Not null checks:
		if (routeName == null || routeName.trim().length() == 0) {
			throw new NotNullableException("routeName");
		}

		// Check if this entity allready exists in the database
		if (routeDTODAO.existsRouteDTO(routeName)) {
			throw new AlreadyInDBException("RouteDTO");
		}

		// Create a new entity using the Entity Factory

		RouteDTO routeDTO = entityFactory.createRouteDTO(routeName);

		// Persist the entity into the database

		routeDTODAO.storeRouteDTO(routeDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateRouteDTO(String newRouteName, String oldRouteName)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException

	{

		// Not null checks:
		if (newRouteName == null || newRouteName.trim().length() == 0) {
			throw new NotNullableException("newRouteName");
		}
		if (oldRouteName == null || oldRouteName.trim().length() == 0) {
			throw new NotNullableException("oldRouteName");
		}

		// Only check dupplication if old != new
		if (!(oldRouteName.equals(newRouteName))) {

			// Check if this entity allready exists in the database
			if (routeDTODAO.existsRouteDTO(newRouteName)) {
				throw new AlreadyInDBException("RouteDTO");
			}

		}

		// Get the entity out of the database

		RouteDTO routeDTO = this.getRouteDTOByRouteName(oldRouteName);

		// Set the attributes to the new values

		routeDTO.setRouteName(newRouteName);

		// Persist the entity back into the database

		routeDTODAO.updateRouteDTO(routeDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteRouteDTO(String routeName) throws NotFoundException,
			UnassignException, NotNullableException

	{

		// Get the entit(y/ies) out of the database

		RouteDTO routeDTO = this.getRouteDTOByRouteName(routeName);

		// --------------------------------------------------------------------------
		// |     Begin Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------

		// Copy the child entities to a new set to avoid updade conflicts while deleting.			
		Set<MaterialGatheringPointDTO> _temp_MaterialGatheringPoints = new HashSet<MaterialGatheringPointDTO>();
		_temp_MaterialGatheringPoints.addAll(routeDTO
				.getMaterialGatheringPoints());
		for (MaterialGatheringPointDTO materialGatheringPoints : _temp_MaterialGatheringPoints) {
			carcassFacade
					.unassignMaterialGatheringPointDTOFromRouteDTOMaterialGatheringPoints(
							routeName,
							materialGatheringPoints.getPointIdentifier(),
							materialGatheringPoints.getCustomerNumber());
			Logger.getLogger(this.getClass().getName()).log(
					Level.INFO,
					"Unassigned children MaterialGatheringPointDTO from RouteDTO - Values: "
							+ routeName + ", "
							+ materialGatheringPoints.getPointIdentifier()
							+ ", "
							+ materialGatheringPoints.getCustomerNumber());
		}

		// --------------------------------------------------------------------------
		// |      End Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------			

		// Delete the entity
		routeDTODAO.deleteRouteDTO(routeDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void assignMaterialGatheringPointDTOToRouteDTOMaterialGatheringPoints(
			String routeName, String targetPointIdentifier,
			String targetCustomerNumber) throws NotFoundException,
			NotNullableException, AssignException

	{

		// Not null checks:
		if (routeName == null || routeName.trim().length() == 0) {
			throw new NotNullableException("routeName");
		}
		if (targetPointIdentifier == null
				|| targetPointIdentifier.trim().length() == 0) {
			throw new NotNullableException("targetPointIdentifier");
		}
		if (targetCustomerNumber == null
				|| targetCustomerNumber.trim().length() == 0) {
			throw new NotNullableException("targetCustomerNumber");
		}

		// Get the entities out of the database

		RouteDTO routeDTO = carcassFacade.getRouteDTOByRouteName(routeName);

		MaterialGatheringPointDTO targetMaterialGatheringPointDTO = carcassFacade
				.getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber(
						targetPointIdentifier, targetCustomerNumber);

		// Add the attribute
		routeDTO.addMaterialGatheringPointDTOToMaterialGatheringPoints(targetMaterialGatheringPointDTO);

		// Persist the entit(y/ies) back into the database

		routeDTODAO.updateRouteDTO(routeDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void unassignMaterialGatheringPointDTOFromRouteDTOMaterialGatheringPoints(
			String routeName, String targetPointIdentifier,
			String targetCustomerNumber) throws NotFoundException,
			NotNullableException, UnassignException

	{

		// Not null checks:
		if (routeName == null || routeName.trim().length() == 0) {
			throw new NotNullableException("routeName");
		}
		if (targetPointIdentifier == null
				|| targetPointIdentifier.trim().length() == 0) {
			throw new NotNullableException("targetPointIdentifier");
		}
		if (targetCustomerNumber == null
				|| targetCustomerNumber.trim().length() == 0) {
			throw new NotNullableException("targetCustomerNumber");
		}

		// Get the entities out of the database

		RouteDTO routeDTO = carcassFacade.getRouteDTOByRouteName(routeName);

		MaterialGatheringPointDTO targetMaterialGatheringPointDTO = carcassFacade
				.getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber(
						targetPointIdentifier, targetCustomerNumber);

		// Remove the attribute
		routeDTO.removeMaterialGatheringPointDTOFromMaterialGatheringPoints(targetMaterialGatheringPointDTO);

		// Persist the entit(y/ies) back into the database

		routeDTODAO.updateRouteDTO(routeDTO);

	}

}

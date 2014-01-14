package Route.controller;

import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwth.swc.oosc.carcase.common.domain.DeadType;
import de.rwth.swc.oosc.carcass.common.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.common.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.common.exceptions.UnassignException;
import de.rwth.swc.oosc.carcass.material.core.dao.RouteDAOLocal;
import de.rwth.swc.oosc.carcass.material.core.dao.MaterialGatheringPointDAOLocal;
import de.rwth.swc.oosc.carcass.material.core.domain.EntityFactoryLocal;
import de.rwth.swc.oosc.carcass.material.core.domain.Route;
import de.rwth.swc.oosc.carcass.material.core.domain.MaterialGatheringPoint;
import de.rwth.swc.oosc.carcass.material.core.facade.CarcassMaterialGatheringFacadeLocal;

@Stateless
public class RouteControllerBean implements RouteControllerLocal {
	private @EJB
	RouteDAOLocal routeDAO;
	private @EJB
	CarcassMaterialGatheringFacadeLocal carcassFacade;
	private @EJB
	MaterialGatheringPointDAOLocal materialGatheringPointDAO;

	private @EJB
	EntityFactoryLocal entityFactory;

	/**
	 * TODO Add a comment to this method.
	 */
	public Route getRouteByIdentification(
			String identification)
			throws NotFoundException

	{

		// Get the entity out of the database

		return routeDAO
				.getRouteByIdentification(
						identification);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void createRoute(String identification)
			throws AlreadyInDBException, NotNullableException

	{

		// Not null checks:
		if (identification == null || identification.trim().length() == 0) {
			throw new NotNullableException("identification");
		}
	

		// Check if this entity allready exists in the database
		if (routeDAO.existsRoute(identification)) {
			throw new AlreadyInDBException("Route");
		}

		// Create a new entity using the Entity Factory

		Route route = entityFactory.createRoute(identification);

		// --------------------------------------------------------------------------
		// |     Begin Assigning Containment                                           |
		// --------------------------------------------------------------------------
		// Get the entity out of the database

		// Persist the entity into the database

		routeDAO.storeRoute(route);

		// --------------------------------------------------------------------------
		// |     End Assigning Containment                                             |
		// --------------------------------------------------------------------------

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateRoute(String newIdentification, String oldIdentification) throws NotFoundException, AlreadyInDBException,
			NotNullableException

	{

		// Not null checks:
		if (newIdentification == null || newIdentification.trim().length() == 0) {
			throw new NotNullableException("newIdentification");
		}
		if (oldIdentification == null || oldIdentification.trim().length() == 0) {
			throw new NotNullableException("oldIdentification");
		}
		

		// Only check dupplication if old != new
		if (!(oldIdentification.equals(newIdentification))) {

			// Check if this entity allready exists in the database
			if (routeDAO.existsRoute(newIdentification)) {
				throw new AlreadyInDBException("Route");
			}

		}

		// Get the entity out of the database

		Route route = this
				.getRouteByIdentification(
						oldIdentification);

		// Set the attributes to the new values

		route.setIdentification(newIdentification);

		// Persist the entity back into the database

		routeDAO.updateRoute(route);

	}

	

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteRoute(String identification) throws NotFoundException, UnassignException,
			NotNullableException

	{

		// Get the entit(y/ies) out of the database

		Route route = this
				.getRouteByIdentification(
						identification);

		Set<MaterialGatheringPoint> points = route.getGatheringPoints();
		

		// --------------------------------------------------------------------------
		// |     Begin Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------

		if (points != null) {
			for(MaterialGatheringPoint m: points){
			carcassFacade
					.unassignMaterialGatheringPointFromRoute(m.getPointIdentifier(),m.getCustomerNumber(),route.getIdentification());
			materialGatheringPointDAO
			.updateMaterialGatheringPoint(m);
			}
		}



		// --------------------------------------------------------------------------
		// |      End Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------			

		// Delete the entity
		routeDAO.deleteRoute(route);

	}



}

package de.rwth.swc.oosc.carcass.core.dao;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.rwth.swc.oosc.carcass.core.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.core.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.core.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.core.domain.RouteDTO;

@Stateless
public class RouteDTODAOBean implements RouteDTODAOLocal {

	private @PersistenceContext(name = "carcass")
	EntityManager em;

	/**
	 * TODO Add a comment to this method.
	 */
	public void storeRouteDTO(RouteDTO RouteDTO) {

		em.persist(RouteDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateRouteDTO(RouteDTO RouteDTO) {

		em.merge(RouteDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteRouteDTO(RouteDTO RouteDTO) {

		em.remove(RouteDTO);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public RouteDTO getRouteDTOByRouteName(String routeName)
			throws NotFoundException {

		Query query = em.createNamedQuery("getRouteDTOByRouteName");

		query.setParameter("routeName", routeName);

		try {
			return (RouteDTO) query.getSingleResult();
		} catch (NoResultException e) {
			throw new NotFoundException("RouteDTO", "RouteName" + routeName);
		}

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public boolean existsRouteDTO(String routeName) {

		try {
			if (this.getRouteDTOByRouteName(routeName) != null) {
				return true;
			} else {
				return false;
			}
		} catch (NotFoundException e) {
			return false;
		}

	}

}

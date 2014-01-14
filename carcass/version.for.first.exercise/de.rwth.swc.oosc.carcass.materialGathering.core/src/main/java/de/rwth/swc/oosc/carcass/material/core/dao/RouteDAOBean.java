package de.rwth.swc.oosc.carcass.material.core.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.material.core.domain.Route;

@Stateless
public class RouteDAOBean implements RouteDAOLocal {

	private @PersistenceContext(name = "carcass")
	EntityManager em;

	/**
	 * TODO Add a comment to this method.
	 */
	public void storeRoute(Route Route) {

		em.persist(Route);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateRoute(Route Route) {

		em.merge(Route);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteRoute(Route Route) {

		em.remove(Route);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Route getRouteByIdentification(String identification)
			throws NotFoundException {

		Query query = em
				.createNamedQuery("getRouteByIdentification");

		query.setParameter("identification", identification);

		

		try {
			return (Route) query.getSingleResult();
		} catch (NoResultException e) {
			throw new NotFoundException("Route", "Identification"
					+ identification );
		}

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public boolean existsRoute(String identification) {

		try {
			if (this.getRouteByIdentification(
					identification) != null) {
				return true;
			} else {
				return false;
			}
		} catch (NotFoundException e) {
			return false;
		}

	}


}

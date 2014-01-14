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

import de.rwth.swc.oosc.carcass.core.domain.CustomerDTO;
import de.rwth.swc.oosc.carcass.core.domain.RouteDTO;
import de.rwth.swc.oosc.carcass.core.domain.MaterialGatheringPointDTO;

@Stateless
public class ModelRootDAOBean implements ModelRootDAOLocal {

	private @PersistenceContext(name = "carcass")
	EntityManager em;

	/**
	 * TODO Add a comment to this method.
	 */
	public Set<CustomerDTO> getAllCustomerDTO() {

		Query query = em.createNamedQuery("getAllCustomerDTO");

		@SuppressWarnings("unchecked")
		Set<CustomerDTO> result = new HashSet<CustomerDTO>(
				query.getResultList());
		return result;

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Set<RouteDTO> getAllRouteDTO() {

		Query query = em.createNamedQuery("getAllRouteDTO");

		@SuppressWarnings("unchecked")
		Set<RouteDTO> result = new HashSet<RouteDTO>(query.getResultList());
		return result;

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Set<MaterialGatheringPointDTO> getAllMaterialGatheringPointDTO() {

		Query query = em.createNamedQuery("getAllMaterialGatheringPointDTO");

		@SuppressWarnings("unchecked")
		Set<MaterialGatheringPointDTO> result = new HashSet<MaterialGatheringPointDTO>(
				query.getResultList());
		return result;

	}

}

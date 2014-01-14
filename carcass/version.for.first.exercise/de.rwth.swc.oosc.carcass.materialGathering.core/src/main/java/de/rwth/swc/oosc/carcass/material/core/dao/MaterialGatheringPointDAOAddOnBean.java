package de.rwth.swc.oosc.carcass.material.core.dao;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.rwth.swc.oosc.carcass.material.core.domain.MaterialGatheringPoint;

@Stateless
public class MaterialGatheringPointDAOAddOnBean implements MaterialGatheringPointDAOAddOnLocal {

	private @PersistenceContext(name = "carcass")
	EntityManager em;

	/* (non-Javadoc)
	 * @see de.rwth.swc.oosc.carcass.material.core.dao.MaterialGatheringPointDAOAddOnLocal#getMaterialGatheringPointsForCustomerNumber(java.lang.String)
	 */
	public Set<MaterialGatheringPoint> getMaterialGatheringPointsForCustomerNumber(String customerNumber) {
		Query query = em
				.createQuery("SELECT materialGatheringPoint FROM MaterialGatheringPoint materialGatheringPoint JOIN materialGatheringPoint.customer customer WHERE customer.customerNumber = :customerNumber");

		query.setParameter("customerNumber", customerNumber);

		@SuppressWarnings("unchecked")
		Set<MaterialGatheringPoint> result = new HashSet<MaterialGatheringPoint>(query.getResultList());
		return result;
	}

	/* (non-Javadoc)
	 * @see de.rwth.swc.oosc.carcass.material.core.dao.MaterialGatheringPointDAOAddOnLocal#getAllMaterialGatheringPoints()
	 */
	public Set<MaterialGatheringPoint> getAllMaterialGatheringPoints() {
		Query query = em
				.createQuery("SELECT materialGatheringPoint FROM MaterialGatheringPoint materialGatheringPoint");

		@SuppressWarnings("unchecked")
		Set<MaterialGatheringPoint> result = new HashSet<MaterialGatheringPoint>(query.getResultList());
		return result;
	}
}

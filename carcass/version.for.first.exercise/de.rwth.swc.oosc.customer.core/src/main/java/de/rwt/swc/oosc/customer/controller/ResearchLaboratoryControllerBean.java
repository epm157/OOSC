package de.rwt.swc.oosc.customer.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwt.swc.oosc.customer.dao.ResearchLaboratoryDAOLocal;
import de.rwt.swc.oosc.customer.domain.EntityFactoryLocal;
import de.rwt.swc.oosc.customer.domain.ResearchLaboratory;
import de.rwth.swc.oosc.carcass.common.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.common.exceptions.UnassignException;

@Stateless
public class ResearchLaboratoryControllerBean implements
		ResearchLaboratoryControllerLocal {
	private @EJB
	ResearchLaboratoryDAOLocal researchLaboratoryDAO;

	private @EJB
	EntityFactoryLocal entityFactory;

	/**
	 * TODO Add a comment to this method.
	 */
	public ResearchLaboratory getResearchLaboratoryByCustomerNumber(
			String customerNumber) throws NotFoundException

	{

		// Get the entity out of the database

		return researchLaboratoryDAO
				.getResearchLaboratoryByCustomerNumber(customerNumber);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void createResearchLaboratory(String customerNumber,
			String customerName, String zip, double lat, double lng)
			throws AlreadyInDBException, NotNullableException

	{

		// Not null checks:
		if (customerNumber == null || customerNumber.trim().length() == 0) {
			throw new NotNullableException("customerNumber");
		}

		// Check if this entity allready exists in the database
		if (researchLaboratoryDAO.existsResearchLaboratory(customerNumber)) {
			throw new AlreadyInDBException("ResearchLaboratory");
		}

		// Create a new entity using the Entity Factory

		ResearchLaboratory researchLaboratory = entityFactory
				.createResearchLaboratory(customerNumber);

		// Set attributes

		researchLaboratory.setCustomerName(customerName);

		researchLaboratory.setZip(zip);

		researchLaboratory.setLat(lat);

		researchLaboratory.setLng(lng);

		// Persist the entity into the database

		researchLaboratoryDAO.storeResearchLaboratory(researchLaboratory);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateResearchLaboratory(String newCustomerNumber,
			String oldCustomerNumber, String customerName, String zip,
			double lat, double lng) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	{

		// Not null checks:
		if (newCustomerNumber == null || newCustomerNumber.trim().length() == 0) {
			throw new NotNullableException("newCustomerNumber");
		}
		if (oldCustomerNumber == null || oldCustomerNumber.trim().length() == 0) {
			throw new NotNullableException("oldCustomerNumber");
		}

		// Only check dupplication if old != new
		if (!(oldCustomerNumber.equals(newCustomerNumber))) {

			// Check if this entity allready exists in the database
			if (researchLaboratoryDAO
					.existsResearchLaboratory(newCustomerNumber)) {
				throw new AlreadyInDBException("ResearchLaboratory");
			}

		}

		// Get the entity out of the database

		ResearchLaboratory researchLaboratory = this
				.getResearchLaboratoryByCustomerNumber(oldCustomerNumber);

		// Set the attributes to the new values

		researchLaboratory.setCustomerNumber(newCustomerNumber);

		researchLaboratory.setCustomerName(customerName);

		researchLaboratory.setZip(zip);

		researchLaboratory.setLat(lat);

		researchLaboratory.setLng(lng);

		// Persist the entity back into the database

		researchLaboratoryDAO.updateResearchLaboratory(researchLaboratory);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteResearchLaboratory(String customerNumber)
			throws NotFoundException, UnassignException, NotNullableException

	{

		// Get the entit(y/ies) out of the database

		ResearchLaboratory researchLaboratory = this
				.getResearchLaboratoryByCustomerNumber(customerNumber);

		// --------------------------------------------------------------------------
		// | Begin Unassigning Associated Entities |
		// --------------------------------------------------------------------------

		// --------------------------------------------------------------------------
		// | End Unassigning Associated Entities |
		// --------------------------------------------------------------------------

		// Delete the entity
		researchLaboratoryDAO.deleteResearchLaboratory(researchLaboratory);

	}

}

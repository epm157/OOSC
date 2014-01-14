package de.rwth.swc.oosc.carcass.crud.complete.controller;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.crud.complete.domain.ResearchLaboratory;
import de.rwth.swc.oosc.carcass.crud.complete.domain.MaterialGatheringPoint;

@Local
public interface ResearchLaboratoryControllerLocal {

	ResearchLaboratory getResearchLaboratoryByCustomerNumber(
			String customerNumber) throws NotFoundException

	;

	void createResearchLaboratory(String customerNumber, String customerName,
			String zip, double lat, double lng) throws AlreadyInDBException,
			NotNullableException

	;

	void updateResearchLaboratory(String newCustomerNumber,
			String oldCustomerNumber, String customerName, String zip,
			double lat, double lng) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	;

	void deleteResearchLaboratory(String customerNumber)
			throws NotFoundException, UnassignException, NotNullableException

	;

}

package de.rwth.swc.oosc.carcass.crud.complete.dao;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.crud.complete.domain.ResearchLaboratory;

@Local
public interface ResearchLaboratoryDAOLocal {

	void storeResearchLaboratory(ResearchLaboratory ResearchLaboratory);

	void updateResearchLaboratory(ResearchLaboratory ResearchLaboratory);

	void deleteResearchLaboratory(ResearchLaboratory ResearchLaboratory);

	ResearchLaboratory getResearchLaboratoryByCustomerNumber(
			String customerNumber) throws NotFoundException;

	boolean existsResearchLaboratory(String customerNumber);

}

package de.rwt.swc.oosc.customer.dao;

import javax.ejb.Local;

import de.rwt.swc.oosc.customer.domain.ResearchLaboratory;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;

@Local
public interface ResearchLaboratoryDAOLocal {

	void storeResearchLaboratory(ResearchLaboratory ResearchLaboratory);

	void updateResearchLaboratory(ResearchLaboratory ResearchLaboratory);

	void deleteResearchLaboratory(ResearchLaboratory ResearchLaboratory);

	ResearchLaboratory getResearchLaboratoryByCustomerNumber(
			String customerNumber) throws NotFoundException;

	boolean existsResearchLaboratory(String customerNumber);

}

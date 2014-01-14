package de.rwth.swc.oosc.carcass.core.dao;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.core.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.core.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.core.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.core.domain.CustomerDTO;

@Local
public interface CustomerDTODAOLocal {

	void storeCustomerDTO(CustomerDTO CustomerDTO);

	void updateCustomerDTO(CustomerDTO CustomerDTO);

	void deleteCustomerDTO(CustomerDTO CustomerDTO);

	CustomerDTO getCustomerDTOByCustomerNumber(String customerNumber)
			throws NotFoundException;

	boolean existsCustomerDTO(String customerNumber);

}

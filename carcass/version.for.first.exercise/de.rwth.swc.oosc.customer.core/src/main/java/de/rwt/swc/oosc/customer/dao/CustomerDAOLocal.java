package de.rwt.swc.oosc.customer.dao;

import javax.ejb.Local;

import de.rwt.swc.oosc.customer.domain.Customer;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;

@Local
public interface CustomerDAOLocal {

	void storeCustomer(Customer Customer);

	void updateCustomer(Customer Customer);

	void deleteCustomer(Customer Customer);

	Customer getCustomerByCustomerNumber(String customerNumber)
			throws NotFoundException;

	boolean existsCustomer(String customerNumber);

}

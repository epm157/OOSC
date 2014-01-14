package de.rwth.swc.oosc.carcass.simple.application.dto.transform;

import javax.ejb.Stateless;

import de.rwt.swc.oosc.customer.domain.Customer;
import de.rwt.swc.oosc.customer.domain.Farmer;
import de.rwt.swc.oosc.customer.domain.ResearchLaboratory;
import de.rwth.swc.oosc.carcass.client.dtos.CustomerDTO;
import de.rwth.swc.oosc.carcass.client.dtos.enums.CustomerType;

@Stateless
public class CustomerDTOTransformBean {

	public CustomerDTO transformCustomer(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		
		customerDTO.setCustomerName(customer.getCustomerName());
		customerDTO.setCustomerNumber(customer.getCustomerNumber());
		
		customerDTO.setLat(customer.getLat());
		customerDTO.setLng(customer.getLng());
		
		customerDTO.setZip(customer.getZip());
		
		if (customer instanceof Farmer) {
			customerDTO.setCustomerType(CustomerType.FARMER);
		} else if (customer instanceof ResearchLaboratory) {
			customerDTO.setCustomerType(CustomerType.RESEARCH_LABORATORY);
		} else {
			customerDTO.setCustomerType(CustomerType.UNSPECIFIC);
		}
		return customerDTO;
	}

}

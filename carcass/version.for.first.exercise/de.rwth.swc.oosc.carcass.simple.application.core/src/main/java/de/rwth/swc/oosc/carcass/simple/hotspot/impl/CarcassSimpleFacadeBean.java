package de.rwth.swc.oosc.carcass.simple.hotspot.impl;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwt.swc.oosc.customer.domain.Customer;
import de.rwt.swc.oosc.customer.domain.Farmer;
import de.rwt.swc.oosc.customer.domain.ResearchLaboratory;
import de.rwth.swc.oosc.carcase.common.domain.DeadType;
import de.rwth.swc.oosc.carcass.client.dtos.CustomerDTO;
import de.rwth.swc.oosc.carcass.client.dtos.MaterialDTO;
import de.rwth.swc.oosc.carcass.client.dtos.MaterialGatheringPointDTO;
import de.rwth.swc.oosc.carcass.client.dtos.RouteDTO;
import de.rwth.swc.oosc.carcass.client.dtos.enums.CustomerType;
import de.rwth.swc.oosc.carcass.client.dtos.enums.MaterialType;
import de.rwth.swc.oosc.carcass.client.hotspot.CarcassFacadeLocal;
import de.rwth.swc.oosc.carcass.common.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.common.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.common.exceptions.TypeChangeException;
import de.rwth.swc.oosc.carcass.common.exceptions.UnassignException;
import de.rwth.swc.oosc.carcass.material.core.domain.MaterialGatheringPoint;
import de.rwth.swc.oosc.carcass.material.core.facade.CarcassMaterialGatheringFacadeLocal;
import de.rwth.swc.oosc.carcass.simple.application.dto.transform.CustomerDTOTransformBean;
import de.rwth.swc.oosc.carcass.simple.application.dto.transform.RouteDTOTransformBean;

@Stateless
public class CarcassSimpleFacadeBean implements CarcassFacadeLocal {

	private @EJB
	CarcassMaterialGatheringFacadeLocal materialGatheringFacade;
	private @EJB
	CustomerDTOTransformBean customerDTOTransform;
	
	private @EJB
	RouteDTOTransformBean routeDTOTransform;

	public Set<CustomerDTO> getAllCustomerDTO() throws NotFoundException {
		Set<CustomerDTO> result = new HashSet<CustomerDTO>();

		for (Customer customer : materialGatheringFacade.getAllCustomer()) {
			CustomerDTO customerDTO = customerDTOTransform
					.transformCustomer(customer);

			result.add(customerDTO);
		}

		return result;
	}

	public void deleteCustomer(String customerNumber) throws NotFoundException,
			UnassignException, NotNullableException {
		materialGatheringFacade.deleteCustomer(customerNumber);
	}

	public void updateCustomer(String newCustomerNumber,
			String oldCustomerNumber, CustomerType customerType,
			String customerName, String zip, double lat, double lng)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException {
		Customer customer = materialGatheringFacade
				.getCustomerByCustomerNumber(oldCustomerNumber);

		if (customerType == CustomerType.FARMER
				&& !(customer instanceof Farmer)) {
			throw new TypeChangeException("Customer type can not be changed!");
		}
		if (customerType == CustomerType.RESEARCH_LABORATORY
				&& !(customer instanceof ResearchLaboratory)) {
			throw new TypeChangeException("Customer type can not be changed!");
		}
		if (customerType == CustomerType.UNSPECIFIC
				&& (customer instanceof Farmer || customer instanceof ResearchLaboratory)) {
			throw new TypeChangeException("Customer type can not be changed!");
		}

		materialGatheringFacade.updateCustomer(newCustomerNumber,
				oldCustomerNumber, customerName, zip, lat, lng);
	}

	public void createCustomer(String customerNumber,
			CustomerType customerType, String customerName, String zip,
			double lat, double lng) throws AlreadyInDBException,
			NotNullableException {
		switch (customerType) {
		case FARMER:
			materialGatheringFacade.createFarmer(customerNumber, customerName,
					zip, lat, lng);
			break;
		case RESEARCH_LABORATORY:
			materialGatheringFacade.createResearchLaboratory(customerNumber,
					customerName, zip, lat, lng);
			break;
		case UNSPECIFIC:
		default:
			materialGatheringFacade.createCustomer(customerNumber,
					customerName, zip, lat, lng);
			break;
		}
	}

	public CustomerDTO getCustomerDTOByCustomerNumber(String customerNumber)
			throws NotFoundException {
		return customerDTOTransform.transformCustomer(materialGatheringFacade
				.getCustomerByCustomerNumber(customerNumber));
	}

	// ////////////////////////////////////////////////
	//
	// YOUR ASSIGNMENT STARTS HERE...
	//
	// ////////////////////////////////////////////////

	// ////////
	// Some Dummies to help you to get used to the system...
	// ///////

	MaterialGatheringPointDTO tempMGP;

	private MaterialGatheringPointDTO createDummyMGP() {
		if (tempMGP == null) {
			MaterialGatheringPointDTO result = new MaterialGatheringPointDTO();
			tempMGP = result;

			result.setCustomerNumber("C52074-1");
			result.setPointIdentifier("MGP-12-06-2012 10:00pm");

			MaterialDTO material = createDummyMaterial();
			result.addMaterialDTOToGatheredMaterial(material);
			material.setMaterialGatheringPoint(result);
		}
		return tempMGP;
	}

	MaterialDTO tempMat;

	private MaterialDTO createDummyMaterial() {
		if (tempMat == null) {
			MaterialDTO result = new MaterialDTO();
			tempMat = result;

			result.setIdentification("M12-06-2012 10:30pm-1");
			result.setWeight(25.3);

			result.setDeadType(DeadType.CommunicableIllness);
			result.setMaterialType(MaterialType.GOAT);
			result.setMaterialGatheringPoint(createDummyMGP());
		}

		return tempMat;
	}

	private RouteDTO createDummyRouteDTO() {
		RouteDTO result = new RouteDTO();

		result.setRouteName("Pick up some Material at the dummy locations at Dec. 06 2012.");
		result.addMaterialGatheringPointDTO(createDummyMGP());

		return result;
	}

	// ////////
	// The methods that you are supposed to implement...
	// ///////

	public Set<MaterialGatheringPointDTO> getAllMaterialGatheringPointDTO()
			throws NotFoundException {
		// TODO Auto-generated method stub
		Set<MaterialGatheringPointDTO> result = new HashSet<MaterialGatheringPointDTO>();
		result.add(createDummyMGP());

		return result;
	}

	public Set<RouteDTO> getAllRouteDTO() throws NotFoundException {
		// TODO Auto-generated method stub
		Set<RouteDTO> result = new HashSet<RouteDTO>();

		result.add(createDummyRouteDTO());

		return result;
	}

	public void deleteMaterialGatheringPoint(String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException {
		// TODO Auto-generated method stub
	}

	public void updateMaterialGatheringPoint(String newPointIdentifier,
			String oldPointIdentifier, String newCustomerNumber,
			String oldCustomerNumber) throws NotFoundException,
			AlreadyInDBException, NotNullableException {
		// TODO Auto-generated method stub
	}

	public void createMaterialGatheringPoint(String pointIdentifier,
			String customerNumber) throws AlreadyInDBException,
			NotNullableException {
		// TODO Auto-generated method stub
	}

	public MaterialGatheringPointDTO getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber(
			String pointIdentifier, String customerNumber)
			throws NotFoundException {
		// TODO Auto-generated method stub
		return createDummyMGP();
	}

	public void deleteRoute(String routeName) throws NotFoundException,
			UnassignException, NotNullableException {
		materialGatheringFacade.deleteRoute(routeName);
	}

	public void updateRoute(String newRouteName, String oldRouteName)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException {
		materialGatheringFacade.updateRoute(newRouteName,oldRouteName);
	}

	public void createRoute(String routeName) throws AlreadyInDBException,
			NotNullableException {
		materialGatheringFacade.createRoute(routeName);
	}

	public RouteDTO getRouteDTOByRouteName(String routeName)
			throws NotFoundException {
		// TODO Auto-generated method stub
		materialGatheringFacade.getRouteByIdentification(routeName);
		return createDummyRouteDTO();
	}

	public void deleteMaterial(String identification, String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException {
		// TODO Auto-generated method stub
	}

	public void updateMaterial(String newIdentification,
			String oldIdentification, String pointIdentifier,
			String customerNumber, MaterialType materialType, double weight,
			DeadType deadType, String cowEarNumber) throws NotFoundException,
			AlreadyInDBException, NotNullableException {
		// TODO Auto-generated method stub
	}

	public void createMaterial(String identification, String pointIdentifier,
			String customerNumber, MaterialType materialType, double weight,
			DeadType deadType, String cowEarNumber)
			throws AlreadyInDBException, NotNullableException, AssignException,
			NotFoundException {
		// TODO Auto-generated method stub
	}

	public MaterialDTO getMaterialDTOByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException {
		// TODO Auto-generated method stub
		return createDummyMaterial();
	}

	public void assignMaterialGatheringPointToRoute(String routeName,
			String targetPointIdentifier, String targetCustomerNumber)
			throws NotFoundException, NotNullableException, AssignException {
		// TODO Auto-generated method stub
	}

	public void unassignMaterialGatheringPointFromRoute(String routeName,
			String targetPointIdentifier, String targetCustomerNumber)
			throws NotFoundException, NotNullableException, UnassignException {
		// TODO Auto-generated method stub
	}

	public Set<MaterialGatheringPointDTO> getMaterialGatheringPointsForCustomerNumber(
			String customerNumber) throws NotFoundException {
		// TODO Auto-generated method stub
		return this.getAllMaterialGatheringPointDTO();
	}
}

package de.rwth.swc.oosc.carcass.core.facade;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwth.swc.oosc.carcass.core.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.core.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.core.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.core.domain.MaterialGatheringPointDTO;
import de.rwth.swc.oosc.carcass.core.domain.MaterialDTO;
import de.rwth.swc.oosc.carcass.core.domain.RouteDTO;
import de.rwth.swc.oosc.carcass.core.domain.CustomerDTO;

import de.rwth.swc.oosc.carcass.core.controller.MaterialGatheringPointDTOControllerLocal;
import de.rwth.swc.oosc.carcass.core.controller.RouteDTOControllerLocal;
import de.rwth.swc.oosc.carcass.core.controller.MaterialDTOControllerLocal;
import de.rwth.swc.oosc.carcass.core.controller.CustomerDTOControllerLocal;
import de.rwth.swc.oosc.carcass.core.controller.ModelRootControllerLocal;

@Stateless
public class CarcassFacadeBean implements CarcassFacadeLocal {
	private @EJB
	MaterialGatheringPointDTOControllerLocal materialGatheringPointDTOController;
	private @EJB
	RouteDTOControllerLocal routeDTOController;
	private @EJB
	MaterialDTOControllerLocal materialDTOController;
	private @EJB
	CustomerDTOControllerLocal customerDTOController;
	private @EJB
	ModelRootControllerLocal modelRootController;

	/**
	 * The method delegates the call to the  
	 * method getAllMaterialGatheringPointDTO in the 
	 * controller ModelRootController
	 */
	public Set<MaterialGatheringPointDTO> getAllMaterialGatheringPointDTO()
			throws NotFoundException

	{

		return modelRootController.getAllMaterialGatheringPointDTO();

	}

	/**
	 * The method delegates the call to the  
	 * method getAllRouteDTO in the 
	 * controller ModelRootController
	 */
	public Set<RouteDTO> getAllRouteDTO() throws NotFoundException

	{

		return modelRootController.getAllRouteDTO();

	}

	/**
	 * The method delegates the call to the  
	 * method getAllCustomerDTO in the 
	 * controller ModelRootController
	 */
	public Set<CustomerDTO> getAllCustomerDTO() throws NotFoundException

	{

		return modelRootController.getAllCustomerDTO();

	}

	/**
	 * The method delegates the call to the  
	 * method deleteMaterialGatheringPointDTO in the 
	 * controller MaterialGatheringPointDTOController
	 */
	public void deleteMaterialGatheringPointDTO(String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	{

		materialGatheringPointDTOController.deleteMaterialGatheringPointDTO(
				pointIdentifier, customerNumber);

	}

	/**
	 * The method delegates the call to the  
	 * method updateMaterialGatheringPointDTO in the 
	 * controller MaterialGatheringPointDTOController
	 */
	public void updateMaterialGatheringPointDTO(String newPointIdentifier,
			String oldPointIdentifier, String newCustomerNumber,
			String oldCustomerNumber) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	{

		materialGatheringPointDTOController.updateMaterialGatheringPointDTO(
				newPointIdentifier, oldPointIdentifier, newCustomerNumber,
				oldCustomerNumber);

	}

	/**
	 * The method delegates the call to the  
	 * method createMaterialGatheringPointDTO in the 
	 * controller MaterialGatheringPointDTOController
	 */
	public void createMaterialGatheringPointDTO(String pointIdentifier,
			String customerNumber) throws AlreadyInDBException,
			NotNullableException

	{

		materialGatheringPointDTOController.createMaterialGatheringPointDTO(
				pointIdentifier, customerNumber);

	}

	/**
	 * The method delegates the call to the  
	 * method getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber in the 
	 * controller MaterialGatheringPointDTOController
	 */
	public MaterialGatheringPointDTO getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber(
			String pointIdentifier, String customerNumber)
			throws NotFoundException

	{

		return materialGatheringPointDTOController
				.getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber(
						pointIdentifier, customerNumber);

	}

	/**
	 * The method delegates the call to the  
	 * method deleteRouteDTO in the 
	 * controller RouteDTOController
	 */
	public void deleteRouteDTO(String routeName) throws NotFoundException,
			UnassignException, NotNullableException

	{

		routeDTOController.deleteRouteDTO(routeName);

	}

	/**
	 * The method delegates the call to the  
	 * method updateRouteDTO in the 
	 * controller RouteDTOController
	 */
	public void updateRouteDTO(String newRouteName, String oldRouteName)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException

	{

		routeDTOController.updateRouteDTO(newRouteName, oldRouteName);

	}

	/**
	 * The method delegates the call to the  
	 * method createRouteDTO in the 
	 * controller RouteDTOController
	 */
	public void createRouteDTO(String routeName) throws AlreadyInDBException,
			NotNullableException

	{

		routeDTOController.createRouteDTO(routeName);

	}

	/**
	 * The method delegates the call to the  
	 * method getRouteDTOByRouteName in the 
	 * controller RouteDTOController
	 */
	public RouteDTO getRouteDTOByRouteName(String routeName)
			throws NotFoundException

	{

		return routeDTOController.getRouteDTOByRouteName(routeName);

	}

	/**
	 * The method delegates the call to the  
	 * method deleteMaterialDTO in the 
	 * controller MaterialDTOController
	 */
	public void deleteMaterialDTO(String identification,
			String pointIdentifier, String customerNumber)
			throws NotFoundException, UnassignException, NotNullableException

	{

		materialDTOController.deleteMaterialDTO(identification,
				pointIdentifier, customerNumber);

	}

	/**
	 * The method delegates the call to the  
	 * method updateMaterialDTO in the 
	 * controller MaterialDTOController
	 */
	public void updateMaterialDTO(String newIdentification,
			String oldIdentification, String pointIdentifier,
			String customerNumber, MaterialType materialType, double weight,
			DeadType deadType, String cowEarNumber) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	{

		materialDTOController.updateMaterialDTO(newIdentification,
				oldIdentification, pointIdentifier, customerNumber,
				materialType, weight, deadType, cowEarNumber);

	}

	/**
	 * The method delegates the call to the  
	 * method createMaterialDTO in the 
	 * controller MaterialDTOController
	 */
	public void createMaterialDTO(String identification,
			String pointIdentifier, String customerNumber,
			MaterialType materialType, double weight, DeadType deadType,
			String cowEarNumber) throws AlreadyInDBException,
			NotNullableException, AssignException, NotFoundException

	{

		materialDTOController.createMaterialDTO(identification,
				pointIdentifier, customerNumber, materialType, weight,
				deadType, cowEarNumber);

	}

	/**
	 * The method delegates the call to the  
	 * method getMaterialDTOByIdentificationAndPointIdentifierAndCustomerNumber in the 
	 * controller MaterialDTOController
	 */
	public MaterialDTO getMaterialDTOByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException

	{

		return materialDTOController
				.getMaterialDTOByIdentificationAndPointIdentifierAndCustomerNumber(
						identification, pointIdentifier, customerNumber);

	}

	/**
	 * The method delegates the call to the  
	 * method deleteCustomerDTO in the 
	 * controller CustomerDTOController
	 */
	public void deleteCustomerDTO(String customerNumber)
			throws NotFoundException

	{

		customerDTOController.deleteCustomerDTO(customerNumber);

	}

	/**
	 * The method delegates the call to the  
	 * method updateCustomerDTO in the 
	 * controller CustomerDTOController
	 */
	public void updateCustomerDTO(String newCustomerNumber,
			String oldCustomerNumber, CustomerType customerType,
			String customerName, String zip, double lat, double lng)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException

	{

		customerDTOController.updateCustomerDTO(newCustomerNumber,
				oldCustomerNumber, customerType, customerName, zip, lat, lng);

	}

	/**
	 * The method delegates the call to the  
	 * method createCustomerDTO in the 
	 * controller CustomerDTOController
	 */
	public void createCustomerDTO(String customerNumber,
			CustomerType customerType, String customerName, String zip,
			double lat, double lng) throws AlreadyInDBException,
			NotNullableException

	{

		customerDTOController.createCustomerDTO(customerNumber, customerType,
				customerName, zip, lat, lng);

	}

	/**
	 * The method delegates the call to the  
	 * method getCustomerDTOByCustomerNumber in the 
	 * controller CustomerDTOController
	 */
	public CustomerDTO getCustomerDTOByCustomerNumber(String customerNumber)
			throws NotFoundException

	{

		return customerDTOController
				.getCustomerDTOByCustomerNumber(customerNumber);

	}

	/**
	 * The method delegates the call to the  
	 * method unassignMaterialGatheringPointDTOFromMaterialDTOMaterialGatheringPoint in the 
	 * controller MaterialDTOController
	 */
	public void unassignMaterialGatheringPointDTOFromMaterialDTOMaterialGatheringPoint(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException, NotNullableException, UnassignException

	{

		materialDTOController
				.unassignMaterialGatheringPointDTOFromMaterialDTOMaterialGatheringPoint(
						identification, pointIdentifier, customerNumber);

	}

	/**
	 * The method delegates the call to the  
	 * method assignMaterialGatheringPointDTOToRouteDTOMaterialGatheringPoints in the 
	 * controller RouteDTOController
	 */
	public void assignMaterialGatheringPointDTOToRouteDTOMaterialGatheringPoints(
			String routeName, String targetPointIdentifier,
			String targetCustomerNumber) throws NotFoundException,
			NotNullableException, AssignException

	{

		routeDTOController
				.assignMaterialGatheringPointDTOToRouteDTOMaterialGatheringPoints(
						routeName, targetPointIdentifier, targetCustomerNumber);

	}

	/**
	 * The method delegates the call to the  
	 * method unassignMaterialGatheringPointDTOFromRouteDTOMaterialGatheringPoints in the 
	 * controller RouteDTOController
	 */
	public void unassignMaterialGatheringPointDTOFromRouteDTOMaterialGatheringPoints(
			String routeName, String targetPointIdentifier,
			String targetCustomerNumber) throws NotFoundException,
			NotNullableException, UnassignException

	{

		routeDTOController
				.unassignMaterialGatheringPointDTOFromRouteDTOMaterialGatheringPoints(
						routeName, targetPointIdentifier, targetCustomerNumber);

	}

	/**
	 * The method delegates the call to the  
	 * method unassignMaterialDTOFromMaterialGatheringPointDTOGatheredMaterial in the 
	 * controller MaterialGatheringPointDTOController
	 */
	public void unassignMaterialDTOFromMaterialGatheringPointDTOGatheredMaterial(
			String pointIdentifier, String customerNumber, String identification)
			throws NotFoundException, NotNullableException, UnassignException

	{

		materialGatheringPointDTOController
				.unassignMaterialDTOFromMaterialGatheringPointDTOGatheredMaterial(
						pointIdentifier, customerNumber, identification);

	}

}

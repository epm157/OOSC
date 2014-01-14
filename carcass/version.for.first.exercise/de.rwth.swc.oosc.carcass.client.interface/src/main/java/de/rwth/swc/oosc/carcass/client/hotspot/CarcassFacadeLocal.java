package de.rwth.swc.oosc.carcass.client.hotspot;

import java.util.Set;

import javax.ejb.Local;

import de.rwth.swc.oosc.carcase.common.domain.DeadType;
import de.rwth.swc.oosc.carcass.client.dtos.CustomerDTO;
import de.rwth.swc.oosc.carcass.client.dtos.MaterialDTO;
import de.rwth.swc.oosc.carcass.client.dtos.MaterialGatheringPointDTO;
import de.rwth.swc.oosc.carcass.client.dtos.RouteDTO;
import de.rwth.swc.oosc.carcass.client.dtos.enums.CustomerType;
import de.rwth.swc.oosc.carcass.client.dtos.enums.MaterialType;
import de.rwth.swc.oosc.carcass.common.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.common.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.common.exceptions.UnassignException;

@Local
public interface CarcassFacadeLocal {

	Set<MaterialGatheringPointDTO> getAllMaterialGatheringPointDTO()
			throws NotFoundException

	;
	
	Set<MaterialGatheringPointDTO> getMaterialGatheringPointsForCustomerNumber(
			String customerNumber) throws NotFoundException;

	Set<RouteDTO> getAllRouteDTO() throws NotFoundException

	;

	Set<CustomerDTO> getAllCustomerDTO() throws NotFoundException

	;

	void deleteMaterialGatheringPoint(String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	;

	void updateMaterialGatheringPoint(String newPointIdentifier,
			String oldPointIdentifier, String newCustomerNumber,
			String oldCustomerNumber) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	;

	void createMaterialGatheringPoint(String pointIdentifier,
			String customerNumber) throws AlreadyInDBException,
			NotNullableException

	;

	MaterialGatheringPointDTO getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber(
			String pointIdentifier, String customerNumber)
			throws NotFoundException

	;

	void deleteRoute(String routeName) throws NotFoundException,
			UnassignException, NotNullableException

	;

	void updateRoute(String newRouteName, String oldRouteName)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException

	;

	void createRoute(String routeName) throws AlreadyInDBException,
			NotNullableException

	;

	RouteDTO getRouteDTOByRouteName(String routeName) throws NotFoundException

	;

	void deleteMaterial(String identification, String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	;

	void updateMaterial(String newIdentification, String oldIdentification,
			String pointIdentifier, String customerNumber,
			MaterialType materialType, double weight, DeadType deadType,
			String cowEarNumber) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	;

	void createMaterial(String identification, String pointIdentifier,
			String customerNumber, MaterialType materialType, double weight,
			DeadType deadType, String cowEarNumber)
			throws AlreadyInDBException, NotNullableException, AssignException,
			NotFoundException

	;

	MaterialDTO getMaterialDTOByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException

	;

	void deleteCustomer(String customerNumber) throws NotFoundException, UnassignException, NotNullableException

	;

	void updateCustomer(String newCustomerNumber, String oldCustomerNumber,
			CustomerType customerType, String customerName, String zip,
			double lat, double lng) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	;

	void createCustomer(String customerNumber, CustomerType customerType,
			String customerName, String zip, double lat, double lng)
			throws AlreadyInDBException, NotNullableException

	;

	CustomerDTO getCustomerDTOByCustomerNumber(String customerNumber)
			throws NotFoundException

	;

	void assignMaterialGatheringPointToRoute(
			String routeName, String targetPointIdentifier,
			String targetCustomerNumber) throws NotFoundException,
			NotNullableException, AssignException

	;

	void unassignMaterialGatheringPointFromRoute(
			String routeName, String targetPointIdentifier,
			String targetCustomerNumber) throws NotFoundException,
			NotNullableException, UnassignException

	;
}

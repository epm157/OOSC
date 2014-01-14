package de.rwth.swc.oosc.carcass.core.facade;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.core.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.core.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.core.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.core.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.core.domain.MaterialGatheringPointDTO;
import de.rwth.swc.oosc.carcass.core.domain.MaterialDTO;
import de.rwth.swc.oosc.carcass.core.domain.RouteDTO;
import de.rwth.swc.oosc.carcass.core.domain.CustomerDTO;

@Local
public interface CarcassFacadeLocal {

	Set<MaterialGatheringPointDTO> getAllMaterialGatheringPointDTO()
			throws NotFoundException

	;

	Set<RouteDTO> getAllRouteDTO() throws NotFoundException

	;

	Set<CustomerDTO> getAllCustomerDTO() throws NotFoundException

	;

	void deleteMaterialGatheringPointDTO(String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	;

	void updateMaterialGatheringPointDTO(String newPointIdentifier,
			String oldPointIdentifier, String newCustomerNumber,
			String oldCustomerNumber) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	;

	void createMaterialGatheringPointDTO(String pointIdentifier,
			String customerNumber) throws AlreadyInDBException,
			NotNullableException

	;

	MaterialGatheringPointDTO getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber(
			String pointIdentifier, String customerNumber)
			throws NotFoundException

	;

	void deleteRouteDTO(String routeName) throws NotFoundException,
			UnassignException, NotNullableException

	;

	void updateRouteDTO(String newRouteName, String oldRouteName)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException

	;

	void createRouteDTO(String routeName) throws AlreadyInDBException,
			NotNullableException

	;

	RouteDTO getRouteDTOByRouteName(String routeName) throws NotFoundException

	;

	void deleteMaterialDTO(String identification, String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	;

	void updateMaterialDTO(String newIdentification, String oldIdentification,
			String pointIdentifier, String customerNumber,
			MaterialType materialType, double weight, DeadType deadType,
			String cowEarNumber) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	;

	void createMaterialDTO(String identification, String pointIdentifier,
			String customerNumber, MaterialType materialType, double weight,
			DeadType deadType, String cowEarNumber)
			throws AlreadyInDBException, NotNullableException, AssignException,
			NotFoundException

	;

	MaterialDTO getMaterialDTOByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException

	;

	void deleteCustomerDTO(String customerNumber) throws NotFoundException

	;

	void updateCustomerDTO(String newCustomerNumber, String oldCustomerNumber,
			CustomerType customerType, String customerName, String zip,
			double lat, double lng) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	;

	void createCustomerDTO(String customerNumber, CustomerType customerType,
			String customerName, String zip, double lat, double lng)
			throws AlreadyInDBException, NotNullableException

	;

	CustomerDTO getCustomerDTOByCustomerNumber(String customerNumber)
			throws NotFoundException

	;

	void unassignMaterialGatheringPointDTOFromMaterialDTOMaterialGatheringPoint(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException, NotNullableException, UnassignException

	;

	void assignMaterialGatheringPointDTOToRouteDTOMaterialGatheringPoints(
			String routeName, String targetPointIdentifier,
			String targetCustomerNumber) throws NotFoundException,
			NotNullableException, AssignException

	;

	void unassignMaterialGatheringPointDTOFromRouteDTOMaterialGatheringPoints(
			String routeName, String targetPointIdentifier,
			String targetCustomerNumber) throws NotFoundException,
			NotNullableException, UnassignException

	;

	void unassignMaterialDTOFromMaterialGatheringPointDTOGatheredMaterial(
			String pointIdentifier, String customerNumber, String identification)
			throws NotFoundException, NotNullableException, UnassignException

	;

}

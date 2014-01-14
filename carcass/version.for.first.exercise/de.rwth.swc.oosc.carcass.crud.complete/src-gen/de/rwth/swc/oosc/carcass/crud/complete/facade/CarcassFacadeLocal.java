package de.rwth.swc.oosc.carcass.crud.complete.facade;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;

import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.crud.complete.exceptions.UnassignException;

import de.rwth.swc.oosc.carcass.crud.complete.domain.Goat;
import de.rwth.swc.oosc.carcass.crud.complete.domain.MaterialGatheringPoint;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Pig;
import de.rwth.swc.oosc.carcass.crud.complete.domain.ResearchLaboratory;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Farmer;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Customer;
import de.rwth.swc.oosc.carcass.crud.complete.domain.Material;

@Local
public interface CarcassFacadeLocal {

	Set<Goat> getAllGoat() throws NotFoundException

	;

	Set<Pig> getAllPig() throws NotFoundException

	;

	Set<ResearchLaboratory> getAllResearchLaboratory() throws NotFoundException

	;

	Set<Farmer> getAllFarmer() throws NotFoundException

	;

	Set<Customer> getAllCustomer() throws NotFoundException

	;

	void deleteGoat(String identification, String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	;

	void updateGoat(String newIdentification, String oldIdentification,
			String pointIdentifier, String customerNumber, double weight,
			DeadType deadType) throws NotFoundException, AlreadyInDBException,
			NotNullableException

	;

	void createGoat(String identification, String pointIdentifier,
			String customerNumber, double weight, DeadType deadType)
			throws AlreadyInDBException, NotNullableException, AssignException,
			NotFoundException

	;

	Goat getGoatByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException

	;

	void deletePig(String identification, String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	;

	void updatePig(String newIdentification, String oldIdentification,
			String pointIdentifier, String customerNumber, double weight,
			DeadType deadType) throws NotFoundException, AlreadyInDBException,
			NotNullableException

	;

	void createPig(String identification, String pointIdentifier,
			String customerNumber, double weight, DeadType deadType)
			throws AlreadyInDBException, NotNullableException, AssignException,
			NotFoundException

	;

	Pig getPigByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException

	;

	void deleteResearchLaboratory(String customerNumber)
			throws NotFoundException, UnassignException, NotNullableException

	;

	void updateResearchLaboratory(String newCustomerNumber,
			String oldCustomerNumber, String customerName, String zip,
			double lat, double lng) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	;

	void createResearchLaboratory(String customerNumber, String customerName,
			String zip, double lat, double lng) throws AlreadyInDBException,
			NotNullableException

	;

	ResearchLaboratory getResearchLaboratoryByCustomerNumber(
			String customerNumber) throws NotFoundException

	;

	void deleteFarmer(String customerNumber) throws NotFoundException,
			UnassignException, NotNullableException

	;

	void updateFarmer(String newCustomerNumber, String oldCustomerNumber,
			String customerName, String zip, double lat, double lng)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException

	;

	void createFarmer(String customerNumber, String customerName, String zip,
			double lat, double lng) throws AlreadyInDBException,
			NotNullableException

	;

	Farmer getFarmerByCustomerNumber(String customerNumber)
			throws NotFoundException

	;

	void deleteMaterial(String identification, String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	;

	void updateMaterial(String newIdentification, String oldIdentification,
			String pointIdentifier, String customerNumber, double weight,
			DeadType deadType) throws NotFoundException, AlreadyInDBException,
			NotNullableException

	;

	void createMaterial(String identification, String pointIdentifier,
			String customerNumber, double weight, DeadType deadType)
			throws AlreadyInDBException, NotNullableException, AssignException,
			NotFoundException

	;

	Material getMaterialByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException

	;

	void deleteMaterialGatheringPoint(String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	;

	void updateMaterialGatheringPoint(String newPointIdentifier,
			String oldPointIdentifier, String customerNumber)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException

	;

	void createMaterialGatheringPoint(String pointIdentifier,
			String customerNumber) throws AlreadyInDBException,
			NotNullableException, AssignException, NotFoundException

	;

	MaterialGatheringPoint getMaterialGatheringPointByPointIdentifierAndCustomerNumber(
			String pointIdentifier, String customerNumber)
			throws NotFoundException

	;

	void deleteCustomer(String customerNumber) throws NotFoundException,
			UnassignException, NotNullableException

	;

	void updateCustomer(String newCustomerNumber, String oldCustomerNumber,
			String customerName, String zip, double lat, double lng)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException

	;

	void createCustomer(String customerNumber, String customerName, String zip,
			double lat, double lng) throws AlreadyInDBException,
			NotNullableException

	;

	Customer getCustomerByCustomerNumber(String customerNumber)
			throws NotFoundException

	;

	void unassignMaterialGatheringPointFromCustomerMaterialGatheringPoints(
			String customerNumber, String pointIdentifier)
			throws NotFoundException, NotNullableException, UnassignException

	;

	void unassignMaterialFromMaterialGatheringPointGatheredMaterial(
			String pointIdentifier, String customerNumber, String identification)
			throws NotFoundException, NotNullableException, UnassignException

	;

	void unassignCustomerFromMaterialGatheringPointCustomer(
			String pointIdentifier, String customerNumber)
			throws NotFoundException, NotNullableException, UnassignException

	;

	void unassignMaterialGatheringPointFromMaterialMaterialGatheringPoint(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException, NotNullableException, UnassignException

	;

}

package de.rwth.swc.oosc.carcass.material.core.facade;

import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import Route.controller.RouteControllerLocal;

import de.rwt.swc.oosc.customer.controller.CustomerControllerLocal;
import de.rwt.swc.oosc.customer.controller.FarmerControllerLocal;
import de.rwt.swc.oosc.customer.controller.ResearchLaboratoryControllerLocal;
import de.rwt.swc.oosc.customer.core.controller.CustomerControllerAddOn;
import de.rwt.swc.oosc.customer.domain.Customer;
import de.rwt.swc.oosc.customer.domain.Farmer;
import de.rwt.swc.oosc.customer.domain.ResearchLaboratory;
import de.rwth.swc.oosc.carcase.common.domain.DeadType;
import de.rwth.swc.oosc.carcass.common.exceptions.AlreadyInDBException;
import de.rwth.swc.oosc.carcass.common.exceptions.AssignException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotFoundException;
import de.rwth.swc.oosc.carcass.common.exceptions.NotNullableException;
import de.rwth.swc.oosc.carcass.common.exceptions.UnassignException;
import de.rwth.swc.oosc.carcass.material.core.controller.GoatControllerLocal;
import de.rwth.swc.oosc.carcass.material.core.controller.MaterialControllerLocal;
import de.rwth.swc.oosc.carcass.material.core.controller.MaterialGatheringPointControllerAddOn;
import de.rwth.swc.oosc.carcass.material.core.controller.MaterialGatheringPointControllerLocal;
import de.rwth.swc.oosc.carcass.material.core.controller.ModelRootControllerLocal;
import de.rwth.swc.oosc.carcass.material.core.controller.PigControllerLocal;
import de.rwth.swc.oosc.carcass.material.core.domain.Goat;
import de.rwth.swc.oosc.carcass.material.core.domain.Material;
import de.rwth.swc.oosc.carcass.material.core.domain.MaterialGatheringPoint;
import de.rwth.swc.oosc.carcass.material.core.domain.Pig;
import de.rwth.swc.oosc.carcass.material.core.domain.Route;

@Stateless
public class CarcassMaterialGatheringFacadeBean implements
		CarcassMaterialGatheringFacadeLocal {
	private @EJB
	MaterialControllerLocal materialController;
	private @EJB
	MaterialGatheringPointControllerLocal materialGatheringPointController;
	private @EJB
	CustomerControllerLocal customerController;
	private @EJB
	FarmerControllerLocal farmerController;
	private @EJB
	ResearchLaboratoryControllerLocal researchLaboratoryController;
	private @EJB
	PigControllerLocal pigController;
	private @EJB
	GoatControllerLocal goatController;
	private @EJB
	RouteControllerLocal routeController;
	private @EJB
	ModelRootControllerLocal modelRootController;

	private @EJB
	CustomerControllerAddOn customerAddOn;
	private @EJB
	MaterialGatheringPointControllerAddOn mgpAddOn;

	/**
	 * The method delegates the call to the method getAllGoat in the controller
	 * ModelRootController
	 */
	public Set<Goat> getAllGoat() throws NotFoundException

	{

		return modelRootController.getAllGoat();

	}

	/**
	 * The method delegates the call to the method getAllPig in the controller
	 * ModelRootController
	 */
	public Set<Pig> getAllPig() throws NotFoundException

	{

		return modelRootController.getAllPig();

	}

	/**
	 * The method delegates the call to the method getAllResearchLaboratory in
	 * the controller ModelRootController
	 */
	public Set<ResearchLaboratory> getAllResearchLaboratory()
			throws NotFoundException

	{

		return modelRootController.getAllResearchLaboratory();

	}

	/**
	 * The method delegates the call to the method getAllFarmer in the
	 * controller ModelRootController
	 */
	public Set<Farmer> getAllFarmer() throws NotFoundException

	{

		return modelRootController.getAllFarmer();

	}

	/**
	 * The method delegates the call to the method getAllCustomer in the
	 * controller ModelRootController
	 */
	public Set<Customer> getAllCustomer()

	{

		return modelRootController.getAllCustomer();

	}

	/**
	 * The method delegates the call to the method deleteGoat in the controller
	 * GoatController
	 */
	public void deleteGoat(String identification, String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	{

		goatController.deleteGoat(identification, pointIdentifier,
				customerNumber);

	}

	/**
	 * The method delegates the call to the method updateGoat in the controller
	 * GoatController
	 */
	public void updateGoat(String newIdentification, String oldIdentification,
			String pointIdentifier, String customerNumber, double weight,
			DeadType deadType) throws NotFoundException, AlreadyInDBException,
			NotNullableException

	{

		goatController.updateGoat(newIdentification, oldIdentification,
				pointIdentifier, customerNumber, weight, deadType);

	}

	/**
	 * The method delegates the call to the method createGoat in the controller
	 * GoatController
	 */
	public void createGoat(String identification, String pointIdentifier,
			String customerNumber, double weight, DeadType deadType)
			throws AlreadyInDBException, NotNullableException, AssignException,
			NotFoundException

	{

		goatController.createGoat(identification, pointIdentifier,
				customerNumber, weight, deadType);

	}

	/**
	 * The method delegates the call to the method
	 * getGoatByIdentificationAndPointIdentifierAndCustomerNumber in the
	 * controller GoatController
	 */
	public Goat getGoatByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException

	{

		return goatController
				.getGoatByIdentificationAndPointIdentifierAndCustomerNumber(
						identification, pointIdentifier, customerNumber);

	}

	/**
	 * The method delegates the call to the method deletePig in the controller
	 * PigController
	 */
	public void deletePig(String identification, String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	{

		pigController
				.deletePig(identification, pointIdentifier, customerNumber);

	}

	/**
	 * The method delegates the call to the method updatePig in the controller
	 * PigController
	 */
	public void updatePig(String newIdentification, String oldIdentification,
			String pointIdentifier, String customerNumber, double weight,
			DeadType deadType) throws NotFoundException, AlreadyInDBException,
			NotNullableException

	{

		pigController.updatePig(newIdentification, oldIdentification,
				pointIdentifier, customerNumber, weight, deadType);

	}

	/**
	 * The method delegates the call to the method createPig in the controller
	 * PigController
	 */
	public void createPig(String identification, String pointIdentifier,
			String customerNumber, double weight, DeadType deadType)
			throws AlreadyInDBException, NotNullableException, AssignException,
			NotFoundException

	{

		pigController.createPig(identification, pointIdentifier,
				customerNumber, weight, deadType);

	}

	/**
	 * The method delegates the call to the method
	 * getPigByIdentificationAndPointIdentifierAndCustomerNumber in the
	 * controller PigController
	 */
	public Pig getPigByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException

	{

		return pigController
				.getPigByIdentificationAndPointIdentifierAndCustomerNumber(
						identification, pointIdentifier, customerNumber);

	}

	/**
	 * The method delegates the call to the method deleteResearchLaboratory in
	 * the controller ResearchLaboratoryController
	 */
	public void deleteResearchLaboratory(String customerNumber)
			throws NotFoundException, UnassignException, NotNullableException

	{

		researchLaboratoryController.deleteResearchLaboratory(customerNumber);

	}

	/**
	 * The method delegates the call to the method updateResearchLaboratory in
	 * the controller ResearchLaboratoryController
	 */
	public void updateResearchLaboratory(String newCustomerNumber,
			String oldCustomerNumber, String customerName, String zip,
			double lat, double lng) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	{

		researchLaboratoryController.updateResearchLaboratory(
				newCustomerNumber, oldCustomerNumber, customerName, zip, lat,
				lng);

	}

	/**
	 * The method delegates the call to the method createResearchLaboratory in
	 * the controller ResearchLaboratoryController
	 */
	public void createResearchLaboratory(String customerNumber,
			String customerName, String zip, double lat, double lng)
			throws AlreadyInDBException, NotNullableException

	{

		researchLaboratoryController.createResearchLaboratory(customerNumber,
				customerName, zip, lat, lng);

	}

	/**
	 * The method delegates the call to the method
	 * getResearchLaboratoryByCustomerNumber in the controller
	 * ResearchLaboratoryController
	 */
	public ResearchLaboratory getResearchLaboratoryByCustomerNumber(
			String customerNumber) throws NotFoundException

	{

		return researchLaboratoryController
				.getResearchLaboratoryByCustomerNumber(customerNumber);

	}

	/**
	 * The method delegates the call to the method deleteFarmer in the
	 * controller FarmerController
	 */
	public void deleteFarmer(String customerNumber) throws NotFoundException,
			UnassignException, NotNullableException

	{

		farmerController.deleteFarmer(customerNumber);

	}

	/**
	 * The method delegates the call to the method updateFarmer in the
	 * controller FarmerController
	 */
	public void updateFarmer(String newCustomerNumber,
			String oldCustomerNumber, String customerName, String zip,
			double lat, double lng) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	{

		farmerController.updateFarmer(newCustomerNumber, oldCustomerNumber,
				customerName, zip, lat, lng);

	}

	/**
	 * The method delegates the call to the method createFarmer in the
	 * controller FarmerController
	 */
	public void createFarmer(String customerNumber, String customerName,
			String zip, double lat, double lng) throws AlreadyInDBException,
			NotNullableException

	{

		farmerController.createFarmer(customerNumber, customerName, zip, lat,
				lng);

	}

	/**
	 * The method delegates the call to the method getFarmerByCustomerNumber in
	 * the controller FarmerController
	 */
	public Farmer getFarmerByCustomerNumber(String customerNumber)
			throws NotFoundException

	{

		return farmerController.getFarmerByCustomerNumber(customerNumber);

	}

	/**
	 * The method delegates the call to the method deleteMaterial in the
	 * controller MaterialController
	 */
	public void deleteMaterial(String identification, String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	{

		materialController.deleteMaterial(identification, pointIdentifier,
				customerNumber);

	}

	/**
	 * The method delegates the call to the method updateMaterial in the
	 * controller MaterialController
	 */
	public void updateMaterial(String newIdentification,
			String oldIdentification, String pointIdentifier,
			String customerNumber, double weight, DeadType deadType)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException

	{

		materialController.updateMaterial(newIdentification, oldIdentification,
				pointIdentifier, customerNumber, weight, deadType);

	}

	/**
	 * The method delegates the call to the method createMaterial in the
	 * controller MaterialController
	 */
	public void createMaterial(String identification, String pointIdentifier,
			String customerNumber, double weight, DeadType deadType)
			throws AlreadyInDBException, NotNullableException, AssignException,
			NotFoundException

	{

		materialController.createMaterial(identification, pointIdentifier,
				customerNumber, weight, deadType);

	}

	/**
	 * The method delegates the call to the method
	 * getMaterialByIdentificationAndPointIdentifierAndCustomerNumber in the
	 * controller MaterialController
	 */
	public Material getMaterialByIdentificationAndPointIdentifierAndCustomerNumber(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException

	{

		return materialController
				.getMaterialByIdentificationAndPointIdentifierAndCustomerNumber(
						identification, pointIdentifier, customerNumber);

	}

	/**
	 * The method delegates the call to the method deleteMaterialGatheringPoint
	 * in the controller MaterialGatheringPointController
	 */
	public void deleteMaterialGatheringPoint(String pointIdentifier,
			String customerNumber) throws NotFoundException, UnassignException,
			NotNullableException

	{

		materialGatheringPointController.deleteMaterialGatheringPoint(
				pointIdentifier, customerNumber);

	}

	/**
	 * The method delegates the call to the method updateMaterialGatheringPoint
	 * in the controller MaterialGatheringPointController
	 */
	public void updateMaterialGatheringPoint(String newPointIdentifier,
			String oldPointIdentifier, String customerNumber)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException

	{

		materialGatheringPointController.updateMaterialGatheringPoint(
				newPointIdentifier, oldPointIdentifier, customerNumber);

	}

	/**
	 * The method delegates the call to the method createMaterialGatheringPoint
	 * in the controller MaterialGatheringPointController
	 */
	public void createMaterialGatheringPoint(String pointIdentifier,
			String customerNumber) throws AlreadyInDBException,
			NotNullableException, AssignException, NotFoundException

	{

		materialGatheringPointController.createMaterialGatheringPoint(
				pointIdentifier, customerNumber);

	}

	/**
	 * The method delegates the call to the method
	 * getMaterialGatheringPointByPointIdentifierAndCustomerNumber in the
	 * controller MaterialGatheringPointController
	 */
	public MaterialGatheringPoint getMaterialGatheringPointByPointIdentifierAndCustomerNumber(
			String pointIdentifier, String customerNumber)
			throws NotFoundException

	{

		return materialGatheringPointController
				.getMaterialGatheringPointByPointIdentifierAndCustomerNumber(
						pointIdentifier, customerNumber);

	}

	/**
	 * The method delegates the call to the method deleteCustomer in the
	 * controller CustomerController
	 */
	public void deleteCustomer(String customerNumber) throws NotFoundException,
			UnassignException, NotNullableException

	{
		customerAddOn.deleteCustomerAddOn(customerNumber);
		customerController.deleteCustomer(customerNumber);

	}

	/**
	 * The method delegates the call to the method updateCustomer in the
	 * controller CustomerController
	 */
	public void updateCustomer(String newCustomerNumber,
			String oldCustomerNumber, String customerName, String zip,
			double lat, double lng) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	{

		customerController.updateCustomer(newCustomerNumber, oldCustomerNumber,
				customerName, zip, lat, lng);

	}

	/**
	 * The method delegates the call to the method createCustomer in the
	 * controller CustomerController
	 */
	public void createCustomer(String customerNumber, String customerName,
			String zip, double lat, double lng) throws AlreadyInDBException,
			NotNullableException

	{

		customerController.createCustomer(customerNumber, customerName, zip,
				lat, lng);

	}

	/**
	 * The method delegates the call to the method getCustomerByCustomerNumber
	 * in the controller CustomerController
	 */
	public Customer getCustomerByCustomerNumber(String customerNumber)
			throws NotFoundException

	{

		return customerController.getCustomerByCustomerNumber(customerNumber);

	}

	/**
	 * The method delegates the call to the method
	 * unassignMaterialGatheringPointFromCustomerMaterialGatheringPoints in the
	 * controller CustomerController
	 */
	public void unassignMaterialGatheringPointFromCustomerMaterialGatheringPoints(
			String customerNumber, String pointIdentifier)
			throws NotFoundException, NotNullableException, UnassignException

	{

		customerAddOn
				.unassignMaterialGatheringPointFromCustomerMaterialGatheringPoints(
						customerNumber, pointIdentifier);

	}

	/**
	 * The method delegates the call to the method
	 * unassignMaterialFromMaterialGatheringPointGatheredMaterial in the
	 * controller MaterialGatheringPointController
	 */
	public void unassignMaterialFromMaterialGatheringPointGatheredMaterial(
			String pointIdentifier, String customerNumber, String identification)
			throws NotFoundException, NotNullableException, UnassignException

	{

		materialGatheringPointController
				.unassignMaterialFromMaterialGatheringPointGatheredMaterial(
						pointIdentifier, customerNumber, identification);

	}

	/**
	 * The method delegates the call to the method
	 * unassignCustomerFromMaterialGatheringPointCustomer in the controller
	 * MaterialGatheringPointController
	 */
	public void unassignCustomerFromMaterialGatheringPointCustomer(
			String pointIdentifier, String customerNumber)
			throws NotFoundException, NotNullableException, UnassignException

	{

		materialGatheringPointController
				.unassignCustomerFromMaterialGatheringPointCustomer(
						pointIdentifier, customerNumber);

	}

	/**
	 * The method delegates the call to the method
	 * unassignMaterialGatheringPointFromMaterialMaterialGatheringPoint in the
	 * controller MaterialController
	 */
	public void unassignMaterialGatheringPointFromMaterialMaterialGatheringPoint(
			String identification, String pointIdentifier, String customerNumber)
			throws NotFoundException, NotNullableException, UnassignException

	{

		materialController
				.unassignMaterialGatheringPointFromMaterialMaterialGatheringPoint(
						identification, pointIdentifier, customerNumber);

	}

	public Set<MaterialGatheringPoint> getAllMaterialGatheringPointsForCustomer(
			String customerNumber) {
		return mgpAddOn
				.getMaterialGatheringPointsForCustomerNumber(customerNumber);
	}

	public Set<MaterialGatheringPoint> getAllMaterialGatheringPoint() {
		return mgpAddOn.getAllMaterialGatheringPoints();
	}

	public Set<Route> getAllRoute() throws NotFoundException {
		return modelRootController.getAllRoute();
	}

	public void deleteRoute(String identification) throws NotFoundException,
			UnassignException, NotNullableException {
		routeController.deleteRoute(identification);

	}

	public void updateRoute(String newIdentification, String oldIdentification)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException {
		routeController.updateRoute(newIdentification, oldIdentification);

	}

	public void createRoute(String identification) throws AlreadyInDBException,
			NotNullableException {
		routeController.createRoute(identification);

	}

	public Route getRouteByIdentification(String identification)
			throws NotFoundException {
		return routeController.getRouteByIdentification(identification);
	}

	public void unassignMaterialGatheringPointFromRoute(String pointIdentifier,
			String customerNumber, String identification) {
		MaterialGatheringPoint point=null;
		Route route=null;
		try {
			point = materialGatheringPointController
					.getMaterialGatheringPointByPointIdentifierAndCustomerNumber(
							pointIdentifier, customerNumber);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		try {
			route = routeController.getRouteByIdentification(identification);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		if (point != null && route != null) {
			if (point.getRoute().equals(route)) {
				point.setRoute(null);
			}
		}
	}

}

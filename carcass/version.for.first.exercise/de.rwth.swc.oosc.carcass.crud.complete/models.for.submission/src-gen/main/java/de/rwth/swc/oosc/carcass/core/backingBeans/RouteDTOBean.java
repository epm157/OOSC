package de.rwth.swc.oosc.carcass.core.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import de.rwth.swc.jsf.common.BeanBase;

@ManagedBean
@RequestScoped
public class RouteDTOBean extends BeanBase implements Serializable {

	/**
	 * Serialization ID is required by Serializable...
	 */
	private static final long serialVersionUID = 1L;

	/*  ----------
		EJB Connections
	    ----------
	 */
	private @EJB
	CarcassFacadeLocal carcassFacade;

	/*  ----------
		PROPERTIES
	    ----------
	 */
	private String routeName;
	private List<MaterialGatheringPointDTO> materialGatheringPoints;
	private RouteDTO routeDTO;

	/*  ----------------
		Accessor Methods
	    ----------------
	 */

	public String getRouteName() {

		return this.routeName;
	}

	public void setRouteName(String aRouteName) {
		this.routeName = aRouteName;
	}

	public List<MaterialGatheringPointDTO> getMaterialGatheringPoints() {

		return this.materialGatheringPoints;
	}

	public void setMaterialGatheringPoints(
			List<MaterialGatheringPointDTO> aMaterialGatheringPoints) {
		this.materialGatheringPoints = aMaterialGatheringPoints;
	}

	public RouteDTO getRouteDTO() {

		if (this.routeDTO == null) {
			this.fetchRouteDTO();
		}

		return this.routeDTO;
	}

	public void setRouteDTO(RouteDTO aRouteDTO) {
		this.routeDTO = aRouteDTO;
	}

	/*  --------------------------
		Normal Methods and Actions
	    --------------------------
	 */

	public void fetchRouteDTO() {

		try {
			this.routeDTO = carcassFacade
					.getRouteDTOByRouteName(this.routeName);
			// Synchronize the atomic properties
			this.setRouteName(this.getRouteDTO().getRouteName());
			this.setMaterialGatheringPoints(new ArrayList(this.getRouteDTO()
					.getMaterialGatheringPoints()));

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

	public String createRouteDTO() {

		try {
			carcassFacade.createRouteDTO(this.routeName);
			addGeneralInfoMessage("RouteDTO created successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String updateRouteDTO() {

		try {
			carcassFacade.updateRouteDTO(this.routeName, this.routeName);
			addGeneralInfoMessage("RouteDTO was updated.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String deleteRouteDTO() {

		try {
			carcassFacade.deleteRouteDTO(this.routeName);
			addGeneralInfoMessage("RouteDTO deleted successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String assignMaterialGatheringPointDTOToRouteDTOMaterialGatheringPoints() {
		MaterialGatheringPointDTOBean localMaterialGatheringPointDTOBean = (MaterialGatheringPointDTOBean) this
				.getRequestBean("materialGatheringPointDTOBean");
		String _pointIdentifier = localMaterialGatheringPointDTOBean
				.getPointIdentifier();
		String _customerNumber = localMaterialGatheringPointDTOBean
				.getCustomerNumber();

		try {
			carcassFacade
					.assignMaterialGatheringPointDTOToRouteDTOMaterialGatheringPoints(
							this.routeName, _pointIdentifier, _customerNumber);
			addGeneralInfoMessage("Successfully assigned.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String unassignMaterialGatheringPointDTOFromRouteDTOMaterialGatheringPoints() {
		MaterialGatheringPointDTOBean localMaterialGatheringPointDTOBean = (MaterialGatheringPointDTOBean) this
				.getRequestBean("materialGatheringPointDTOBean");
		String _pointIdentifier = localMaterialGatheringPointDTOBean
				.getPointIdentifier();
		String _customerNumber = localMaterialGatheringPointDTOBean
				.getCustomerNumber();

		try {
			carcassFacade
					.unassignMaterialGatheringPointDTOFromRouteDTOMaterialGatheringPoints(
							this.routeName, _pointIdentifier, _customerNumber);
			addGeneralInfoMessage("Successfully unassigned.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

}

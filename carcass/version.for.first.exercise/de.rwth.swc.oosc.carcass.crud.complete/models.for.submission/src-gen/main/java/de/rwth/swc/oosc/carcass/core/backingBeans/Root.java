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
public class Root extends BeanBase implements Serializable {

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
	private List<MaterialGatheringPointDTO> allMaterialGatheringPointDTO;
	private List<RouteDTO> allRouteDTO;
	private List<CustomerDTO> allCustomerDTO;

	/*  ----------------
		Accessor Methods
	    ----------------
	 */

	public List<MaterialGatheringPointDTO> getAllMaterialGatheringPointDTO() {

		if (this.allMaterialGatheringPointDTO == null) {
			this.fetchAllMaterialGatheringPointDTO();
		}

		return this.allMaterialGatheringPointDTO;
	}

	public void setAllMaterialGatheringPointDTO(
			List<MaterialGatheringPointDTO> aAllMaterialGatheringPointDTO) {
		this.allMaterialGatheringPointDTO = aAllMaterialGatheringPointDTO;
	}

	public List<RouteDTO> getAllRouteDTO() {

		if (this.allRouteDTO == null) {
			this.fetchAllRouteDTO();
		}

		return this.allRouteDTO;
	}

	public void setAllRouteDTO(List<RouteDTO> aAllRouteDTO) {
		this.allRouteDTO = aAllRouteDTO;
	}

	public List<CustomerDTO> getAllCustomerDTO() {

		if (this.allCustomerDTO == null) {
			this.fetchAllCustomerDTO();
		}

		return this.allCustomerDTO;
	}

	public void setAllCustomerDTO(List<CustomerDTO> aAllCustomerDTO) {
		this.allCustomerDTO = aAllCustomerDTO;
	}

	/*  --------------------------
		Normal Methods and Actions
	    --------------------------
	 */

	public void fetchAllMaterialGatheringPointDTO() {

		try {
			this.allMaterialGatheringPointDTO = new ArrayList(
					carcassFacade.getAllMaterialGatheringPointDTO());

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

	public void fetchAllRouteDTO() {

		try {
			this.allRouteDTO = new ArrayList(carcassFacade.getAllRouteDTO());

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

	public void fetchAllCustomerDTO() {

		try {
			this.allCustomerDTO = new ArrayList(
					carcassFacade.getAllCustomerDTO());

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

}

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
public class CustomerDTOBean extends BeanBase implements Serializable {

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
	private String customerNumber;
	private CustomerType customerType;
	private String customerName;
	private String zip;
	private double lat;
	private double lng;
	private CustomerDTO customerDTO;

	/*  ----------------
		Accessor Methods
	    ----------------
	 */

	public String getCustomerNumber() {

		return this.customerNumber;
	}

	public void setCustomerNumber(String aCustomerNumber) {
		this.customerNumber = aCustomerNumber;
	}

	public CustomerType getCustomerType() {

		return this.customerType;
	}

	public void setCustomerType(CustomerType aCustomerType) {
		this.customerType = aCustomerType;
	}

	public String getCustomerName() {

		return this.customerName;
	}

	public void setCustomerName(String aCustomerName) {
		this.customerName = aCustomerName;
	}

	public String getZip() {

		return this.zip;
	}

	public void setZip(String aZip) {
		this.zip = aZip;
	}

	public double getLat() {

		return this.lat;
	}

	public void setLat(double aLat) {
		this.lat = aLat;
	}

	public double getLng() {

		return this.lng;
	}

	public void setLng(double aLng) {
		this.lng = aLng;
	}

	public CustomerDTO getCustomerDTO() {

		if (this.customerDTO == null) {
			this.fetchCustomerDTO();
		}

		return this.customerDTO;
	}

	public void setCustomerDTO(CustomerDTO aCustomerDTO) {
		this.customerDTO = aCustomerDTO;
	}

	/*  --------------------------
		Normal Methods and Actions
	    --------------------------
	 */

	public void fetchCustomerDTO() {
		MaterialGatheringPointDTOBean localMaterialGatheringPointDTOBean = (MaterialGatheringPointDTOBean) this
				.getRequestBean("materialGatheringPointDTOBean");
		String _customerNumber = localMaterialGatheringPointDTOBean
				.getCustomerNumber();

		try {
			this.customerDTO = carcassFacade
					.getCustomerDTOByCustomerNumber(_customerNumber);
			// Synchronize the atomic properties
			this.setCustomerNumber(this.getCustomerDTO().getCustomerNumber());
			this.setCustomerType(this.getCustomerDTO().getCustomerType());
			this.setCustomerName(this.getCustomerDTO().getCustomerName());
			this.setZip(this.getCustomerDTO().getZip());
			this.setLat(this.getCustomerDTO().getLat());
			this.setLng(this.getCustomerDTO().getLng());

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

	public String createCustomerDTO() {
		MaterialGatheringPointDTOBean localMaterialGatheringPointDTOBean = (MaterialGatheringPointDTOBean) this
				.getRequestBean("materialGatheringPointDTOBean");
		String _customerNumber = localMaterialGatheringPointDTOBean
				.getCustomerNumber();

		try {
			carcassFacade.createCustomerDTO(_customerNumber, this.customerType,
					this.customerName, this.zip, this.lat, this.lng);
			addGeneralInfoMessage("CustomerDTO created successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String updateCustomerDTO() {
		MaterialGatheringPointDTOBean localMaterialGatheringPointDTOBean = (MaterialGatheringPointDTOBean) this
				.getRequestBean("materialGatheringPointDTOBean");
		String _customerNumber = localMaterialGatheringPointDTOBean
				.getCustomerNumber();

		try {
			carcassFacade.updateCustomerDTO(_customerNumber, _customerNumber,
					this.customerType, this.customerName, this.zip, this.lat,
					this.lng);
			addGeneralInfoMessage("CustomerDTO was updated.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String deleteCustomerDTO() {
		MaterialGatheringPointDTOBean localMaterialGatheringPointDTOBean = (MaterialGatheringPointDTOBean) this
				.getRequestBean("materialGatheringPointDTOBean");
		String _customerNumber = localMaterialGatheringPointDTOBean
				.getCustomerNumber();

		try {
			carcassFacade.deleteCustomerDTO(_customerNumber);
			addGeneralInfoMessage("CustomerDTO deleted successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

}

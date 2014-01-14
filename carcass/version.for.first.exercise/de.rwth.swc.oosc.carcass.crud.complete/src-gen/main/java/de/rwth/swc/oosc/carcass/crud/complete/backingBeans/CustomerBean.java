package de.rwth.swc.oosc.carcass.crud.complete.backingBeans;

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
public class CustomerBean extends BeanBase implements Serializable {

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
	private String customerName;
	private String zip;
	private double lat;
	private double lng;
	private List<MaterialGatheringPoint> materialGatheringPoints;
	private Customer customer;

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

	public List<MaterialGatheringPoint> getMaterialGatheringPoints() {

		return this.materialGatheringPoints;
	}

	public void setMaterialGatheringPoints(
			List<MaterialGatheringPoint> aMaterialGatheringPoints) {
		this.materialGatheringPoints = aMaterialGatheringPoints;
	}

	public Customer getCustomer() {

		if (this.customer == null) {
			this.fetchCustomer();
		}

		return this.customer;
	}

	public void setCustomer(Customer aCustomer) {
		this.customer = aCustomer;
	}

	/*  --------------------------
		Normal Methods and Actions
	    --------------------------
	 */

	public void fetchCustomer() {

		try {
			this.customer = carcassFacade
					.getCustomerByCustomerNumber(this.customerNumber);
			// Synchronize the atomic properties
			this.setCustomerNumber(this.getCustomer().getCustomerNumber());
			this.setCustomerName(this.getCustomer().getCustomerName());
			this.setZip(this.getCustomer().getZip());
			this.setLat(this.getCustomer().getLat());
			this.setLng(this.getCustomer().getLng());
			this.setMaterialGatheringPoints(new ArrayList(this.getCustomer()
					.getMaterialGatheringPoints()));

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

	public String createCustomer() {

		try {
			carcassFacade.createCustomer(this.customerNumber,
					this.customerName, this.zip, this.lat, this.lng);
			addGeneralInfoMessage("Customer created successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String updateCustomer() {

		try {
			carcassFacade.updateCustomer(this.customerNumber,
					this.customerNumber, this.customerName, this.zip, this.lat,
					this.lng);
			addGeneralInfoMessage("Customer was updated.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String deleteCustomer() {

		try {
			carcassFacade.deleteCustomer(this.customerNumber);
			addGeneralInfoMessage("Customer deleted successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

}

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
public class FarmerBean extends BeanBase implements Serializable {

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
	private Farmer farmer;

	/*  ----------------
		Accessor Methods
	    ----------------
	 */

	public Farmer getFarmer() {

		if (this.farmer == null) {
			this.fetchFarmer();
		}

		return this.farmer;
	}

	public void setFarmer(Farmer aFarmer) {
		this.farmer = aFarmer;
	}

	/*  --------------------------
		Normal Methods and Actions
	    --------------------------
	 */

	public void fetchFarmer() {
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _customerNumber = localCustomerBean.getCustomerNumber();

		try {
			this.farmer = carcassFacade
					.getFarmerByCustomerNumber(_customerNumber);
			// Synchronize the atomic properties

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

	public String createFarmer() {
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _customerNumber = localCustomerBean.getCustomerNumber();
		String _customerName = localCustomerBean.getCustomerName();
		String _zip = localCustomerBean.getZip();
		double _lat = localCustomerBean.getLat();
		double _lng = localCustomerBean.getLng();

		try {
			carcassFacade.createFarmer(_customerNumber, _customerName, _zip,
					_lat, _lng);
			addGeneralInfoMessage("Farmer created successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String updateFarmer() {
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _customerNumber = localCustomerBean.getCustomerNumber();
		String _customerName = localCustomerBean.getCustomerName();
		String _zip = localCustomerBean.getZip();
		double _lat = localCustomerBean.getLat();
		double _lng = localCustomerBean.getLng();

		try {
			carcassFacade.updateFarmer(_customerNumber, _customerNumber,
					_customerName, _zip, _lat, _lng);
			addGeneralInfoMessage("Farmer was updated.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String deleteFarmer() {
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _customerNumber = localCustomerBean.getCustomerNumber();

		try {
			carcassFacade.deleteFarmer(_customerNumber);
			addGeneralInfoMessage("Farmer deleted successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

}

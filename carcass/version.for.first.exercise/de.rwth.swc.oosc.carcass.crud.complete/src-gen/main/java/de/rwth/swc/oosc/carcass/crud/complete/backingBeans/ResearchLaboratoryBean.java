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
public class ResearchLaboratoryBean extends BeanBase implements Serializable {

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
	private ResearchLaboratory researchLaboratory;

	/*  ----------------
		Accessor Methods
	    ----------------
	 */

	public ResearchLaboratory getResearchLaboratory() {

		if (this.researchLaboratory == null) {
			this.fetchResearchLaboratory();
		}

		return this.researchLaboratory;
	}

	public void setResearchLaboratory(ResearchLaboratory aResearchLaboratory) {
		this.researchLaboratory = aResearchLaboratory;
	}

	/*  --------------------------
		Normal Methods and Actions
	    --------------------------
	 */

	public void fetchResearchLaboratory() {
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _customerNumber = localCustomerBean.getCustomerNumber();

		try {
			this.researchLaboratory = carcassFacade
					.getResearchLaboratoryByCustomerNumber(_customerNumber);
			// Synchronize the atomic properties

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

	public String createResearchLaboratory() {
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _customerNumber = localCustomerBean.getCustomerNumber();
		String _customerName = localCustomerBean.getCustomerName();
		String _zip = localCustomerBean.getZip();
		double _lat = localCustomerBean.getLat();
		double _lng = localCustomerBean.getLng();

		try {
			carcassFacade.createResearchLaboratory(_customerNumber,
					_customerName, _zip, _lat, _lng);
			addGeneralInfoMessage("ResearchLaboratory created successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String updateResearchLaboratory() {
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _customerNumber = localCustomerBean.getCustomerNumber();
		String _customerName = localCustomerBean.getCustomerName();
		String _zip = localCustomerBean.getZip();
		double _lat = localCustomerBean.getLat();
		double _lng = localCustomerBean.getLng();

		try {
			carcassFacade.updateResearchLaboratory(_customerNumber,
					_customerNumber, _customerName, _zip, _lat, _lng);
			addGeneralInfoMessage("ResearchLaboratory was updated.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String deleteResearchLaboratory() {
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _customerNumber = localCustomerBean.getCustomerNumber();

		try {
			carcassFacade.deleteResearchLaboratory(_customerNumber);
			addGeneralInfoMessage("ResearchLaboratory deleted successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

}

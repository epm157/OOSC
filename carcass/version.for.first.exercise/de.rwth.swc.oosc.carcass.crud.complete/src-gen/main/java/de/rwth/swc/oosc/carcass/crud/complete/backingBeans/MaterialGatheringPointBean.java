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
public class MaterialGatheringPointBean extends BeanBase
		implements
			Serializable {

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
	private String pointIdentifier;
	private List<Material> gatheredMaterial;
	private Customer customer;
	private MaterialGatheringPoint materialGatheringPoint;

	/*  ----------------
		Accessor Methods
	    ----------------
	 */

	public String getPointIdentifier() {

		return this.pointIdentifier;
	}

	public void setPointIdentifier(String aPointIdentifier) {
		this.pointIdentifier = aPointIdentifier;
	}

	public List<Material> getGatheredMaterial() {

		return this.gatheredMaterial;
	}

	public void setGatheredMaterial(List<Material> aGatheredMaterial) {
		this.gatheredMaterial = aGatheredMaterial;
	}

	public Customer getCustomer() {

		return this.customer;
	}

	public void setCustomer(Customer aCustomer) {
		this.customer = aCustomer;
	}

	public MaterialGatheringPoint getMaterialGatheringPoint() {

		if (this.materialGatheringPoint == null) {
			this.fetchMaterialGatheringPoint();
		}

		return this.materialGatheringPoint;
	}

	public void setMaterialGatheringPoint(
			MaterialGatheringPoint aMaterialGatheringPoint) {
		this.materialGatheringPoint = aMaterialGatheringPoint;
	}

	/*  --------------------------
		Normal Methods and Actions
	    --------------------------
	 */

	public void fetchMaterialGatheringPoint() {
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _customerNumber = localCustomerBean.getCustomerNumber();

		try {
			this.materialGatheringPoint = carcassFacade
					.getMaterialGatheringPointByPointIdentifierAndCustomerNumber(
							this.pointIdentifier, _customerNumber);
			// Synchronize the atomic properties
			this.setPointIdentifier(this.getMaterialGatheringPoint()
					.getPointIdentifier());
			this.setGatheredMaterial(new ArrayList(this
					.getMaterialGatheringPoint().getGatheredMaterial()));
			this.setCustomer(this.getMaterialGatheringPoint().getCustomer());

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

	public String createMaterialGatheringPoint() {
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _customerNumber = localCustomerBean.getCustomerNumber();

		try {
			carcassFacade.createMaterialGatheringPoint(this.pointIdentifier,
					_customerNumber);
			addGeneralInfoMessage("MaterialGatheringPoint created successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String updateMaterialGatheringPoint() {
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _customerNumber = localCustomerBean.getCustomerNumber();

		try {
			carcassFacade.updateMaterialGatheringPoint(this.pointIdentifier,
					this.pointIdentifier, _customerNumber);
			addGeneralInfoMessage("MaterialGatheringPoint was updated.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String deleteMaterialGatheringPoint() {
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _customerNumber = localCustomerBean.getCustomerNumber();

		try {
			carcassFacade.deleteMaterialGatheringPoint(this.pointIdentifier,
					_customerNumber);
			addGeneralInfoMessage("MaterialGatheringPoint deleted successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String unassignCustomerFromMaterialGatheringPointCustomer() {
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _customerNumber = localCustomerBean.getCustomerNumber();

		try {
			carcassFacade.unassignCustomerFromMaterialGatheringPointCustomer(
					this.pointIdentifier, _customerNumber);
			addGeneralInfoMessage("Child entity was successfully deleted.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

}

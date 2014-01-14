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
public class MaterialBean extends BeanBase implements Serializable {

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
	private String identification;
	private double weight;
	private DeadType deadType;
	private MaterialGatheringPoint materialGatheringPoint;
	private Material material;

	/*  ----------------
		Accessor Methods
	    ----------------
	 */

	public String getIdentification() {

		return this.identification;
	}

	public void setIdentification(String aIdentification) {
		this.identification = aIdentification;
	}

	public double getWeight() {

		return this.weight;
	}

	public void setWeight(double aWeight) {
		this.weight = aWeight;
	}

	public DeadType getDeadType() {

		return this.deadType;
	}

	public void setDeadType(DeadType aDeadType) {
		this.deadType = aDeadType;
	}

	public MaterialGatheringPoint getMaterialGatheringPoint() {

		return this.materialGatheringPoint;
	}

	public void setMaterialGatheringPoint(
			MaterialGatheringPoint aMaterialGatheringPoint) {
		this.materialGatheringPoint = aMaterialGatheringPoint;
	}

	public Material getMaterial() {

		if (this.material == null) {
			this.fetchMaterial();
		}

		return this.material;
	}

	public void setMaterial(Material aMaterial) {
		this.material = aMaterial;
	}

	/*  --------------------------
		Normal Methods and Actions
	    --------------------------
	 */

	public void fetchMaterial() {
		MaterialGatheringPointBean localMaterialGatheringPointBean = (MaterialGatheringPointBean) this
				.getRequestBean("materialGatheringPointBean");
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _pointIdentifier = localMaterialGatheringPointBean
				.getPointIdentifier();
		String _customerNumber = localCustomerBean.getCustomerNumber();

		try {
			this.material = carcassFacade
					.getMaterialByIdentificationAndPointIdentifierAndCustomerNumber(
							this.identification, _pointIdentifier,
							_customerNumber);
			// Synchronize the atomic properties
			this.setIdentification(this.getMaterial().getIdentification());
			this.setWeight(this.getMaterial().getWeight());
			this.setDeadType(this.getMaterial().getDeadType());
			this.setMaterialGatheringPoint(this.getMaterial()
					.getMaterialGatheringPoint());

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

	public String createMaterial() {
		MaterialGatheringPointBean localMaterialGatheringPointBean = (MaterialGatheringPointBean) this
				.getRequestBean("materialGatheringPointBean");
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _pointIdentifier = localMaterialGatheringPointBean
				.getPointIdentifier();
		String _customerNumber = localCustomerBean.getCustomerNumber();

		try {
			carcassFacade.createMaterial(this.identification, _pointIdentifier,
					_customerNumber, this.weight, this.deadType);
			addGeneralInfoMessage("Material created successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String updateMaterial() {
		MaterialGatheringPointBean localMaterialGatheringPointBean = (MaterialGatheringPointBean) this
				.getRequestBean("materialGatheringPointBean");
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _pointIdentifier = localMaterialGatheringPointBean
				.getPointIdentifier();
		String _customerNumber = localCustomerBean.getCustomerNumber();

		try {
			carcassFacade.updateMaterial(this.identification,
					this.identification, _pointIdentifier, _customerNumber,
					this.weight, this.deadType);
			addGeneralInfoMessage("Material was updated.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String deleteMaterial() {
		MaterialGatheringPointBean localMaterialGatheringPointBean = (MaterialGatheringPointBean) this
				.getRequestBean("materialGatheringPointBean");
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _pointIdentifier = localMaterialGatheringPointBean
				.getPointIdentifier();
		String _customerNumber = localCustomerBean.getCustomerNumber();

		try {
			carcassFacade.deleteMaterial(this.identification, _pointIdentifier,
					_customerNumber);
			addGeneralInfoMessage("Material deleted successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String unassignMaterialGatheringPointFromMaterialMaterialGatheringPoint() {
		MaterialGatheringPointBean localMaterialGatheringPointBean = (MaterialGatheringPointBean) this
				.getRequestBean("materialGatheringPointBean");
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _pointIdentifier = localMaterialGatheringPointBean
				.getPointIdentifier();
		String _customerNumber = localCustomerBean.getCustomerNumber();

		try {
			carcassFacade
					.unassignMaterialGatheringPointFromMaterialMaterialGatheringPoint(
							this.identification, _pointIdentifier,
							_customerNumber);
			addGeneralInfoMessage("Child entity was successfully deleted.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

}

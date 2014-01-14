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
public class MaterialDTOBean extends BeanBase implements Serializable {

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
	private MaterialType materialType;
	private double weight;
	private DeadType deadType;
	private String cowEarNumber;
	private MaterialGatheringPointDTO materialGatheringPoint;
	private MaterialDTO materialDTO;

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

	public MaterialType getMaterialType() {

		return this.materialType;
	}

	public void setMaterialType(MaterialType aMaterialType) {
		this.materialType = aMaterialType;
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

	public String getCowEarNumber() {

		return this.cowEarNumber;
	}

	public void setCowEarNumber(String aCowEarNumber) {
		this.cowEarNumber = aCowEarNumber;
	}

	public MaterialGatheringPointDTO getMaterialGatheringPoint() {

		return this.materialGatheringPoint;
	}

	public void setMaterialGatheringPoint(
			MaterialGatheringPointDTO aMaterialGatheringPoint) {
		this.materialGatheringPoint = aMaterialGatheringPoint;
	}

	public MaterialDTO getMaterialDTO() {

		if (this.materialDTO == null) {
			this.fetchMaterialDTO();
		}

		return this.materialDTO;
	}

	public void setMaterialDTO(MaterialDTO aMaterialDTO) {
		this.materialDTO = aMaterialDTO;
	}

	/*  --------------------------
		Normal Methods and Actions
	    --------------------------
	 */

	public void fetchMaterialDTO() {
		MaterialGatheringPointDTOBean localMaterialGatheringPointDTOBean = (MaterialGatheringPointDTOBean) this
				.getRequestBean("materialGatheringPointDTOBean");
		String _pointIdentifier = localMaterialGatheringPointDTOBean
				.getPointIdentifier();
		String _customerNumber = localMaterialGatheringPointDTOBean
				.getCustomerNumber();

		try {
			this.materialDTO = carcassFacade
					.getMaterialDTOByIdentificationAndPointIdentifierAndCustomerNumber(
							this.identification, _pointIdentifier,
							_customerNumber);
			// Synchronize the atomic properties
			this.setIdentification(this.getMaterialDTO().getIdentification());
			this.setMaterialType(this.getMaterialDTO().getMaterialType());
			this.setWeight(this.getMaterialDTO().getWeight());
			this.setDeadType(this.getMaterialDTO().getDeadType());
			this.setCowEarNumber(this.getMaterialDTO().getCowEarNumber());
			this.setMaterialGatheringPoint(this.getMaterialDTO()
					.getMaterialGatheringPoint());

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

	public String createMaterialDTO() {
		MaterialGatheringPointDTOBean localMaterialGatheringPointDTOBean = (MaterialGatheringPointDTOBean) this
				.getRequestBean("materialGatheringPointDTOBean");
		String _pointIdentifier = localMaterialGatheringPointDTOBean
				.getPointIdentifier();
		String _customerNumber = localMaterialGatheringPointDTOBean
				.getCustomerNumber();

		try {
			carcassFacade.createMaterialDTO(this.identification,
					_pointIdentifier, _customerNumber, this.materialType,
					this.weight, this.deadType, this.cowEarNumber);
			addGeneralInfoMessage("MaterialDTO created successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String updateMaterialDTO() {
		MaterialGatheringPointDTOBean localMaterialGatheringPointDTOBean = (MaterialGatheringPointDTOBean) this
				.getRequestBean("materialGatheringPointDTOBean");
		String _pointIdentifier = localMaterialGatheringPointDTOBean
				.getPointIdentifier();
		String _customerNumber = localMaterialGatheringPointDTOBean
				.getCustomerNumber();

		try {
			carcassFacade.updateMaterialDTO(this.identification,
					this.identification, _pointIdentifier, _customerNumber,
					this.materialType, this.weight, this.deadType,
					this.cowEarNumber);
			addGeneralInfoMessage("MaterialDTO was updated.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String deleteMaterialDTO() {
		MaterialGatheringPointDTOBean localMaterialGatheringPointDTOBean = (MaterialGatheringPointDTOBean) this
				.getRequestBean("materialGatheringPointDTOBean");
		String _pointIdentifier = localMaterialGatheringPointDTOBean
				.getPointIdentifier();
		String _customerNumber = localMaterialGatheringPointDTOBean
				.getCustomerNumber();

		try {
			carcassFacade.deleteMaterialDTO(this.identification,
					_pointIdentifier, _customerNumber);
			addGeneralInfoMessage("MaterialDTO deleted successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String unassignMaterialGatheringPointDTOFromMaterialDTOMaterialGatheringPoint() {
		MaterialGatheringPointDTOBean localMaterialGatheringPointDTOBean = (MaterialGatheringPointDTOBean) this
				.getRequestBean("materialGatheringPointDTOBean");
		String _pointIdentifier = localMaterialGatheringPointDTOBean
				.getPointIdentifier();
		String _customerNumber = localMaterialGatheringPointDTOBean
				.getCustomerNumber();

		try {
			carcassFacade
					.unassignMaterialGatheringPointDTOFromMaterialDTOMaterialGatheringPoint(
							this.identification, _pointIdentifier,
							_customerNumber);
			addGeneralInfoMessage("Child entity was successfully deleted.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

}

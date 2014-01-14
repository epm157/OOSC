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
public class MaterialGatheringPointDTOBean extends BeanBase
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
	private String customerNumber;
	private List<MaterialDTO> gatheredMaterial;
	private MaterialGatheringPointDTO materialGatheringPointDTO;

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

	public String getCustomerNumber() {

		return this.customerNumber;
	}

	public void setCustomerNumber(String aCustomerNumber) {
		this.customerNumber = aCustomerNumber;
	}

	public List<MaterialDTO> getGatheredMaterial() {

		return this.gatheredMaterial;
	}

	public void setGatheredMaterial(List<MaterialDTO> aGatheredMaterial) {
		this.gatheredMaterial = aGatheredMaterial;
	}

	public MaterialGatheringPointDTO getMaterialGatheringPointDTO() {

		if (this.materialGatheringPointDTO == null) {
			this.fetchMaterialGatheringPointDTO();
		}

		return this.materialGatheringPointDTO;
	}

	public void setMaterialGatheringPointDTO(
			MaterialGatheringPointDTO aMaterialGatheringPointDTO) {
		this.materialGatheringPointDTO = aMaterialGatheringPointDTO;
	}

	/*  --------------------------
		Normal Methods and Actions
	    --------------------------
	 */

	public void fetchMaterialGatheringPointDTO() {

		try {
			this.materialGatheringPointDTO = carcassFacade
					.getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber(
							this.pointIdentifier, this.customerNumber);
			// Synchronize the atomic properties
			this.setPointIdentifier(this.getMaterialGatheringPointDTO()
					.getPointIdentifier());
			this.setCustomerNumber(this.getMaterialGatheringPointDTO()
					.getCustomerNumber());
			this.setGatheredMaterial(new ArrayList(this
					.getMaterialGatheringPointDTO().getGatheredMaterial()));

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

	public String createMaterialGatheringPointDTO() {

		try {
			carcassFacade.createMaterialGatheringPointDTO(this.pointIdentifier,
					this.customerNumber);
			addGeneralInfoMessage("MaterialGatheringPointDTO created successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String updateMaterialGatheringPointDTO() {

		try {
			carcassFacade.updateMaterialGatheringPointDTO(this.pointIdentifier,
					this.pointIdentifier, this.customerNumber,
					this.customerNumber);
			addGeneralInfoMessage("MaterialGatheringPointDTO was updated.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String deleteMaterialGatheringPointDTO() {

		try {
			carcassFacade.deleteMaterialGatheringPointDTO(this.pointIdentifier,
					this.customerNumber);
			addGeneralInfoMessage("MaterialGatheringPointDTO deleted successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

}

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
public class PigBean extends BeanBase implements Serializable {

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
	private Pig pig;

	/*  ----------------
		Accessor Methods
	    ----------------
	 */

	public Pig getPig() {

		if (this.pig == null) {
			this.fetchPig();
		}

		return this.pig;
	}

	public void setPig(Pig aPig) {
		this.pig = aPig;
	}

	/*  --------------------------
		Normal Methods and Actions
	    --------------------------
	 */

	public void fetchPig() {
		MaterialBean localMaterialBean = (MaterialBean) this
				.getRequestBean("materialBean");
		MaterialGatheringPointBean localMaterialGatheringPointBean = (MaterialGatheringPointBean) this
				.getRequestBean("materialGatheringPointBean");
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _identification = localMaterialBean.getIdentification();
		String _pointIdentifier = localMaterialGatheringPointBean
				.getPointIdentifier();
		String _customerNumber = localCustomerBean.getCustomerNumber();

		try {
			this.pig = carcassFacade
					.getPigByIdentificationAndPointIdentifierAndCustomerNumber(
							_identification, _pointIdentifier, _customerNumber);
			// Synchronize the atomic properties

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

	public String createPig() {
		MaterialBean localMaterialBean = (MaterialBean) this
				.getRequestBean("materialBean");
		MaterialGatheringPointBean localMaterialGatheringPointBean = (MaterialGatheringPointBean) this
				.getRequestBean("materialGatheringPointBean");
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _identification = localMaterialBean.getIdentification();
		String _pointIdentifier = localMaterialGatheringPointBean
				.getPointIdentifier();
		String _customerNumber = localCustomerBean.getCustomerNumber();
		double _weight = localMaterialBean.getWeight();
		DeadType _deadType = localMaterialBean.getDeadType();

		try {
			carcassFacade.createPig(_identification, _pointIdentifier,
					_customerNumber, _weight, _deadType);
			addGeneralInfoMessage("Pig created successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String updatePig() {
		MaterialBean localMaterialBean = (MaterialBean) this
				.getRequestBean("materialBean");
		MaterialGatheringPointBean localMaterialGatheringPointBean = (MaterialGatheringPointBean) this
				.getRequestBean("materialGatheringPointBean");
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _identification = localMaterialBean.getIdentification();
		String _pointIdentifier = localMaterialGatheringPointBean
				.getPointIdentifier();
		String _customerNumber = localCustomerBean.getCustomerNumber();
		double _weight = localMaterialBean.getWeight();
		DeadType _deadType = localMaterialBean.getDeadType();

		try {
			carcassFacade.updatePig(_identification, _identification,
					_pointIdentifier, _customerNumber, _weight, _deadType);
			addGeneralInfoMessage("Pig was updated.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String deletePig() {
		MaterialBean localMaterialBean = (MaterialBean) this
				.getRequestBean("materialBean");
		MaterialGatheringPointBean localMaterialGatheringPointBean = (MaterialGatheringPointBean) this
				.getRequestBean("materialGatheringPointBean");
		CustomerBean localCustomerBean = (CustomerBean) this
				.getRequestBean("customerBean");
		String _identification = localMaterialBean.getIdentification();
		String _pointIdentifier = localMaterialGatheringPointBean
				.getPointIdentifier();
		String _customerNumber = localCustomerBean.getCustomerNumber();

		try {
			carcassFacade.deletePig(_identification, _pointIdentifier,
					_customerNumber);
			addGeneralInfoMessage("Pig deleted successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String unassignMaterialGatheringPointFromMaterialMaterialGatheringPoint() {

		try {
			carcassFacade
					.unassignMaterialGatheringPointFromMaterialMaterialGatheringPoint(
							_, _, _);
			addGeneralInfoMessage("Child entity was successfully deleted.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

}

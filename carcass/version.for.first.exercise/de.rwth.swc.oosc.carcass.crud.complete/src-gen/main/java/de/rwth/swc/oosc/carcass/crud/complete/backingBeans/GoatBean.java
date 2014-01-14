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
public class GoatBean extends BeanBase implements Serializable {

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
	private Goat goat;

	/*  ----------------
		Accessor Methods
	    ----------------
	 */

	public Goat getGoat() {

		if (this.goat == null) {
			this.fetchGoat();
		}

		return this.goat;
	}

	public void setGoat(Goat aGoat) {
		this.goat = aGoat;
	}

	/*  --------------------------
		Normal Methods and Actions
	    --------------------------
	 */

	public void fetchGoat() {
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
			this.goat = carcassFacade
					.getGoatByIdentificationAndPointIdentifierAndCustomerNumber(
							_identification, _pointIdentifier, _customerNumber);
			// Synchronize the atomic properties

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

	public String createGoat() {
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
			carcassFacade.createGoat(_identification, _pointIdentifier,
					_customerNumber, _weight, _deadType);
			addGeneralInfoMessage("Goat created successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String updateGoat() {
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
			carcassFacade.updateGoat(_identification, _identification,
					_pointIdentifier, _customerNumber, _weight, _deadType);
			addGeneralInfoMessage("Goat was updated.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "";
	}

	public String deleteGoat() {
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
			carcassFacade.deleteGoat(_identification, _pointIdentifier,
					_customerNumber);
			addGeneralInfoMessage("Goat deleted successfully.");
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

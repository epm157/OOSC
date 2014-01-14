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
public class Root extends BeanBase implements Serializable {

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
	private List<Goat> allGoat;
	private List<Pig> allPig;
	private List<ResearchLaboratory> allResearchLaboratory;
	private List<Farmer> allFarmer;
	private List<Customer> allCustomer;

	/*  ----------------
		Accessor Methods
	    ----------------
	 */

	public List<Goat> getAllGoat() {

		if (this.allGoat == null) {
			this.fetchAllGoat();
		}

		return this.allGoat;
	}

	public void setAllGoat(List<Goat> aAllGoat) {
		this.allGoat = aAllGoat;
	}

	public List<Pig> getAllPig() {

		if (this.allPig == null) {
			this.fetchAllPig();
		}

		return this.allPig;
	}

	public void setAllPig(List<Pig> aAllPig) {
		this.allPig = aAllPig;
	}

	public List<ResearchLaboratory> getAllResearchLaboratory() {

		if (this.allResearchLaboratory == null) {
			this.fetchAllResearchLaboratory();
		}

		return this.allResearchLaboratory;
	}

	public void setAllResearchLaboratory(
			List<ResearchLaboratory> aAllResearchLaboratory) {
		this.allResearchLaboratory = aAllResearchLaboratory;
	}

	public List<Farmer> getAllFarmer() {

		if (this.allFarmer == null) {
			this.fetchAllFarmer();
		}

		return this.allFarmer;
	}

	public void setAllFarmer(List<Farmer> aAllFarmer) {
		this.allFarmer = aAllFarmer;
	}

	public List<Customer> getAllCustomer() {

		if (this.allCustomer == null) {
			this.fetchAllCustomer();
		}

		return this.allCustomer;
	}

	public void setAllCustomer(List<Customer> aAllCustomer) {
		this.allCustomer = aAllCustomer;
	}

	/*  --------------------------
		Normal Methods and Actions
	    --------------------------
	 */

	public void fetchAllGoat() {

		try {
			this.allGoat = new ArrayList(carcassFacade.getAllGoat());

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

	public void fetchAllPig() {

		try {
			this.allPig = new ArrayList(carcassFacade.getAllPig());

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

	public void fetchAllResearchLaboratory() {

		try {
			this.allResearchLaboratory = new ArrayList(
					carcassFacade.getAllResearchLaboratory());

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

	public void fetchAllFarmer() {

		try {
			this.allFarmer = new ArrayList(carcassFacade.getAllFarmer());

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

	public void fetchAllCustomer() {

		try {
			this.allCustomer = new ArrayList(carcassFacade.getAllCustomer());

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

}

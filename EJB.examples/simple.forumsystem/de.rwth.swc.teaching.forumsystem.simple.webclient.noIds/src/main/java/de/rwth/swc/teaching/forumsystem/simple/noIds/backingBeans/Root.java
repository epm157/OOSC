package de.rwth.swc.teaching.forumsystem.simple.noIds.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import de.rwth.swc.jsf.common.BeanBase;
import de.rwth.swc.teaching.forumsystem.simple.business.ForumSystemBusinessFacadeNoIdsLocal;

@ManagedBean
@RequestScoped
public class Root extends BeanBase implements Serializable {

	/**
	 * Serialization ID is required by Serializable...
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * ---------- EJB Connections ----------
	 */
	private @EJB
	ForumSystemBusinessFacadeNoIdsLocal forumsystemFacade;

	/*
	 * ---------- PROPERTIES ----------
	 */
	private List<Thread> allThread;

	/*
	 * ---------------- Accessor Methods ----------------
	 */

	public List<Thread> getAllThread() {

		if (this.allThread == null) {
			this.fetchAllThread();
		}

		return this.allThread;
	}

	public void setAllThread(List<Thread> aAllThread) {
		this.allThread = aAllThread;
	}

	/*
	 * -------------------------- Normal Methods and Actions
	 * --------------------------
	 */

	public void fetchAllThread() {

		try {
			this.allThread = new ArrayList(forumsystemFacade.getAllThread());

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

}

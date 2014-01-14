package de.rwth.swc.teaching.forumsystem.simple.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import de.rwth.swc.jsf.common.BeanBase;
import de.rwth.swc.teaching.forumsystem.simple.business.ForumSystemBusinessFacadeLocal;
import de.rwth.swc.teaching.forumsystem.simple.domain.Post;
import de.rwth.swc.teaching.forumsystem.simple.domain.Thread;

@ManagedBean
@RequestScoped
public class ThreadBean extends BeanBase implements Serializable {

	/**
	 * Serialization ID is required by Serializable...
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * ---------- EJB Connections ----------
	 */
	private @EJB
	ForumSystemBusinessFacadeLocal forumsystemFacade;

	/*
	 * ---------- PROPERTIES ----------
	 */
	private int threadId;
	private String threadTitle;
	private boolean locked;
	private List<Post> posts;
	private Thread thread;

	/*
	 * ---------------- Accessor Methods ----------------
	 */

	public int getThreadId() {

		return this.threadId;
	}

	public void setThreadId(int aThreadId) {
		this.threadId = aThreadId;
	}

	public String getThreadTitle() {

		return this.threadTitle;
	}

	public void setThreadTitle(String aThreadTitle) {
		this.threadTitle = aThreadTitle;
	}

	public boolean getLocked() {

		return this.locked;
	}

	public void setLocked(boolean aLocked) {
		this.locked = aLocked;
	}

	public List<Post> getPosts() {

		return this.posts;
	}

	public void setPosts(List<Post> aPosts) {
		this.posts = aPosts;
	}

	public Thread getThread() {

		if (this.thread == null) {
			this.fetchThread();
		}

		return this.thread;
	}

	public void setThread(Thread aThread) {
		this.thread = aThread;
	}

	/*
	 * -------------------------- Normal Methods and Actions
	 * --------------------------
	 */

	public void fetchThread() {

		try {
			this.thread = forumsystemFacade.getThreadByThreadId(this.threadId);
			// Synchronize the atomic properties
			this.setThreadId(this.getThread().getThreadId());
			this.setThreadTitle(this.getThread().getThreadTitle());
			this.setLocked(this.getThread().getLocked());
			this.setPosts(new ArrayList(this.getThread().getPosts()));

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

	public String createThread() {

		try {
			forumsystemFacade.createThread(this.threadId, this.threadTitle,
					this.locked);
			addGeneralInfoMessage("Thread created successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "pretty:ThreadViewPage";
	}

	public String updateThread() {

		try {
			forumsystemFacade.updateThread(this.threadId, this.threadId,
					this.threadTitle, this.locked);
			addGeneralInfoMessage("Thread was updated.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "pretty:ThreadViewPage";
	}

	public String deleteThread() {

		try {
			forumsystemFacade.deleteThread(this.threadId);
			addGeneralInfoMessage("Thread deleted successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "pretty:ThreadListAllPage";
	}

}

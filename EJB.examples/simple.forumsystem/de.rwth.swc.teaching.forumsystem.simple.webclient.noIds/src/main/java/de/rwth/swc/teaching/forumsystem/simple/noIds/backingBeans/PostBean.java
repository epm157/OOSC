package de.rwth.swc.teaching.forumsystem.simple.noIds.backingBeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import de.rwth.swc.jsf.common.BeanBase;
import de.rwth.swc.teaching.forumsystem.simple.business.ForumSystemBusinessFacadeNoIdsLocal;
import de.rwth.swc.teaching.forumsystem.simple.domain.Post;
import de.rwth.swc.teaching.forumsystem.simple.domain.Thread;

@ManagedBean
@RequestScoped
public class PostBean extends BeanBase implements Serializable {

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
	private int postId;
	private String text;
	private Thread thread;
	private Post post;

	/*
	 * ---------------- Accessor Methods ----------------
	 */

	public int getPostId() {

		return this.postId;
	}

	public void setPostId(int aPostId) {
		this.postId = aPostId;
	}

	public String getText() {

		return this.text;
	}

	public void setText(String aText) {
		this.text = aText;
	}

	public Thread getThread() {

		return this.thread;
	}

	public void setThread(Thread aThread) {
		this.thread = aThread;
	}

	public Post getPost() {

		if (this.post == null) {
			this.fetchPost();
		}

		return this.post;
	}

	public void setPost(Post aPost) {
		this.post = aPost;
	}

	/*
	 * -------------------------- Normal Methods and Actions
	 * --------------------------
	 */

	public void fetchPost() {
		ThreadBean localThreadBean = (ThreadBean) this
				.getRequestBean("threadBean");
		int _threadId = localThreadBean.getThreadId();

		try {
			this.post = forumsystemFacade.getPostByPostIdAndThreadId(
					this.postId, _threadId);
			// Synchronize the atomic properties
			this.setPostId(this.getPost().getPostId());
			this.setText(this.getPost().getText());
			this.setThread(this.getPost().getThread());

		} catch (Exception e) {
			addMessageForException(e);
		}

	}

	public String createPost() {
		ThreadBean localThreadBean = (ThreadBean) this
				.getRequestBean("threadBean");
		int _threadId = localThreadBean.getThreadId();

		try {
			forumsystemFacade.createPost(_threadId, this.text);
			addGeneralInfoMessage("Post created successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "pretty:ThreadViewPage";
	}

	public String updatePost() {
		ThreadBean localThreadBean = (ThreadBean) this
				.getRequestBean("threadBean");
		int _threadId = localThreadBean.getThreadId();

		try {
			forumsystemFacade.updatePost(this.postId, _threadId, this.text);
			addGeneralInfoMessage("Post was updated.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "pretty:ThreadViewPage";
	}

	public String deletePost() {
		ThreadBean localThreadBean = (ThreadBean) this
				.getRequestBean("threadBean");
		int _threadId = localThreadBean.getThreadId();

		try {
			forumsystemFacade.deletePost(this.postId, _threadId);
			addGeneralInfoMessage("Post deleted successfully.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "pretty:ThreadViewPage";
	}

	public String unassignThreadFromPostThread() {
		ThreadBean localThreadBean = (ThreadBean) this
				.getRequestBean("threadBean");
		int _threadId = localThreadBean.getThreadId();

		try {
			forumsystemFacade.unassignThreadFromPostThread(this.postId,
					_threadId);
			addGeneralInfoMessage("Child entity was successfully deleted.");
		} catch (Exception e) {
			addMessageForException(e);
		}

		return "pretty:ThreadViewPage";
	}

}

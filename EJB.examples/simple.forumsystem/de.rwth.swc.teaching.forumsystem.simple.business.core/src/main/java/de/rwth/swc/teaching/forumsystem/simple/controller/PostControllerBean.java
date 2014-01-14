package de.rwth.swc.teaching.forumsystem.simple.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwth.swc.teaching.forumsystem.simple.dao.PostDAOLocal;
import de.rwth.swc.teaching.forumsystem.simple.dao.ThreadDAOLocal;
import de.rwth.swc.teaching.forumsystem.simple.domain.EntityFactoryLocal;
import de.rwth.swc.teaching.forumsystem.simple.domain.Post;
import de.rwth.swc.teaching.forumsystem.simple.domain.Thread;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.AlreadyInDBException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.AssignException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotFoundException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotNullableException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.UnassignException;
import de.rwth.swc.teaching.forumsystem.simple.facade.ForumsystemFacadeLocal;

@Stateless
public class PostControllerBean implements PostControllerLocal {
	private @EJB
	ForumsystemFacadeLocal forumsystemFacade;
	private @EJB
	PostDAOLocal postDAO;
	private @EJB
	ThreadDAOLocal threadDAO;

	private @EJB
	EntityFactoryLocal entityFactory;

	/**
	 * TODO Add a comment to this method.
	 */
	public Post getPostByPostIdAndThreadId(int postId, int threadId)
			throws NotFoundException

	{

		// Get the entity out of the database

		return postDAO.getPostByPostIdAndThreadId(postId, threadId);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void createPost(int postId, int threadId, String text)
			throws AlreadyInDBException, NotNullableException, AssignException,
			NotFoundException

	{

		// Check if this entity allready exists in the database
		if (postDAO.existsPost(postId, threadId)) {
			throw new AlreadyInDBException("Post");
		}

		// Create a new entity using the Entity Factory

		Post post = entityFactory.createPost(postId);

		// Set attributes

		post.setText(text);

		// --------------------------------------------------------------------------
		// |     Begin Assigning Containment                                           |
		// --------------------------------------------------------------------------
		// Get the entity out of the database

		Thread thread = forumsystemFacade.getThreadByThreadId(threadId);

		// Set the inverse attribute if not already another one exists
		if (post.getThread() != null)
			throw new AssignException("posts");
		post.setThread(thread);

		// Persist the entity into the database

		postDAO.storePost(post);

		// Add the attribute
		thread.addPostToPosts(post);

		// Persist the entity back into the database

		threadDAO.updateThread(thread);

		// --------------------------------------------------------------------------
		// |     End Assigning Containment                                             |
		// --------------------------------------------------------------------------

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updatePost(int newPostId, int oldPostId, int threadId,
			String text) throws NotFoundException, AlreadyInDBException,
			NotNullableException

	{

		// Only check dupplication if old != new
		if (!(oldPostId == newPostId)) {

			// Check if this entity allready exists in the database
			if (postDAO.existsPost(newPostId, threadId)) {
				throw new AlreadyInDBException("Post");
			}

		}

		// Get the entity out of the database

		Post post = this.getPostByPostIdAndThreadId(oldPostId, threadId);

		// Set the attributes to the new values

		post.setPostId(newPostId);

		post.setText(text);

		// Persist the entity back into the database

		postDAO.updatePost(post);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deletePost(int postId, int threadId) throws NotFoundException,
			UnassignException, NotNullableException

	{

		// Get the entit(y/ies) out of the database

		Post post = this.getPostByPostIdAndThreadId(postId, threadId);

		Thread thread = forumsystemFacade.getThreadByThreadId(threadId);

		// --------------------------------------------------------------------------
		// |     Begin Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------

		// Remove the inverse attribute
		thread.removePostFromPosts(post);

		// Set the attribute null if not set to another attribute
		if (!post.getThread().equals(thread))
			throw new UnassignException("thread");
		post.setThread(null);

		// Persist the entity back into the database

		threadDAO.updateThread(thread);

		// --------------------------------------------------------------------------
		// |      End Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------			

		// Delete the entity
		postDAO.deletePost(post);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void unassignThreadFromPostThread(int postId, int threadId)
			throws NotFoundException, NotNullableException, UnassignException

	{

		//Delegate Unassign

		forumsystemFacade.unassignPostFromThreadPosts(threadId, postId);

	}

}

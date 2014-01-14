package de.rwth.swc.teaching.forumsystem.simple.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwth.swc.teaching.forumsystem.simple.dao.ThreadDAOLocal;
import de.rwth.swc.teaching.forumsystem.simple.domain.EntityFactoryLocal;
import de.rwth.swc.teaching.forumsystem.simple.domain.Post;
import de.rwth.swc.teaching.forumsystem.simple.domain.Thread;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.AlreadyInDBException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotFoundException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotNullableException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.UnassignException;
import de.rwth.swc.teaching.forumsystem.simple.facade.ForumsystemFacadeLocal;

@Stateless
public class ThreadControllerBean implements ThreadControllerLocal {
	private @EJB
	ForumsystemFacadeLocal forumsystemFacade;
	private @EJB
	ThreadDAOLocal threadDAO;

	private @EJB
	EntityFactoryLocal entityFactory;

	/**
	 * TODO Add a comment to this method.
	 */
	public Thread getThreadByThreadId(int threadId) throws NotFoundException

	{

		// Get the entity out of the database

		return threadDAO.getThreadByThreadId(threadId);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void createThread(int threadId, String threadTitle, boolean locked)
			throws AlreadyInDBException, NotNullableException

	{

		// Check if this entity allready exists in the database
		if (threadDAO.existsThread(threadId)) {
			throw new AlreadyInDBException("Thread");
		}

		// Create a new entity using the Entity Factory

		Thread thread = entityFactory.createThread(threadId);

		// Set attributes

		thread.setThreadTitle(threadTitle);

		thread.setLocked(locked);

		// Persist the entity into the database

		threadDAO.storeThread(thread);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updateThread(int newThreadId, int oldThreadId,
			String threadTitle, boolean locked) throws NotFoundException,
			AlreadyInDBException, NotNullableException

	{

		// Only check dupplication if old != new
		if (!(oldThreadId == newThreadId)) {

			// Check if this entity allready exists in the database
			if (threadDAO.existsThread(newThreadId)) {
				throw new AlreadyInDBException("Thread");
			}

		}

		// Get the entity out of the database

		Thread thread = this.getThreadByThreadId(oldThreadId);

		// Set the attributes to the new values

		thread.setThreadId(newThreadId);

		thread.setThreadTitle(threadTitle);

		thread.setLocked(locked);

		// Persist the entity back into the database

		threadDAO.updateThread(thread);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deleteThread(int threadId) throws NotFoundException,
			UnassignException, NotNullableException {
		// Get the entit(y/ies) out of the database
		Thread thread = this.getThreadByThreadId(threadId);

		// --------------------------------------------------------------------------
		// |     Begin Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------

		// Copy the child entities to a new set to avoid updade conflicts 
		// while deleting.			
		Set<Post> _temp_Posts = new HashSet<Post>();
		_temp_Posts.addAll(thread.getPosts());
		for (Post posts : _temp_Posts) {
			forumsystemFacade.unassignPostFromThreadPosts(threadId,
					posts.getPostId());
			Logger.getLogger(this.getClass().getName()).log(
					Level.INFO,
					"Unassigned children Post from Thread - Values: "
							+ threadId + ", " + posts.getPostId());
		}

		// --------------------------------------------------------------------------
		// |      End Unassigning Associated Entities                                 |
		// --------------------------------------------------------------------------			

		// Delete the entity
		threadDAO.deleteThread(thread);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void unassignPostFromThreadPosts(int threadId, int postId)
			throws NotFoundException, NotNullableException, UnassignException

	{

		// Delete contained Entity	

		forumsystemFacade.deletePost(postId, threadId);

	}

}

package de.rwth.swc.teaching.forumsystem.simple.facade;

import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwth.swc.teaching.forumsystem.simple.controller.ModelRootControllerLocal;
import de.rwth.swc.teaching.forumsystem.simple.controller.PostControllerLocal;
import de.rwth.swc.teaching.forumsystem.simple.controller.ThreadControllerLocal;
import de.rwth.swc.teaching.forumsystem.simple.domain.Post;
import de.rwth.swc.teaching.forumsystem.simple.domain.Thread;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.AlreadyInDBException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.AssignException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotFoundException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotNullableException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.UnassignException;

@Stateless
public class ForumsystemFacadeBean implements ForumsystemFacadeLocal {
	private @EJB
	PostControllerLocal postController;
	private @EJB
	ThreadControllerLocal threadController;

	/**
	 * The method delegates the call to the method deleteThread in the
	 * controller ThreadController
	 */
	public void deleteThread(int threadId) throws NotFoundException,
			UnassignException, NotNullableException {
		threadController.deleteThread(threadId);
	}

	/**
	 * The method delegates the call to the method updateThread in the
	 * controller ThreadController
	 */
	public void updateThread(int newThreadId, int oldThreadId,
			String threadTitle, boolean locked) throws NotFoundException,
			AlreadyInDBException, NotNullableException {
		threadController.updateThread(newThreadId, oldThreadId, threadTitle,
				locked);
	}

	private @EJB
	ModelRootControllerLocal modelRootController;

	/**
	 * The method delegates the call to the method getAllThread in the
	 * controller ModelRootController
	 */
	public Set<Thread> getAllThread() throws NotFoundException {
		return modelRootController.getAllThread();
	}

	/**
	 * The method delegates the call to the method deletePost in the controller
	 * PostController
	 */
	public void deletePost(int postId, int threadId) throws NotFoundException,
			UnassignException, NotNullableException {
		postController.deletePost(postId, threadId);
	}

	/**
	 * The method delegates the call to the method updatePost in the controller
	 * PostController
	 */
	public void updatePost(int newPostId, int oldPostId, int threadId,
			String text) throws NotFoundException, AlreadyInDBException,
			NotNullableException

	{

		postController.updatePost(newPostId, oldPostId, threadId, text);

	}

	/**
	 * The method delegates the call to the method createPost in the controller
	 * PostController
	 */
	public void createPost(int postId, int threadId, String text)
			throws AlreadyInDBException, NotNullableException, AssignException,
			NotFoundException

	{

		postController.createPost(postId, threadId, text);

	}

	/**
	 * The method delegates the call to the method getPostByPostIdAndThreadId in
	 * the controller PostController
	 */
	public Post getPostByPostIdAndThreadId(int postId, int threadId)
			throws NotFoundException

	{

		return postController.getPostByPostIdAndThreadId(postId, threadId);

	}

	/**
	 * The method delegates the call to the method createThread in the
	 * controller ThreadController
	 */
	public void createThread(int threadId, String threadTitle, boolean locked)
			throws AlreadyInDBException, NotNullableException

	{

		threadController.createThread(threadId, threadTitle, locked);

	}

	/**
	 * The method delegates the call to the method getThreadByThreadId in the
	 * controller ThreadController
	 */
	public Thread getThreadByThreadId(int threadId) throws NotFoundException

	{

		return threadController.getThreadByThreadId(threadId);

	}

	/**
	 * The method delegates the call to the method unassignPostFromThreadPosts
	 * in the controller ThreadController
	 */
	public void unassignPostFromThreadPosts(int threadId, int postId)
			throws NotFoundException, NotNullableException, UnassignException

	{

		threadController.unassignPostFromThreadPosts(threadId, postId);

	}

	/**
	 * The method delegates the call to the method unassignThreadFromPostThread
	 * in the controller PostController
	 */
	public void unassignThreadFromPostThread(int postId, int threadId)
			throws NotFoundException, NotNullableException, UnassignException

	{

		postController.unassignThreadFromPostThread(postId, threadId);

	}

}

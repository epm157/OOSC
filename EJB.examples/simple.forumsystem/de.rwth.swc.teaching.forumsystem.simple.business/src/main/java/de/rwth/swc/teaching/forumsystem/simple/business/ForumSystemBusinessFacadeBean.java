package de.rwth.swc.teaching.forumsystem.simple.business;

import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.rwth.swc.teaching.forumsystem.simple.domain.Post;
import de.rwth.swc.teaching.forumsystem.simple.domain.Thread;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.AlreadyInDBException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.AssignException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotFoundException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotNullableException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.UnassignException;
import de.rwth.swc.teaching.forumsystem.simple.facade.ForumsystemFacadeLocal;

@Stateless
public class ForumSystemBusinessFacadeBean implements ForumSystemBusinessFacadeLocal {

	private @EJB ForumsystemFacadeLocal forumSystemCore;

	/* (non-Javadoc)
	 * @see de.rwth.swc.teaching.forumsystem.simple.business.ForumSystemBusinessFacadeLocal#getAllThread()
	 */
	public Set<Thread> getAllThread() throws NotFoundException {
		return forumSystemCore.getAllThread();
	}

	/* (non-Javadoc)
	 * @see de.rwth.swc.teaching.forumsystem.simple.business.ForumSystemBusinessFacadeLocal#deletePost(int, int)
	 */
	public void deletePost(int postId, int threadId) throws NotFoundException,
			UnassignException, NotNullableException {
		forumSystemCore.deletePost(postId, threadId);
	}

	/* (non-Javadoc)
	 * @see de.rwth.swc.teaching.forumsystem.simple.business.ForumSystemBusinessFacadeLocal#updatePost(int, int, int, java.lang.String)
	 */
	public void updatePost(int newPostId, int oldPostId, int threadId,
			String text) throws NotFoundException, AlreadyInDBException,
			NotNullableException {
		forumSystemCore.updatePost(newPostId, oldPostId, threadId, text);
	}

	/* (non-Javadoc)
	 * @see de.rwth.swc.teaching.forumsystem.simple.business.ForumSystemBusinessFacadeLocal#createPost(int, int, java.lang.String)
	 */
	public void createPost(int postId, int threadId, String text)
			throws AlreadyInDBException, NotNullableException, AssignException,
			NotFoundException {
		forumSystemCore.createPost(postId, threadId, text);
	}

	/* (non-Javadoc)
	 * @see de.rwth.swc.teaching.forumsystem.simple.business.ForumSystemBusinessFacadeLocal#getPostByPostIdAndThreadId(int, int)
	 */
	public Post getPostByPostIdAndThreadId(int postId, int threadId)
			throws NotFoundException {
		return forumSystemCore.getPostByPostIdAndThreadId(postId, threadId);
	}

	/* (non-Javadoc)
	 * @see de.rwth.swc.teaching.forumsystem.simple.business.ForumSystemBusinessFacadeLocal#deleteThread(int)
	 */
	public void deleteThread(int threadId) throws NotFoundException,
			UnassignException, NotNullableException {
		forumSystemCore.deleteThread(threadId);
	}

	/* (non-Javadoc)
	 * @see de.rwth.swc.teaching.forumsystem.simple.business.ForumSystemBusinessFacadeLocal#updateThread(int, int, java.lang.String, boolean)
	 */
	public void updateThread(int newThreadId, int oldThreadId,
			String threadTitle, boolean locked) throws NotFoundException,
			AlreadyInDBException, NotNullableException {
		forumSystemCore.updateThread(newThreadId, oldThreadId, threadTitle,
				locked);
	}

	/* (non-Javadoc)
	 * @see de.rwth.swc.teaching.forumsystem.simple.business.ForumSystemBusinessFacadeLocal#createThread(int, java.lang.String, boolean)
	 */
	public void createThread(int threadId, String threadTitle, boolean locked)
			throws AlreadyInDBException, NotNullableException {
		forumSystemCore.createThread(threadId, threadTitle, locked);
	}

	/* (non-Javadoc)
	 * @see de.rwth.swc.teaching.forumsystem.simple.business.ForumSystemBusinessFacadeLocal#getThreadByThreadId(int)
	 */
	public Thread getThreadByThreadId(int threadId) throws NotFoundException {
		return forumSystemCore.getThreadByThreadId(threadId);
	}

	/* (non-Javadoc)
	 * @see de.rwth.swc.teaching.forumsystem.simple.business.ForumSystemBusinessFacadeLocal#unassignPostFromThreadPosts(int, int)
	 */
	public void unassignPostFromThreadPosts(int threadId, int postId)
			throws NotFoundException, NotNullableException, UnassignException {
		forumSystemCore.unassignPostFromThreadPosts(threadId, postId);
	}

	/* (non-Javadoc)
	 * @see de.rwth.swc.teaching.forumsystem.simple.business.ForumSystemBusinessFacadeLocal#unassignThreadFromPostThread(int, int)
	 */
	public void unassignThreadFromPostThread(int postId, int threadId)
			throws NotFoundException, NotNullableException, UnassignException {
		forumSystemCore.unassignThreadFromPostThread(postId, threadId);
	}
	
}

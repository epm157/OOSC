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
public class ForumSystemBusinessFacadeNoIdsBean implements
		ForumSystemBusinessFacadeNoIdsLocal {

	private @EJB ForumsystemFacadeLocal forumSystemCore;
	private @EJB SequenceLocal sequence;

	public void createPost(int threadId, String text)
			throws AlreadyInDBException, NotNullableException, AssignException,
			NotFoundException {
		forumSystemCore.createPost(sequence.getId(), threadId, text);
	}

	public void createThread(String threadTitle, boolean locked)
			throws AlreadyInDBException, NotNullableException {
		forumSystemCore.createThread(sequence.getId(), threadTitle, locked);
	}

	public void deletePost(int postId, int threadId) throws NotFoundException,
			UnassignException, NotNullableException {
		forumSystemCore.deletePost(postId, threadId);
	}

	public Set<Thread> getAllThread() throws NotFoundException {
		return forumSystemCore.getAllThread();
	}

	public void updatePost(int postId, int threadId, String text)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException {
		forumSystemCore.updatePost(postId, postId, threadId, text);
	}

	public Post getPostByPostIdAndThreadId(int postId, int threadId)
			throws NotFoundException {
		return forumSystemCore.getPostByPostIdAndThreadId(postId, threadId);
	}

	public void deleteThread(int threadId) throws NotFoundException,
			UnassignException, NotNullableException {
		forumSystemCore.deleteThread(threadId);
	}

	public void updateThread(int threadId, String threadTitle, boolean locked)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException {
		forumSystemCore.updateThread(threadId, threadId, threadTitle, locked);
	}

	public Thread getThreadByThreadId(int threadId) throws NotFoundException {
		return forumSystemCore.getThreadByThreadId(threadId);
	}

	public void unassignPostFromThreadPosts(int threadId, int postId)
			throws NotFoundException, NotNullableException, UnassignException {
		forumSystemCore.unassignPostFromThreadPosts(threadId, postId);
	}

	public void unassignThreadFromPostThread(int postId, int threadId)
			throws NotFoundException, NotNullableException, UnassignException {
		forumSystemCore.unassignThreadFromPostThread(postId, threadId);
	}
}

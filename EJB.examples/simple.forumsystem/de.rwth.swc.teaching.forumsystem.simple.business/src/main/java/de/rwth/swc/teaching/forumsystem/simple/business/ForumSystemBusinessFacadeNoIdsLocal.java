package de.rwth.swc.teaching.forumsystem.simple.business;

import java.util.Set;

import javax.ejb.Local;

import de.rwth.swc.teaching.forumsystem.simple.domain.Post;
import de.rwth.swc.teaching.forumsystem.simple.domain.Thread;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.AlreadyInDBException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.AssignException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotFoundException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotNullableException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.UnassignException;

@Local
public interface ForumSystemBusinessFacadeNoIdsLocal {
	public abstract Set<Thread> getAllThread() throws NotFoundException;

	public abstract void createThread(String threadTitle, boolean locked)
			throws AlreadyInDBException, NotNullableException;

	public abstract void createPost(int threadId, String text)
			throws AlreadyInDBException, NotNullableException, AssignException,
			NotFoundException;

	public abstract void deletePost(int postId, int threadId)
			throws NotFoundException, UnassignException, NotNullableException;

	public abstract void updatePost(int postId, int threadId, String text)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException;

	public abstract Post getPostByPostIdAndThreadId(int postId, int threadId)
			throws NotFoundException;

	public abstract void deleteThread(int threadId) throws NotFoundException,
			UnassignException, NotNullableException;

	public abstract void updateThread(int threadId, String threadTitle,
			boolean locked) throws NotFoundException, AlreadyInDBException,
			NotNullableException;

	public abstract Thread getThreadByThreadId(int threadId)
			throws NotFoundException;

	public abstract void unassignPostFromThreadPosts(int threadId, int postId)
			throws NotFoundException, NotNullableException, UnassignException;

	public abstract void unassignThreadFromPostThread(int postId, int threadId)
			throws NotFoundException, NotNullableException, UnassignException;
}
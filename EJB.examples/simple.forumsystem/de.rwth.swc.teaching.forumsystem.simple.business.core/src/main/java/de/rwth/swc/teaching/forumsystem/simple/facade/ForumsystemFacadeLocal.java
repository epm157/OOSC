package de.rwth.swc.teaching.forumsystem.simple.facade;

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
public interface ForumsystemFacadeLocal {
	Set<Thread> getAllThread() throws NotFoundException;

	void deleteThread(int threadId) throws NotFoundException,
			UnassignException, NotNullableException;

	void updateThread(int newThreadId, int oldThreadId, String threadTitle,
			boolean locked) throws NotFoundException, AlreadyInDBException,
			NotNullableException;

	void createThread(int threadId, String threadTitle, boolean locked)
			throws AlreadyInDBException, NotNullableException;

	Thread getThreadByThreadId(int threadId) throws NotFoundException;

	void deletePost(int postId, int threadId) throws NotFoundException,
			UnassignException, NotNullableException;

	void updatePost(int newPostId, int oldPostId, int threadId, String text)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException;

	void createPost(int postId, int threadId, String text)
			throws AlreadyInDBException, NotNullableException, AssignException,
			NotFoundException;

	Post getPostByPostIdAndThreadId(int postId, int threadId)
			throws NotFoundException;

	void unassignPostFromThreadPosts(int threadId, int postId)
			throws NotFoundException, NotNullableException, UnassignException;

	void unassignThreadFromPostThread(int postId, int threadId)
			throws NotFoundException, NotNullableException, UnassignException;
}

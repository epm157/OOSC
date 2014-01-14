package de.rwth.swc.teaching.forumsystem.simple.controller;

import javax.ejb.Local;

import de.rwth.swc.teaching.forumsystem.simple.domain.Thread;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.AlreadyInDBException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotFoundException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotNullableException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.UnassignException;

@Local
public interface ThreadControllerLocal {

	Thread getThreadByThreadId(int threadId) throws NotFoundException

	;

	void createThread(int threadId, String threadTitle, boolean locked)
			throws AlreadyInDBException, NotNullableException

	;

	void updateThread(int newThreadId, int oldThreadId, String threadTitle,
			boolean locked) throws NotFoundException, AlreadyInDBException,
			NotNullableException

	;

	void deleteThread(int threadId) throws NotFoundException,
			UnassignException, NotNullableException

	;

	void unassignPostFromThreadPosts(int threadId, int postId)
			throws NotFoundException, NotNullableException, UnassignException

	;

}

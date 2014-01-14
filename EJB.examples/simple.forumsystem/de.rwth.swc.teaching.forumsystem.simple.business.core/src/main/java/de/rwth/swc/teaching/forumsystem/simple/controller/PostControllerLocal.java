package de.rwth.swc.teaching.forumsystem.simple.controller;

import javax.ejb.Local;

import de.rwth.swc.teaching.forumsystem.simple.domain.Post;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.AlreadyInDBException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.AssignException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotFoundException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotNullableException;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.UnassignException;

@Local
public interface PostControllerLocal {

	Post getPostByPostIdAndThreadId(int postId, int threadId)
			throws NotFoundException

	;

	void createPost(int postId, int threadId, String text)
			throws AlreadyInDBException, NotNullableException, AssignException,
			NotFoundException

	;

	void updatePost(int newPostId, int oldPostId, int threadId, String text)
			throws NotFoundException, AlreadyInDBException,
			NotNullableException

	;

	void deletePost(int postId, int threadId) throws NotFoundException,
			UnassignException, NotNullableException

	;

	void unassignThreadFromPostThread(int postId, int threadId)
			throws NotFoundException, NotNullableException, UnassignException

	;

}

package de.rwth.swc.teaching.forumsystem.simple.dao;

import javax.ejb.Local;

import de.rwth.swc.teaching.forumsystem.simple.domain.Post;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotFoundException;

@Local
public interface PostDAOLocal {

	void storePost(Post Post);

	void updatePost(Post Post);

	void deletePost(Post Post);

	Post getPostByPostIdAndThreadId(int postId, int threadId)
			throws NotFoundException;

	boolean existsPost(int postId, int threadId);

}

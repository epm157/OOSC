package de.rwth.swc.teaching.forumsystem.simple.domain;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class EntityFactory
 */
@Stateless
public class EntityFactoryBean implements EntityFactoryLocal {

	public Thread createThread(int threadId) {
		Thread result = new Thread();

		result.setThreadId(threadId);

		return result;
	}

	public Post createPost(int postId) {
		Post result = new Post();

		result.setPostId(postId);

		return result;
	}

}

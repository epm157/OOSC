package de.rwth.swc.teaching.forumsystem.simple.domain;

import javax.ejb.Local;

@Local
public interface EntityFactoryLocal {

	Thread createThread(int threadId);

	Post createPost(int postId);

}

package de.rwth.swc.teaching.forumsystem.simple.dao;

import javax.ejb.Local;

import de.rwth.swc.teaching.forumsystem.simple.domain.Thread;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotFoundException;

@Local
public interface ThreadDAOLocal {

	void storeThread(Thread Thread);

	void updateThread(Thread Thread);

	void deleteThread(Thread Thread);

	Thread getThreadByThreadId(int threadId) throws NotFoundException;

	boolean existsThread(int threadId);

}

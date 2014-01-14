package de.rwth.swc.teaching.forumsystem.simple.dao;

import java.util.Set;

import javax.ejb.Local;

import de.rwth.swc.teaching.forumsystem.simple.domain.Thread;

@Local
public interface ModelRootDAOLocal {

	Set<Thread> getAllThread();

}

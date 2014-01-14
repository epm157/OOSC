package de.rwth.swc.teaching.forumsystem.simple.controller;

import java.util.Set;

import javax.ejb.Local;

import de.rwth.swc.teaching.forumsystem.simple.domain.Thread;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotFoundException;

@Local
public interface ModelRootControllerLocal {

	Set<Thread> getAllThread() throws NotFoundException

	;

}

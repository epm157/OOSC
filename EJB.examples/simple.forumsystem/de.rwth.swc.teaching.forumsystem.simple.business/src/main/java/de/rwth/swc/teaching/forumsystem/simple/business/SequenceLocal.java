package de.rwth.swc.teaching.forumsystem.simple.business;

import javax.ejb.Local;

@Local
public interface SequenceLocal {
	public abstract int getId();
}
package de.rwth.swc.teaching.forumsystem.simple.business;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Singleton
public class SequenceBean implements SequenceLocal {
	
	/**
	 * Counter which is increase to get unique ids
	 */
	private long lastId;
	
	/**
	 * Initialize the counter
	 */
	@PostConstruct
	public void initializeCounter() {
		lastId = System.nanoTime();
	}
	
	/**
	 * Returns a unique Id
	 * @see de.rwth.swc.teaching.forumsystem.simple.business.SequenceLocal#getId()
	 */
	public int getId() {
		return (int) lastId++;
	}
}

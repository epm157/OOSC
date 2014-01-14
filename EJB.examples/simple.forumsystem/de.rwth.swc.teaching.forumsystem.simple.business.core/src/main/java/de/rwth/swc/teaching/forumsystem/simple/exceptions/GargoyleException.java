package de.rwth.swc.teaching.forumsystem.simple.exceptions;
public class GargoyleException extends Exception {

	/**
	 * version number
	 */
	private static final long serialVersionUID = 5795956732708733074L;

	public GargoyleException() {
		super();
	}

	public GargoyleException(String message) {
		super(message);
	}
}

package de.rwth.swc.teaching.forumsystem.simple.exceptions;

public class AlreadyInDBException extends GargoyleException {

	/**
	 * version number
	 */
	private static final long serialVersionUID = 2669786603331578748L;

	public AlreadyInDBException() {
		super();
	}

	public AlreadyInDBException(String exceptionCausingObject) {
		super("There already exists a similar " + exceptionCausingObject
				+ " in the database. ()");
	}

	public String getFaultInfo() {
		return this.getClass().getSimpleName();
	}
}

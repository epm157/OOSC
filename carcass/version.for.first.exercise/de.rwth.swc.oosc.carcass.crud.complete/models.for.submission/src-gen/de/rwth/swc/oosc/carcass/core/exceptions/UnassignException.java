package de.rwth.swc.oosc.carcass.core.exceptions;

public class UnassignException extends GargoyleException {

	/**
	 * version number
	 */
	private static final long serialVersionUID = -4812945726334210536L;

	public UnassignException() {
		super();
	}

	public UnassignException(String entity) {
		super("You tried to unassign an element" + entity
				+ "which is not assigned.");
	}

	public String getFaultInfo() {
		return this.getClass().getSimpleName();
	}
}

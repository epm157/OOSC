package de.rwth.swc.oosc.carcass.crud.complete.exceptions;

public class AssignException extends GargoyleException {

	/**
	 * version number
	 */
	private static final long serialVersionUID = 5978341021971508551L;

	public AssignException() {
		super();
	}

	public AssignException(String entity) {
		super("You tried to assign a one to one relationship for " + entity
				+ " which is already assigned.");
	}

	public String getFaultInfo() {
		return this.getClass().getSimpleName();
	}
}

package de.rwth.swc.oosc.carcass.core.exceptions;

public class NotFoundException extends GargoyleException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5450392748529840789L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String type, String parameters) {
		super("The entity of type \"" + type + "\" with parameters: "
				+ parameters + " was not found in the database!");
	}

	public String getFaultInfo() {
		return this.getClass().getSimpleName();
	}
}

package de.rwth.swc.oosc.carcass.common.exceptions;

public class NotNullableException extends GargoyleException {

	/**
	 * version number
	 */
	private static final long serialVersionUID = -2253199491068092486L;

	public NotNullableException() {
		super();
	}

	public NotNullableException(String nulledField) {
		super("" + nulledField + " must not be null or empty.");
	}

	public String getFaultInfo() {
		return this.getClass().getSimpleName();
	}
}

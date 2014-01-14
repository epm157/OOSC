/**
 * Entity implementation class for Entity: CustomerDTO
 *
 * TODO Add a description to the class CustomerDTO
 */
package de.rwth.swc.oosc.carcass.client.dtos;

import de.rwth.swc.oosc.carcass.client.dtos.enums.CustomerType;

public class CustomerDTO {
	private String customerNumber;

	private CustomerType customerType;

	private String customerName;

	private String zip;

	private double lat;

	private double lng;

	public CustomerDTO() {
		super();
	}

	public String getCustomerNumber() {
		return this.customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public CustomerType getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public double getLat() {
		return this.lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return this.lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	/**
	 * Special implementation of the equals method due to some issues with lazy fetching and
	 * creation of entities.
	 * See http://burtbeckwith.com/blog/?p=53 for more details.
	 * {@inheritDoc}
	 **/
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (CustomerDTO.class.isInstance(object)) {
				return

				this.getCustomerNumber() != null && this.getCustomerNumber()
						.equals(((CustomerDTO) object).getCustomerNumber());
		} else {
			return false;
		}
	}

	/**
	 * Special implementation of the hash code method due to some issues with lazy fetching and
	 * creation of entities.
	 * See http://burtbeckwith.com/blog/?p=53 for more details.
	 * {@inheritDoc}
	 **/
	@Override
	public int hashCode() {
		int hash = 1;

		hash = hash
				* 31
				+ (this.getCustomerNumber() == null ? 0 : this
						.getCustomerNumber().hashCode());

		return hash;
	}

}

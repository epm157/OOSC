/**
 * Entity implementation class for Entity: MaterialGatheringPointDTO
 *
 * TODO Add a description to the class MaterialGatheringPointDTO
 */
package de.rwth.swc.oosc.carcass.client.dtos;

import java.util.HashSet;
import java.util.Set;

public class MaterialGatheringPointDTO {

	private String pointIdentifier;

	private String customerNumber;

	private Set<MaterialDTO> gatheredMaterial = new HashSet<MaterialDTO>();
	
	private CustomerDTO customer;

	public MaterialGatheringPointDTO() {
		super();
	}

	public String getPointIdentifier() {
		return this.pointIdentifier;
	}

	public void setPointIdentifier(String pointIdentifier) {
		this.pointIdentifier = pointIdentifier;
	}

	public String getCustomerNumber() {
		return this.customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Set<MaterialDTO> getGatheredMaterial() {
		return this.gatheredMaterial;
	}

	public void addMaterialDTOToGatheredMaterial(MaterialDTO gatheredMaterial) {
		this.gatheredMaterial.add(gatheredMaterial);
	}

	public void clearGatheredMaterial() {
		this.gatheredMaterial.clear();
	}

	public void removeMaterialDTOFromGatheredMaterial(
			MaterialDTO gatheredMaterial) {
		this.gatheredMaterial.remove(gatheredMaterial);
	}

	/**
	 * Special implementation of the equals method due to some issues with lazy
	 * fetching and creation of entities. See http://burtbeckwith.com/blog/?p=53
	 * for more details. {@inheritDoc}
	 **/
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (MaterialGatheringPointDTO.class.isInstance(object)) {
			return (

			this.getPointIdentifier() != null
					&& this.getPointIdentifier().equals(
							((MaterialGatheringPointDTO) object)
									.getPointIdentifier())

					&&

					this.getCustomerNumber() != null && this
					.getCustomerNumber().equals(
							((MaterialGatheringPointDTO) object)
									.getCustomerNumber())

			);
		} else {
			return false;
		}
	}

	/**
	 * Special implementation of the hash code method due to some issues with
	 * lazy fetching and creation of entities. See
	 * http://burtbeckwith.com/blog/?p=53 for more details. {@inheritDoc}
	 **/
	@Override
	public int hashCode() {
		int hash = 1;

		hash = hash
				* 31
				+ (this.getPointIdentifier() == null ? 0 : this
						.getPointIdentifier().hashCode());

		hash = hash
				* 31
				+ (this.getCustomerNumber() == null ? 0 : this
						.getCustomerNumber().hashCode());

		return hash;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

}

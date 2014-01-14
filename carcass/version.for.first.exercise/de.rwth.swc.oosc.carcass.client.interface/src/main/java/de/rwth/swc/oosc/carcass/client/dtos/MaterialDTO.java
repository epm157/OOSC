/**
 * Entity implementation class for Entity: MaterialDTO
 *
 * TODO Add a description to the class MaterialDTO
 */
package de.rwth.swc.oosc.carcass.client.dtos;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import de.rwth.swc.oosc.carcase.common.domain.DeadType;
import de.rwth.swc.oosc.carcass.client.dtos.enums.MaterialType;

public class MaterialDTO {
	private String identification;

	private MaterialType materialType;

	private double weight;

	private DeadType deadType;

	private String cowEarNumber;

	private MaterialGatheringPointDTO materialGatheringPoint;

	public MaterialDTO() {
		super();
	}

	public String getIdentification() {
		return this.identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public MaterialType getMaterialType() {
		return this.materialType;
	}

	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
	}

	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public DeadType getDeadType() {
		return this.deadType;
	}

	public void setDeadType(DeadType deadType) {
		this.deadType = deadType;
	}

	public String getCowEarNumber() {
		return this.cowEarNumber;
	}

	public void setCowEarNumber(String cowEarNumber) {
		this.cowEarNumber = cowEarNumber;
	}

	public MaterialGatheringPointDTO getMaterialGatheringPoint() {
		return this.materialGatheringPoint;
	}

	public void setMaterialGatheringPoint(
			MaterialGatheringPointDTO materialGatheringPoint) {
		this.materialGatheringPoint = materialGatheringPoint;
	}

	public String getPointIdentifier() {
		if (this.getMaterialGatheringPoint() == null)
			return null;
		return this.getMaterialGatheringPoint().getPointIdentifier();
	}

	public String getCustomerNumber() {
		if (this.getMaterialGatheringPoint() == null)
			return null;
		return this.getMaterialGatheringPoint().getCustomerNumber();
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
		if (MaterialDTO.class.isInstance(object)) {
			return (

			this.getIdentification() != null
					&& this.getIdentification().equals(
							((MaterialDTO) object).getIdentification())

					&&

					this.getPointIdentifier() != null
					&& this.getPointIdentifier().equals(
							((MaterialDTO) object).getPointIdentifier())

					&&

					this.getCustomerNumber() != null && this
					.getCustomerNumber().equals(
							((MaterialDTO) object).getCustomerNumber())

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
				+ (this.getIdentification() == null ? 0 : this
						.getIdentification().hashCode());

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

}

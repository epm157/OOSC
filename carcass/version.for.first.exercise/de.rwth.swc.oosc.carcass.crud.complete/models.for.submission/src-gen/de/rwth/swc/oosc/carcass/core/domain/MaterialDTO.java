/**
 * Entity implementation class for Entity: MaterialDTO
 *
 * TODO Add a description to the class MaterialDTO
 */
package de.rwth.swc.oosc.carcass.core.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Inheritance;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.DiscriminatorType;
import javax.persistence.InheritanceType;

import de.rwth.swc.oosc.carcass.core.domain.MaterialGatheringPointDTO;

@Entity
@NamedQueries({

@NamedQuery(name = "getMaterialDTOByIdentificationAndPointIdentifierAndCustomerNumber", query = "SELECT materialDTO FROM MaterialGatheringPointDTO materialGatheringPointDTO JOIN materialGatheringPointDTO.gatheredMaterial materialDTO WHERE materialDTO.identification = :identification and materialGatheringPointDTO.pointIdentifier = :pointIdentifier and materialGatheringPointDTO.customerNumber = :customerNumber")

})
public class MaterialDTO

{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String identification;

	private MaterialType materialType;

	private double weight;

	private DeadType deadType;

	private String cowEarNumber;

	@ManyToOne(cascade = {CascadeType.MERGE})
	private MaterialGatheringPointDTO materialGatheringPoint;

	public MaterialDTO() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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
		if (MaterialDTO.class.isInstance(object)) {
			if (this.getId() != 0 && ((MaterialDTO) object).getId() != 0
					&& this.getId() == ((MaterialDTO) object).getId()) {
				return true;
			} else {
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
			}
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

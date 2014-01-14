/**
 * Entity implementation class for Entity: Material
 *
 * TODO Add a description to the class Material
 */
package de.rwth.swc.oosc.carcass.crud.complete.domain;

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

import de.rwth.swc.oosc.carcass.crud.complete.domain.MaterialGatheringPoint;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "name", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Material")
@NamedQueries({

@NamedQuery(name = "getMaterialByIdentificationAndPointIdentifierAndCustomerNumber", query = "SELECT material FROM Customer customer JOIN customer.materialGatheringPoints materialGatheringPoint JOIN materialGatheringPoint.gatheredMaterial material WHERE material.identification = :identification and materialGatheringPoint.pointIdentifier = :pointIdentifier and customer.customerNumber = :customerNumber")

})
public class Material

{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String identification;

	private double weight;

	private DeadType deadType;

	@ManyToOne(cascade = {CascadeType.MERGE})
	private MaterialGatheringPoint materialGatheringPoint;

	public Material() {
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

	public MaterialGatheringPoint getMaterialGatheringPoint() {
		return this.materialGatheringPoint;
	}

	public void setMaterialGatheringPoint(
			MaterialGatheringPoint materialGatheringPoint) {
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
		if (Material.class.isInstance(object)) {
			if (this.getId() != 0 && ((Material) object).getId() != 0
					&& this.getId() == ((Material) object).getId()) {
				return true;
			} else {
				return (

				this.getIdentification() != null
						&& this.getIdentification().equals(
								((Material) object).getIdentification())

						&&

						this.getPointIdentifier() != null
						&& this.getPointIdentifier().equals(
								((Material) object).getPointIdentifier())

						&&

						this.getCustomerNumber() != null && this
						.getCustomerNumber().equals(
								((Material) object).getCustomerNumber())

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

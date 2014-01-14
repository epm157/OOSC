/**
 * Entity implementation class for Entity: Pig
 *
 * TODO Add a description to the class Pig
 */
package de.rwth.swc.oosc.carcass.material.core.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("Pig")
@NamedQueries({
		@NamedQuery(name = "getAllPig", query = "SELECT pig FROM Pig pig"),
		@NamedQuery(name = "getPigByIdentificationAndPointIdentifierAndCustomerNumber", query = "SELECT thepigs FROM MaterialGatheringPoint materialGatheringPoint JOIN materialGatheringPoint.gatheredMaterial thepigs JOIN materialGatheringPoint.customer customer WHERE TYPE(thepigs) = Pig and thepigs.identification = :identification and materialGatheringPoint.pointIdentifier = :pointIdentifier and customer.customerNumber = :customerNumber") })
public class Pig extends Material

{

	public Pig() {
		super();
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
		if (Pig.class.isInstance(object)) {
			if (this.getId() != 0 && ((Pig) object).getId() != 0
					&& this.getId() == ((Pig) object).getId()) {
				return true;
			} else {
				return (

				this.getIdentification() != null
						&& this.getIdentification().equals(
								((Pig) object).getIdentification())

						&&

						this.getPointIdentifier() != null
						&& this.getPointIdentifier().equals(
								((Pig) object).getPointIdentifier())

						&&

						this.getCustomerNumber() != null && this
						.getCustomerNumber().equals(
								((Pig) object).getCustomerNumber())

				);
			}
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

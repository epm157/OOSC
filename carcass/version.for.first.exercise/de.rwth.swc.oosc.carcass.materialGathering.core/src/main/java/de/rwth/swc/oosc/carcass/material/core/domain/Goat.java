/**
 * Entity implementation class for Entity: Goat
 *
 * TODO Add a description to the class Goat
 */
package de.rwth.swc.oosc.carcass.material.core.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("Goat")
@NamedQueries({
		@NamedQuery(name = "getAllGoat", query = "SELECT goat FROM Goat goat"),
		@NamedQuery(name = "getGoatByIdentificationAndPointIdentifierAndCustomerNumber", query = "SELECT thegoats FROM MaterialGatheringPoint materialGatheringPoint JOIN materialGatheringPoint.gatheredMaterial thegoats JOIN materialGatheringPoint.customer customer WHERE TYPE(thegoats) IN (Goat) and thegoats.identification = :identification and materialGatheringPoint.pointIdentifier = :pointIdentifier and customer.customerNumber = :customerNumber") })
public class Goat extends Material

{

	public Goat() {
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
		if (Goat.class.isInstance(object)) {
			if (this.getId() != 0 && ((Goat) object).getId() != 0
					&& this.getId() == ((Goat) object).getId()) {
				return true;
			} else {
				return (

				this.getIdentification() != null
						&& this.getIdentification().equals(
								((Goat) object).getIdentification())

						&&

						this.getPointIdentifier() != null
						&& this.getPointIdentifier().equals(
								((Goat) object).getPointIdentifier())

						&&

						this.getCustomerNumber() != null && this
						.getCustomerNumber().equals(
								((Goat) object).getCustomerNumber())

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

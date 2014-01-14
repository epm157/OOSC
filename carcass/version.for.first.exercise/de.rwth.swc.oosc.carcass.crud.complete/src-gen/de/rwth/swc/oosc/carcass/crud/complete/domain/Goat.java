/**
 * Entity implementation class for Entity: Goat
 *
 * TODO Add a description to the class Goat
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
@DiscriminatorValue("Goat")
@NamedQueries({

		@NamedQuery(name = "getAllGoat", query = "SELECT goat FROM Goat goat"),
		@NamedQuery(name = "getGoatByIdentificationAndPointIdentifierAndCustomerNumber", query = "SELECT goat FROM Customer customer JOIN customer.materialGatheringPoints materialGatheringPoint JOIN materialGatheringPoint.gatheredMaterial material WHERE goat.identification = :identification and materialGatheringPoint.pointIdentifier = :pointIdentifier and customer.customerNumber = :customerNumber")

})
public class Goat extends Material

{

	public Goat() {
		super();
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

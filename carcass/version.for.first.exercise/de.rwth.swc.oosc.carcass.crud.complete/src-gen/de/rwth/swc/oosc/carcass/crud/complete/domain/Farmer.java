/**
 * Entity implementation class for Entity: Farmer
 *
 * TODO Add a description to the class Farmer
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
@DiscriminatorValue("Farmer")
@NamedQueries({

		@NamedQuery(name = "getAllFarmer", query = "SELECT farmer FROM Farmer farmer"),
		@NamedQuery(name = "getFarmerByCustomerNumber", query = "SELECT farmer FROM Farmer farmer WHERE farmer.customerNumber = :customerNumber")

})
public class Farmer extends Customer

{

	public Farmer() {
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
		if (Farmer.class.isInstance(object)) {
			if (this.getId() != 0 && ((Farmer) object).getId() != 0
					&& this.getId() == ((Farmer) object).getId()) {
				return true;
			} else {
				return (

				this.getCustomerNumber() != null && this.getCustomerNumber()
						.equals(((Farmer) object).getCustomerNumber())

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
				+ (this.getCustomerNumber() == null ? 0 : this
						.getCustomerNumber().hashCode());

		return hash;
	}

}

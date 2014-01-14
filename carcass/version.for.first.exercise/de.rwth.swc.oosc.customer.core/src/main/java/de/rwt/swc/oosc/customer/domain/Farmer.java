/**
 * Entity implementation class for Entity: Farmer
 *
 * TODO Add a description to the class Farmer
 */
package de.rwt.swc.oosc.customer.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

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

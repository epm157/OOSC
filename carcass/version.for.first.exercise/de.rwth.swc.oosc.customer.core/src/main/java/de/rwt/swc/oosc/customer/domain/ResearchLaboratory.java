/**
 * Entity implementation class for Entity: ResearchLaboratory
 *
 * TODO Add a description to the class ResearchLaboratory
 */
package de.rwt.swc.oosc.customer.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("ResearchLaboratory")
@NamedQueries({

		@NamedQuery(name = "getAllResearchLaboratory", query = "SELECT researchLaboratory FROM ResearchLaboratory researchLaboratory"),
		@NamedQuery(name = "getResearchLaboratoryByCustomerNumber", query = "SELECT researchLaboratory FROM ResearchLaboratory researchLaboratory WHERE researchLaboratory.customerNumber = :customerNumber")

})
public class ResearchLaboratory extends Customer

{

	public ResearchLaboratory() {
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
		if (ResearchLaboratory.class.isInstance(object)) {
			if (this.getId() != 0 && ((ResearchLaboratory) object).getId() != 0
					&& this.getId() == ((ResearchLaboratory) object).getId()) {
				return true;
			} else {
				return (

				this.getCustomerNumber() != null && this.getCustomerNumber()
						.equals(((ResearchLaboratory) object)
								.getCustomerNumber())

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

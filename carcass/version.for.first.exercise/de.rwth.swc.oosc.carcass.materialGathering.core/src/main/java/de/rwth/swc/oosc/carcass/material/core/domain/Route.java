package de.rwth.swc.oosc.carcass.material.core.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Route")
@NamedQueries({
		@NamedQuery(name = "getAllRoute", query = "SELECT route FROM Route route") })
		//@NamedQuery(name = "getRouteByIdentification", query = "SELECT theroutes FROM MaterialGatheringPoint materialGatheringPoint JOIN materialGatheringPoint.gatheredMaterial theroutes JOIN materialGatheringPoint.customer customer WHERE TYPE(theroutes) IN (Route) and theroutes.identification = :identification and materialGatheringPoint.pointIdentifier = :pointIdentifier and customer.customerNumber = :customerNumber") })
public class Route extends Material

{
	@OneToMany(cascade={ CascadeType.REMOVE}, mappedBy=("Route"))
	private Set<MaterialGatheringPoint> Points;

	public Route() {
		super();
		Points=new HashSet<MaterialGatheringPoint>();
		
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
		if (Route.class.isInstance(object)) {
			if (this.getId() != 0 && ((Route) object).getId() != 0
					&& this.getIdentification() == ((Route) object).getIdentification()) {
				return true;
			} else {
				return (

				this.getIdentification() != null
						&& this.getIdentification().equals(
								((Route) object).getIdentification())

						&&

						this.getPointIdentifier() != null
						&& this.getPointIdentifier().equals(
								((Route) object).getPointIdentifier())

						&&

						this.getCustomerNumber() != null && this
						.getCustomerNumber().equals(
								((Route) object).getCustomerNumber())

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
	
	public Set<MaterialGatheringPoint> getGatheringPoints()
	{
		return this.Points;
	}

}
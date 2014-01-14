/**
 * Entity implementation class for Entity: MaterialGatheringPoint
 *
 * TODO Add a description to the class MaterialGatheringPoint
 */
package de.rwth.swc.oosc.carcass.material.core.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import de.rwt.swc.oosc.customer.domain.Customer;

@Entity
@NamedQueries({

@NamedQuery(name = "getMaterialGatheringPointByPointIdentifierAndCustomerNumber", query = "SELECT materialGatheringPoint FROM MaterialGatheringPoint materialGatheringPoint JOIN materialGatheringPoint.customer customer WHERE materialGatheringPoint.pointIdentifier = :pointIdentifier and customer.customerNumber = :customerNumber")

})
public class MaterialGatheringPoint

{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String pointIdentifier;

	@OneToMany(cascade = {CascadeType.MERGE}, mappedBy = "materialGatheringPoint")
	private Set<Material> gatheredMaterial = new HashSet<Material>();

	@ManyToOne(cascade = {CascadeType.MERGE})
	private Customer customer;

	public MaterialGatheringPoint() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPointIdentifier() {
		return this.pointIdentifier;
	}

	public void setPointIdentifier(String pointIdentifier) {
		this.pointIdentifier = pointIdentifier;
	}

	public Set<Material> getGatheredMaterial() {
		return this.gatheredMaterial;
	}

	public void addMaterialToGatheredMaterial(Material gatheredMaterial) {
		this.gatheredMaterial.add(gatheredMaterial);
	}

	public void clearGatheredMaterial() {
		this.gatheredMaterial.clear();
	}

	public void removeMaterialFromGatheredMaterial(Material gatheredMaterial) {
		this.gatheredMaterial.remove(gatheredMaterial);
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCustomerNumber() {
		if (this.getCustomer() == null)
			return null;
		return this.getCustomer().getCustomerNumber();
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
		if (MaterialGatheringPoint.class.isInstance(object)) {
			if (this.getId() != 0
					&& ((MaterialGatheringPoint) object).getId() != 0
					&& this.getId() == ((MaterialGatheringPoint) object)
							.getId()) {
				return true;
			} else {
				return (

				this.getPointIdentifier() != null
						&& this.getPointIdentifier().equals(
								((MaterialGatheringPoint) object)
										.getPointIdentifier())

						&&

						this.getCustomerNumber() != null && this
						.getCustomerNumber().equals(
								((MaterialGatheringPoint) object)
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
				+ (this.getPointIdentifier() == null ? 0 : this
						.getPointIdentifier().hashCode());

		hash = hash
				* 31
				+ (this.getCustomerNumber() == null ? 0 : this
						.getCustomerNumber().hashCode());

		return hash;
	}

}

/**
 * Entity implementation class for Entity: Customer
 *
 * TODO Add a description to the class Customer
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
@DiscriminatorValue("Customer")
@NamedQueries({

		@NamedQuery(name = "getAllCustomer", query = "SELECT customer FROM Customer customer"),
		@NamedQuery(name = "getCustomerByCustomerNumber", query = "SELECT customer FROM Customer customer WHERE customer.customerNumber = :customerNumber")

})
public class Customer

{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String customerNumber;

	private String customerName;

	private String zip;

	private double lat;

	private double lng;

	@OneToMany(cascade = {CascadeType.MERGE}, mappedBy = "customer")
	private List<MaterialGatheringPoint> materialGatheringPoints = new LinkedList<MaterialGatheringPoint>();

	public Customer() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerNumber() {
		return this.customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public double getLat() {
		return this.lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return this.lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public List<MaterialGatheringPoint> getMaterialGatheringPoints() {
		return this.materialGatheringPoints;
	}

	public void addMaterialGatheringPointToMaterialGatheringPoints(
			MaterialGatheringPoint materialGatheringPoints) {
		this.materialGatheringPoints.add(materialGatheringPoints);
	}

	public void clearMaterialGatheringPoints() {
		this.materialGatheringPoints.clear();
	}

	public void removeMaterialGatheringPointFromMaterialGatheringPoints(
			MaterialGatheringPoint materialGatheringPoints) {
		this.materialGatheringPoints.remove(materialGatheringPoints);
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
		if (Customer.class.isInstance(object)) {
			if (this.getId() != 0 && ((Customer) object).getId() != 0
					&& this.getId() == ((Customer) object).getId()) {
				return true;
			} else {
				return (

				this.getCustomerNumber() != null && this.getCustomerNumber()
						.equals(((Customer) object).getCustomerNumber())

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

/**
 * Entity implementation class for Entity: MaterialGatheringPointDTO
 *
 * TODO Add a description to the class MaterialGatheringPointDTO
 */
package de.rwth.swc.oosc.carcass.core.domain;

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

import de.rwth.swc.oosc.carcass.core.domain.MaterialDTO;

@Entity
@NamedQueries({

		@NamedQuery(name = "getAllMaterialGatheringPointDTO", query = "SELECT materialGatheringPointDTO FROM MaterialGatheringPointDTO materialGatheringPointDTO"),
		@NamedQuery(name = "getMaterialGatheringPointDTOByPointIdentifierAndCustomerNumber", query = "SELECT materialGatheringPointDTO FROM MaterialGatheringPointDTO materialGatheringPointDTO WHERE materialGatheringPointDTO.pointIdentifier = :pointIdentifier and materialGatheringPointDTO.customerNumber = :customerNumber")

})
public class MaterialGatheringPointDTO

{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String pointIdentifier;

	private String customerNumber;

	@OneToMany(cascade = {CascadeType.MERGE}, mappedBy = "materialGatheringPoint")
	private Set<MaterialDTO> gatheredMaterial = new HashSet<MaterialDTO>();

	public MaterialGatheringPointDTO() {
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

	public String getCustomerNumber() {
		return this.customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Set<MaterialDTO> getGatheredMaterial() {
		return this.gatheredMaterial;
	}

	public void addMaterialDTOToGatheredMaterial(MaterialDTO gatheredMaterial) {
		this.gatheredMaterial.add(gatheredMaterial);
	}

	public void clearGatheredMaterial() {
		this.gatheredMaterial.clear();
	}

	public void removeMaterialDTOFromGatheredMaterial(
			MaterialDTO gatheredMaterial) {
		this.gatheredMaterial.remove(gatheredMaterial);
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
		if (MaterialGatheringPointDTO.class.isInstance(object)) {
			if (this.getId() != 0
					&& ((MaterialGatheringPointDTO) object).getId() != 0
					&& this.getId() == ((MaterialGatheringPointDTO) object)
							.getId()) {
				return true;
			} else {
				return (

				this.getPointIdentifier() != null
						&& this.getPointIdentifier().equals(
								((MaterialGatheringPointDTO) object)
										.getPointIdentifier())

						&&

						this.getCustomerNumber() != null && this
						.getCustomerNumber().equals(
								((MaterialGatheringPointDTO) object)
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

/**
 * Entity implementation class for Entity: RouteDTO
 *
 * TODO Add a description to the class RouteDTO
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

import de.rwth.swc.oosc.carcass.core.domain.MaterialGatheringPointDTO;

@Entity
@NamedQueries({

		@NamedQuery(name = "getAllRouteDTO", query = "SELECT routeDTO FROM RouteDTO routeDTO"),
		@NamedQuery(name = "getRouteDTOByRouteName", query = "SELECT routeDTO FROM RouteDTO routeDTO WHERE routeDTO.routeName = :routeName")

})
public class RouteDTO

{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String routeName;

	@ManyToMany(cascade = {CascadeType.MERGE})
	private Set<MaterialGatheringPointDTO> materialGatheringPoints = new HashSet<MaterialGatheringPointDTO>();

	public RouteDTO() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRouteName() {
		return this.routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public Set<MaterialGatheringPointDTO> getMaterialGatheringPoints() {
		return this.materialGatheringPoints;
	}

	public void addMaterialGatheringPointDTOToMaterialGatheringPoints(
			MaterialGatheringPointDTO materialGatheringPoints) {
		this.materialGatheringPoints.add(materialGatheringPoints);
	}

	public void clearMaterialGatheringPoints() {
		this.materialGatheringPoints.clear();
	}

	public void removeMaterialGatheringPointDTOFromMaterialGatheringPoints(
			MaterialGatheringPointDTO materialGatheringPoints) {
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
		if (RouteDTO.class.isInstance(object)) {
			if (this.getId() != 0 && ((RouteDTO) object).getId() != 0
					&& this.getId() == ((RouteDTO) object).getId()) {
				return true;
			} else {
				return (

				this.getRouteName() != null && this.getRouteName().equals(
						((RouteDTO) object).getRouteName())

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
				+ (this.getRouteName() == null ? 0 : this.getRouteName()
						.hashCode());

		return hash;
	}

}

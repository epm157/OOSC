/**
 * Entity implementation class for Entity: RouteDTO
 *
 * TODO Add a description to the class RouteDTO
 */
package de.rwth.swc.oosc.carcass.client.dtos;

import java.util.HashSet;
import java.util.Set;

public class RouteDTO {
	private String routeName;

	private Set<MaterialGatheringPointDTO> materialGatheringPoints = new HashSet<MaterialGatheringPointDTO>();

	public RouteDTO() {
		super();
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

	public void addMaterialGatheringPointDTO(
			MaterialGatheringPointDTO materialGatheringPoints) {
		this.materialGatheringPoints.add(materialGatheringPoints);
	}

	public void clearMaterialGatheringPoints() {
		this.materialGatheringPoints.clear();
	}

	public void removeMaterialDTOFromMaterialGatheringPoints(
			MaterialGatheringPointDTO materialGatheringPoints) {
		this.materialGatheringPoints.remove(materialGatheringPoints);
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
		if (RouteDTO.class.isInstance(object)) {
			return (

			this.getRouteName() != null && this.getRouteName().equals(
					((RouteDTO) object).getRouteName())

			);
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
				+ (this.getRouteName() == null ? 0 : this.getRouteName()
						.hashCode());

		return hash;
	}

}

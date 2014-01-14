package de.rwth.swc.oosk.tierkoerperverwertung.model;

import java.util.HashSet;
import java.util.Set;

public class Route {
	private int id;
	Set<Wegpunkt> wegpunkte = new HashSet<Wegpunkt>();

	public Route() {
		super();
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Set<Wegpunkt> getWegpunkte() {
		return this.wegpunkte;
	}
	public void setWegpunkte(Set<Wegpunkt> aWegpunkte) {
		this.wegpunkte = aWegpunkte;
	}

	public void wegpunktHinzufuegen(Wegpunkt aAWegpunkt) {
		if (!this.wegpunkte.contains(aAWegpunkt)) {
			this.wegpunkte.add(aAWegpunkt);
			aAWegpunkt.setRoute(this);
		}
	}
}
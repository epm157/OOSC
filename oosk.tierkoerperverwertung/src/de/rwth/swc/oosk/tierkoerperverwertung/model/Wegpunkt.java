package de.rwth.swc.oosk.tierkoerperverwertung.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Wegpunkt {
	public Wegpunkt() {
		super();
		eingesammeltesMaterial = new HashSet<Material>();
	}
	public Wegpunkt(Kunde kunde) {
		super();
		this.setKunde(kunde);
		eingesammeltesMaterial = new HashSet<Material>();
	}
	
	private int id = -1;
	private Date einsammelZeitpunkt;
	de.rwth.swc.oosk.tierkoerperverwertung.model.Route route;
	de.rwth.swc.oosk.tierkoerperverwertung.model.Kunde kunde;
	Set<Material> eingesammeltesMaterial;

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getEinsammelZeitpunkt() {
		return this.einsammelZeitpunkt;
	}
	public void setEinsammelZeitpunkt(Date einsammelZeitpunkt) {
		this.einsammelZeitpunkt = einsammelZeitpunkt;
	}
	public de.rwth.swc.oosk.tierkoerperverwertung.model.Route getRoute() {
		return this.route;
	}
	public void setRoute(de.rwth.swc.oosk.tierkoerperverwertung.model.Route route) {
		if (this.route != route) {
			this.route = route;
			route.wegpunktHinzufuegen(this);
		}
	}
	public de.rwth.swc.oosk.tierkoerperverwertung.model.Kunde getKunde() {
		return this.kunde;
	}
	public void setKunde(de.rwth.swc.oosk.tierkoerperverwertung.model.Kunde kunde) {
		if (this.kunde != kunde) {
			this.kunde = kunde;
			this.kunde.abholungHinzufuegen(this);
		}
	}
	public Set<Material> getEingesammeltesMaterial() {
		return this.eingesammeltesMaterial;
	}
	public void setEingesammeltesMaterial(Set<Material> material) {
		this.eingesammeltesMaterial = material;
	}
	
	public void tierEinsammeln(Material aMaterial) {
		if (!this.eingesammeltesMaterial.contains(aMaterial)) {
			this.eingesammeltesMaterial.add(aMaterial);
			aMaterial.setWegpunkt(this);
		}
	}
}
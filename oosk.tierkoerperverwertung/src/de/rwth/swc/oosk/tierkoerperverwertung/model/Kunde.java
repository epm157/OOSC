package de.rwth.swc.oosk.tierkoerperverwertung.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Kunde {
	private int id;
	private String name;
	private int x;
	private int y;
	Set<Wegpunkt> abholungen = new HashSet<Wegpunkt>();

	public abstract de.rwth.swc.oosk.tierkoerperverwertung.model.VerwertungsKategorie hoechsteKategorie();

	public void abholungHinzufuegen(Wegpunkt aWegpunkt) {
		if (!this.abholungen.contains(aWegpunkt)) {
			this.abholungen.add(aWegpunkt);
			aWegpunkt.setKunde(this);		
		}
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Set<Wegpunkt> getAbholungen() {
		return this.abholungen;
	}
	
	public void setAbholungen(Set<Wegpunkt> aAbholungen) {
		this.abholungen = aAbholungen;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
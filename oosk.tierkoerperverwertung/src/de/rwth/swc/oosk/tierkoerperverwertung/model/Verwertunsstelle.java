package de.rwth.swc.oosk.tierkoerperverwertung.model;

import java.util.HashSet;
import java.util.Set;

public abstract class Verwertunsstelle {
	private int id;
	Set<Verwertungsplan> verwertungsplaene = new HashSet<Verwertungsplan>();

	public abstract Boolean aktzeptiertKategorie(de.rwth.swc.oosk.tierkoerperverwertung.model.VerwertungsKategorie aAVerwertungsKategorie);

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Verwertungsplan> getVerwertungsplaene() {
		return this.verwertungsplaene;
	}

	public void verwertungsplanHinzufuegen(Verwertungsplan aVerwertungsplan) {
		if (!this.verwertungsplaene.contains(aVerwertungsplan)) {
			this.verwertungsplaene.add(aVerwertungsplan);
			aVerwertungsplan.setVerwertungsstelle(this);
		}
	}
}
package de.rwth.swc.oosk.tierkoerperverwertung.model;

import de.rwth.swc.oosk.tierkoerperverwertung.model.pruefergebnisse.VerwertungsplanPruefungsErgebnis;

public class Verwertungsplan {
	public Verwertungsplan(Verwertunsstelle verwertungsstelle) {
		super();
		this.setVerwertungsstelle(verwertungsstelle);
	}

	public static final String _KATEGORIEFEHLER = "Die Verwertungsstelle aktzeptiert die Kategorie des Materials nicht.";
	
	private int id;
	de.rwth.swc.oosk.tierkoerperverwertung.model.Material material;
	de.rwth.swc.oosk.tierkoerperverwertung.model.Verwertunsstelle verwertungsstelle;

	public VerwertungsplanPruefungsErgebnis pruefen() {
		VerwertungsplanPruefungsErgebnis ergebnis = new VerwertungsplanPruefungsErgebnis(this);

		if (!this.verwertungsstelle.aktzeptiertKategorie(this.material.berechneKategorie())) {
			ergebnis.fehlermeldungHinzufuegen(_KATEGORIEFEHLER);
		}

		return ergebnis;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public de.rwth.swc.oosk.tierkoerperverwertung.model.Material getMaterial() {
		return this.material;
	}

	public void setMaterial(de.rwth.swc.oosk.tierkoerperverwertung.model.Material material) {
		if (this.material != material) {
			this.material = material;
			material.setVerwertungsplan(this);
		}
	}

	public de.rwth.swc.oosk.tierkoerperverwertung.model.Verwertunsstelle getVerwertungsstelle() {
		return this.verwertungsstelle;
	}

	public void setVerwertungsstelle(de.rwth.swc.oosk.tierkoerperverwertung.model.Verwertunsstelle verwertungsstelle) {
		if (this.verwertungsstelle != verwertungsstelle) {
			this.verwertungsstelle = verwertungsstelle;
			verwertungsstelle.verwertungsplanHinzufuegen(this);
		}
	}
}
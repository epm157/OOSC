package de.rwth.swc.oosk.tierkoerperverwertung.model;

import de.rwth.swc.oosk.tierkoerperverwertung.model.pruefergebnisse.MaterialFeldPruefungsErgebnis;

public class Material {
	public static final String _WEGPUNKT_FEHLT = "Ein Tier muss immer einem Wegpunkt zugeordnet sein.";
	public static final String _GEWICHT_FEHLT = "Es muss ein Gewicht angegeben werden.";
	public static final String _TODESART_FEHLT = "Es muss eine Todesart angegeben werden.";
	public static final String _KRANKHEIT_FEHLT = "Wenn das Tier an einer Krankheit gestorben ist, muss der Typ der Krankheit angegeben werden.";
	public static final String _KRANKHEIT_ZU_VIEL = "Ein Krankheitstyp darf nur angegeben werden, wenn der Todestyp Krankheit ist.";
	
	private int id = -1;
	private de.rwth.swc.oosk.tierkoerperverwertung.model.Tode todesart;
	private int gewicht;
	private de.rwth.swc.oosk.tierkoerperverwertung.model.Krankheiten krankheit;
	de.rwth.swc.oosk.tierkoerperverwertung.model.Wegpunkt wegpunkt;
	de.rwth.swc.oosk.tierkoerperverwertung.model.Verwertungsplan verwertungsplan;

	public de.rwth.swc.oosk.tierkoerperverwertung.model.VerwertungsKategorie berechneKategorie() {
		// Wenn die hoechste Kategorie die moeglich ist 1 ist, dann kann man
		// sich die Pruefungen schenken.
		if (this.wegpunkt.kunde.hoechsteKategorie() == VerwertungsKategorie.KATEGORIE1) {
			return VerwertungsKategorie.KATEGORIE1;
		}

		// Hoechste Kategorie ist mindestens 2, also muss man gucken, ob es ein
		// natuerlicher oder
		// unnatuerlicher tod war.
		if (this.todesart == Tode.NATUERLICH) {
			// Bei einem natuerlichen Tod, oder wenn das Material nicht Krank
			// war hat es
			// die hoechste Kategorie (3)
			return this.wegpunkt.kunde.hoechsteKategorie();
		} else if (this.todesart == Tode.KRANKHEIT && this.krankheit == Krankheiten.NICHT_UEBERTRAGBAR) {
			// Bei einer nicht uebertragbaren Krankheit hat das Material
			// Kategorie 2.
			// Kunde muss nicht mehr geprueft werdne, denn haette er maximal
			// Kategorie 1, waere er
			// schon oben rausgeflogen.
			return VerwertungsKategorie.KATEGORIE2;
		} else {
			// Material hatte eine uebertragbare Krankheit oder ist unnatuerlich gestorben, also ist es
			// Kategorie 1.
			return VerwertungsKategorie.KATEGORIE1;
		}
	}

	public MaterialFeldPruefungsErgebnis pruefeFelder() {
		MaterialFeldPruefungsErgebnis ergebnis = new MaterialFeldPruefungsErgebnis(this);
		
		if (this.todesart == null) {
			ergebnis.fehlermeldungHinzufuegen(_TODESART_FEHLT);
		}
		
		if (this.todesart == Tode.KRANKHEIT && this.krankheit == null) {
			ergebnis.fehlermeldungHinzufuegen(_KRANKHEIT_FEHLT);
		}
		
		if (this.todesart != Tode.KRANKHEIT && this.krankheit != null) {
			ergebnis.fehlermeldungHinzufuegen(_KRANKHEIT_ZU_VIEL);
		}
		
		if (this.gewicht <= 0) {
			ergebnis.fehlermeldungHinzufuegen(_GEWICHT_FEHLT);
		}
		
		if (this.wegpunkt == null) {
			ergebnis.fehlermeldungHinzufuegen(_WEGPUNKT_FEHLT);
		}
		
		return ergebnis;
	}
	
	public void setVerwertungsplan(Verwertungsplan aVerwertungsplan) {
		if (this.verwertungsplan != aVerwertungsplan) {
			this.verwertungsplan = aVerwertungsplan;
			this.verwertungsplan.setMaterial(this);
		}
	}
	
	public Verwertungsplan getVerwertungsplan() {
		return this.verwertungsplan;
	}

	public de.rwth.swc.oosk.tierkoerperverwertung.model.Tode getTodesart() {
		return this.todesart;
	}

	public void setTodesart(
			de.rwth.swc.oosk.tierkoerperverwertung.model.Tode todesart) {
		this.todesart = todesart;
	}

	public int getGewicht() {
		return this.gewicht;
	}

	public void setGewicht(int gewicht) {
		this.gewicht = gewicht;
	}

	public de.rwth.swc.oosk.tierkoerperverwertung.model.Krankheiten getKrankheit() {
		return this.krankheit;
	}

	public void setKrankheit(
			de.rwth.swc.oosk.tierkoerperverwertung.model.Krankheiten krankheit) {
		this.krankheit = krankheit;
	}

	public de.rwth.swc.oosk.tierkoerperverwertung.model.Wegpunkt getWegpunkt() {
		return this.wegpunkt;
	}

	public void setWegpunkt(de.rwth.swc.oosk.tierkoerperverwertung.model.Wegpunkt wegpunkt) {
		if (this.wegpunkt != wegpunkt) {
			this.wegpunkt = wegpunkt;
			wegpunkt.tierEinsammeln(this);
		}
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
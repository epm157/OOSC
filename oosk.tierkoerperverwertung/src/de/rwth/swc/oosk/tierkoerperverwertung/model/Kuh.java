package de.rwth.swc.oosk.tierkoerperverwertung.model;

import de.rwth.swc.oosk.tierkoerperverwertung.model.pruefergebnisse.MaterialFeldPruefungsErgebnis;

public class Kuh extends de.rwth.swc.oosk.tierkoerperverwertung.model.Material {
	private String ohrnummer;

	public MaterialFeldPruefungsErgebnis pruefeFelder() {
		MaterialFeldPruefungsErgebnis ergebnis = super.pruefeFelder();

		if (this.ohrnummer == null || this.ohrnummer.trim() == "") {
			ergebnis.fehlermeldungHinzufuegen("Bei einer Kuh muss immer eine Ohrnummer angegeben werden.");
		}

		return ergebnis;
	}

	public String getOhrnummer() {
		return this.ohrnummer;
	}

	public void setOhrnummer(String ohrnummer) {
		this.ohrnummer = ohrnummer;
	}
}
package de.rwth.swc.oosk.tierkoerperverwertung.model.pruefergebnisse;

import de.rwth.swc.oosk.tierkoerperverwertung.model.Verwertungsplan;

public class VerwertungsplanPruefungsErgebnis extends de.rwth.swc.oosk.tierkoerperverwertung.model.pruefergebnisse.Pruefergebnis {
	private Verwertungsplan verwertungsplan;

	public VerwertungsplanPruefungsErgebnis(Verwertungsplan aAVerwertungsplan) {
		super();
		this.verwertungsplan = aAVerwertungsplan; 
	}
}
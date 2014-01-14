package de.rwth.swc.oosk.tierkoerperverwertung.model.pruefergebnisse;

import de.rwth.swc.oosk.tierkoerperverwertung.model.Material;

public class MaterialFeldPruefungsErgebnis extends de.rwth.swc.oosk.tierkoerperverwertung.model.pruefergebnisse.Pruefergebnis {
	private Material material;

	public MaterialFeldPruefungsErgebnis(Material aMaterial) {
		this.material = aMaterial;
	}
}
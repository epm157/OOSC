package de.rwth.swc.oosk.tierkoerperverwertung.model;

public class TiermehlErzeugung extends de.rwth.swc.oosk.tierkoerperverwertung.model.Verwertunsstelle {

	public Boolean aktzeptiertKategorie(de.rwth.swc.oosk.tierkoerperverwertung.model.VerwertungsKategorie aAVerwertungsKategorie) {
		return (aAVerwertungsKategorie == VerwertungsKategorie.KATEGORIE3);
	}
}
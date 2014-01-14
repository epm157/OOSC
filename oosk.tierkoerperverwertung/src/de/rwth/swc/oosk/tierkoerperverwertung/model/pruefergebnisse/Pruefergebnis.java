package de.rwth.swc.oosk.tierkoerperverwertung.model.pruefergebnisse;

public class Pruefergebnis {
	private String fehlermeldung;
	
	public Pruefergebnis() {
		super();
		this.fehlermeldung = "";
	}

	public Boolean hatFehler() {
		return (this.fehlermeldung.trim() != "");
	}

	public String gibFehlermeldung() {
		return this.fehlermeldung;
	}

	public void fehlermeldungHinzufuegen(String aAFehlermeldung) {
		this.fehlermeldung += aAFehlermeldung + System.getProperty("line.separator");
	}
}
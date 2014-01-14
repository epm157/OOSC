package de.rwth.swc.oosk.tierkoerperverwertung.tests;

import junit.framework.Assert;

import org.junit.Test;

import de.rwth.swc.oosk.tierkoerperverwertung.model.Verwertungsplan;

public class VerwertungsplanTests extends TierverwertungsTests {
	@Test public void versuchtierDarfKeinTiermehlWerden() {
		versuchsSchwein.setVerwertungsplan(new Verwertungsplan(tiermehl));
		
		Assert.assertTrue(versuchsSchwein.getVerwertungsplan().pruefen().hatFehler());
	}
	
	@Test public void krankesSchweinDarfNieTiermehlWerden() {
		krankesNormalesSchwein.setVerwertungsplan(new Verwertungsplan(tiermehl));
		krankesSchweinegrippenSchwein.setVerwertungsplan(new Verwertungsplan(tiermehl));
		
		Assert.assertTrue(krankesNormalesSchwein.getVerwertungsplan().pruefen().hatFehler());
		Assert.assertTrue(krankesSchweinegrippenSchwein.getVerwertungsplan().pruefen().hatFehler());
	}

	@Test public void alleTiereDuerfenVerbranntWerden() {
		versuchsSchwein.setVerwertungsplan(new Verwertungsplan(verbrennung));
		krankesNormalesSchwein.setVerwertungsplan(new Verwertungsplan(verbrennung));
		krankesSchweinegrippenSchwein.setVerwertungsplan(new Verwertungsplan(verbrennung));
		altesNormalesSchwein.setVerwertungsplan(new Verwertungsplan(verbrennung));
		
		Assert.assertFalse(versuchsSchwein.getVerwertungsplan().pruefen().hatFehler());
		Assert.assertFalse(krankesNormalesSchwein.getVerwertungsplan().pruefen().hatFehler());
		Assert.assertFalse(krankesSchweinegrippenSchwein.getVerwertungsplan().pruefen().hatFehler());
		Assert.assertFalse(altesNormalesSchwein.getVerwertungsplan().pruefen().hatFehler());
	}
}

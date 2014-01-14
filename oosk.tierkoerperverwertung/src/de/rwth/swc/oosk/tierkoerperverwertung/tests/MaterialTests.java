package de.rwth.swc.oosk.tierkoerperverwertung.tests;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.rwth.swc.oosk.tierkoerperverwertung.model.Biene;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Krankheiten;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Kuh;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Material;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Schwein;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Tode;

public class MaterialTests extends TierverwertungsTests {
	Schwein schwein;
	Kuh kuh;
	Biene biene;
	
	@Before public void falschesMaterialInitalisieren() {
		schwein = new Schwein();
		kuh = new Kuh();
		biene = new Biene();
		
		wegpunktBauernhof.tierEinsammeln(schwein);
		wegpunktBauernhof.tierEinsammeln(kuh);
		wegpunktBauernhof.tierEinsammeln(biene);
	}
	
	public void standardPflichtfelderPruefen(Material aMaterial) {
		Assert.assertEquals(0, aMaterial.getGewicht());
		Assert.assertNull(aMaterial.getTodesart());
		Assert.assertNull(aMaterial.getKrankheit());
		Assert.assertNull(aMaterial.getVerwertungsplan());
		
		Assert.assertTrue(aMaterial.pruefeFelder().hatFehler());
		
		aMaterial.setGewicht(20000);
		Assert.assertTrue(aMaterial.pruefeFelder().hatFehler());
		aMaterial.setGewicht(0);
		
		aMaterial.setTodesart(Tode.NATUERLICH);
		Assert.assertTrue(aMaterial.pruefeFelder().hatFehler());
		
		aMaterial.setGewicht(20000);
		Assert.assertFalse(aMaterial.pruefeFelder().hatFehler());
		
		aMaterial.setKrankheit(Krankheiten.NICHT_UEBERTRAGBAR);
		Assert.assertTrue(aMaterial.pruefeFelder().hatFehler());
		aMaterial.setKrankheit(null);

		aMaterial.setTodesart(Tode.KRANKHEIT);
		Assert.assertTrue(aMaterial.pruefeFelder().hatFehler());
		
		aMaterial.setKrankheit(Krankheiten.NICHT_UEBERTRAGBAR);
		Assert.assertFalse(aMaterial.pruefeFelder().hatFehler());
	}
	
	@Test public void schweinePflichtfelder() {
		this.standardPflichtfelderPruefen(schwein);
	}
	
	@Test public void bienenPflichtfelder() {
		this.standardPflichtfelderPruefen(biene);
	}
	
	@Test public void kuhPflichtfelder() {		
		kuh.setOhrnummer("03245sdfg0435");
		this.standardPflichtfelderPruefen(kuh);
		
		kuh.setOhrnummer(null);
		Assert.assertTrue(kuh.pruefeFelder().hatFehler());

		kuh.setOhrnummer("");
		Assert.assertTrue(kuh.pruefeFelder().hatFehler());
		
		kuh.setOhrnummer("Test234");
		Assert.assertFalse(kuh.pruefeFelder().hatFehler());
	}
}

package de.rwth.swc.oosk.tierkoerperverwertung.tests;

import junit.framework.Assert;

import org.junit.Test;

import de.rwth.swc.oosk.tierkoerperverwertung.model.Bauernhof;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Kunde;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Material;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Route;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Schwein;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Verbrennung;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Verwertungsplan;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Verwertunsstelle;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Wegpunkt;

public class Zuordnungstests {
	@Test public void materialZuVerwertungsplan () {
		Material schwein = new Schwein();
		Verwertungsplan plan = new Verwertungsplan(new Verbrennung());

		Assert.assertNull(schwein.getVerwertungsplan());
		Assert.assertNull(plan.getMaterial());
		
		schwein.setVerwertungsplan(plan);
		
		Assert.assertEquals(plan, schwein.getVerwertungsplan());
		Assert.assertEquals(schwein, plan.getMaterial());
	}

	@Test public void verwertungsplanZuMaterial () {
		Material schwein = new Schwein();
		Verwertungsplan plan = new Verwertungsplan(new Verbrennung());

		Assert.assertNull(schwein.getVerwertungsplan());
		Assert.assertNull(plan.getMaterial());
		
		plan.setMaterial(schwein);
		
		Assert.assertEquals(plan, schwein.getVerwertungsplan());
		Assert.assertEquals(schwein, plan.getMaterial());
	}
	
	@Test public void planZuStelle() {
		Verwertunsstelle stelle = new Verbrennung();
		Verwertungsplan plan = new Verwertungsplan(null);
		
		Assert.assertTrue(stelle.getVerwertungsplaene().isEmpty());
		Assert.assertNull(plan.getVerwertungsstelle());
		
		stelle.verwertungsplanHinzufuegen(plan);
		
		Assert.assertEquals(stelle, plan.getVerwertungsstelle());
		Assert.assertTrue(stelle.getVerwertungsplaene().contains(plan));
	}
	
	@Test public void stelleZuPlan() {
		Verwertunsstelle stelle = new Verbrennung();
		Assert.assertTrue(stelle.getVerwertungsplaene().isEmpty());

		Verwertungsplan plan = new Verwertungsplan(stelle);
		
		Assert.assertEquals(stelle, plan.getVerwertungsstelle());
		Assert.assertTrue(stelle.getVerwertungsplaene().contains(plan));
	}
	
	@Test public void wegpunktZuMaterial() {
		Material schwein = new Schwein();
		Wegpunkt wegpunkt = new Wegpunkt(null);
		
		Assert.assertNull(schwein.getWegpunkt());
		Assert.assertTrue(wegpunkt.getEingesammeltesMaterial().isEmpty());
		
		wegpunkt.tierEinsammeln(schwein);
		
		Assert.assertEquals(wegpunkt, schwein.getWegpunkt());
		Assert.assertTrue(wegpunkt.getEingesammeltesMaterial().contains(schwein));
	}

	@Test public void materialZuWegpunkt() {
		Material schwein = new Schwein();
		Wegpunkt wegpunkt = new Wegpunkt(null);
		
		Assert.assertNull(schwein.getWegpunkt());
		Assert.assertTrue(wegpunkt.getEingesammeltesMaterial().isEmpty());
		
		schwein.setWegpunkt(wegpunkt);
		
		Assert.assertEquals(wegpunkt, schwein.getWegpunkt());
		Assert.assertTrue(wegpunkt.getEingesammeltesMaterial().contains(schwein));
	}

	@Test public void wegpunktZuKunden() {
		Wegpunkt wegpunkt = new Wegpunkt(null);
		Kunde bauernhof = new Bauernhof();
		
		Assert.assertNull(wegpunkt.getKunde());
		Assert.assertTrue(bauernhof.getAbholungen().isEmpty());
		
		wegpunkt.setKunde(bauernhof);
		
		Assert.assertEquals(bauernhof, wegpunkt.getKunde());
		Assert.assertTrue(bauernhof.getAbholungen().contains(wegpunkt));
	}

	@Test public void kundenZuWegpunkt() {
		Wegpunkt wegpunkt = new Wegpunkt(null);
		Kunde bauernhof = new Bauernhof();
		
		Assert.assertNull(wegpunkt.getKunde());
		Assert.assertTrue(bauernhof.getAbholungen().isEmpty());
		
		bauernhof.abholungHinzufuegen(wegpunkt);
		
		Assert.assertEquals(bauernhof, wegpunkt.getKunde());
		Assert.assertTrue(bauernhof.getAbholungen().contains(wegpunkt));
	}

	@Test public void wegpunktZuRoute() {
		Wegpunkt wegpunkt = new Wegpunkt(null);
		Route route = new Route();
		
		Assert.assertNull(wegpunkt.getRoute());
		Assert.assertTrue(route.getWegpunkte().isEmpty());
		
		wegpunkt.setRoute(route);
		
		Assert.assertEquals(route, wegpunkt.getRoute());
		Assert.assertTrue(route.getWegpunkte().contains(wegpunkt));
	}

	@Test public void routeZuWegpunkt() {
		Wegpunkt wegpunkt = new Wegpunkt(null);
		Route route = new Route();
		
		Assert.assertNull(wegpunkt.getRoute());
		Assert.assertTrue(route.getWegpunkte().isEmpty());
		
		route.wegpunktHinzufuegen(wegpunkt);
		
		Assert.assertEquals(route, wegpunkt.getRoute());
		Assert.assertTrue(route.getWegpunkte().contains(wegpunkt));
	}
}

package de.rwth.swc.oosk.tierkoerperverwertung.tests;

import junit.framework.Assert;

import org.hibernate.Session;
import org.junit.Test;

import de.rwth.swc.oosk.tierkoerperverwertung.model.Bauernhof;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Biene;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Kuh;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Kunde;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Material;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Route;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Schwein;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Wegpunkt;
import de.rwth.swc.oosk.tierkoerperverwertung.util.HibernateUtil;

public class HibernateTest {
	private static void speichern(Object something) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(something);
		session.getTransaction().commit();
	}
	
	private static void loeschen(Object something) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(something);
        session.getTransaction().commit();
	}
	
	private static Object laden(int id, Class theclass) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Object object = session.get(theclass, id);
        session.getTransaction().commit();
        
        return object;		
	}

	private static Kuh kuhLaden(int id) {
		return (Kuh) laden(id, Kuh.class);
	}

	@Test public void materialCRUD() {
		// Kuh erzeugen und Speichern
        Kuh kuh = new Kuh();
        
        kuh.setGewicht(20000);
        kuh.setOhrnummer("08154711");

        speichern(kuh);
        
        // Kuh aus der Datenbank laden und gucken ob noch alles gleich ist
        int kuhId = kuh.getId();

        Kuh neueKuh = kuhLaden(kuhId);
        
        Assert.assertEquals(kuh.getGewicht(), neueKuh.getGewicht());
        Assert.assertEquals(kuh.getOhrnummer(), neueKuh.getOhrnummer());

        // Gewicht aendern, wegschreiben und gucken ob alles gut gegangen ist
        neueKuh.setGewicht(21000);
        
        speichern(neueKuh);
        
        Kuh neuereKuh = kuhLaden(kuhId);
        
        Assert.assertEquals(21000, neuereKuh.getGewicht());
        Assert.assertFalse(kuh.getGewicht() == neuereKuh.getGewicht());
        Assert.assertEquals(kuh.getOhrnummer(), neuereKuh.getOhrnummer());

        // Und wieder loeschen
        loeschen(neuereKuh);
        
        Kuh neuesteKuh = kuhLaden(kuhId);
        
        Assert.assertNull(neuesteKuh);

        HibernateUtil.getSessionFactory().close();
	}
	
	@Test public void materialMitWegpunkt() {
		Kuh kuh = new Kuh();
		Schwein schwein = new Schwein();
		Wegpunkt wegpunkt = new Wegpunkt();
				
		kuh.setOhrnummer("08154711");
		schwein.setGewicht(12000);
		
		speichern(kuh);
		
		int kuhId = kuh.getId();
		
		kuh = kuhLaden(kuhId);

		wegpunkt.tierEinsammeln(kuh);
		wegpunkt.tierEinsammeln(schwein);

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(wegpunkt);
		session.getTransaction().commit();
				
		int schweinId = schwein.getId(); // Schweine ID ist jetzt erst da, weil es vorher noch nicht geschrieben wurde
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Wegpunkt neuerWegpunkt = (Wegpunkt) session.get(Wegpunkt.class, wegpunkt.getId());		
		
		for (Material material : neuerWegpunkt.getEingesammeltesMaterial()) {
			Assert.assertTrue(material.getId() == schweinId || material.getId() == kuhId); // entweder Kuh- oder Schweine-id 
		}
		
		session.getTransaction().commit();
		
		loeschen(neuerWegpunkt);
	}
	
	@Test public void wegpunktMitKunde() {
		Wegpunkt wegpunkt1 = new Wegpunkt();
		Wegpunkt wegpunkt2 = new Wegpunkt();
		Kunde kunde = new Bauernhof();
		
		kunde.setName("Marius Mustermann");
		kunde.abholungHinzufuegen(wegpunkt1);
		kunde.abholungHinzufuegen(wegpunkt2);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(kunde);
		session.getTransaction().commit();
		
		Assert.assertNotNull(wegpunkt1.getId());
		Assert.assertNotNull(wegpunkt2.getId());
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Kunde neuerKunde = (Kunde) session.get(Kunde.class, kunde.getId());
		session.getTransaction().commit();
		
		Assert.assertEquals(neuerKunde.getName(), kunde.getName());
		
		for (Wegpunkt wegpunkt : neuerKunde.getAbholungen()) {
			Assert.assertTrue(wegpunkt.getId() == wegpunkt1.getId() || wegpunkt.getId() == wegpunkt2.getId());
		}
		
		loeschen(kunde);
	}
	
	@Test public void routeMitWegpunkten() {
		Wegpunkt wegpunkt1 = new Wegpunkt();
		Wegpunkt wegpunkt2 = new Wegpunkt();
		Route route = new Route();
		
		route.wegpunktHinzufuegen(wegpunkt1);
		route.wegpunktHinzufuegen(wegpunkt2);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(route);
		session.getTransaction().commit();
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Route neueRoute = (Route) session.get(Route.class, route.getId());
		session.getTransaction().commit();
		
		for (Wegpunkt wegpunkt : neueRoute.getWegpunkte()) {
			Assert.assertTrue(wegpunkt.getId() == wegpunkt1.getId() || wegpunkt.getId() == wegpunkt2.getId());
		}
		
		loeschen(route);
	}
	
	@Test public void alles() {
		Wegpunkt wegpunkt1 = new Wegpunkt();
		Wegpunkt wegpunkt2 = new Wegpunkt();
		Route route = new Route();
		Kunde kunde = new Bauernhof();
		Kuh kuh = new Kuh();
		Schwein schwein = new Schwein();

		kunde.setName("Marius Mustermann");
		speichern(kunde);

		kuh.setOhrnummer("08154711");
		schwein.setGewicht(12000);

		wegpunkt1.tierEinsammeln(kuh);
		wegpunkt2.tierEinsammeln(schwein);
		
		route.wegpunktHinzufuegen(wegpunkt1);
		route.wegpunktHinzufuegen(wegpunkt2);
				
		kunde.abholungHinzufuegen(wegpunkt1);
		kunde.abholungHinzufuegen(wegpunkt2);
		
		speichern(route);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Kunde neuerKunde = (Kunde) session.get(Kunde.class, kunde.getId());
		Route neueRoute = (Route) session.get(Route.class, route.getId());
		session.getTransaction().commit();
		
		for (Wegpunkt wegpunkt : neuerKunde.getAbholungen()) {
			for (Material material : wegpunkt.getEingesammeltesMaterial()) {
				Assert.assertTrue(material.getId() == kuh.getId() || material.getId() == schwein.getId());
			}
		}
		for (Wegpunkt wegpunkt : neueRoute.getWegpunkte()) {
			for (Material material : wegpunkt.getEingesammeltesMaterial()) {
				Assert.assertTrue(material.getId() == kuh.getId() || material.getId() == schwein.getId());
			}
		}
	}
}

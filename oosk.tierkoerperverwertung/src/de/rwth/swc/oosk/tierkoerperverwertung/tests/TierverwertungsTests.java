package de.rwth.swc.oosk.tierkoerperverwertung.tests;

import java.util.Date;

import org.junit.Before;

import de.rwth.swc.oosk.tierkoerperverwertung.model.Bauernhof;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Krankheiten;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Kunde;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Material;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Route;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Schwein;
import de.rwth.swc.oosk.tierkoerperverwertung.model.TiermehlErzeugung;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Tode;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Verbrennung;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Versuchslabor;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Verwertunsstelle;
import de.rwth.swc.oosk.tierkoerperverwertung.model.Wegpunkt;

public class TierverwertungsTests {
	Kunde versuchslabor;
	Kunde bauernhof;
	Route route;
	Wegpunkt wegpunktVersuchslabor;
	Wegpunkt wegpunktBauernhof;
	Material versuchsSchwein;
	Material krankesNormalesSchwein;
	Material krankesSchweinegrippenSchwein;
	Material altesNormalesSchwein;
	
	Verwertunsstelle verbrennung;
	Verwertunsstelle tiermehl;

	@Before public void umgebungInitalisieren() {
		versuchslabor = new Versuchslabor();
		bauernhof = new Bauernhof();
		route = new Route();
		wegpunktVersuchslabor = new Wegpunkt(versuchslabor);
		wegpunktBauernhof = new Wegpunkt(bauernhof);
		verbrennung = new Verbrennung();
		tiermehl = new TiermehlErzeugung();

		versuchsSchwein = new Schwein();
		krankesNormalesSchwein = new Schwein();
		krankesSchweinegrippenSchwein = new Schwein();
		altesNormalesSchwein = new Schwein();
		
		versuchsSchwein.setGewicht(20000);
		versuchsSchwein.setTodesart(Tode.NATUERLICH);

		krankesNormalesSchwein.setGewicht(15000);
		krankesNormalesSchwein.setTodesart(Tode.KRANKHEIT);
		krankesNormalesSchwein.setKrankheit(Krankheiten.NICHT_UEBERTRAGBAR);
		
		krankesSchweinegrippenSchwein.setGewicht(15000);
		krankesSchweinegrippenSchwein.setTodesart(Tode.KRANKHEIT);
		krankesSchweinegrippenSchwein.setKrankheit(Krankheiten.UEBERTRAGBAR);
		
		altesNormalesSchwein.setGewicht(12000);
		altesNormalesSchwein.setTodesart(Tode.NATUERLICH);

		wegpunktVersuchslabor.setEinsammelZeitpunkt(new Date());
		route.wegpunktHinzufuegen(wegpunktVersuchslabor);
		wegpunktVersuchslabor.tierEinsammeln(versuchsSchwein);
		
		wegpunktBauernhof.setEinsammelZeitpunkt(new Date());
		route.wegpunktHinzufuegen(wegpunktBauernhof);
		wegpunktBauernhof.tierEinsammeln(krankesNormalesSchwein);
		wegpunktBauernhof.tierEinsammeln(krankesSchweinegrippenSchwein);
		wegpunktBauernhof.tierEinsammeln(altesNormalesSchwein);
	}
}

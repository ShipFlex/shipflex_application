package main.test.io.github.ShipFlex.shipflex_application;

import main.java.io.github.ShipFlex.shipflex_application.Opties;
import main.java.io.github.ShipFlex.shipflex_application.OptiesInvoer;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OptiesInvoerTest {

    private OptiesInvoer oi;

    @Before
    public void setUp() {
        oi = new OptiesInvoer();
    }

    @Test
    // Deze methode test de methode addEssentieleOpties() in de klasse OptiesInvoer.
    // Het maakt een nieuw Opties object aan, voegt er essentiële opties aan toe met behulp van de methode
    // addEssentieleOpties() en checked vervolgens dat het aantal essentiële opties in het object gelijk is aan 4.
    public void testAddEssentieleOpties() {
        Opties opties = new Opties("Boot", 0);
        oi.addEssentieleOpties(opties);
        assertEquals(4, opties.getEssentieleOpties().size());
    }

    @Test
    // Deze methode test de methode addExtraOpties() in de klasse OptiesInvoer.
    // Het maakt een nieuw Opties object aan, voegt er extra opties aan toe met behulp
    // van de methode addExtraOpties() en checked vervolgens dat het aantal extra opties in het object gelijk is aan 4.
    public void testAddExtraOpties() {
        Opties opties = new Opties("Boot", 0);
        oi.addExtraOpties(opties);
        assertEquals(4, opties.getExtraOpties().size());
    }

    @Test
    // Deze methode test de methode getGeselecteerdeOpties() in de klasse OptiesInvoer.
    // Het haalt de geselecteerde opties op met behulp van de methode getGeselecteerdeOpties()
    // en checked dat de lijst niet null en leeg is.
    public void testGetGeselecteerdeOpties() {
        List<Opties> geselecteerdeOpties = oi.getGeselecteerdeOpties();
        assertNotNull(geselecteerdeOpties);
        assertTrue(geselecteerdeOpties.isEmpty());
    }

    @Test
    // Methode om te verifiëren dat de methode geen exceptions geeft.
    // Deze methode test de methode displayEssentieleOpties() in de klasse OptiesInvoer.
    // Het maakt een nieuw object Opties en roept daarmee de methode displayEssentieleOpties() aan,
    // waarbij wordt gecontroleerd of er geen uitzonderingen worden gegeven.
    public void testDisplayEssentieleOpties() {
        Opties opties = new Opties("Test", 0);
        oi.displayEssentieleOpties(opties);
    }

    @Test
    // Methode om te verifiëren dat de methode geen exceptions geeft.
    public void testDisplayExtraOpties() {
        Opties opties = new Opties("Test", 0);
        oi.displayExtraOpties(opties);
    }
}

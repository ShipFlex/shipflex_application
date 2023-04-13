package main.test.io.github.ShipFlex.shipflex_application;
// Imports
import main.java.io.github.ShipFlex.shipflex_application.Klant;
import main.java.io.github.ShipFlex.shipflex_application.Opties;
import main.java.io.github.ShipFlex.shipflex_application.OptiesInvoer;
import main.java.io.github.ShipFlex.shipflex_application.Offerte;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class OfferteTest {
    //  Deze test controleert of de 'berekenTotalePrijs' functie van de Offerte klasse
    //  correct werkt. De test zorgt ervoor dat het programma de totale prijs van de
    //  geselecteerde opties correct berekent en retourneert.
    @Test
    public void testBerekenTotalePrijs() {
        Klant klant = new Klant("berkay", "Straat 1", "1234", "Den Haag", "Nederland", "test@test.com", "202020", "123");
        OptiesInvoer optiesInvoer = new OptiesInvoer();
        Offerte offerte = new Offerte(klant, optiesInvoer);

        // Arrange
        Opties essentieleOptie1 = new Opties("Optie1", 100);
        Opties essentieleOptie2 = new Opties("Optie2", 200);
        optiesInvoer.getGeselecteerdeOpties().add(essentieleOptie1);
        optiesInvoer.getGeselecteerdeOpties().add(essentieleOptie2);

        // Act
        int totalePrijs = offerte.berekenTotalePrijs();

        // Assert
        assertEquals(300, totalePrijs);
    }

    //hier moeten wij controleren of de offerte correct wordt afgedrukt
    //heb hier beetje hulp nodig

//    @Test
//    public void testPrintOfferte(){}

}
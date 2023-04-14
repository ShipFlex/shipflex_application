package main.test.io.github.ShipFlex.shipflex_application;
// Imports
import main.java.io.github.ShipFlex.shipflex_application.Klant;
import main.java.io.github.ShipFlex.shipflex_application.Opties;
import main.java.io.github.ShipFlex.shipflex_application.OptiesInvoer;
import main.java.io.github.ShipFlex.shipflex_application.Offerte;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;


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

    @Test
    // Deze methode verifieerd of de 'getTotaleGegevens()' methode de totale gegevens correct returned
    public void testGetTotaleGegevens() {
        Klant klant = new Klant("Mr Bean", "Main Street 321", "0101 AZ", "London", "Engeland",
                                "mrbean@gmail.com", "0611111111", "555 1234");
        OptiesInvoer optiesInvoer = new OptiesInvoer();
        Offerte offerte = new Offerte(klant, optiesInvoer);
        String expected = "==== Bedrijfsgegevens ====\n" +
                          "MyCompany \n" +
                          "Voorbeeldstraat 3 \n" +
                          "0101AB \nDen Haag \n" +
                          "MyCompany.org0102 0304 \n" +
                          "\n" +
                          "==== Klantgegevens ====\n" +
                          "Mr Bean\n" +
                          "Main Street 321\n" +
                          "0101 AZ\n" +
                          "London\n" +
                          "Engeland\n" +
                          "mrbean@gmail.com\n" +
                          "0611111111" + klant.getExtraDetails();
        assertEquals(expected, offerte.getTotaleGegevens());
    }    
}


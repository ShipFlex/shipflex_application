// Package
package main.test.io.github.ShipFlex.shipflex_application;

// Imports
import main.java.io.github.ShipFlex.shipflex_application.Bedrijf;
import main.java.io.github.ShipFlex.shipflex_application.Klant;
import main.java.io.github.ShipFlex.shipflex_application.KlantInvoer;
import main.java.io.github.ShipFlex.shipflex_application.Particulier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class KlantInvoerTest {


    /*
     * De eerste test controleert of de functie 'getValidInput' correct werkt
     * wanneer
     * geldige input wordt gegeven. Deze test is bedoeld om ervoor te zorgen dat de
     * invoer van de gebruiker correct wordt opgehaald en opgeslagen.
     */
    @Test
    public void testGetValidInput() {
        KlantInvoer klantInvoer = new KlantInvoer(new Scanner("mr Bean\n"));
        String input = klantInvoer.getValidInput("Voer uw naam in: ");
        assertEquals("mr Bean", input);
    }

    /*
     * De tweede test controleert of de functie 'getValidInput' correct omgaat met
     * ongeldige invoer.
     * Deze test zorgt ervoor dat het programma onjuiste invoer kan verwerken en een
     * foutmelding
     * als reactie op de onjuiste invoer aan de gebruiker kan geven.
     */
    @Test
    public void testGetValidInputWithInvalidInput() {
        KlantInvoer klantInvoer = new KlantInvoer(new Scanner("test1"));
        String prompt = "Voer een waarde in: ";
        String expected = "test";
        String result = klantInvoer.getValidInput(prompt);
        assertNotEquals(expected, result);
    }

    /*
     * De derde test van deze classe controleert of de functie 'getCapitalizedInput'
     * correct werkt en dus de eerste
     */
    @Test
    public void getCapitalizedInput() {
        Scanner scanner = new Scanner("john doe");
        KlantInvoer klantInvoer = new KlantInvoer(scanner);
        String prompt = "Enter your name: ";
        String expected = "John Doe";
        String actual = klantInvoer.getCapitalizedInput(prompt);
        assertEquals(expected, actual);
    }

    /*
     * De vierde test van deze classe controleert of de functie 'getKlantGegevens'
     * correct werkt voor een
     * object van het type Particulier. Deze test zorgt ervoor dat het programma de
     * details (in dit geval
     * dus de klantgegevens) van een Particulier object correct ophaalt en opslaat.
     */
    @Test
    public void testGetKlantGegevensParticulier() {
        KlantInvoer klantInvoer = new KlantInvoer(new Scanner(
            "1\nMr Bean\nMain Street 321\n0101 AZ\nLondon\nEngeland\nmrbean@gmail.com\n0601010101\n12345\n"));

        Klant klant = klantInvoer.getKlantGegevens();
        assertEquals(Particulier.class, klant.getClass());
        assertEquals("Mr Bean", klant.getNaam());
        assertEquals("Main Street 321", klant.getAdres());
        assertEquals("0101 AZ", klant.getPostcode());
        assertEquals("London", klant.getPlaats());
        assertEquals("Engeland", klant.getLand());
        assertEquals("mrbean@gmail.com", klant.getEmailadres());
        assertEquals("0601010101", klant.getTelefoonnummer());
        assertEquals("12345", ((Particulier) klant).getExtraDetails());
    }

    /*
     * De vijfde test controleert of de functie 'getKlantGegevens' correct werkt
     * voor een
     * object van het type Bedrijf. Deze test zorgt ervoor dat het programma de
     * details (in dit geval
     * dus de klantgegevens) van een Bedrijf object correct ophaalt en opslaat.
     */
    @Test
    public void testGetKlantGegevensBedrijf() {
        KlantInvoer ki = new KlantInvoer(new Scanner(
                "2\nMr Bean\nMain Street 321\n0101 AZ\nLondon\nEngeland\n0601010102\nmrbean@gmail.com\n12345\n"));

        Klant klant = ki.getKlantGegevens();
        assertEquals(Bedrijf.class, klant.getClass());
        assertEquals("Mr Bean", klant.getNaam());
        assertEquals("Main Street 321", klant.getAdres());
        assertEquals("0101 AZ", klant.getPostcode());
        assertEquals("London", klant.getPlaats());
        assertEquals("Engeland", klant.getLand());
        assertEquals("mrbean@gmail.com", klant.getEmailadres());
        assertEquals("0601010102", klant.getTelefoonnummer());
        assertEquals("12345", ((Bedrijf) klant).getExtraDetails());
    }
}

package main.test.io.github.ShipFlex.shipflex_application;

import main.java.io.github.ShipFlex.shipflex_application.Opties;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OptiesTest {
    // hier wordt getest of de naam correct is ingesteld
    @Test
    public void testGetNaam() {
        //Arrange
        Opties opties = new Opties("TestOptie", 100);
        // Act
        String naam = opties.getNaam();
        // Assert
        assertEquals("TestOptie", naam);
    }

    // hier wordt getest of de prijs correct is ingesteld
    @Test
    public void testGetPrijs() {

        Opties opties = new Opties("TestOptie", 100);
        // Act
        Integer prijs = opties.getPrijs();
        // Assert
        assertEquals(100, prijs.intValue());
    }

    //  controleren of het toevoegen van essentiële opties correct werkt
    @Test
    public void testAddEssentieleOpties() {
        // Arrange
        Opties opties = new Opties("TestOptie", 100);
        String categorie = "Categorie1";
        String naam = "Optie1";
        Integer prijs = 50;
        // Act
        opties.addEssentieleOpties(categorie, naam, prijs);
        Opties essentieleOptie = opties.getEssentieleOpties().get(categorie).get(0);

        // Assert
        assertNotNull(opties.getEssentieleOpties().get(categorie));
        assertEquals(1, opties.getEssentieleOpties().get(categorie).size());
        assertEquals(naam, essentieleOptie.getNaam());
        assertEquals(prijs.intValue(), essentieleOptie.getPrijs().intValue());
    }

    //  controleren of het toevoegen van extra opties correct werkt
    @Test
    public void testAddExtraOpties() {
        // Arrange

        Opties opties = new Opties("TestOptie", 100);
        String categorie = "Categorie2";
        String naam = "Optie2";
        Integer prijs = 75;
        // Act
        opties.addExtraOpties(categorie, naam, prijs);
        Opties extraOptie = opties.getExtraOpties().get(categorie).get(0);

        // Assert
        assertNotNull(opties.getExtraOpties().get(categorie));
        assertEquals(1, opties.getExtraOpties().get(categorie).size());
        assertEquals(naam, extraOptie.getNaam());
        assertEquals(prijs.intValue(), extraOptie.getPrijs().intValue());
    }
}

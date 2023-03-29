// Package
package main.java.io.github.ShipFlex.shipflex_application;

// Imports
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Offerte {
    private Klant klant;
    private List<Opties> gekozenOpties;

    // Constructor
    public Offerte(Klant klant) {
        this.klant = klant;
        this.gekozenOpties = new ArrayList<>();

    }

    public void addOptie(Opties optie) {
        this.gekozenOpties.add(optie);
    }

    // methode die de uiteindelijk offerte print
    public void printOfferte() {
        System.out.println("\n" + "---. Bedrijfsgegevens .---");
        System.out.println("MyCompany \n" + "Voorbeeldstraat 3 \n" + "0101AB \n" +
                "Den Haag \n" + "MyCompany.org" + "0102 0304 \n");

        System.out.println("\n" + "---. Klantgegevens .---");
        System.out.println(
                klant.getNaam() + "\n" +
                        klant.getAdres() + "\n" +
                        klant.getPostcode() + "\n" +
                        klant.getPlaats() + "\n" +
                        klant.getLand() + "\n" +
                        klant.getEmailadres() + "\n" +
                        klant.getTelefoonnummer());

        if (klant instanceof Bedrijf) {
            Bedrijf bedrijf = (Bedrijf) klant;
            System.out.println(bedrijf.getKvkNummer());
        } else if (klant instanceof Particulier) {
            Particulier particulier = (Particulier) klant;
            System.out.println(particulier.getKlantnummer());
        }
    }
}

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
    private OptiesInvoer optiesInvoer;
    private List<Opties> gekozenOpties;

    // Constructor
    public Offerte(Klant klant, OptiesInvoer optiesInvoer) {
        this.klant = klant;
        this.optiesInvoer = optiesInvoer;
        this.gekozenOpties = new ArrayList<>();

    }

    public void addOptie(Opties optie) {
        this.gekozenOpties.add(optie);
    }

    public int berekenTotalePrijs() {
        int totalePrijs = 0;
        List<Opties> geselecteerdeOpties = optiesInvoer.getGeselecteerdeOpties();
        for (Opties optie : geselecteerdeOpties) {
            totalePrijs += optie.getPrijs();
        }
        return totalePrijs;
    }

    private void printBedrijfsGegevens() {
        System.out.println("\n" + "---. Bedrijfsgegevens .---");
        System.out.println("MyCompany \n" + "Voorbeeldstraat 3 \n" + "0101AB \n" +
                "Den Haag \n" + "MyCompany.org" + "0102 0304 \n");
    }

    // methode die de uiteindelijk offerte print
    public void printTotaleGegevens() {
        printBedrijfsGegevens();

        System.out.println("\n" + "---. Klantgegevens .---");
        System.out.println(
                klant.getNaam() + "\n" +
                        klant.getAdres() + "\n" +
                        klant.getPostcode() + "\n" +
                        klant.getPlaats() + "\n" +
                        klant.getLand() + "\n" +
                        klant.getEmailadres() + "\n" +
                        klant.getTelefoonnummer());
        klant.getExtraDetails();
    }

    public void printOfferte() {
        printTotaleGegevens();

        System.out.println("=============================================================");
        System.out.println("                        ~~ OFFERTE ~~                        ");
        System.out.println("=============================================================");
        System.out.printf("%-22s %7s %8s %12s%n", "Beschrijving", "BTW%", "BTW", "Prijs");
        System.out.println("-------------------------------------------------------------");

        double totalePrijsExclBtw = 0;

        List<Opties> geselecteerdeOpties = optiesInvoer.getGeselecteerdeOpties();
        for (Opties optie : geselecteerdeOpties) {
            if (optie.getPrijs() > 0) {
                double prijsExclBtw = optie.getPrijs() / 1.21;
                String btw = "21%";
                double totalePrijsInclBtw = optie.getPrijs();
    
                System.out.printf("%-22s %7s %8.2f EUR %10.2f EUR%n", optie.getNaam(),btw, prijsExclBtw, totalePrijsInclBtw);
    
                totalePrijsExclBtw += prijsExclBtw;
            }
        }

        double totaleBtw = berekenTotalePrijs() - totalePrijsExclBtw;
        double totalePrijsInclBtw = berekenTotalePrijs();

        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-45s %8.2f EUR%n", "Totaal excl. BTW:", totalePrijsExclBtw);
        System.out.printf("%-45s %8.2f EUR%n", "BTW:", totaleBtw);
        System.out.printf("\n%-45s %8.2f EUR%n", "Totaal incl. BTW:", totalePrijsInclBtw);
        System.out.println("=============================================================");
    }
}

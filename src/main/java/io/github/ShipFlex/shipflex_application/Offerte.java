// Package
package main.java.io.github.ShipFlex.shipflex_application;

// Imports
import java.io.IOException;
import java.io.PrintWriter;
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

    // Getter
    public List<Opties> getGekozenOpties() {
        return gekozenOpties;
    }

    public void addOptie(Opties optie) {
        this.gekozenOpties.add(optie);
    }

    // methode die de bedrijfsgegevens returned als String
    private String getBedrijfsGegevens() {
        String bedrijfsGegevens = "==== Bedrijfsgegevens ====" + "\n"
                + "MyCompany \n" + "Voorbeeldstraat 3 \n" + "0101AB \n" +
                "Den Haag \n" + "MyCompany.org" + "0102 0304 \n";

        return bedrijfsGegevens;
    }

    // methode die alle gegevens returned als String
    private String getTotaleGegevens() {
        String totaleGegevens = getBedrijfsGegevens() +
                "\n" + "==== Klantgegevens ====" + "\n" + klant.getNaam() + "\n" +
                klant.getAdres() + "\n" +
                klant.getPostcode() + "\n" +
                klant.getPlaats() + "\n" +
                klant.getLand() + "\n" +
                klant.getEmailadres() + "\n" +
                klant.getTelefoonnummer();
        klant.getExtraDetails();

        return totaleGegevens;
    }

    public int berekenTotalePrijs() {
        int totalePrijs = 0;
        List<Opties> geselecteerdeOpties = optiesInvoer.getGeselecteerdeOpties();
        for (Opties optie : geselecteerdeOpties) {
            totalePrijs += optie.getPrijs();
        }
        return totalePrijs;
    }

    public void printOfferte(boolean printToFile) {
        // print to console
        printOfferte(new PrintWriter(System.out));

        if (printToFile) {
            try {
                // Maak een PrintWrite object aan om de offerte naar een file te 'writen'
                PrintWriter writer = new PrintWriter("offerte.txt", "UTF-8");

                // print de offerte naar de file
                printOfferte(writer);

                System.out.println("Offerte is succesvol opgeslagen in offerte.txt!");
                // sluit de writer om de changes te saven
                writer.close();
            } catch (IOException e) {
                System.out.println("Er is een fout opgetreden bij het opslaan van de offerte.");
                e.printStackTrace();
            }
        } else {
            // print naar console
            printOfferte(new PrintWriter(System.out));
        }
    }

    void printOfferte(PrintWriter writer) {
        // print de klant- en bedrijfsgegevens
        writer.println(getTotaleGegevens());

        // print de offerte
        writer.println("\n\n=============================================================");
        writer.println("                        ~~ OFFERTE ~~                        ");
        writer.println("=============================================================");
        writer.printf("%-22s %7s %8s %12s%n", "Beschrijving", "BTW%", "BTW", "Prijs");
        writer.println("-------------------------------------------------------------");

        double totalePrijsExclBtw = 0;

        List<Opties> geselecteerdeOpties = optiesInvoer.getGeselecteerdeOpties();
        for (Opties optie : geselecteerdeOpties) {
            if (optie.getPrijs() > 0) {
                double prijsExclBtw = optie.getPrijs() / 1.21;
                String btw = "21%";
                double totalePrijsInclBtw = optie.getPrijs();

                writer.printf("%-22s %7s %8.2f EUR %10.2f EUR%n", optie.getNaam(), btw, prijsExclBtw,
                        totalePrijsInclBtw);

                totalePrijsExclBtw += prijsExclBtw;
            }
        }

        double totaleBtw = berekenTotalePrijs() - totalePrijsExclBtw;
        double totalePrijsInclBtw = berekenTotalePrijs();

        writer.println("-------------------------------------------------------------");
        writer.printf("%-45s %8.2f EUR%n", "Totaal excl. BTW:", totalePrijsExclBtw);
        writer.printf("%-45s %8.2f EUR%n", "BTW:", totaleBtw);
        writer.printf("\n%-45s %8.2f EUR%n", "Totaal incl. BTW:", totalePrijsInclBtw);
        writer.println("=============================================================");
    }
}

// Package
package main.java.io.github.ShipFlex.shipflex_application;

// Imports
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
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

    public void printOfferte(boolean printToFile, String filename) throws IOException {
        try {
            // print naar console
            PrintWriter writer;

            if (printToFile) {
                writer = new PrintWriter(filename + "_" + LocalDate.now() + ".txt");
                
                printOfferte(writer);

                writer.close();
                System.out.println("De offerte is opgeslagen in " + filename + "_" + LocalDate.now() + ".txt\n");
            }
        } catch (IOException e) {
            System.err.println("Fout bij het afdrukken van offerte: " + e.getMessage());
            throw e;
        }
    }

    private void printOfferte(PrintWriter writer) {
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

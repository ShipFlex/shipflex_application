// Package
package main.java.io.github.ShipFlex.shipflex_application;

// Imports
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String args[]) throws IOException {
        String outputFile = "B:/Java/shipflex_application/output_offerte.txt";
        KlantInvoer klantInvoer = new KlantInvoer();
        Klant klant =  klantInvoer.getKlantGegevens();

        OptiesInvoer oi = new OptiesInvoer();
        
        Opties op = oi.getOpties();
        
        oi.displayOpties(op);
        oi.getGeselecteerdeOpties(op);

        System.out.println("");
        Offerte offerte = new Offerte(klant);
        offerte.printOfferte();

    
        // offerte.printOfferteToFile(outputFile);
    }
}

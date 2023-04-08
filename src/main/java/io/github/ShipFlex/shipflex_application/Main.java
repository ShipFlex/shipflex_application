// Package
package main.java.io.github.ShipFlex.shipflex_application;

// Imports
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String args[]) throws IOException {
        
        KlantInvoer klantInvoer = new KlantInvoer();
        Klant klant = klantInvoer.getKlantGegevens();

        OptiesInvoer oi = new OptiesInvoer();
        Opties op = oi.getOpties();

        oi.displayEssentieleOpties(op);
        oi.optiesJSON(op);

        System.out.println("");

        Offerte of = new Offerte(klant, oi);
        of.printOfferte();
    }
}

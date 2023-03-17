package main.java.io.github.ShipFlex.shipflex_application;

import java.io.IOException;

class Main {
    public static void main(String args[]) throws IOException {

    Klant klant = new KlantInvoer().getKlantGegevens();
    Offerte offerte = new Offerte(klant);
    offerte.printOfferte();
    offerte.printOfferteToFile("B:/Java/shipflex/output_offerte.txt");
    }
}

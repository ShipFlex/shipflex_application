package main.java.io.github.ShipFlex.shipflex_application;

import java.io.IOException;

class Main {
    public static void main(String args[]) throws IOException {

    Klant klant = new KlantInvoer().getKlantGegevens();
    Schip schip = new KlantInvoer().getSchipGegevens();
    EOpties romp= new KlantInvoer().getEOpties();
    Offerte offerte = new Offerte(klant,schip,romp);
    offerte.printOfferte();
    offerte.printOfferteToFile("B:/Java/shipflex/output_offerte.txt");
    }
}

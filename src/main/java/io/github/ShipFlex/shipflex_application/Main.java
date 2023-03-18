package main.java.io.github.ShipFlex.shipflex_application;

import java.io.IOException;

class Main {
    public static void main(String args[]) throws IOException {
    KlantInvoer klantInvoer = new KlantInvoer();
    Klant klant =  klantInvoer.getKlantGegevens();
    Schip schip =   klantInvoer.getSchipGegevens();
    EOpties eOpties = klantInvoer.getEOpties();
    Offerte offerte = new Offerte(klant,schip,eOpties);
    offerte.printOfferte();
    offerte.printOfferteToFile("B:/Java/shipflex/output_offerte.txt");
    }
}

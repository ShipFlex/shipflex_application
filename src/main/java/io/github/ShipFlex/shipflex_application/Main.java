package main.java.io.github.ShipFlex.shipflex_application;

import java.io.IOException;

class Main {
    public static void main(String args[]) throws IOException {
        String outputFile = "B:/Java/shipflex_application/output_offerte.txt";
        KlantInvoer klantInvoer = new KlantInvoer();
        Klant klant =  klantInvoer.getKlantGegevens();
        Schip schip =   klantInvoer.getSchipGegevens();
        EOpties eOpties = klantInvoer.getEOpties();
        System.out.println("");
        ExtraOpties extraOpties = klantInvoer.getExtraOpties();
        Offerte offerte = new Offerte(klant,schip,eOpties,extraOpties);
        offerte.printOfferte();
        offerte.printOfferteToFile(outputFile);
    }
}

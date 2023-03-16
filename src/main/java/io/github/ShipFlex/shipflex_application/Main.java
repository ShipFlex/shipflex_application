package main.java.io.github.ShipFlex.shipflex_application;

import java.util.Scanner;

class Main {
    public static void main(String args[]) {

    Klant klant = new KlantInvoer().getKlantGegevens();
    Offerte offerte = new Offerte(klant);
    offerte.printOfferte();
    }
}

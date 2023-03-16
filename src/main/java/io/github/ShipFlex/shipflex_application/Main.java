package main.java.io.github.ShipFlex.shipflex_application;

import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

class Main {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);

        Klant k = new Klant(null, null, null, null, null, null, null);
        Bedrijf b = new Bedrijf(null, null, null, null, null, null, null);

        k.Printgegevens();
        b.Printgegevens();

    }
}

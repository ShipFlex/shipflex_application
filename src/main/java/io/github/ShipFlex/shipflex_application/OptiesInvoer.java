// Package
package main.java.io.github.ShipFlex.shipflex_application;

import java.util.ArrayList;
// Imports
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class OptiesInvoer {
    private Scanner scanner;
    private Map<String, Map<String, Double>> optiesMap;
    private List<Opties> geselecteerdeOpties = new ArrayList<Opties>();

    // Constructor
    public OptiesInvoer() {
        scanner = new Scanner(System.in);
    }

    public Opties getOpties() {
        Opties opties = new Opties("Auto", 0.0);
        addEssentieleOpties(opties);
        addExtraOpties(opties);
        return opties;
    }

    /*
     * Toevoegen Essentiële opties
     * ---------------------
     * Dit kan je handmatig aanpassen door:
     * boot.addExtraOpties(<categorie> , <naam> , <prijs>);
     */

    public void addEssentieleOpties(Opties opties) {
        opties.addEssentieleOpties("Scheepstype", "Motorboot", 10000);
        opties.addEssentieleOpties("Scheepstype", "Zeilboot", 10000);
        opties.addEssentieleOpties("Scheepstype", "Catarman", 10000);
        opties.addEssentieleOpties("Scheepstype", "Jacht", 10000);

        opties.addEssentieleOpties("Romp", "Aluminium", 20000);
        opties.addEssentieleOpties("Romp", "Staal", 20000);
        opties.addEssentieleOpties("Romp", "Composiet", 20000);
        opties.addEssentieleOpties("Romp", "Hout", 20000);

        opties.addEssentieleOpties("Stuurinrichting", "Hydraulisch", 30000);
        opties.addEssentieleOpties("Stuurinrichting", "Mechanisch", 30000);
        opties.addEssentieleOpties("Stuurinrichting", "Elektrisch", 30000);
        opties.addEssentieleOpties("Stuurinrichting", "Elektrisch Hydraulisch", 30000);

        opties.addEssentieleOpties("Motortype", "Diesel", 40000);
        opties.addEssentieleOpties("Motortype", "Benzine", 40000);
        opties.addEssentieleOpties("Motortype", "Elektrisch", 40000);
    }

    /*
     * Toevoegen Extra opties
     * ---------------------
     * Dit kan je handmatig aanpassen door:
     * boot.addExtraOpties(<categorie> , <naam> , <prijs>);
     */

    public void addExtraOpties(Opties opties) {
        opties.addExtraOpties("Stoelen", "Lederen bekleding", 1500.0);
        opties.addExtraOpties("Stoelen", "Verwarmde stoelen", 750.0);
        opties.addExtraOpties("Navigatie", "GPS", 500.0);
        opties.addExtraOpties("Navigatie", "Kaartupdates", 250.0);
    }

    public void displayOpties(Opties opties) {
        System.out.println("Beschikbare opties:");
        System.out.println("=====================================\n");
        System.out.println("~~ ESSENTIËLE OPTIES ~~");
        displayEssentieleOpties(opties);
        System.out.println("~~ EXTRA OPTIES ~~");
        displayExtraOpties(opties);
        System.out.println("\n=====================================");
    }

    public void displayEssentieleOpties(Opties opties) {
        Map<String, List<Opties>> essentieleOpties = opties.getEssentieleOpties();
        for (String categorie : essentieleOpties.keySet()) {
            System.out.println("\n|" + categorie + "|");
            for (Opties optie : essentieleOpties.get(categorie)) {
                System.out.println("  -" + optie.getNaam() + " (" + optie.getPrijs() + " EUR)");
            }
        }
    }

    public void displayExtraOpties(Opties opties) {
        Map<String, List<Opties>> extraOpties = opties.getExtraOpties();
        for (String categorie : extraOpties.keySet()) {
            System.out.println("\n|" + categorie + "|");
            for (Opties optie : extraOpties.get(categorie)) {
                System.out.println("  -" + optie.getNaam() + " (" + optie.getPrijs() + " EUR)");
            }
        }
    }

    public List<Opties> getGeselecteerdeOpties(Opties opties) {
        List<Opties> geselecteerdeOpties = new ArrayList<Opties>();
        geselecteerdeOpties.add(opties.kiesOptie("\nSelecteer Romp:", opties.getEssentieleOpties()));
        geselecteerdeOpties.add(opties.kiesOptie("\nSelecteer Stuurinrichting:", opties.getEssentieleOpties()));
        geselecteerdeOpties.add(opties.kiesOptie("\nSelecteer Motortype:", opties.getEssentieleOpties()));
        geselecteerdeOpties.add(opties.kiesOptie("\nSelecteer Scheepstype:", opties.getEssentieleOpties()));

        geselecteerdeOpties.add(opties.kiesOptie("\nSelecteer Stoelen opties:", opties.getExtraOpties()));
        geselecteerdeOpties.add(opties.kiesOptie("\nSelecteer Navigatie opties:", opties.getExtraOpties()));
    
        return geselecteerdeOpties;
    }

    // public void printReceipt(List<Opties> gekozenOpties) {
    //     double totaalPrijs = 0;
    //     System.out.println("=============================");
    //     System.out.println("            Bon          ");
    //     System.out.println("=============================");
    //     for (Opties optie : gekozenOpties) {
    //         System.out.printf("%-20s € %.2f%n", optie.getNaam(), optie.getPrijs());
    //         totaalPrijs += optie.getPrijs();
    //     }
    //     System.out.println("=============================");
    //     System.out.printf("%-20s € %.2f%n", "Totaal", totaalPrijs);
    // }
}

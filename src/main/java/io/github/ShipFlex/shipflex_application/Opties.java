// Package
package main.java.io.github.ShipFlex.shipflex_application;

// Imports
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Opties {
    private String naam;
    private double prijs;
    private Map<String, List<Opties>> essentieleOpties;
    private Map<String, List<Opties>> extraOpties;

    // Constructor
    public Opties(String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
        this.essentieleOpties = new HashMap<String, List<Opties>>();
        this.extraOpties = new HashMap<String, List<Opties>>();
    }

    // Getters & Setters
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public Map<String, List<Opties>> getEssentieleOpties() {
        return this.essentieleOpties;
    }

    public Map<String, List<Opties>> getExtraOpties() {
        return this.extraOpties;
    }

    // Methode voor het toevoegen van Essentiële Opties
    public void addEssentieleOpties(String categorie, String naam, double prijs) {
        Opties optie = new Opties(naam, prijs);

        if (!this.essentieleOpties.containsKey(categorie)) {
            this.essentieleOpties.put(categorie, new ArrayList<Opties>());
        }
        this.essentieleOpties.get(categorie).add(optie);
    }

    // Methode voor het toevoegen van Extra Opties
    public void addExtraOpties(String categorie, String naam, double prijs) {
        Opties optie = new Opties(naam, prijs);

        if (!this.extraOpties.containsKey(categorie)) {
            this.extraOpties.put(categorie, new ArrayList<Opties>());
        }
        this.extraOpties.get(categorie).add(optie);
    }
}
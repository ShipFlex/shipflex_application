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

    // Methode voor het verzamelen van de gekozen opties
    public Opties kiesOptie(String prompt, Map<String, List<Opties>> opties) {
        Scanner s = new Scanner(System.in);
        Opties gekozenOptie = null;

        while (gekozenOptie == null) {
            System.out.println(prompt);
            String input = s.nextLine();

            for (List<Opties> optieList : opties.values()) {
                for (Opties optie : optieList) {
                    if (optie.getNaam().equalsIgnoreCase(input)) {
                        gekozenOptie = optie;
                        System.out.println("Komt deze optie in aanmerking voor korting? (ja / nee)");
                        String antwoord = s.nextLine();

                        if (antwoord.equalsIgnoreCase("ja")) {
                            double korting = 0.0;
                            boolean validKorting = false;
                            while (!validKorting) {
                                System.out.print("Aantal procent korting voor deze optie is:");
                                String kortingInput = s.nextLine();

                                try {
                                    korting = Double.parseDouble(kortingInput);
                                    VoegKortingToe(gekozenOptie, korting);

                                    if (korting < 0 || korting > 100) {
                                        System.out.println(
                                                "Ongeldig kortingspercentage. Voer een waarde tussen 0 en 100 in.");

                                    } else {
                                        validKorting = true;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println(
                                            "Ongeldige kortingspercentage. Gelieve een numerieke waarde in te vullen");
                                }
                            }
                        }
                        break;
                    }
                }
            }
            if (gekozenOptie == null) {
                System.out.println("Ongeldige keuze. Probeer het opnieuw");
            }
        }
        return gekozenOptie;
    }
    public void VoegKortingToe(Opties optie, double korting){

        if (korting > 0) {
            System.out.println("Milieu-korting van " + korting + "% toegepast op " + optie.getNaam());
            optie.setPrijs(optie.getPrijs() * (100 - korting) / 100);
            System.out.printf( "De nieuwe prijs van " + optie.getNaam() +  " is " + "$" + optie.getPrijs()
            );

        }
    }
}

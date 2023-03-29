// Package
package main.java.io.github.ShipFlex.shipflex_application;

// Imports
import java.util.Scanner;

public class KlantInvoer {
    private Scanner input;

    public KlantInvoer() {
        this.input = new Scanner(System.in);
    }

    // Functie om ervoor te zorgen dat alle velden worden ingvuld bij het invoeren van de klantgegevens.
    private String getValidInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = this.input.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Dit veld mag niet leeg zijn!");
            }
        } while (input.trim().isEmpty());
        return input;
    }

    public Klant getKlantGegevens() {
        int keuze;
        while (true) {
            System.out.println("Welk type klant heeft u?");
            System.out.println("1. Particulier");
            System.out.println("2. Bedrijf");

            try {
                keuze = Integer.parseInt(input.nextLine());
                if (keuze == 1 || keuze == 2) {
                    break;
                }
            } catch (NumberFormatException e) {
                // hij negeert dit, de loop gaat door
            }

            // Op dit punt betekent het dat de ingevulde keuze ongeldig is
            System.out.println("Ongeldige keuze, kies 1 of 2.");
        }

        if (keuze == 1) {
            // Begin vragenlijst Particulier
            System.out.println("~~Invulformulier Klantgegevens ~~\n");
            String naam = getValidInput("Voer de naam van de klant in: ");
            String adres = getValidInput("Voer het adres van de klant in: ");
            String postcode = getValidInput("Voer de postcode van de klant in: ");
            String plaats = getValidInput("Voer de plaats van de klant in: ");
            String land = getValidInput("Voer het land van de klant in: ");
            String emailadres = getValidInput("Voer het email van de klant in: ");
            String telefoonnummer = getValidInput("Voer het telefoonnummer (+316) van de klant in: ");
            String klantnummer = getValidInput("Voer het klantnummer van de klant in: ");

            return new Particulier(naam, adres, postcode, plaats, land, emailadres, telefoonnummer, klantnummer);

        }
        if (keuze == 2) {
            // Begin vragenlijst Bedrijf
            String bedrijfsnaam = getValidInput("Voer de naam van het bedrijf in: ");
            String bedrijfsadres = getValidInput("Voer het adres van het bedrijf in: ");
            String bedrijfspostcode = getValidInput("Voer de postcode van het bedrijf in: ");
            String bedrijfsplaats = getValidInput("Voer de plaats van het bedrijf in: ");
            String bedrijfsland = getValidInput("Voer het land van het bedrijf in: ");
            String bedrijfstelefoon = getValidInput("Voer het telefoonnummer van het bedrijf in: ");
            String bedrijfsemailadres = getValidInput("Voer het emailadres van het bedrijf in: ");
            String kvkNummer = getValidInput("Voer het KVK-nummer van het bedrijf in: ");
            return new Bedrijf(bedrijfsnaam, bedrijfsadres, bedrijfspostcode, bedrijfsplaats, bedrijfsland,
                    bedrijfsemailadres, bedrijfstelefoon, bedrijfsemailadres, kvkNummer);
        }
        return null;
    }
}
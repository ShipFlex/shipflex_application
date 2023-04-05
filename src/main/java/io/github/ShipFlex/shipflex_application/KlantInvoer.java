// Package
package main.java.io.github.ShipFlex.shipflex_application;

// Imports
import java.util.Scanner;

public class KlantInvoer {
    private Scanner input;

    public KlantInvoer() {
        this.input = new Scanner(System.in);
    }

    public KlantInvoer(Scanner scanner) {
        this.input = scanner;
    }

    // Functie om ervoor te zorgen dat alle velden worden ingvuld bij het invoeren van de klantgegevens.
    public String getValidInput(String prompt) {
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
            System.out.println("1. Particulier   2. Bedrijf  3.anders");
            System.out.println("------------------------------");
            
            try {
                keuze = Integer.parseInt(input.nextLine());
                if (keuze == 1 || keuze == 2 || keuze == 3) {
                    break;
                }
            } catch (NumberFormatException e) {
                // hij negeert dit, de loop gaat door
            }

            // Op dit punt betekent het dat de ingevulde keuze ongeldig is
            System.out.println("Ongeldige keuze, kies 1, 2 of 3!");
        }

        if (keuze == 1) {
            // Begin vragenlijst Particulier
            System.out.println("~~ Invulformulier Klantgegevens ~~\n".toUpperCase());
            String naam = getValidInput("Voer de naam van de klant in: ");
            String adres = getValidInput("Voer het adres van de klant in: ");
            String postcode = getValidInput("Voer de postcode van de klant in: ");
            String plaats = getValidInput("Voer de plaats van de klant in: ");
            String land = getValidInput("Voer het land van de klant in: ");
            String emailadres = getValidInput("Voer het email van de klant in: ");
            String telefoonnummer = getValidInput("Voer het telefoonnummer van de klant in: ");
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
          // vragenlijst voor overige klanttypes
        if (keuze == 3){
        String klanttype = getValidInput("Voer het type klant in: ");
        String naamtype = getValidInput("Voer de naam van de " + klanttype + "in: ");
        String adrestype = getValidInput("Voer de adres van de " + klanttype + "in: ");
        String postcodetype = getValidInput("Voer de postcode van de " + klanttype + "in: ");
        String plaatstype = getValidInput("Voer de postcode van de "+ klanttype + "in: ");
        String landtype = getValidInput("Voer het land van de "+ klanttype + "in: ");
        String emailtype = getValidInput("Voer het emailadres van de " + klanttype + "in: ");
        String telefoonnummertype = getValidInput("Voer het telefoonnummer van de " + klanttype + "in: ");
        String typenummer = getValidInput("voer het " + klanttype + "code in van de " + klanttype + "in: ");

        return new OverigeType(klanttype, naamtype, adrestype, postcodetype, plaatstype, landtype, emailtype, telefoonnummertype, 
        typenummer);
        
            
    

        }
        return null;
    }
}
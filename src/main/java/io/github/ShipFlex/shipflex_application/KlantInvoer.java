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

    // Functie om ervoor te zorgen dat alle velden worden ingvuld bij het invoeren
    // van de klantgegevens.
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

    // Functie om de eerste letter van een woord in hoofdletter te zetten
    public String getCapitalizedInput(String prompt) {
        String input;
        do {
            input = getValidInput(prompt);
            String[] words = input.trim().split("\\s+");
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                if (word.length() > 0) {
                    sb.append(Character.toUpperCase(word.charAt(0)));
                    if (word.length() > 1) {
                        sb.append(word.substring(1).toLowerCase());
                    }
                    sb.append(" ");
                }
            }
            input = sb.toString().trim();
            if (input.isEmpty()) {
                System.out.println("Dit veld mag niet leeg zijn!");
            }
        } while (input.isEmpty());
        return input;
    }

    public Klant getKlantGegevens() {
        int keuze;
        while (true) {
            System.out.println("\nWelk type klant heeft u?");
            System.out.println("1. Particulier   2. Bedrijf   3. Anders");
            System.out.println("---------------------------------------");

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

        System.out.println("====| Invulformulier Klantgegevens |====\n".toUpperCase());

        if (keuze == 1) {
            // Begin vragenlijst Particulier
            String naam = getCapitalizedInput("Voer de naam van de klant in: ");
            String adres = getCapitalizedInput("Voer het adres van de klant in: ");
            String postcode = getValidInput("Voer de postcode van de klant in: ");
            String plaats = getCapitalizedInput("Voer de plaats van de klant in: ");
            String land = getCapitalizedInput("Voer het land van de klant in: ");
            String emailadres = getValidInput("Voer het email van de klant in: ");
            String telefoonnummer = getValidInput("Voer het telefoonnummer van de klant in: ");
            String klantnummer = getValidInput("Voer het klantnummer van de klant in: ");

            return new Particulier(naam, adres, postcode, plaats, land, emailadres, telefoonnummer, klantnummer);

        }
        if (keuze == 2) {
            // Begin vragenlijst Bedrijf
            String bedrijfsnaam = getCapitalizedInput("Voer de naam van het bedrijf in: ");
            String bedrijfsadres = getCapitalizedInput("Voer het adres van het bedrijf in: ");
            String bedrijfspostcode = getValidInput("Voer de postcode van het bedrijf in: ");
            String bedrijfsplaats = getCapitalizedInput("Voer de plaats van het bedrijf in: ");
            String bedrijfsland = getCapitalizedInput("Voer het land van het bedrijf in: ");
            String bedrijfstelefoon = getValidInput("Voer het telefoonnummer van het bedrijf in: ");
            String bedrijfsemailadres = getValidInput("Voer het emailadres van het bedrijf in: ");
            String kvkNummer = getValidInput("Voer het KVK-nummer van het bedrijf in: ");
            return new Bedrijf(bedrijfsnaam, bedrijfsadres, bedrijfspostcode, bedrijfsplaats, bedrijfsland,
                    bedrijfsemailadres, bedrijfstelefoon, bedrijfsemailadres, kvkNummer);
        }
        // vragenlijst voor overige klanttypes
        if (keuze == 3) {
            String klanttype = getCapitalizedInput("Voer het type klant in: ");
            String naamtype = getCapitalizedInput("Voer de naam van de " + klanttype + " in: ");
            String adrestype = getCapitalizedInput("Voer de adres van de " + klanttype + " in: ");
            String postcodetype = getValidInput("Voer de postcode van de " + klanttype + " in: ");
            String plaatstype = getCapitalizedInput("Voer de postcode van de " + klanttype + " in: ");
            String landtype = getCapitalizedInput("Voer het land van de " + klanttype + " in: ");
            String emailtype = getValidInput("Voer het emailadres van de " + klanttype + " in: ");
            String telefoonnummertype = getValidInput("Voer het telefoonnummer van de " + klanttype + " in: ");
            String typenummer = getValidInput("voer het " + klanttype + "code in van de " + klanttype + " in: ");

            return new OverigeType(klanttype, naamtype, adrestype, postcodetype, plaatstype, landtype, emailtype,
                    telefoonnummertype,
                    typenummer);
        }
        return null;
    }
}
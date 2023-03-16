package main.java.io.github.ShipFlex.shipflex_application;

import java.util.Scanner;

public class KlantInvoer {
    public Klant getKlantGegevens() {
        try (Scanner input = new Scanner(System.in)) {
            {
                      
            // Startvraag
            System.out.println("Welk type klant heeft u?");
            System.out.println("1. Particulier");
            System.out.println("2. Bedrijf");

            int keuze = input.nextInt();
            input.nextLine(); // consumeer de rest van de lijn na de int

            // Begin vragenlijst
            System.out.println("~~Invulformulier Klantgegevens ~~\n");
            switch (keuze) {
                // Type klant = particulier
                case 1:
                    System.out.print("Voer de naam van de klant in: ");
                    String naam = input.nextLine();

                    System.out.print("Voer het adres van de klant in: ");
                    String adres = input.nextLine();

                    System.out.print("Voer de postcode van de klant in: ");
                    String postcode = input.nextLine();

                    System.out.print("Voer de plaats van de klant in: ");
                    String plaats = input.nextLine();

                    System.out.print("Voer het land van de klant in: ");
                    String land = input.nextLine();

                    System.out.print("Voer het email van de klant in: ");
                    String emailadres = input.nextLine();

                    System.out.print("Voer het telefoonnummer (+316) van de klant in: ");
                    String telefoonnummer = input.nextLine();

                    System.out.print("Voer het klantnummer van de klant in: ");
                    String klantnummer = input.nextLine();

                    return new Particulier(naam, adres, postcode, plaats, land, emailadres, telefoonnummer, klantnummer);

                // Type klant = bedrijf
                case 2:
                    System.out.print("Voer de naam van het bedrijf in: ");
                    String bedrijfsnaam = input.nextLine();

                    System.out.print("Voer het adres van het bedrijf in: ");
                    String bedrijfsadres = input.nextLine();

                    System.out.print("Voer de postcode van het bedrijf in: ");
                    String bedrijfspostcode = input.nextLine();

                    System.out.print("Voer de plaats van het bedrijf in: ");
                    String bedrijfsplaats = input.nextLine();

                    System.out.print("Voer het land van het bedrijf in: ");
                    String bedrijfsland = input.nextLine();

                    System.out.print("Voer het telefoonnummer van het bedrijf in: ");
                    String bedrijfstelefoon = input.nextLine();

                    System.out.print("Voer het emailadres van het bedrijf in: ");
                    String bedrijfsemailadres = input.nextLine();

                    System.out.print("Voer het KVK-nummer van het bedrijf in: ");
                    String kvkNummer = input.nextLine();

                    return new Bedrijf(bedrijfsnaam, bedrijfsadres, bedrijfspostcode, bedrijfsplaats, bedrijfsland, bedrijfsemailadres, bedrijfstelefoon, bedrijfsemailadres, kvkNummer);
                }
            }
        } 
        return null;
    }
}
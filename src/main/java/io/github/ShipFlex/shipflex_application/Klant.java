package main.java.io.github.ShipFlex.shipflex_application;

import java.util.Scanner;

public class Klant implements Printgegevens {
    private String naam;
    private String achternaam;
    private String adres;
    private String postcodeString;
    private String woonplaats;
    private String klantNummer;
    private String klantTel;

    // Constructor
    public Klant(String naam, String achternaam, String adres, String postcodeString, String woonplaats,
            String klantNummer, String klantTel) {
        this.naam = naam;
        this.achternaam = achternaam;
        this.adres = adres;
        this.postcodeString = postcodeString;
        this.woonplaats = woonplaats;
        this.klantNummer = klantNummer;
        this.klantTel = klantTel;
    }

    // Getters & Setters
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPostcodeString() {
        return postcodeString;
    }

    public void setPostcodeString(String postcodeString) {
        this.postcodeString = postcodeString;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getKlantNummer() {
        return klantNummer;
    }

    public void setKlantNummer(String klantNummer) {
        this.klantNummer = klantNummer;
    }

    public String getKlantTel() {
        return klantTel;
    }

    public void setKlantTel(String klantTel) {
        this.klantTel = klantTel;
    }

    @Override
    public void Printgegevens() {
        Scanner input = new Scanner(System.in);

        // Vragenlijst
        System.out.println("~~Invulformulier Klantgegevens ~~\n");
        System.out.print("Voer de naam van de klant in: ... ");
        String naam = input.nextLine();

        System.out.print("Voer de achternaam van de klant in: ... ");
        String achternaam = input.nextLine();

        System.out.print("Voer het adres (Straatnaam + Huisnummer) van de klant in: ...");
        String adres = input.nextLine();

        System.out.print("Voer de postcode van de klant in: ... ");
        String postcodeString = input.nextLine();

        System.out.print("Voer de woonplaats van de klant in: ... ");
        String woonplaats = input.nextLine();

        System.out.print("Voer de klantnummer (#.....) van de klant in: ... ");
        String klantNummer = input.nextLine();

        System.out.print("Voer het telefoonnummer (+316...) van de klant in: ... ");
        String klantTel = input.nextLine();
        System.out.println("");

        // Print klantgegevens
        System.out.println("~~ Klantgegevens ~~\n" +
                naam + " " + achternaam + "\n" + adres + " " + postcodeString + "\n" + woonplaats + "\n" + klantNummer
                + "\n" + klantTel);

        // Close input stream
        input.close();
    }
}
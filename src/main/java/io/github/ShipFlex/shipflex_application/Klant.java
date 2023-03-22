// Package
package main.java.io.github.ShipFlex.shipflex_application;

public class Klant {
    private String naam;
    private String adres;
    private String postcode;
    private String plaats;
    private String land;
    private String emailadres;
    private String telefoonnummer;
    private String klantnummer;

    //Constructor
    public Klant(String naam, String adres, String postcode, String plaats, String land, String emailadres, String telefoonnummer
    , String klantnummer) {
        this.naam = naam;
        this.adres = adres;
        this.postcode = postcode;
        this.plaats = plaats;
        this.land = land;
        this.emailadres = emailadres;
        this.telefoonnummer = telefoonnummer;
        this.klantnummer = klantnummer;
    }

    // getters en setters voor alle velden

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getEmailadres() {
        return emailadres;
    }

    public void setEmailadres(String emailadres) {
        this.emailadres = emailadres;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public String getKlantnummer() {
        return klantnummer;
    }

    public void setKlantnummer(String klantnummer) {
        this.klantnummer = klantnummer;
    }
}




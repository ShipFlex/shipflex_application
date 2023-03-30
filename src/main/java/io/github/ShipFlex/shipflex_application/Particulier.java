package main.java.io.github.ShipFlex.shipflex_application;

public class Particulier extends Klant {
    private String klantnummer;

    public Particulier(String naam, String adres, String postcode, String plaats, 
    String land, String emailadres, String telefoonnummer, String klantnummer) {
        
        super(naam, adres, postcode, plaats, land, emailadres, telefoonnummer, klantnummer);
        this.klantnummer = klantnummer;
    }

    @Override
    public String getExtraDetails() {
        return this.klantnummer;
    }
}


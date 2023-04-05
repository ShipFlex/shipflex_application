package main.java.io.github.ShipFlex.shipflex_application;

public class OverigeType extends Klant {
    private String typenummer;

    // Constructor
    public OverigeType(String naam, String adres, String postcode, String plaats,
            String land, String emailadres, String telefoonnummer, String klantnummer, String typenummer) {

        super(naam, adres, postcode, plaats, land, emailadres, telefoonnummer, klantnummer);
        this.typenummer = typenummer;
    }

    // Getter & Setter
    public void setKvkNummer(String typenummer) {
        this.typenummer = typenummer;
    }

    @Override
    public String getExtraDetails() {
        return typenummer;
    }
}

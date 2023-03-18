public class Bedrijf extends Klant{
    private String kvkNummer;

    // Constructor
    public Bedrijf(String naam, String adres, String postcode, String plaats, 
    String land, String emailadres, String telefoonnummer, String klantnummer, String kvkNummer) {
        
        super(naam, adres, postcode, plaats, land, emailadres, telefoonnummer, klantnummer);
        this.kvkNummer = kvkNummer;
    }

    // Getter & Setter
    public String getKvkNummer() {
        return kvkNummer;
    }

    public void setKvkNummer(String kvkNummer) {
        this.kvkNummer = kvkNummer;
    }

    public String toString() {
        return super.toString() + "\n KVK: " + kvkNummer;
    }
}

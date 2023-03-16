package main.java.io.github.ShipFlex.shipflex_application;

public class Offerte {
    private Klant klant;

    public Offerte(Klant klant) {
        this.klant = klant;
    }
    
    // methode die de uiteindelijk offerte print
    public void printOfferte() {
        System.out.println("\n" + "---. Bedrijfsgegevens .---");
        System.out.println("MyCompany \n" + "Voorbeeldstraat 3 \n" + "0101AB \n" + 
        "Den Haag \n" + "MyCompany.org" + "0102 0304 \n");

        System.out.println("\n" + "---. Klantgegevens .---");
        System.out.println(klant.getNaam());
        System.out.println(klant.getAdres());
        System.out.println(klant.getPostcode());
        System.out.println(klant.getPlaats());
        System.out.println(klant.getLand());
        System.out.println(klant.getEmailadres());
        System.out.println(klant.getTelefoonnummer());

        if (klant instanceof Bedrijf) {
            Bedrijf bedrijf = (Bedrijf) klant;
            System.out.println(bedrijf.getKvkNummer());
        } else if (klant instanceof Particulier) {
            Particulier particulier = (Particulier) klant;
            System.out.println(particulier.getKlantnummer());
        }
    }
}


package main.java.io.github.ShipFlex.shipflex_application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Offerte {
    private Klant klant;
    private Schip schip;

    private EOpties romp;

    public Offerte(Klant klant,Schip schip,EOpties romp) {
        this.klant = klant;
        this.schip = schip;
        this.romp = romp;
    }

    // methode die de uiteindelijk offerte print
    public void printOfferte() {
        System.out.println("\n" + "---. Bedrijfsgegevens .---");
        System.out.println("MyCompany \n" + "Voorbeeldstraat 3 \n" + "0101AB \n" + 
        "Den Haag \n" + "MyCompany.org" + "0102 0304 \n");

        System.out.println("\n" + "---. Klantgegevens .---");
        System.out.println(
            klant.getNaam() + "\n" +
            klant.getAdres()+ "\n" +
            klant.getPostcode()+ "\n" +
            klant.getPlaats()+ "\n" +
            klant.getLand()+ "\n" +
            klant.getEmailadres()+ "\n" +
            klant.getTelefoonnummer()
        );


        if (klant instanceof Bedrijf) {
            Bedrijf bedrijf = (Bedrijf) klant;
            System.out.println(bedrijf.getKvkNummer());
        } else if (klant instanceof Particulier) {
            Particulier particulier = (Particulier) klant;
            System.out.println(particulier.getKlantnummer());
        }
        System.out.println("\n" + "-Scheeptype-");
        System.out.println(schip.getModel()+"\n");
        System.out.println("\n "+ "-Essentiele Opties-");
        System.out.println(romp.getOpties()+"\n");
    }

    public void printOfferteToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
    
        // Definieerd de Lijst van variabelen die geprint worden naar de externe file
        List<String> variables = new ArrayList<>();
        variables.add(klant.getNaam());
        variables.add(klant.getAdres());
        variables.add(klant.getPostcode());
        variables.add(klant.getPlaats());
        variables.add(klant.getLand());
        variables.add(klant.getEmailadres());
        variables.add(klant.getTelefoonnummer());
        
        // Voegt het kvkNummer of klantnummer toe aan de List gebasseerd op het type klant 
        if (klant instanceof Bedrijf) {
            Bedrijf bedrijf = (Bedrijf) klant;
            variables.add(bedrijf.getKvkNummer());
        } else if (klant instanceof Particulier) {
            Particulier particulier = (Particulier) klant;
            variables.add(particulier.getKlantnummer());
        }
        
        writer.write("---. Bedrijfsgegevens .--- \n");
        writer.write("MyCompany \n" + "Voorbeeldstraat 3 " + "0101AB \n" + 
        "Den Haag \n" + "MyCompany.org" + " \n 0102 0304 \n");

        writer.write("---. Klantgegevens .--- \n");

        // Write the variables to the file using a enhanced for loop
        for (String variable : variables) {
            writer.write(variable + "\n");
        }
    
            writer.close();
            
        }
        catch (IOException e) 
        {
            System.err.println("Failed to write file: " + e.getMessage());
            System.exit(1);
        }
    }
}


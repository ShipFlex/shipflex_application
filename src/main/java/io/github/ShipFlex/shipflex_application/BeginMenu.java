package main.java.io.github.ShipFlex.shipflex_application;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class BeginMenu {

    private Scanner invoer;

    public BeginMenu() {
        this.invoer = new Scanner(System.in);
    }
    public void start() throws IOException {
        int menuOptie;
        do {
            menuOptie = welkomsBericht();

            if (menuOptie == 1) {
                // Implementeer hier de methode om klanttypes weer te geven
            }

            if (menuOptie == 2) {
                toonUitgebreideOptielijst();
            }

            if (menuOptie == 3) {
                KlantInvoer klantInvoer = new KlantInvoer();
                Klant klant = klantInvoer.getKlantGegevens();

                OptiesInvoer oi = new OptiesInvoer();
                Opties op = oi.getOpties();

                oi.displayEssentieleOpties(op);
                oi.optiesJSON(op);

                System.out.println("");

                // vraagt de gebruiker of de offerte extern geschreven moet worden
                System.out.println("Wilt u de offerte opslaan in een tekstbestand? (ja/nee)");
                String invoerString = invoer.nextLine();
                boolean printToFile = invoerString.equalsIgnoreCase("ja");

                Offerte of = new Offerte(klant, oi);
                of.printOfferte(printToFile);

                // print to console
                of.printOfferte(false);
            }

        } while (menuOptie != 4);
    }


    public int welkomsBericht() {
        int optie;
        while (true) {
            System.out.println("Welkom bij de OfferteGenerator van ShipFlex");
            System.out.println("Selecteer hieronder wat u wilt doen:");
            System.out.println("1. Klanttypes inzien\n2. Uitgebreide optielijst weergeven\n3. Offerte genereren\n4. Afsluiten");

            String invoerString = invoer.nextLine();
            try {
                optie = Integer.parseInt(invoerString);

                if (optie < 1 || optie > 4) {
                    System.out.println("Ongeldige invoer, probeer opnieuw!");
                } else {
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Ongeldige invoer, probeer opnieuw!");
            }
        }
        return optie;
    }


    private int valideerCategorieKeuze() {
        int gekozenCategorie = 0;
        boolean isGeldigeInvoer = false;
        while (!isGeldigeInvoer) {
            try {
                gekozenCategorie = Integer.parseInt(invoer.nextLine());
                if (gekozenCategorie < 1 || gekozenCategorie > 7) {
                    System.out.println("Ongeldige invoer, probeer opnieuw!");
                } else {
                    isGeldigeInvoer = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ongeldige invoer, probeer opnieuw!");
            }
        }
        return gekozenCategorie;
    }

    private void toonUitgebreideOptielijst() {
        String jsonData = leesBestand();
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray essentieleOptiesArray = jsonObject.getJSONArray("wikiEssentieleOpties");
        JSONArray extraOptiesArray = jsonObject.getJSONArray("wikiExtraOpties");

        while (true) {
            int gekozenCategorie = toonCategorieMenu();

            if (gekozenCategorie == 7) {
                break;
            }

            if (gekozenCategorie >= 1 && gekozenCategorie <= 4) {
                System.out.println("\nEssentiele opties:");
                toonOptiesPerCategorie(essentieleOptiesArray, gekozenCategorie);
            } else {
                System.out.println("\nExtra opties:");
                toonOptiesPerCategorie(extraOptiesArray, gekozenCategorie);
            }

            int actie = toonActieMenu();

            if (actie == 1) {
                break;
            }
        }
    }

    private int toonCategorieMenu() {
        System.out.println("Kies een categorie:");
        System.out.println("1. Scheepstype");
        System.out.println("2. Romp");
        System.out.println("3. Stuurinrichting");
        System.out.println("4. Motortype");
        System.out.println("5. Stoelen");
        System.out.println("6. Navigatie");
        System.out.println("7. Terug naar hoofdmenu");

        return valideerCategorieKeuze();
    }

    private int toonActieMenu() {
        System.out.println();
        System.out.println("Selecteer wat u wilt doen:");
        System.out.println("1. Terug naar hoofdmenu");
        System.out.println("2. Opnieuw categorie kiezen om verder informatie te lezen");

        while (true) {
            String invoerString = invoer.nextLine();
            try {
                int optie = Integer.parseInt(invoerString);

                if (optie < 1 || optie > 2) {
                    System.out.println("Ongeldige invoer, probeer opnieuw!");
                } else {
                    return optie;
                }

            } catch (NumberFormatException e) {
                System.out.println("Ongeldige invoer, probeer opnieuw!");
            }
        }
    }


    private void toonOptiesPerCategorie(JSONArray optiesArray, int categorieIndex) {
        String[] categorieen = {"Scheepstype", "Romp", "Stuurinrichting", "Motortype", "Stoelen", "Navigatie"};
        String gekozenCategorieNaam = categorieen[categorieIndex - 1];

        for (int i = 0; i < optiesArray.length(); i++) {
            JSONObject optie = optiesArray.getJSONObject(i);
            if (optie.getString("categorie").equals(gekozenCategorieNaam)) {
                String naam = optie.getString("naam");
                String info = optie.getString("info");
                double prijs = optie.getDouble("prijs");

                String[] infoChunks = info.split("(?<=\\G.{60})");

                System.out.println(naam + ": (Prijs: " + prijs + ")");
                for (String chunk : infoChunks) {
                    System.out.println("\t" + chunk);
                }
            }
        }
    }


    private String leesBestand() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("wiki.json"))) {
            String regel;
            while ((regel = br.readLine()) != null) {
                sb.append(regel);
            }
        } catch (IOException e ) {

        }
        return sb.toString();
    }
}
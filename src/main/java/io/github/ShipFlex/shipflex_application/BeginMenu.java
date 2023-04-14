package main.java.io.github.ShipFlex.shipflex_application;

import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


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
                genereerOfferte();
            }

        } while (menuOptie != 4);
    }

    public int welkomsBericht() {
        int optie;
        while (true) {
            System.out.println("====| Welkom bij de OfferteGenerator van ShipFlex |====");
            System.out.println("\nSelecteer hieronder wat u wilt doen:");
            System.out.println(
                    "--------------\n1. Klanttypes inzien\n2. Uitgebreide optielijst weergeven\n3. Offerte genereren\n4. Afsluiten");

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
                if (gekozenCategorie < 1 || gekozenCategorie > 9) {
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
        JSONObject jsonObject = leesJsonBestand("wiki.json");
        JSONArray essentieleOptiesArray = (JSONArray) jsonObject.get("wikiEssentieleOpties");
        JSONArray extraOptiesArray = (JSONArray) jsonObject.get("wikiExtraOpties");

        while (true) {
            int gekozenCategorie = toonCategorieMenu();

            if (gekozenCategorie == 9) {
                break;
            }

            if (gekozenCategorie >= 1 && gekozenCategorie <= 4) {
                System.out.println("\nEssentiele opties:\n");
                toonOptiesPerCategorie(essentieleOptiesArray, gekozenCategorie);
            } else {
                System.out.println("\nExtra opties:\n");
                toonOptiesPerCategorie(extraOptiesArray, gekozenCategorie);
            }

            int actie = toonActieMenu();

            if (actie == 1) {
                break;
            }
        }
    }

    private int toonCategorieMenu() {
        System.out.println("\nKies een categorie:\n--------------");
        System.out.println("1. Scheepstype");
        System.out.println("2. Romp");
        System.out.println("3. Stuurinrichting");
        System.out.println("4. Motortype");
        System.out.println("5. Stoelen");
        System.out.println("6. Navigatie");
        System.out.println("7. Comfort");
        System.out.println("8. Veiligheid");
        System.out.println("9. Terug naar hoofdmenu");

        return valideerCategorieKeuze();
    }

    private int toonActieMenu() {
        System.out.println();
        System.out.println("\nSelecteer wat u wilt doen:");
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

    private void printWrappedText(String text, int maxWidth) {
        String[] words = text.split(" ");
        String line = "";

        for (String word : words) {
            if (line.length() + word.length() + 1 > maxWidth) {
                System.out.println(line);
                line = "";
            }
            if (!line.isEmpty()) {
                line += " ";
            }
            line += word;
        }
        if (!line.isEmpty()) {
            System.out.println(line);
        }
    }

    private void toonOptiesPerCategorie(JSONArray optiesArray, int categorieIndex) {
        String[] categorieen = {"Scheepstype", "Romp", "Stuurinrichting", "Motortype", "Stoelen", "Navigatie", "Comfort", "Veiligheid"};
        String gekozenCategorieNaam = categorieen[categorieIndex - 1];

        for (int i = 0; i < optiesArray.size(); i++) {
            JSONObject optie = (JSONObject) optiesArray.get(i);
            if (optie.get("categorie").equals(gekozenCategorieNaam)) {
                String naam = (String) optie.get("naam");
                String info = (String) optie.get("info");
                double prijs = ((Number) optie.get("prijs")).doubleValue();

                System.out.println(naam + ": (Prijs: " + prijs + ")");
                printWrappedText("@" + info + "\n", 60);
            }
        }
    }

    private JSONObject leesJsonBestand(String filename) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try (FileReader reader = new FileReader(filename)) {
            jsonObject = (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            System.err.println("\nEr is een fout opgetreden tijdens het lezen van het bestand: " + e.getMessage());
            System.exit(0);
        }
        return jsonObject;
    }

    private void genereerOfferte() throws IOException {
        KlantInvoer klantInvoer = new KlantInvoer();
        Klant klant = klantInvoer.getKlantGegevens();

        OptiesInvoer oi = new OptiesInvoer();
        Opties op = oi.getOpties();

        oi.displayEssentieleOpties(op);
        oi.optiesJSON(op);

        System.out.println("");

        // vraagt de gebruiker of de offerte extern geschreven moet worden
        System.out.println("Wilt u de offerte opslaan in een tekstbestand? (Ja/Nee)");
        String invoerString = invoer.nextLine();
        boolean printToFile = invoerString.equalsIgnoreCase("ja");

        if ((!invoerString.equalsIgnoreCase("ja") ) || (!invoerString.equalsIgnoreCase("nee")))  {
            System.out.println("Ongeldige invoer, probeer opnieuw!");
             invoerString = invoer.nextLine();
             }

        if (printToFile) {
            System.out.println("Geef een bestandsnaam op voor de offerte:");
            String bestandsnaam = invoer.nextLine();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime nu = LocalDateTime.now();
            String datum = dtf.format(nu);
            String folder = "offertes\\";
            String filename = folder + bestandsnaam + "-" + datum + ".txt";
            Offerte of = new Offerte(klant, oi);
            of.printOfferte(printToFile, filename);

        } else {
            Offerte of = new Offerte(klant, oi);
            of.printOfferte(false, "");
        }
    }

    public void toonKlanttyppes(){

        System.out.println("Dit zijn de huideige klantypes: ");
        System.out.println("1. Particulier " + "/n"
        + "2. Bedrijf " + "/n" + 
        "3. Overige klanttypes die u zelf in kunt stellen");

    }
}
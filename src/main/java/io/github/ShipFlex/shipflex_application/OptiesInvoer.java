// Package
package main.java.io.github.ShipFlex.shipflex_application;

// Imports
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

// JSON.Simple Imports
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class OptiesInvoer {
    private List<Opties> geselecteerdeOpties = new ArrayList<Opties>();

    // Constructor
    public OptiesInvoer() {
    }

    public Opties getOpties() {
        Opties opties = new Opties("Auto", 0);
        addEssentieleOpties(opties);
        addExtraOpties(opties);
        return opties;
    }

    // Functie om de essentiële opties uit een JSON file (database) uit te lezen en
    // toe te voegen aan een object van het type Opties.
    public void addEssentieleOpties(Opties opties) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("opties.json")) {
            JSONObject obj = (JSONObject) parser.parse(reader);
            JSONArray essentieleOpties = (JSONArray) obj.get("essentieleOpties");
            for (Object o : essentieleOpties) {
                JSONObject optie = (JSONObject) o;
                String categorie = (String) optie.get("categorie");
                String naam = (String) optie.get("naam");
                Number prijsObj = (Number) optie.get("prijs");
                Integer prijs = prijsObj.intValue();
                opties.addEssentieleOpties(categorie, naam, prijs);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    // Functie om de extra opties uit een JSON file (database) uit te lezen en toe
    // te voegen aan een object van het type Opties.
    public void addExtraOpties(Opties opties) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("opties.json")) {
            JSONObject obj = (JSONObject) parser.parse(reader);
            JSONArray extraOpties = (JSONArray) obj.get("extraOpties");
            for (Object o : extraOpties) {
                JSONObject option = (JSONObject) o;
                String categorie = (String) option.get("categorie");
                String naam = (String) option.get("naam");
                Number prijsObj = (Number) option.get("prijs");
                Integer prijs = prijsObj.intValue();
                opties.addExtraOpties(categorie, naam, prijs);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    // Funtie om de essentiële opties weer te geven aan de gebruiker, de functie
    // roept daarna ook de *displayExtraOpties* functie op
    // die de extra opties print.
    public void displayEssentieleOpties(Opties opties) {
        System.out.println("=====================================\n");
        System.out.println("~~ ESSENTIËLE OPTIES ~~");
        Map<String, List<Opties>> essentieleOpties = opties.getEssentieleOpties();
        for (String categorie : essentieleOpties.keySet()) {
            System.out.println("\n|" + categorie + "|");
            for (Opties optie : essentieleOpties.get(categorie)) {
                System.out.println("  -" + optie.getNaam() + " (" + optie.getPrijs() + " EUR)");
            }
        }
        displayExtraOpties(opties);
    }

    public void displayExtraOpties(Opties opties) {
        System.out.println("\n~~ EXTRA OPTIES ~~");

        Map<String, List<Opties>> extraOpties = opties.getExtraOpties();
        for (String categorie : extraOpties.keySet()) {
            System.out.println("\n|" + categorie + "|");
            for (Opties optie : extraOpties.get(categorie)) {
                System.out.println("  -" + optie.getNaam() + " (" + optie.getPrijs() + " EUR)");
            }
        }
        System.out.println("\n=====================================");
    }

    // Functie om de gebruiker zowel essentiële als extra opties te laten
    // selecteren, het neemt een Opties object in en returned een List van Opties
    // objecten.
    // Hierbij leest hij de JSON file uit en filtert alle categorieën en print deze
    // vervolgens.
    // Er wordt ook rekening gehouden met categorieën die al zijn verwerkt.
    public List<Opties> selecteerOpties(Opties opties) {
        List<Opties> geselecteerdeOpties = new ArrayList<Opties>();
        Set<String> behandeldeCategorieen = new HashSet<String>();
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("opties.json")) {
            JSONObject obj = (JSONObject) parser.parse(reader);
            JSONArray essentieleOpties = (JSONArray) obj.get("essentieleOpties");
            JSONArray extraOpties = (JSONArray) obj.get("extraOpties");
            for (Object o : essentieleOpties) {
                JSONObject optie = (JSONObject) o;
                String categorie = (String) optie.get("categorie");
                if (!behandeldeCategorieen.contains(categorie)) {
                    geselecteerdeOpties.add(kiesOptie("\nSelecteer " + categorie + ":", opties.getEssentieleOpties()));
                    behandeldeCategorieen.add(categorie);
                }
            }
            for (Object o : extraOpties) {
                JSONObject optie = (JSONObject) o;
                String categorie = (String) optie.get("categorie");
                if (!behandeldeCategorieen.contains(categorie)) {
                    geselecteerdeOpties.add(kiesOptie("\nSelecteer " + categorie + ":", opties.getExtraOpties()));
                    behandeldeCategorieen.add(categorie);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return geselecteerdeOpties;
    }

    public Opties kiesOptie(String prompt, Map<String, List<Opties>> opties) {
        Scanner s = new Scanner(System.in);
        Opties gekozenOptie = null;

        while (gekozenOptie == null) {
            System.out.println(prompt);
            String input = s.nextLine();

            // check if input matches any available option
            for (List<Opties> optieList : opties.values()) {
                for (Opties optie : optieList) {
                    if (optie.getNaam().equalsIgnoreCase(input)) {
                        // if the option has not been selected before, choose it
                        if (!geselecteerdeOpties.contains(optie)) {
                            geselecteerdeOpties.add(optie);
                            gekozenOptie = optie;
                            System.out.println("Komt deze optie in aanmerking voor korting? (ja / nee)");
                            String antwoord = s.nextLine();

                            if (antwoord.equalsIgnoreCase("ja")) {
                                Integer korting = 0;
                                boolean validKorting = false;
                                while (!validKorting) {
                                    System.out.print("Aantal procent korting voor deze optie is:");
                                    String kortingInput = s.nextLine();

                                    try {
                                        korting = Integer.parseInt(kortingInput);
                                        VoegKortingToe(gekozenOptie, korting);

                                        if (korting < 0 || korting > 100) {
                                            System.out.println(
                                                    "Ongeldig kortingspercentage. Voer een waarde tussen 0 en 100 in.");

                                        } else {
                                            validKorting = true;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println(
                                                "Ongeldige kortingspercentage. Gelieve een numerieke waarde in te vullen");
                                    }
                                }
                            }
                        } else {
                            System.out.println("Deze optie is al gekozen. Kies een andere optie.");
                        }
                        break;
                    }
                }
                if (gekozenOptie != null) {
                    break;
                }
            }
        }
        return gekozenOptie;
    }

    public void VoegKortingToe(Opties optie, Integer korting) {

        if (korting > 0) {
            System.out.println("Milieu-korting van " + korting + "% toegepast op " + optie.getNaam());
            optie.setPrijs(optie.getPrijs() * (100 - korting) / 100);
            System.out.printf("De nieuwe prijs van " + optie.getNaam() + " is " + optie.getPrijs() + "EUR");

        }
    }
}

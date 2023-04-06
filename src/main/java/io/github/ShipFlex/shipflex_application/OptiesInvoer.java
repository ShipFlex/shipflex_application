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

public class OptiesInvoer implements OptieValidatie {
    private List<Opties> geselecteerdeOpties = new ArrayList<Opties>();
    private Scanner input;

    // Constructor
    public OptiesInvoer() {
        this.input = new Scanner(System.in);
    }

    public Opties getOpties() {
        Opties opties = new Opties("Boot", 0);
        addEssentieleOpties(opties);
        addExtraOpties(opties);
        return opties;
    }

    @Override
    public void validatieKeuze() {
        System.err.println(
                "\n** Er is een fout opgetreden tijdens het lezen van het bestand. Wilt u doorgaan zonder de Extra Opties? (ja / nee) **");
        String antwoord = input.nextLine().toLowerCase();
        if (antwoord.equals("ja")) {
            return;
        } else if (antwoord.equals("nee")) {
            System.exit(0);
        } else {
            System.out.println("Ongeldige invoer. Het programma stopt nu.");
            System.exit(0);
        }
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
            System.err.println(
                    "\nEr is een fout opgetreden tijdens het lezen van het bestand: " + e.getMessage());
            System.exit(0);
        }
    }

    // Functie om de extra opties uit een JSON file (database) uit te lezen en toe
    // te voegen aan een object van het type Opties.
    public void addExtraOpties(Opties opties) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("optiess.json")) {
            JSONObject obj = (JSONObject) parser.parse(reader);
            JSONArray extraOpties = (JSONArray) obj.get("extraOpties");
            for (Object o : extraOpties) {
                JSONObject optie = (JSONObject) o;
                String categorie = (String) optie.get("categorie");
                String naam = (String) optie.get("naam");
                Number prijsObj = (Number) optie.get("prijs");
                Integer prijs = prijsObj.intValue();
                opties.addExtraOpties(categorie, naam, prijs);
            }
        } catch (IOException | ParseException e) {
            validatieKeuze();
        }
    }

    // Funtie om de essentiële opties weer te geven aan de gebruiker, de functie
    // roept daarna ook de *displayExtraOpties* functie op
    // die de extra opties print.
    public void displayEssentieleOpties(Opties opties) {
        System.out.println("=====================================");
        System.out.println("~~ ESSENTIËLE OPTIES ~~");
        Map<String, List<Opties>> essentieleOpties = opties.getEssentieleOpties();
        for (String categorie : essentieleOpties.keySet()) {
            System.out.println("\n|" + categorie + "|");
            for (Opties optie : essentieleOpties.get(categorie)) {
                System.out.println("  - " + optie.getNaam() + " (" + optie.getPrijs() + " EUR)");
            }
        }
        displayExtraOpties(opties);
    }

    public void displayExtraOpties(Opties opties) {
        System.out.println("\n~~ EXTRA OPTIES ~~");

        Map<String, List<Opties>> extraOpties = opties.getExtraOpties();
        for (String categorie : extraOpties.keySet()) {
            System.out.println("\n|" + categorie.toUpperCase() + "|");
            for (Opties optie : extraOpties.get(categorie)) {
                System.out.println("  - " + optie.getNaam() + " (" + optie.getPrijs() + " EUR)");
            }
        }
        System.out.println("=====================================");
    }

    // Functie om de gebruiker zowel essentiële als extra opties te laten
    // selecteren, het neemt een Opties object in en returned een List van Opties
    // objecten.
    // Hierbij leest hij de JSON file uit en filtert alle categorieën en print deze
    // vervolgens.
    // Er wordt ook rekening gehouden met categorieën die al zijn verwerkt.
    public List<Opties> optiesJSON(Opties opties) {
        List<Opties> geselecteerdeOpties = new ArrayList<Opties>();
        Set<String> behandeldeCategorieen = new HashSet<String>();
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("opties.json")) {
            JSONObject obj = (JSONObject) parser.parse(reader);
            JSONArray essentieleOpties = (JSONArray) obj.get("essentieleOpties");
            JSONArray extraOpties = (JSONArray) obj.get("extraOpties");
            selecteerOpties(opties, geselecteerdeOpties, behandeldeCategorieen, essentieleOpties);
            selecteerExtraOpties(opties, geselecteerdeOpties, behandeldeCategorieen, extraOpties);
        } catch (IOException | ParseException e) {
            System.err.println(
                    "\nEr is een fout opgetreden tijdens het lezen van het bestand: " + e.getMessage());
            System.exit(0);
        }
        return geselecteerdeOpties;
    }

    private void selecteerOpties(Opties opties, List<Opties> geselecteerdeOpties, Set<String> behandeldeCategorieen,
            JSONArray essentieleOpties) {
        for (Object o : essentieleOpties) {
            JSONObject optie = (JSONObject) o;
            String categorie = (String) optie.get("categorie");
            if (!behandeldeCategorieen.contains(categorie)) {
                geselecteerdeOpties.add(kiesOptie("\nSelecteer " + categorie + ":", opties.getEssentieleOpties()));
                behandeldeCategorieen.add(categorie);
            }
        }
    }

    private void selecteerExtraOpties(Opties opties, List<Opties> geselecteerdeOpties,
            Set<String> behandeldeCategorieen,
            JSONArray extraOpties) {
        System.out.println("Wilt u extra opties kiezen? (ja / nee)");
        String keuze = input.nextLine().toLowerCase();
        if (keuze.equals("ja")) {
            for (Object o : extraOpties) {
                JSONObject optie = (JSONObject) o;
                String categorie = (String) optie.get("categorie");
                selecteerMeerOpties(opties, geselecteerdeOpties, behandeldeCategorieen, categorie);
            }
        }
    }

    private void selecteerMeerOpties(Opties opties, List<Opties> geselecteerdeOpties, Set<String> behandeldeCategorieen,
            String categorie) {
        if (!behandeldeCategorieen.contains(categorie)) {
            boolean meerOpties = true;
            while (meerOpties) {
                geselecteerdeOpties.add(kiesOptie("\nSelecteer " + categorie + " optie:", opties.getExtraOpties()));
                System.out.println("Wilt u meer " + categorie + " opties? (ja / nee)");
                String meerOptiesAntwoord = input.nextLine().toLowerCase();
                if (meerOptiesAntwoord.equals("nee")) {
                    meerOpties = false;
                }
            }
            behandeldeCategorieen.add(categorie);
        }
    }

    public Opties kiesOptie(String prompt, Map<String, List<Opties>> opties) {
        Scanner s = new Scanner(System.in);
        Opties gekozenOptie = null;

        while (gekozenOptie == null) {
            System.out.println(prompt);
            String input = s.nextLine();

            gekozenOptie = zoekOptie(input, opties);
            if (gekozenOptie != null) {
                if (!geselecteerdeOpties.contains(gekozenOptie)) {
                    geselecteerdeOpties.add(gekozenOptie);
                    VoegKortingToe(gekozenOptie, s);
                } else {
                    System.out.println("Deze optie is al gekozen. Kies een andere optie.");
                    gekozenOptie = null;
                }
            } else {
                System.out.println("Ongeldige optie. Kies een andere optie.");
            }
        }
        return gekozenOptie;
    }

    private Opties zoekOptie(String input, Map<String, List<Opties>> opties) {
        for (List<Opties> optieList : opties.values()) {
            for (Opties optie : optieList) {
                if (optie.getNaam().equalsIgnoreCase(input)) {
                    return optie;
                }
            }
        }
        return null;
    }

    private void VoegKortingToe(Opties optie, Scanner s) {
        System.out.println("Komt deze optie in aanmerking voor korting? (ja / nee)");
        String antwoord = s.nextLine();

        if (antwoord.equalsIgnoreCase("ja")) {
            Integer korting = 0;
            boolean validKorting = false;
            while (!validKorting) {
                System.out.print("Aantal procent korting voor deze optie is:  ");
                String kortingInput = s.nextLine();

                validKorting = berekenKorting(optie, validKorting, kortingInput);
            }
        }
    }

    private boolean berekenKorting(Opties optie, boolean validKorting, String kortingInput) {
        Integer korting;
        try {
            korting = Integer.parseInt(kortingInput);
            optie.setPrijs(optie.getPrijs() * (100 - korting) / 100);
            System.out.println("Milieu-korting van " + korting + "% toegepast op " + optie.getNaam());
            System.out.printf(
                    "De nieuwe prijs van " + optie.getNaam().toUpperCase() + " is " + optie.getPrijs() + "EUR\n");

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
        return validKorting;
    }

}

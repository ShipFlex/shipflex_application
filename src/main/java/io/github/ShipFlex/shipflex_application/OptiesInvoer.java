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
import java.io.File;

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
        try (FileReader reader = new FileReader("opties.json")) {
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
    // selecteren, het neemt een Opties object in en returned een List van Opties objecten.
    // Hierbij leest hij de JSON file uit en filtert alle categorieën en print deze vervolgens.
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

    // Functie die itereert over een JSON array bestaande uit essentiele opties en vraagt de gebruiker om een
    // optie te selecteren uit elke categorie die nog niet geselecteerd is.
    private void selecteerOpties(Opties opties, List<Opties> geselecteerdeOpties, Set<String> behandeldeCategorieen,
            JSONArray essentieleOpties) {
        for (Object o : essentieleOpties) {
            JSONObject optie = (JSONObject) o;
            String categorie = (String) optie.get("categorie");
            if (!behandeldeCategorieen.contains(categorie)) {
                geselecteerdeOpties
                        .add(kiesOptie("\nSelecteer een optie uit de categorie '" + categorie.toUpperCase() + "'",
                                opties.getEssentieleOpties()));
                behandeldeCategorieen.add(categorie);
            }
        }
    }

    // Functie die de gebruiker vraagt of er nog extra opties gekozen moeten worden, herhaalt vervolgens een JSON-array
    // met extra opties en vraagt de gebruiker vervolgeens om een optie te selecteren uit elke categorie die nog niet
    // geselecteerd is.
    private void selecteerExtraOpties(Opties opties, List<Opties> geselecteerdeOpties,
            Set<String> behandeldeCategorieen,
            JSONArray extraOpties) {
        System.out.println("\nWilt u extra opties kiezen? (ja / nee)");
        String keuze = input.nextLine().toLowerCase();
        if (keuze.equals("ja")) {
            for (Object o : extraOpties) {
                JSONObject optie = (JSONObject) o;
                String categorie = (String) optie.get("categorie");
                selecteerMeerOpties(opties, geselecteerdeOpties, behandeldeCategorieen, categorie);
            }
        }
    }

    // Functie die de gebruiker vraagt om extra opties uit een specifieke categorie te selecteren
    // totdat de gebruiker van de applicatie ervoor kiest om te stoppen.
    private void selecteerMeerOpties(Opties opties, List<Opties> geselecteerdeOpties, Set<String> behandeldeCategorieen,
            String categorie) {
        if (!behandeldeCategorieen.contains(categorie) && vraagExtraOpties(categorie)) {
            boolean meerOpties = true;
            while (meerOpties) {
                Opties geselecteerdeOptie = kiesOptie(
                        "\nSelecteer een optie uit de categorie '" + categorie.toUpperCase() + "'",
                        opties.getExtraOpties());
                geselecteerdeOpties.add(geselecteerdeOptie);

                meerOpties = vraagMeerOpties(categorie);
            }
        }
        behandeldeCategorieen.add(categorie);
    }

    // Functie die vraagt of de gebruiker extra opties uit een specifieke categorie willen selecteren.
    private boolean vraagExtraOpties(String categorie) {
        System.out.println("\nWilt u opties uit de categorie '" + categorie.toUpperCase() + "' kiezen? (ja / nee)");
        while (true) {
            String extraOptiesAntwoord = input.nextLine().toLowerCase();
            if (extraOptiesAntwoord.equals("ja")) {
                return true;
            } else if (extraOptiesAntwoord.equals("nee")) {
                return false;
            } else {
                System.out.println("Ongeldige invoer. Voer alstublieft 'ja' of 'nee' in.");
            }
        }
    }

    // Functie die vraagt of de gebruiker meer extra opties uit een specifieke categorie willen selecteren.
    private boolean vraagMeerOpties(String categorie) {
        System.out.println(
                "\nWilt u meer opties uit deze categorie (" + categorie.toUpperCase() + ") kiezen? (ja / nee)");
        while (true) {
            String meerOptiesAntwoord = input.nextLine().toLowerCase();
            if (meerOptiesAntwoord.equals("ja")) {
                return true;
            } else if (meerOptiesAntwoord.equals("nee")) {
                return false;
            } else {
                System.out.println("Ongeldig invoer. Voer alstublieft 'ja' of 'nee' in.");
            }
        }
    }

    // Vraagt de gebruiker om een optie te selecteren uit een lijst met optiest en retourneert de geselecteerde optie(s)
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

    // Zoekt in een lijst met opies naar een opties met een overeenkomende naam en retourneert dit.
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

    // Methode vraagt of een bepaalde optie in aanmerking komt voor korting en zo ja, kan de gebruiker handmatig
    // het kortinspercentage in voeren. Vervolgens wordt de methode berekenKorting gecalled.
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
            System.out.println("(Milieu) Korting van " + korting + "% toegepast op " + optie.getNaam());
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

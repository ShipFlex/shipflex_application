package main.java.io.github.ShipFlex.shipflex_application;

import java.util.Scanner;

public class KlantInvoer {
    private Scanner input;

    public KlantInvoer() {
        this.input = new Scanner(System.in);
    }

    public Schip getSchipGegevens() {
        String scheepstype;
        while (true) {
            System.out.println("Kies Scheepstype:");
            System.out.println("""
                    ┌─────────────┐┌─────────────┐
                    │ 1-Motorboot ││ 2-Zeilboot  │
                    ├─────────────┤├─────────────┤
                    │ 3-Catamaran ││  4-Jacht    │
                    └─────────────┘└─────────────┘  
                              """);
            int scheepstypeChoice;
            try {
                scheepstypeChoice = Integer.parseInt(input.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Ongeldige keze, kies 1,2,3,4");
                continue;
            }
            switch (scheepstypeChoice) {
                case 1:
                    scheepstype = "Motorboot";
                    break;
                case 2:
                    scheepstype = "Zeilboot";
                    break;
                case 3:
                    scheepstype = "Catamaran";
                    break;
                case 4:
                    scheepstype = "Jacht";
                    break;
                default:
                    System.out.println("Ongeldige keuze, kies 1,2,3,4");
                    continue;
            }
            break;
        }

        return new Schip(scheepstype);
    }

    public EOpties getEOpties() {
        String romptype = getRompType();
        String stuurinrichtingtype = getStuurinrichting();
        String motortype = getMotorType();
        return new EOpties(romptype, stuurinrichtingtype, motortype);
    }

    public String getRompType() {
        String romptype;
        while (true) {
            System.out.println("Kies Romp:");
            System.out.println("""                    
                    ┌─────────────┐┌─────────────┐
                    │ 1-Aluminium ││   2-Staal   │
                    ├─────────────┤├─────────────┤
                    │ 3-Composiet ││   4-Hout    │
                    └─────────────┘└─────────────┘  
                      """);
            int rompchoice;
            try {
                rompchoice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ongeldige keuze kies 1,2,3,4");
                continue;
            }
            switch (rompchoice) {
                case 1:
                    romptype = "Alluminium";
                    break;
                case 2:
                    romptype = "Staal";
                    break;
                case 3:
                    romptype = "Composiet";
                    break;
                case 4:
                    romptype = "Hout";
                    break;
                default:
                    System.out.println("Ongeldige keuze kies 1,2,3,4");
                    continue;
            }
            break;
        }
        return romptype;
    }

    public String getStuurinrichting() {
        String stuurinrichting;
        while (true) {
            System.out.println("Kies Stuur:");
            System.out.println("""                    
                    ┌─────────────┐┌─────────────┐
                    │      1      ││      2      │   
                    │ Hydraulisch ││  Elektrisch │
                    ├─────────────┤├─────────────┤
                    │      3      ││      4      │ 
                    │  Mechanisch ││   Elektro   │
                    │             ││ Hydraulisch │   
                    └─────────────┘└─────────────┘  
                      """);
            int stchoice;
            try {
                stchoice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ongeldige keuze kies 1,2,3,4");
                continue;
            }
            switch (stchoice) {
                case 1:
                    stuurinrichting = "Hydraulisch";
                    break;
                case 2:
                    stuurinrichting = "Elektrisch";
                    break;
                case 3:
                    stuurinrichting = "Mechanisch";
                    break;
                case 4:
                    stuurinrichting = "Elektro Hydraulisch";
                    break;
                default:
                    System.out.println("Ongeldige keuze kies 1,2,3,4");
                    continue;
            }
            break;
        }
        return stuurinrichting;
    }

    public String getMotorType() {
        String motortype;
        while (true) {
            System.out.println("Kies Motortype:");
            System.out.println("""                    
                    ┌─────────────┐┌─────────────┐
                    │    Diesel   ││    Diesel   │   
                    │     40pk    ││     60pk    │
                    ├─────────────┤├─────────────┤
                    │  Elektrisch ││  Elektrisch │    
                    │     20kW    ││     40kW    │
                    └─────────────┘└─────────────┘  
                      """);
            int motorchoice;
            try {
                motorchoice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ongeldige keuze kies 1,2,3,4");
                continue;
            }
            switch (motorchoice) {
                case 1:
                    motortype = "Diesel 40pk";
                    break;
                case 2:
                    motortype = "Diesel 60pk";
                    break;
                case 3:
                    motortype = "Elektrisch  20kW";
                    break;
                case 4:
                    motortype = "Elektrisch 40kW";
                    break;
                default:
                    System.out.println("Ongeldige keuze kies 1,2,3,4");
                    continue;
            }
            break;
        }
        return motortype;



    }
        public ExtraOpties getExtraOpties(){
            String airco = getAircoKlant();
            String navigatie = getNavigatieKlant();
            String zonnepanel = getZonnepanelKlant();
            return new ExtraOpties(airco, navigatie, zonnepanel);
        }

        public String getAircoKlant(){
            String keuzeAirco;
            while(true){
                System.out.println("Wilt u Airco? j/n");
                keuzeAirco = input.nextLine().toUpperCase();
                if(keuzeAirco.equals("J")){
                    String a = "Airco ✓ ";
                    return a;

                }
                else if(keuzeAirco.equals("N")){
                    String b = "Geen Airco X ";
                    return b;
                }
                else{
                    System.out.println("Druk op j of n");
                }

            }
        }

        public String getNavigatieKlant(){
        String keuzeNavigatie;
            while(true){
                System.out.println("Wilt u Navigatie? j/n");
                keuzeNavigatie = input.nextLine().toUpperCase();
                if(keuzeNavigatie.equals("J")){
                    String a = "Navigatie ✓ ";
                    return a;
                }
                else if (keuzeNavigatie.equals("N")){
                    String b =  "Geen Navigatie X ";
                    return b;

                }
                else{
                    System.out.println("Druk op j of n");
                }
            }
        }
        public String getZonnepanelKlant(){
        String keuzeZonnepanel;
            while (true){
                System.out.println("Wilt u Zonnepanelen? j/n");
                keuzeZonnepanel = input.nextLine().toUpperCase();
                if(keuzeZonnepanel.equals("J")){
                    String a = "Zonnepanelen ✓ ";
                    return a;

                }
                else if(keuzeZonnepanel.equals("N")){
                    String b = "Geen Zonnepanel X ";
                    return b;

                }
                else{
                    System.out.println("Druk op j of n");
                }
            }
        }


    public Klant getKlantGegevens() {
        int keuze;
        while (true) {
            System.out.println("Welk type klant heeft u?");
            System.out.println("1. Particulier");
            System.out.println("2. Bedrijf");

            try {
                keuze = Integer.parseInt(input.nextLine());
                if (keuze == 1 || keuze == 2) {
                    break;
                }
            } catch (NumberFormatException e) {
                // hij negeert dit, de loop gaat door
            }

            // Op dit punt betekent het dat de ingevulde keuze ongeldig is
            System.out.println("Ongeldige keuze, kies 1 of 2.");
        }
        // Begin vragenlijst
        System.out.println("~~Invulformulier Klantgegevens ~~\n");
        switch (keuze) {
            // Type klant = particulier
            case 1:
                System.out.print("Voer de naam van de klant in: ");
                String naam = input.nextLine();

                System.out.print("Voer het adres van de klant in: ");
                String adres = input.nextLine();

                System.out.print("Voer de postcode van de klant in: ");
                String postcode = input.nextLine();

                System.out.print("Voer de plaats van de klant in: ");
                String plaats = input.nextLine();

                System.out.print("Voer het land van de klant in: ");
                String land = input.nextLine();

                System.out.print("Voer het email van de klant in: ");
                String emailadres = input.nextLine();

                System.out.print("Voer het telefoonnummer (+316) van de klant in: ");
                String telefoonnummer = input.nextLine();

                System.out.print("Voer het klantnummer van de klant in: ");
                String klantnummer = input.nextLine();

                return new Particulier(naam, adres, postcode, plaats, land, emailadres, telefoonnummer, klantnummer);

            // Type klant = bedrijf
            case 2:
                System.out.print("Voer de naam van het bedrijf in: ");
                String bedrijfsnaam = input.nextLine();

                System.out.print("Voer het adres van het bedrijf in: ");
                String bedrijfsadres = input.nextLine();

                System.out.print("Voer de postcode van het bedrijf in: ");
                String bedrijfspostcode = input.nextLine();

                System.out.print("Voer de plaats van het bedrijf in: ");
                String bedrijfsplaats = input.nextLine();

                System.out.print("Voer het land van het bedrijf in: ");
                String bedrijfsland = input.nextLine();

                System.out.print("Voer het telefoonnummer van het bedrijf in: ");
                String bedrijfstelefoon = input.nextLine();

                System.out.print("Voer het emailadres van het bedrijf in: ");
                String bedrijfsemailadres = input.nextLine();

                System.out.print("Voer het KVK-nummer van het bedrijf in: ");
                String kvkNummer = input.nextLine();

                return new Bedrijf(bedrijfsnaam, bedrijfsadres, bedrijfspostcode, bedrijfsplaats, bedrijfsland, bedrijfsemailadres, bedrijfstelefoon, bedrijfsemailadres, kvkNummer);
        }


        return null;
    }
}
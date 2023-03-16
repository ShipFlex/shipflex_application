package main.java.io.github.ShipFlex.shipflex_application;

public class Bedrijf implements Printgegevens {
    private String bedrijfsNaam;
    private String bedrijfsAdres;
    private String bedrijfsPostcode;
    private String bedrijfsPlaats;
    private String bedrijfsTel;
    private String bedrijfsWebsite;
    private String bedrijfsKvk;

    // Constructor
    public Bedrijf(String bedrijfsNaam, String bedrijfsAdres, String bedrijfsPostcode, String bedrijfsPlaats,
            String bedrijfsTel, String bedrijfsWebsite, String bedrijfsKvk) {
        this.bedrijfsNaam = bedrijfsNaam;
        this.bedrijfsAdres = bedrijfsAdres;
        this.bedrijfsPostcode = bedrijfsPostcode;
        this.bedrijfsPlaats = bedrijfsPlaats;
        this.bedrijfsTel = bedrijfsTel;
        this.bedrijfsWebsite = bedrijfsWebsite;
        this.bedrijfsKvk = bedrijfsKvk;
    }

    // Getters & Setters
    public static String getBedrijfsNaam() {
        return "MyCompany";
    }

    public void setBedrijfsNaam(String bedrijfsNaam) {
        this.bedrijfsNaam = bedrijfsNaam;
    }

    public static String getBedrijfsAdres() {
        return "Voorbeeldstraat 3";
    }

    public void setBedrijfsAdres(String bedrijfsAdres) {
        this.bedrijfsAdres = bedrijfsAdres;
    }

    public static String getBedrijfsPostcode() {
        return "0101AB";
    }

    public void setBedrijfsPostcode(String bedrijfsPostcode) {
        this.bedrijfsPostcode = bedrijfsPostcode;
    }

    public static String getBedrijfsPlaats() {
        return "Den Haag";
    }

    public void setBedrijfsPlaats(String bedrijfsPlaats) {
        this.bedrijfsPlaats = bedrijfsPlaats;
    }

    public static String getBedrijfsTel() {
        return "064206910";
    }

    public void setBedrijfsTel(String bedrijfsTel) {
        this.bedrijfsTel = bedrijfsTel;
    }

    public static String getBedrijfsWebsite() {
        return "MyCompany.org";
    }

    public void setBedrijfsWebsite(String bedrijfsWebsite) {
        this.bedrijfsWebsite = bedrijfsWebsite;
    }

    public static String getBedrijfsKvk() {
        return "0102 0304";
    }

    public void setBedrijfsKvk(String bedrijfsKvk) {
        this.bedrijfsKvk = bedrijfsKvk;
    }

    @Override
    public void Printgegevens() {
        System.out.println("\n" + "~~ Bedrijfsgegevens ~~");
        System.out.print(getBedrijfsNaam() + "\n" + getBedrijfsAdres() + "" + getBedrijfsPostcode()
                + "\n" + getBedrijfsPlaats() + "\n" + getBedrijfsTel() + "\n" + getBedrijfsWebsite() + "\n" +
                getBedrijfsKvk());
    }
}

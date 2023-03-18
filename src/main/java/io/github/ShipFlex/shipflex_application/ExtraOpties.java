package main.java.io.github.ShipFlex.shipflex_application;

public class ExtraOpties {
    private String airco;
    private String navigatie;
    private String zonnepanel;

    public ExtraOpties(String airco, String navigatie, String zonnepanel){
        this.airco = airco;
        this.navigatie = navigatie;
        this.zonnepanel = zonnepanel;

    }

    public String getAirco(){
        return airco;

    }
    public void setAirco(String airco){
        this.airco = airco;

    }
    public String getNavigatie(){
        return navigatie;

    }
    public void setNavigatie(String navigatie){
        this.navigatie = navigatie;
    }
    public String getZonnepanel(){
        return zonnepanel;

    }
    public void setZonnepanel(String zonnepanel){
        this.zonnepanel = zonnepanel;
    }
}

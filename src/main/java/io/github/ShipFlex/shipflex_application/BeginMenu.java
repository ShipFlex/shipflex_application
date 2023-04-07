package main.java.io.github.ShipFlex.shipflex_application;

import java.util.Scanner;

public class BeginMenu {

    private Scanner input;

    public BeginMenu(Scanner input){

        this.input = new Scanner(System.in);
    }
    
public void welkomsBericht(){

    System.out.println("Welkom bij de OfferteGenerator van shipflex");
    System.out.println("Selecteer hieronder wat u wilt doen:");
    System.out.println("1. klanttypes inzien  2. Uitgebreide optielijst weergeven  3. offerte genereren");

      int optie = input.nextInt();

      if (optie != 1 || optie != 2 || optie != 3) {        
       System.out.println("Ongeldige invoer, probeer opnieuw!");
      }

      if (optie == 1) {


      }

      if (optie == 2){
    

      }

      if (optie == 3){


      }
}

}

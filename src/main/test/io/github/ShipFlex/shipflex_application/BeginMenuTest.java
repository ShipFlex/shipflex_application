//package
package main.test.io.github.ShipFlex.shipflex_application;

//imports
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import main.java.io.github.ShipFlex.shipflex_application.BeginMenu;

public class BeginMenuTest {
// Deze test controleerd of de methode 'welkomsBericht' correct werkt wanneer de input geldig is
    @Test

    public void testStart() throws IOException {
   //Arrange 

    BeginMenu menu = new BeginMenu();
    int optie = 1;
   //Act


   menu.start();

   //Assert

    }

    // Deze test is ook bedoeld voor de methode 'welkomsBericht' maar controleert of de methode om kan gaan met onjuiste input

  @Test 
    public void testWelkomsBerichtMetVerkeerdeInput(){
        //Arrange 
        int input = 5;
        BeginMenu menu2 = new BeginMenu();

        Scanner scanner = new Scanner ("Ongeldige invoer, probeer opnieuw!");

        



        //Act



        menu2.welkomsBericht();



        //Assert

         }

         /*Na het maken van de offerte wordt er gevraagd aan de gebruiker of hij de offerte naar een extern bestand wilt printen
          * hierop kan de gebruiker ja of nee antwoorden, deze test controleert of je alleen die 2 waardes kunt invullen
          */


    @Test

    public void start(){

   //Arrange

   BeginMenu menu3 = new BeginMenu();

   //Act

   //Assert

    }

    @Test

    public void testValideerCategorieKeuze(){

   //Arrange 

   BeginMenu menu4 = new BeginMenu();

   //Act

   //Assert

    }
}
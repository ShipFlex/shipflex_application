//package
package main.test.io.github.ShipFlex.shipflex_application;

//imports
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;



import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import main.java.io.github.ShipFlex.shipflex_application.BeginMenu;

public class BeginMenuTest {

   
// Deze test controleerd of de methode 'welkomsBericht' correct werkt wanneer de input geldig is
    @Test

    public void testWelkomsBerichtValidInput() throws IOException {
   //Arrange 

    BeginMenu menu = new BeginMenu();
    ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
   //Act
InputStream sysInBackup = System.in;
System.setIn(in);
int optie = menu.welkomsBericht();

  
   //Assert

   assertEquals(1,optie);
   System.setIn(sysInBackup);

    }

    // Deze test is ook bedoeld voor de methode 'welkomsBericht' maar controleert of de methode om kan gaan met onjuiste input

  @Test 
    public void testWelkomsBerichtInvalidInput() throws IOException {
        //Arrange 
        
        BeginMenu menu2 = new BeginMenu();
        ByteArrayInputStream in = new ByteArrayInputStream("5".getBytes());

      
        //Act

        InputStream sysInBackup = System.in;
        System.setIn(in);
        int optie2 = menu2.welkomsBericht();

        menu2.welkomsBericht();



        //Assert

        assertNotEquals(5,optie2);

         }

         /*in deze methode wordt getest of de start methode goed omgaat met ongeldige invoer
          */


    @Test

    public void testStartInvalidInput(){

   //Arrange

   BeginMenu menu3 = new BeginMenu();



   //Act

   String input = "ongeldig";
   InputStream in = new ByteArrayInputStream(input.getBytes());
   System.setIn(in);
   int result = menu3.welkomsBericht();
   //Assert

   assertEquals(5, result);

    }

  }

    

   
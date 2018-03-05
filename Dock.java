/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaport;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Michael Kidwell
 * CMSC335
 * Project 2
 * Professor Bryan Nilsen 
 * This class defines Docks of type thing
 */
public class Dock extends Thing {
    Ship ship;

    public Dock(Scanner sc, HashMap hm) {
        super(sc, hm);
    }
    
    public void setShip(Ship sp){
        ship = sp;
    }
    public Ship getShip(){
        return ship;
    }
}

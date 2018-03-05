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
 * This class defines CargoShip of type Ship
 */
public class CargoShip extends Ship {
    double cargoValue, cargoVolume, cargoWeight;

    public CargoShip(Scanner sc, HashMap hm) {
        super(sc, hm);
    }
    
    public String toString () {
        String st = "Cargo ship: " + super.toString();
        
        /*if (jobs.size() == 0)
            return st;
        for (Job mj: jobs) st += "\n - " + mj;
            return st;
        */
        return st;
 } 
}

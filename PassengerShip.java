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
 * Passgenger Ship class of type Ship
 */
public class PassengerShip extends Ship{
    int numberOfOccupiedRooms, numberOfPassengers, numberOfRooms;

    public PassengerShip(Scanner sc, HashMap hm) {
        super(sc, hm);
    }
    public String toString () {
        String st = "Passenger ship: " + super.toString();
        
        /*if (jobs.size() == 0)
            return st;
        for (Job mj: jobs) st += "\n - " + mj;
            return st;
        */
        return st;
 } 
}

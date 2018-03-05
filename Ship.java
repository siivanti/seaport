/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Michael Kidwell
 * CMSC335
 * Project 2
 * Professor Bryan Nilsen 
 * Ship class extends Thing
 */
public class Ship extends Thing {
    PortTime arrivalTime, dockTime;
    double width, weight, length, draft;
    ArrayList<Job> jobs;

    public Ship(Scanner sc, HashMap hm) {
        super(sc, hm);
        weight = Double.parseDouble(sc.next());
        length = Double.parseDouble(sc.next());
        width = Double.parseDouble(sc.next());
        draft = Double.parseDouble(sc.next());
        
    }
    public double getWeight(){
        return weight;
    }
    public double getLength(){
        return length;
    }
    public double getWidth(){
        return width;
    }
    public double getDraft(){
        return draft;
    }
    
}

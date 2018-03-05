/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Michael Kidwell
 * CMSC335
 * Project 4
 * Professor Bryan Nilsen 
 * SeaPort Class of type Thing 
 */
public class SeaPort extends Thing {
    
    static ArrayList<Dock> docks = new ArrayList<Dock>();
    static ArrayList<Ship> que;
    static ArrayList<Ship> ships = new ArrayList<Ship>();
    static ArrayList<Person> persons = new ArrayList<Person>(); 
    static ArrayList<Job> jobs = new ArrayList<Job>();
        
    public SeaPort(Scanner sc, HashMap hm){
        super(sc, hm);
    }    
    public static void addPship(Scanner sc, HashMap hm){
        ships.add(new PassengerShip(sc, hm));        
    }
    public static void addJob(Scanner sc, HashMap hm, JPanel panel, JTextArea aa, JTextArea bb){
        jobs.add(new Job(sc, hm, panel, aa, bb));        
    }
    public static void addCship(Scanner sc, HashMap hm){
        ships.add(new CargoShip(sc, hm));
    }
     public static void addPersons(Scanner sc, HashMap hm, JTextArea aa, JTextArea bb){
        persons.add(new Person(sc, hm, aa, bb));
    }
     public static void addDock(Scanner sc, HashMap hm){
        docks.add(new Dock(sc, hm));
    }
    public static ArrayList getJobs(){
        return jobs;
    }
    public static ArrayList getPersons(){
        return persons;
    }
    public static ArrayList getPersons(SeaPort port){
        ArrayList<Thing> returnPersons = new ArrayList<>();
        for(Person per: persons){
            if(per.getParent() == port.getIndex()){
                returnPersons.add(per);
            }
        }
        return returnPersons;
    }  
    public static ArrayList getShips(){
        return ships;
    }
   public static ArrayList getShips(SeaPort port){
        ArrayList<Thing> returnShips = new ArrayList<>();
        ArrayList<Dock> returnDocks = getDocks(port);
        for(Ship ship: ships){
            if(ship.getParent() == port.getIndex()){
                returnShips.add(ship);                
            }
            for(Dock dock: returnDocks){
                if(ship.getParent() == dock.getIndex()){
                    returnShips.add(ship);
                }
            }
        }
        
        return returnShips;
    }
    public static ArrayList getDocks(SeaPort port){
        ArrayList<Thing> returnDocks = new ArrayList<>();
        for(Dock doc: docks){
            if(doc.getParent() == port.getIndex()){
                returnDocks.add(doc);
            }
        }
        return returnDocks;
    }  
    
    public static ArrayList getDocks(){
        return docks;
    }
    
    
    public void dockShips(){
        
        for(Ship shi: ships){
            for(Dock dk: docks){
                if(dk.index == shi.parent){
                    dk.setShip(shi);
                }
            }
        }
    } 
     
    @Override
    public String toString () {
        String st = "\n\nSeaPort: " + super.toString();
        for (Dock md: docks){ 
            st += "\n" + md + "\n " + md.getShip();
            
            //add line to include ships
        }
           /* st += "\n\n --- List of all ships in que:";
        for (Ship ms: que ) st += "\n > " + ms;
           */ st += "\n\n --- List of all ships:";
        for (Ship ms: ships) st += "\n > " + ms;
            st += "\n\n --- List of all persons:";
        for (Person mp: persons) st += "\n > " + mp;
             return st;
    } 
    
    
}

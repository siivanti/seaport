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
 * Thing class extends Comparable
 */
public class Thing implements Comparable <Ship> {
     int index, parent;
     String name;
    //pass in hashmap variable
     //populate map
    public Thing(){        
    }
    
    public Thing(Scanner sc){
        name = sc.next();
        index = Integer.parseInt(sc.next());
        parent = Integer.parseInt(sc.next()); 
    }   
    public Thing(Scanner sc, HashMap hm){
        name = sc.next();
        index = Integer.parseInt(sc.next());
        parent = Integer.parseInt(sc.next()); 
        hm.put(index, this);
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getParent(){
        return parent;
    }
    
    public int getIndex(){
        return index;
    }
    
   
   
    @Override
    public String toString(){        
        return this.name + " " + this.index;
    }
    
    public int compareTo(Ship ship) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

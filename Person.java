/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaport;

import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JTextArea;

/**
 * Michael Kidwell
 * CMSC335
 * Project 4
 * Professor Bryan Nilsen 
 * Person class of type Thing
 */
public class Person extends Thing {
    String skill;
    JTextArea jtb, jta;
    //assignment to ship
    //assigemtn to job
    public Person(Scanner sc, HashMap hm, JTextArea a, JTextArea b) {        
        super(sc, hm);    
        jta = a;
        jtb = b;
        skill = sc.next();
        jta.append(this.toString() + "\n");
    }
    
    public String getSkill(){
        return skill;
    }
    
    @Override
    public String toString(){
        return super.toString() + " " + skill;
    }
}

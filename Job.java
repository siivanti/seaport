/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
/**
 * Michael Kidwell
 * CMSC335
 * Project 2
 * Professor Bryan Nilsen 
 * This class defines jobs
 */
public class Job extends Thing implements Runnable{
    double duration;
    JPanel panel;
    boolean noKillFlag = true, goFlag = true; 
    JButton suspend = new JButton("Suspend");
    JButton cancel = new JButton("cancel");
    JButton contin = new JButton("continue");
    JTextArea jtb, jta;
    private ArrayList<String> resources = new ArrayList<>();
    
    public Job(Scanner sc, HashMap hm, JPanel panel, JTextArea aa, JTextArea bb) {
        super(sc, hm);
        this.panel = panel;
        duration = Double.parseDouble(sc.next()); 
        try{
            while(sc.hasNext()){
                resources.add(sc.next());                
            }
        }catch(Exception a){}
        jta = aa;
        jtb = bb;        
    }
    
    @Override
    public void run(){
        boolean addflag;
        JProgressBar jbarr = new JProgressBar();
        jbarr.setStringPainted (true);
            
        JLabel jobName = new JLabel(this.getName());
        JLabel parentName = new JLabel("Dock: " + String.valueOf(this.getParent()));       
        
        panel.add(jbarr);
        panel.add(jobName);
        panel.add(parentName);
        
        panel.add(suspend);
        panel.add(cancel);
        cancel.addActionListener ((ActionEvent e) -> {
            setKillFlag();
            System.out.println("Job Cancelled");
         });
        suspend.addActionListener ((ActionEvent e) -> {
            toggleGoFlag ();
        });
        
        long time = System.currentTimeMillis();
        long startTime = time;
        double stopTime = time + 1000* duration;       
        double totalDuration = stopTime - time;    
        while (!Thread.currentThread().isInterrupted()) {
            
            synchronized(SeaPort.ships){
                addflag = true;
                while (time < stopTime && noKillFlag) {
                try {            
                    Thread.sleep(100);                       
                    if(addflag){
                        for(String str: resources){
                            //somehow add this to jtb
                            System.out.println(str);
                            jtb.setText("");
                            jtb.append("\n" + str);
                        }
                        addflag = false;
                    }
                } catch (InterruptedException ex) {            
                }  
                if(goFlag){
                    time += 1000;
                    jbarr.setValue ((int)(((time - startTime) / totalDuration) * 100));
                }
            }         
            //jbarr.setValue (100);
        }
        }
    }
 public void setKillFlag () {
    noKillFlag = false;  
    panel.remove(cancel);
    
    
  } 
 public void toggleGoFlag(){
     goFlag = !goFlag;             
 } 
}

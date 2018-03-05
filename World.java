
package seaport;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

/**
 * Michael Kidwell
 * CMSC335
 * Project 4
 * Professor Bryan Nilsen 
 * World class extends thing
 */
public class World extends Thing {
    
    private ArrayList<SeaPort> ports = new ArrayList<SeaPort>();
    private ArrayList<Thing> results = new ArrayList();
    private PortTime time;
    private File file;
    private Scanner sc;
    private JPanel panel;
    private HashMap hm;
    private HashMap<String, Object> nameMap = new HashMap<String, Object>();
    private HashMap<String, Object> skillMap = new HashMap<String, Object>();    
    private int count = 0;
    private JTextArea jta, jtb;
    //private ExecutorService executor = Executors.newFixedThreadPool(5);
    private JFrame frame;
    
    public World(File b, HashMap hm, JPanel panel, JTextArea aa, JTextArea bb, JFrame frame){
        this.panel = panel;
        this.hm = hm;
        this.frame = frame;
        file = b;
        jta = aa;
        jtb = bb;
        readIn(file);
        byName();
        bySkill();
    }
    public void byName(){
        //iterate through index keys - pull names - populate byName with keys = name and value = object
        System.out.println("Creating names hash");
        Iterator itr = hm.entrySet().iterator();
        
             while(itr.hasNext()) {
                 
                HashMap.Entry pair = (HashMap.Entry)itr.next();
                
                int key = (int)pair.getKey();                
                Thing value = (Thing)pair.getValue();
                String newInput = value.getName();                
                nameMap.put(newInput, value);
                
            }
    }
    public void bySkill(){        
        System.out.println("Creating skill hash");
        ArrayList<Person> persons = SeaPort.getPersons();
         
        for(Person per: persons){
            String skill = per.getSkill();
            skillMap.put(skill, per);
        }       
           
    }
    public ArrayList getPorts(){
        return ports;
    }
    public void readIn(File a){
        
        try{
        sc = new Scanner(a);
        }catch (FileNotFoundException h) {         
            h.printStackTrace();
        }
        while(sc.hasNextLine()){
            
            String line = sc.nextLine();
            String ab[] = line.split(" ");
            if(ab[0].equals("//")){continue;} 
            
            process(line);   
            
        }
        
        ports.get(0).dockShips();
        //line below prints everything in ports
        //System.out.println(ports);       
        
    }
    public HashMap getByNames(){
        return nameMap;
    }
    public HashMap getBySkill(){
        return skillMap;
    }
    
    void process (String st) { 
        
        Scanner scan = new Scanner (st);  
        if (!scan.hasNext()){           
            return;
        }    
        
        switch (scan.next()) {
           
            case "port" :  
                ports.add(new SeaPort(scan, hm));                ;
                //processPort(scan);
                //data here to process remaining data
                break; 
            case "pship" : SeaPort.addPship(scan, hm);
                break;
            case "cship" : SeaPort.addCship(scan, hm);
                break;
            case "person" : SeaPort.addPersons(scan, hm, jta, jtb);
                break;
            case "dock" : SeaPort.addDock(scan, hm);
                break;
            case "job" : SeaPort.addJob(scan,hm,panel, jta, jtb);
                break;
            
        }
    } 
    
 public void processPort(Scanner scan){
     
 }
 public ArrayList doSort(String str){
     ArrayList<Ship> ship = SeaPort.getShips();
       
     if(str.equals("weight")){        
         Collections.sort(ship, (a, b) -> a.getWeight() < b.getWeight() ? 1 : a.getWeight() == b.getWeight() ? 0 : -1);
         System.out.println("Sorted by Weight ");         
     }
     else if(str.equals("length")){
         Collections.sort(ship, (a, b) -> a.getLength() < b.getLength() ? 1 : a.getLength() == b.getLength() ? 0 : -1);
         System.out.println("Sorted by Length " );   
     }     
     else if(str.equals("draft")){
         Collections.sort(ship, (a, b) -> a.getDraft() < b.getDraft() ? 1 : a.getDraft() == b.getDraft() ? 0 : -1);
         System.out.println("Sorted by Draft " );   
     }
     else if(str.equals("width")){
         Collections.sort(ship, (a, b) -> a.getWidth() < b.getWidth() ? 1 : a.getWidth() == b.getWidth() ? 0 : -1);
         System.out.println("Sorted by Width " );   
     }
     else if(str.equals("name")){         
          Collections.sort(ship, (a, b) -> a.getName().compareToIgnoreCase(b.getName()));
         System.out.println("Sorted by Name " );   
     }
     
     return ship;
 }
 public void beginMulti() throws InterruptedException{
     System.out.println("Getting ship jobs");
     ArrayList<Ship> ships = SeaPort.getShips();
     ArrayList<Job> jobs = SeaPort.getJobs();
     ArrayList<ArrayList> returnData = new ArrayList<>();
        //jobs for specific ship: ship.getParent == job.getParent
        for (Ship ship : ships) {
            ArrayList<Job> shipJobs = new ArrayList<>();
            int parent = ship.getParent();         
            for(Job job: jobs){
                 
                if(job.getParent() == parent){
                    shipJobs.add(job);                    
                }               
            }             
           beginThreads(shipJobs, ship);
           returnData.add(shipJobs);
                     
        }
     
 }
 public void beginThreads(ArrayList arr, Ship ship) throws InterruptedException{
   
     ArrayList<Job> jobsArray = arr;
     if(!arr.isEmpty()){
         for(Job job: jobsArray){
             new Thread (job).start();
             Thread.sleep(10);
         }
     }
    
 }
 public void beginResources(){
     //grab 1 seaport get global array of Persons // begin exec serv array with
     // arraylist.size nubmer of threads in pool
 }
    public void threadTime() throws InterruptedException{
        ArrayList<Dock> dockList = SeaPort.getDocks();
        ArrayList<Job> jobList = SeaPort.getJobs();
        ArrayList<Ship> shipList = SeaPort.getShips();
        
        for(Dock dock: dockList){
            //list of docks specific jobs
            ArrayList<Job> dockJobs = new ArrayList<>();
            
            //grab all jobs for this dock
            for(Job job: jobList){
                if(job.getParent() == dock.getIndex()){
                    dockJobs.add(job);
                    //convert to callable
                   
                }
            }
            //create thread pool of these jobs
            
            //go ship by ship, complete jobs on each ship
            for(Ship ship: shipList){
                if(ship.getParent() == dock.getIndex()){
                    //execute all job threads on ship
                                 
                    for(Job job: dockJobs){
                        //executor.execute(job);                        
                        System.out.println("executing: " + job);
                        
                    }
                    
                    //okay maybe we do 1 at a time
                    //create "future"  using "submit" and call "get" to make it
                    //wait
                                       
                }
            }
        }
        
    }  
}

/*
nonworking invokeall
List<Callable<Object>> passList = new ArrayList<Callable<Object>>();
 Callable<Object> c = Executors.callable(job);
                    passList.add(c);
List<Future<Object>> futures = executor.invokeAll(passList);   
*/


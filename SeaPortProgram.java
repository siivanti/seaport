/*
 * Michael Kidwell
 * CMSC335
 * Project 4
 * Professor Bryan Nilsen 
 * SeaPortProgram of type JFrame main driver of program and contains main GUI elements
 */
package seaport;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
/**
 *
 * @author Owner
 */
public class SeaPortProgram extends JFrame {
    private ArrayList<Thing> results = new ArrayList();
    private JFrame frame = new JFrame("SeaPort Program"); 
    private World world;
    private File file;
    private String[] searchStr = { "name", "index", "skill" }; 
    
    private String[] sortStr = { "weight", "length", "width", "draft", "name"};
    private JButton sortData = new JButton("Sort Data");
    
    private JButton selectFile  = new JButton("Upload File");     
    private JLabel searchFor = new JLabel("Search:  ");
    private JButton search  = new JButton("Search");      
    private JTextArea jta = new JTextArea (10,20);   
    private JTextArea jtb = new JTextArea(10,20);
    private JTextField tf1 = new JTextField("", 15);
    private JComboBox searchBox = new JComboBox(searchStr);
    private JComboBox sortBox = new JComboBox(sortStr);
    
    private HashMap<String, Object> searchHash = new HashMap<String, Object>();  
    private HashMap hm = new HashMap(); 
    private JTree tree;
    JPanel mainPanelc = new JPanel();
    
    public SeaPortProgram (){
       
       frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);  
       frame.setSize(400,400);          
       frame.setVisible(true);
      
       JScrollPane jsp = new JScrollPane (tree);
       
      
       GridBagConstraints c = new GridBagConstraints();
       c.gridwidth = GridBagConstraints.REMAINDER;
       c.anchor = GridBagConstraints.NORTHWEST;
       c.insets = new Insets(5, 0, 5, 0);
       
       JPanel mainPanelx = new JPanel(new GridBagLayout());   
       JPanel mainPanely = new JPanel();
        
       mainPanelx.add(selectFile,c);
       c.gridwidth=1;       
       mainPanelx.add(search,c);  
       
       mainPanelx.add(searchBox,c);  
       mainPanelx.add(tf1);
       c.insets = new Insets(5, 10, 5, 0);  
       
       mainPanelx.add(sortData,c);
       mainPanelx.add(sortBox,c);    
       mainPanely.add(new JScrollPane(jta));       
       mainPanely.add(jtb);
       jta.append("RESOURCE POOL" + "\n\n");
       jtb.append("CURRENTLY WORKING");
       
       mainPanelc.setLayout(new BoxLayout(mainPanelc, BoxLayout.Y_AXIS));      
       frame.add(new JScrollPane(mainPanelc), BorderLayout.EAST);      
       frame.add(mainPanelx, BorderLayout.NORTH);        
       frame.add(jsp);      
       frame.add(mainPanely, BorderLayout.SOUTH);
       frame.pack();         
      
       
       selectFile.addActionListener(new ActionListener(){                  
          
           @Override
            public void actionPerformed(ActionEvent e){                       
                
                JFileChooser jfc = new JFileChooser (".");
                int upload = jfc.showOpenDialog(frame);                
                if (upload == JFileChooser.APPROVE_OPTION) {
                    file = jfc.getSelectedFile();                
                    System.out.println("File: " + file.getName() + "."); 
                    world = new World(file, hm, mainPanelc, jta, jtb, frame);                  
                    tree = createTree(world);                    
                    jsp.setViewportView(tree);                    
                    frame.pack();
                    
                   try {     
                         
                         world.beginMulti();
                         //world.threadTime(); 
                        
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SeaPortProgram.class.getName()).log(Level.SEVERE, null, ex);
                    
                      
                       }
                   
                } else {
                    System.out.println("Open command cancelled by user.");
                }   
               
                
            }}); 

       search.addActionListener (e -> newSearch((String)(searchBox.getSelectedItem()), tf1.getText()));
       //sortData.addActionListener( e -> doSort((String)(sortBox.getSelectedItem())));
    }    
    
    public JTree createTree(World world){
        //create root node and create tree from root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        JTree tree1 = new JTree(root);
        Stack<DefaultMutableTreeNode> stack = new Stack<>();
      
        DefaultMutableTreeNode portsNode = new DefaultMutableTreeNode("Ports");        
     
        ArrayList<SeaPort> ports = world.getPorts();
        for (SeaPort port : ports) {
            
            stack.push(new DefaultMutableTreeNode(port.getName()));
            DefaultMutableTreeNode tempNode = stack.pop(); 
            DefaultMutableTreeNode docksNode = new DefaultMutableTreeNode("Docks");   
            DefaultMutableTreeNode shipsNode = new DefaultMutableTreeNode("Ships");        
            DefaultMutableTreeNode personNode = new DefaultMutableTreeNode("Persons");
            
            ArrayList<Dock> docks = SeaPort.getDocks((SeaPort)port);         
            ArrayList<Ship> ships = SeaPort.getShips((SeaPort)port);
            ArrayList<Person> persons = SeaPort.getPersons((SeaPort)port);
                for(Dock doc: docks){                       
                    docksNode.add(new DefaultMutableTreeNode(doc));                
                } 
                for(Ship ship: ships){
                    shipsNode.add(new DefaultMutableTreeNode(ship));
                }
                for(Person person: persons){
                    personNode.add(new DefaultMutableTreeNode(person));
                }
            tempNode.add(shipsNode);
            tempNode.add(docksNode);
            tempNode.add(personNode);
            root.add(tempNode);
        } 
        return tree1;
    }
    
    public void doSort(String str){
        System.out.println("Begin sort");
        ArrayList<Ship> ships = world.doSort(str);
        //jta.append("\n>>>>>>> Sorted By: " + str + "\n\n");
        
        for(Ship shi: ships){
        //    jta.append(String.valueOf(shi)+"\n");
        }       
    }
    
    public void newSearch(String str, String param){
        Iterator itr = hm.entrySet().iterator();
         
         if(str.equals("index")){
             System.out.println("Searching Index");
             int parama = Integer.parseInt(param);
             while(itr.hasNext()) {
                
                HashMap.Entry pair = (HashMap.Entry)itr.next();
                int key = (int)pair.getKey();
                Thing value = (Thing)pair.getValue();              
                
                if(key == parama ){
                    System.out.println("found");
                    System.out.println(value);
                    JOptionPane.showMessageDialog(frame,String.valueOf(value));                
                }
                
            }
         }
         else if(str.equals("name")){
             System.out.println("Searching names");
             searchHash = world.getByNames();
             Iterator namesIt = searchHash.entrySet().iterator();
             while(namesIt.hasNext()) {                
                HashMap.Entry pair = (HashMap.Entry)namesIt.next();
                String key = String.valueOf(pair.getKey());
                Thing value = (Thing)pair.getValue();              
                
                if(key.equals(param)){
                    System.out.println("found");
                    System.out.println(value);
                    JOptionPane.showMessageDialog(frame,String.valueOf(value));
                }
                
            }
         }
         else if(str.equals("skill")){
             System.out.println("Searching Skills");
             searchHash = world.getBySkill();
             Iterator namesIt = searchHash.entrySet().iterator();
             while(namesIt.hasNext()) {                
                HashMap.Entry pair = (HashMap.Entry)namesIt.next();
                String key = String.valueOf(pair.getKey());
                Thing value = (Thing)pair.getValue();              
                
                if(key.equals(param)){
                    System.out.println("found");
                    System.out.println(value);
                    JOptionPane.showMessageDialog(frame,String.valueOf(value));
                }
                
            }
         }   
    }
    public static void main(String[] args) {
        SeaPortProgram testrun = new SeaPortProgram();
        
    }      
}

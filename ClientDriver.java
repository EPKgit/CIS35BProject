/*
Lab 1
Eric Kim
4/15/17
Windows 8 Netbeans
Prof. Sukhjit Singh 
 */

import Automotive.*;
import Util.*;
import Adapter.*;
import Exception.*;
import Scale.*;
import Client.*;

public class ClientDriver 
{

    public static void main(String[] args)
    {
        /*try
        {
            Automobile temp = new Automobile("RollsRoyce.txt");
            System.out.println("-------------------------------");
            System.out.println("START\n"+temp+"\n\n" + "-------------------------------");
            temp.deleteOptionSet("Color");
            System.out.println("DEL COLOR\n"+temp+"\n\n"+ "-------------------------------");
            temp.addOptionSet("Color");
            temp.addOption("Color","Red",300);
            temp.addOption("Color", "Blue", 1);
            System.out.println("ADD NEW COLOR\n"+temp+"\n\n"+ "-------------------------------");
            temp.updateOption("Color", "Red", "Green");
            temp.updateOption("Blue", 63.4);
            System.out.println("UPDATE COLORS\n"+temp+"\n\n"+ "-------------------------------");
            
            System.out.println(temp.setOptionChoice("Picnic Table", "present"));
            System.out.println("Total price after choosing a picnic table\n"+temp.getTotalPrice()+"\n\n"+ "-------------------------------");
            
            System.out.println(temp.setOptionChoice("Picnic Table", "not present"));
            System.out.println("Total price after removing the picnic table\n"+temp.getTotalPrice()+"\n\n"+ "-------------------------------");
        }
        catch(AutoException e)
        {
            
        }
        
        
        System.out.println("Interface Testing With LinkedHashMap");
        
        CreateAuto a1 = new BuildAuto();
        a1.buildAuto("Ford_Focus_Wagon_ZTW.txt");
        //a1.buildAuto("RollsRoyce.txt");
        
        a1.printAuto("Focus Wagon ZTW");
        //a1.printAuto("Corniche III");
        
        UpdateAuto a2 = (UpdateAuto)a1;
        
        Thread t1 = new Thread(new EditOptions("Focus Wagon ZTW", "Color", "Paint Job", a2));
        Thread t2 = new Thread(new EditOptions("Focus Wagon ZTW", "Paint Job", "Paint Color", a2));
        t1.start();
        t2.start();
        
        System.out.println("\n\n\n\nAfter Thread Update");
        Thread t3 = new Thread(new PrintAuto("Focus Wagon ZTW", a1));
        t3.start();
        //a1.printAuto("Corniche III");
        */
        CarModelOptionsIO t1 = new CarModelOptionsIO();
        
    }
    
}

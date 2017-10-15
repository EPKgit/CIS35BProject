/*
Lab 2
Eric Kim
4/26/17
Windows 8 Netbeans
Prof. Sukhjit Singh 
 
This class 
*/

package Adapter;

import Automotive.*;
import Util.*;
import Exception.*;

import java.util.LinkedHashMap;
import java.util.Collection;
import java.util.Iterator;

public abstract class ProxyAuto
{
    private boolean readyToPrint;
    
    private LinkedHashMap<String, Automobile> myobj;
    
    public ProxyAuto()
    {
        readyToPrint = true;
        myobj = new LinkedHashMap<String, Automobile>();
    }
    
    public void addAuto(Automobile a)
    {
        System.out.println("adding auto");
        myobj.put(a.getModel(), a);
    }
    
    public void buildAuto(String fileName, boolean isProperties)
    {
        Automobile temp;
        if(isProperties)
            temp = new FileIO().parsePropertiesFiles(fileName);
        else
            temp = new Automobile(fileName);
        myobj.put(temp.getModel(), temp);
    }
    
    public boolean readyToPrint() { return readyToPrint; }
    
    public void printAuto(String modelName)
    {
        Collection<Automobile> vals = myobj.values();
        Iterator<Automobile> iter = vals.iterator();
        while(iter.hasNext())
        {
            Automobile temp = iter.next();
            if(temp.getModel().compareTo(modelName) == 0)
                System.out.println(temp.toString());
        }
    }
    
    public Automobile getAuto(String modelName)
    {
        Collection<Automobile> vals = myobj.values();
        Iterator<Automobile> iter = vals.iterator();
        while(iter.hasNext())
        {
            Automobile temp = iter.next();
            if(temp.getModel().compareTo(modelName) == 0)
                return temp;
        }
        return null;
    }
    
    public synchronized void updateOptionSetName(String modelName, String optionSetName, String newName)
    {
        System.out.println("Start updating " + optionSetName +" to " + newName);
        readyToPrint = false;
        Collection<Automobile> vals = myobj.values();
        Iterator<Automobile> iter = vals.iterator();
        while(iter.hasNext())
        {
            Automobile temp = iter.next();
            if(temp.getModel().compareTo(modelName) == 0)
            {
                try
                {
                    temp.updateOptionSet(optionSetName, newName);
                }
                catch(AutoException e)
                {
                    System.out.println("Error #" + e);
                }
            }
        }
        readyToPrint = true;
        System.out.println("End updating " + optionSetName +" to " + newName);
        notifyAll();
    }
    public synchronized void updateOptionPrice(String modelName, String optionSetName, String optionName, double price)
    {
        readyToPrint = false;
        Collection<Automobile> vals = myobj.values();
        Iterator<Automobile> iter = vals.iterator();
        while(iter.hasNext())
        {
            Automobile temp = iter.next();
            if(temp.getModel().compareTo(modelName) == 0)
                try
                {
                    temp.updateOption(optionSetName, optionName, price);
                }
                catch(AutoException e)
                {
                    System.out.println("Error #" + e);
                }
        }
        readyToPrint = true;
        notifyAll();
    }
    
    public void fix(int errorno)
    {
        AutoException e = new AutoException(-1, "NULL");
        e.fix(errorno, null);
    }
    
    public String toString()
    {
        System.out.println("???");
        StringBuilder temp = new StringBuilder();
        Collection<Automobile> vals = myobj.values();
        Iterator<Automobile> iter = vals.iterator();
        while(iter.hasNext())
        {
            Automobile temp2 = iter.next();
            temp.append(temp2.getMake());
            temp.append(" ");
            temp.append(temp2.getModel());
            temp.append("\n");
        }
        System.out.println(temp);
        return temp.toString();
    }
}

/*
Lab 1
Eric Kim
4/15/17
Windows 8 Netbeans
Prof. Sukhjit Singh 
 
This class represents all options avaliable for each type of option
e.g. color which could contain blue or red or green
*/

package Automotive;

import java.util.Arrays;
import java.util.ArrayList;
import java.io.Serializable;
import Exception.*;
import java.util.Collections;

public class OptionSet implements Serializable
{
    
    private ArrayList<Option> opts;
    private String name;
    private Option choice;
    
    protected OptionSet(String s)
    {
        opts = new ArrayList<Option>();
        name = s;
    }
    
    protected void setName(String s) { name = s; }
    protected String getName() { return name; }
    
    protected boolean setOptionChoice(String optionName)
    {
        for(int x = 0; x < opts.size(); x++)
        {
            if(opts.get(x).getName().compareTo(optionName) == 0)
            {
                choice = opts.get(x);
                return true;
            }
        }
        return false;
    }
        
    protected Option getOptionChoice()
    {
        return choice;
    }
    
    protected void addOption(String n, double p)
    {
        opts.add(new Option(n,p));
    }
    protected boolean deleteOption(String n)
    {
        int index = indexOf(n);
        if(index == -1)
            return false;
        else
        {
            opts.remove(index);
            return true;
        }
    }
    protected boolean updateOption(String name, String newName)
    {
        int index = indexOf(name);
        if(index == -1)
            return false;
        else
        {
            opts.get(index).setName(newName);
            return true;
        }
    }
    protected boolean updateOption(String name, double newPrice)
    {
        int index = indexOf(name);
        if(index == -1)
            return false;
        else
        {
            opts.get(index).setPrice(newPrice);
            return true;
        }
    }
    
    protected Option getOption(String name) throws AutoException
    {
        int index = indexOf(name);
        if(index == -1)
            throw new AutoException(3, "Option Does Not Exist");
        else
            return opts.get(index);
    }
    
    protected Option getOption(int index) throws AutoException
    {
        if(index >= 0 && index < opts.size())
            return opts.get(index);
        else
            throw new AutoException(2, "Requested Index Out Of Bounds");
    }
    
    protected int indexOf(String n)
    {
        for(int x = 0; x < opts.size(); x++)
        {
            if(opts.get(x).getName().compareTo(n) == 0)
                return x;
        }
        return -1;
    }
    
    protected String print()
    {
        StringBuilder temp = new StringBuilder(name);
        temp.append(":");
        Collections.sort(opts);
        for(int x = 0; x < opts.size(); x++)
        {
            temp.append("\n   ");
            temp.append(opts.get(x).print());
        }
        return temp.toString();
    }
}

/*
Lab 1
Eric Kim
4/15/17
Windows 8 Netbeans
Prof. Sukhjit Singh 

This class models an automotive by containing an array
of feature sets, that contains an array of features
to choose from
 */
package Automotive;

import Exception.AutoException;
import Util.*;

import java.util.Arrays;
import java.io.Serializable;
import java.util.ArrayList;

public class Automobile implements Serializable
{
    private String make, model;
    private ArrayList<OptionSet> opSet;
    private ArrayList<Option> choice;
    private double basePrice;
    
    public Automobile(String fileName)
    {
        make  = "defaultmake";
        model = "defaultmodel";
        basePrice = -1;
        opSet = new ArrayList<OptionSet>();
        choice = new ArrayList<Option>();
        
        FileIO f = new FileIO();
        try
        {
            f.buildAutoObject(fileName, this);
        }
        catch (AutoException e)
        {
            e.fix(1, this);
            try
            {
                f.buildAutoObject(fileName, this);
            }
            catch (AutoException e2)
            {
                System.out.println("Please fix the file and restart.");
            }
        }      
    }
    
    public Automobile()
    {
        make  = "defaultmake";
        model = "defaultmodel";
        basePrice = -1;
        opSet = new ArrayList<OptionSet>();
        choice = new ArrayList<Option>();
    }
    
    public void setBasePrice(double d) throws AutoException
    { 
        if(d < 0)
            throw new AutoException(5, "Price cannot be negative");
        basePrice = d;
    }
    public double getBasePrice() { return basePrice; }
    
    public String getModel() { return model; }
    public void setModel(String s) throws AutoException
    {
        if(s == null)
            throw new AutoException(6, "Model name cannot be null");
        model = s;
    }
    public String getMake() { return make; }
    public void setMake(String s) { make = s; }      
    
    public String getOptionChoice(String setName)
    {
        int index = indexOf(setName);
        if(index == -1)
            return new String("Incorrect SetName");
        else
            return opSet.get(index).getOptionChoice().getName();// doesn't use choice arraylist because arrylist ordering
                                                                // cannot be guaranteed after any changes
    }
    public double getOptionChoicePrice(String setName)
    {
        int index = indexOf(setName);
        if(index == -1)
            return -1.0;
        else
            return opSet.get(index).getOptionChoice().getPrice();// doesn't use choice arraylist because arrylist ordering
                                                                // cannot be guaranteed after any changes
    }

    public boolean setOptionChoice(String setName, String optionName)
    {
        int index = indexOf(setName);
        if(index == -1)
            return false;
        else
        {
            return opSet.get(index).setOptionChoice(optionName);
        }
    }
    
    public double getTotalPrice()
    {
        double total = 0;
        for(int x = 0; x < opSet.size(); x++)
            if(opSet.get(x).getOptionChoice() != null)
                total += opSet.get(x).getOptionChoice().getPrice();
        return total;
    }
    
    
    public void addOptionSet(String optionSetName)
    {
        opSet.add(new OptionSet(optionSetName));
        choice.add(opSet.get(indexOf(optionSetName)).getOptionChoice());
    }
    public void addOption(String optionSetName, String optionName, double optionPrice) throws AutoException
    {
        int index = indexOf(optionSetName);
        if(index == -1)
            throw new AutoException(4, "OptionSet Not Found");
        else
            opSet.get(index).addOption(optionName, optionPrice);
    }
    
    public void deleteOptionSet(String optionSetName) throws AutoException
    {
        int index = indexOf(optionSetName);
        if(index == -1)
            throw new AutoException(4, "OptionSet Not Found");
        else
        {
            opSet.remove(index);
        }
    }
    public boolean deleteOption(String optionSetName, String optionName) throws AutoException
    {
        int index = indexOf(optionSetName);
        if(index == -1)
            throw new AutoException(4, "OptionSet Not Found");
        else
        {
            opSet.get(index).deleteOption(optionName);
            return true;
        }
    }
    
    public boolean deleteOption(String optionName)//if optionSetName is omitted delete the first instance found
    {
        for(int x = 0; x < opSet.size(); x++)
        {
            if(opSet.get(x).deleteOption(optionName))
                return true;
        }
        return false;
    }
    public void updateOptionSet(String optionSetName, String newOptionSetName) throws AutoException
    {
        int index = indexOf(optionSetName);
        if(index == -1)
            throw new AutoException(4, "OptionSet Not Found");
        else
        {
            opSet.get(index).setName(newOptionSetName);
        }
    }
    public boolean updateOption(String optionSetName, String optionName, String newOptionName) throws AutoException
    {
        int index = indexOf(optionSetName);
        if(index == -1)
            throw new AutoException(4, "OptionSet Not Found");
        else
        {
            return opSet.get(index).updateOption(optionName, newOptionName);
        }
    }
    public boolean updateOption(String optionSetName, String optionName, double newOptionPrice) throws AutoException
    {
        int index = indexOf(optionSetName);
        if(index == -1)
            throw new AutoException(4, "OptionSet Not Found");
        else
        {
            return opSet.get(index).updateOption(optionName, newOptionPrice);
        }
    }
    public boolean updateOption(String optionName, String newOptionName) throws AutoException
    {
        for(int x = 0; x < opSet.size(); x++)
        {
            if(opSet.get(x).updateOption(optionName, newOptionName))
                return true;
        }
        throw new AutoException(3, "Option Does Not Exist");
    }
    public boolean updateOption(String optionName, double newOptionPrice)
    {
        for(int x = 0; x < opSet.size(); x++)
        {
            if(opSet.get(x).updateOption(optionName, newOptionPrice))
                return true;
        }
        return false;
    }
    public String readOptionSet(String optionSetName)
    {
        int index = indexOf(optionSetName);
        if(index == -1)
            return new String();
        else
            return opSet.get(index).print();
    }
    
    public OptionSet getOptionSet(String optionSetName)
    {
        int index = indexOf(optionSetName);
        return opSet.get(index);
    }
    
    public int indexOfChoice(String n)
    {
        for(int x = 0; x < choice.size(); x++)
        {
            if(choice.get(x) != null && choice.get(x).getName().compareTo(n) == 0)
                return x;
        }
        return -1;
    }
    
    public int indexOf(String n)
    {
        for(int x = 0; x < opSet.size(); x++)
        {
            if(opSet.get(x).getName().compareTo(n) == 0)
                return x;
        }
        return -1;
    }
    
    public String getOptionChoices()
    {
        StringBuilder temp = new StringBuilder();
        for(int x = 0; x < opSet.size(); x++)
        {
            temp.append(opSet.get(x).getName());
            temp.append(":");
            temp.append(getOptionChoice(opSet.get(x).getName()));
            temp.append("\n");    
        }
        return temp.toString();
    }
    
    public String toString()
    {
        StringBuilder temp = new StringBuilder(make);
        temp.append(" ");
        temp.append(model);
        temp.append("\nBasePrice: ");
        temp.append(basePrice);
        for(int x = 0; x < opSet.size(); x++)
        {
            temp.append("\n");
            temp.append(opSet.get(x).print());
        }
        return temp.toString();
    }
}

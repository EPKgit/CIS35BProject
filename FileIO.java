/*
Lab 1
Eric Kim
4/15/17
Windows 8 Netbeans
Prof. Sukhjit Singh 
 
This is a utility class used for fileio
*/
package Util;
import Automotive.*;
import Exception.*;
import java.io.*;
import java.util.*;

public class FileIO
{
    private final String ERRORLOGFILENAME = "ErrorLog.txt";
    
    public Automobile buildAutoObject(String fileName, Automobile car) throws AutoException
    {
        try
        {
            FileReader ifile = new FileReader(fileName);
            BufferedReader buff = new BufferedReader(ifile);
            boolean eof = false;
            String line;
           
            car.setMake(buff.readLine());
            car.setModel(buff.readLine());
            car.setBasePrice(Double.parseDouble(buff.readLine()));
             
            while(!eof)
            {
                line = buff.readLine();
                if (line == null)
                    eof = true;
                else
                {
                    if(line.length() > 0 && line.charAt(0) == '.')
                    {
                        String optionSet = line.substring(1); // cut off the period at the front
                        car.addOptionSet(optionSet);// new optionset
                        line = buff.readLine();
                        while(line != null && line.length() != 1 && line.charAt(0) != '#') // go into a loop of adding options to that option set until the # character is reached
                        {
                            car.addOption(optionSet, line.substring(0,line.indexOf('$')), Double.parseDouble(line.substring(line.indexOf('$')+1)));
                            line = buff.readLine();
                        }
                    }
                    else
                    {
                        throw new AutoException(1, "Invalid File Format.");
                    }
                    
                }
            }
            buff.close();
        } 
        catch (IOException e)
        {
            throw new AutoException(6, "File Not Found");
        }
        return car;
    }
    
    public void serializeAuto(String fileName, Automobile car)
    {
        try
        {

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(car);
            out.close();
        }
        catch(IOException e)
        {
            System.out.println("Error ­­ " + e.toString());
        }
    }
    
    public Automobile deserializeAuto(String fileName)
    {
        try
        {

            ObjectInputStream in =  new ObjectInputStream(new FileInputStream(fileName));
            Automobile temp = (Automobile)in.readObject();
            in.close();
            return temp;
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Error ­­ " + e.toString());
        }
        catch(IOException e)
        {
            System.out.println("Error ­­ " + e.toString());
        }
        return new Automobile("ERROR");
    }
    
    
    
    public void LogError(int errorno, String error)
    {
        try
        {
            File ofile = new File(ERRORLOGFILENAME);
            FileWriter temp1 = new FileWriter(ofile, true);
            BufferedWriter temp2 = new BufferedWriter(temp1);
            temp2.write(""+errorno+" "+error+"\n");
            temp2.close();
            temp1.close();
        }
        catch(IOException e)
        {
            System.out.println("Error Logging Error");
        }
        
    }

    public Automobile parsePropertiesFiles(String fileName)
    {
        Automobile temp = new Automobile();
        Properties props = new Properties();
        try
        {
            FileInputStream in = new FileInputStream(fileName);
            props.load(in);
            String CarMake = props.getProperty("CarMake"); 
            temp.setMake(CarMake);
            
            if(!CarMake.equals(null))
            {
                try
                {
                    /*
                    Enumeration<?> e = props.propertyNames();
                    String CarModel = props.getProperty("CarModel");
                    temp.setModel(CarModel);
                    String previousOptionSet = "";
                    props.load(in);
                    while(e.hasMoreElements())
                    {
                        String key = (String)e.nextElement();
                        System.out.println(key);
                        if(key.startsWith("OptionValue"))
                        {
                            temp.addOption(previousOptionSet, props.getProperty(key), 0);
                            System.out.println("Adding option " + props.getProperty(key));
                        }
                        else if(key.startsWith("Option"))
                        {
                            previousOptionSet = props.getProperty(key);
                            temp.addOptionSet(props.getProperty(key));
                            System.out.println("Adding optionset " + props.getProperty(key));
                        }
                    }*/
                    String CarModel = props.getProperty("CarModel");
                    temp.setModel(CarModel);
                    temp.addOptionSet(props.getProperty("Option1"));
                    temp.addOptionSet(props.getProperty("Option2"));
                    temp.addOption(props.getProperty("Option1"), props.getProperty("OptionValue1a"), 0);
                    temp.addOption(props.getProperty("Option1"), props.getProperty("OptionValue1b"), 0);
                    temp.addOption(props.getProperty("Option2"), props.getProperty("OptionValue2a"), 0);
                    temp.addOption(props.getProperty("Option2"), props.getProperty("OptionValue2b"), 0);
                }
                catch(AutoException err){}
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }
        catch(IOException e)
        {
            System.out.println("Error opening File");
        }
        return temp;
    }
}

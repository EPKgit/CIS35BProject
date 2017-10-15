/*
Lab 4
Eric Kim
6/6/17
Windows 8 Netbeans
Prof. Sukhjit Singh 
 
This class represents the instantiation of the server 
*/

package Server;

import Automotive.*;
import java.util.Properties;
import java.io.*;
import java.net.*;
import Adapter.*;
import Util.*;
import Exception.*;

public class BuildCarModelOptions
{
    private AutoServer data;
    
    public BuildCarModelOptions(AutoServer a)
    {
        data = a;
        ServerSocket serverSocket = null;
        int portNumber= 4444;
        try
        {
            serverSocket = new ServerSocket(portNumber);
        } 
        catch (IOException e)
        {
            System.err.println(e);
            System.err.println("Could not listen on port: " + portNumber +".");
            System.exit(1);
        }
        while(true)
        {
            Socket clientSocket = null;
            try
            {
                System.out.println("waiting for client");
                clientSocket = serverSocket.accept();
                System.out.println("found client");
            }
            catch (IOException e)
            {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            try
            {
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                Object input = null;
                String outputLine = "the server has recieved your upload!";
                try
                {
                    System.out.println("1");
                    while ((input = in.readObject()) != null)
                    {
                        System.out.println("Got object");
                        if(input instanceof String)
                        {
                            System.out.println("stringrequest");
                            if(((String)input).compareTo("listrequest") == 0)
                            {
                                out.writeObject(data.toString());
                            }
                            else
                            {
                                System.out.println("2");
                                out.writeObject(data.getAuto((String)input));
                                System.out.println(data.getAuto((String)input));
                            }
                        } else if (input instanceof Properties)
                        {
                            System.out.println("upload");
                            out.writeObject(outputLine);
                            Automobile temp = createAutoMobileFromProperties((Properties)input);
                            addAutoToHash(temp);
                        }
                    }
                }
                catch(ClassNotFoundException e){}
                out.close();
                in.close();
                clientSocket.close();
            }
            catch(IOException e)
            {
                System.out.println("Error opening input or output stream");
            }
        }
    }
    public Automobile createAutoMobileFromProperties(Properties p)
    {
        Automobile temp = new Automobile();
        //try
        {
            String CarMake = p.getProperty("CarMake"); 
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
                    String CarModel = p.getProperty("CarModel");
                    temp.setModel(CarModel);
                    temp.addOptionSet(p.getProperty("Option1"));
                    temp.addOptionSet(p.getProperty("Option2"));
                    temp.addOption(p.getProperty("Option1"), p.getProperty("OptionValue1a"), 0);
                    temp.addOption(p.getProperty("Option1"), p.getProperty("OptionValue1b"), 0);
                    temp.addOption(p.getProperty("Option2"), p.getProperty("OptionValue2a"), 0);
                    temp.addOption(p.getProperty("Option2"), p.getProperty("OptionValue2b"), 0);
                }
                catch(AutoException err){}
            }
        }
        /*catch(FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }
        catch(IOException e)
        {
            System.out.println("Error opening File");
        }*/
        return temp;
    }
    
    public void addAutoToHash(Automobile a)
    {
        data.addAuto(a);
    }
}

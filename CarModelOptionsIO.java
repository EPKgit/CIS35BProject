/*
Lab 4
Eric Kim
6/6/17
Windows 8 Netbeans
Prof. Sukhjit Singh 
 
This class represents a
*/
package Client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.net.*;
import java.io.*;
import Automotive.*;

public class CarModelOptionsIO
{
    public CarModelOptionsIO()
    {
        Socket clientSocket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        try
        {
            clientSocket = new Socket("192.168.1.64", 4444);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } 
        catch (UnknownHostException e)
        {
            System.err.println("Unknown Host!");
            System.exit(1);
        }
        catch (IOException e)
        {
            System.err.println("Couldn't find server");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        System.out.println("Welcome to the client for the KBB prototype!");
        try
        {
            do
            {
                System.out.println("\n1. Enter a properties file for upload\n2. Configure a car from the server\nOr type exit to exit");
                
                fromUser = stdIn.readLine();
                try
                {
                    switch(Integer.parseInt(fromUser))
                    {
                        case 1:
                        {
                            System.out.println("\n\nPlease enter the name of the file to upload:");
                            String fileName = stdIn.readLine();
                            Properties temp = readPropFile(fileName);
                            out.writeObject(temp);
                            System.out.println(in.readObject());
                        }break;
                        
                        case 2:
                        {
                            System.out.println("\n\nHere is a list of all the cars available for customization:");
                            out.writeObject(new String("listrequest"));
                            System.out.println(in.readObject());
                            System.out.println("Please enter the name of the model of car you would like to edit:");
                            String modelName = stdIn.readLine();
                            System.out.println(modelName);
                            out.writeObject(modelName);
                            System.out.println("1");
                            Object toConfig = in.readObject();
                            System.out.println(toConfig.toString());
                            if(toConfig != null)
                            {
                                System.out.println("?");
                                SelectCarOption user = new SelectCarOption((Automobile)toConfig);
                                user.userInteraction();
                                System.out.println("??");
                            }
                            else
                            {
                                System.out.println("Your car could not be found");   
                            }
                            
                        }
                    }
                }
                catch(NumberFormatException e)
                { 
                    if(fromUser.compareToIgnoreCase("exit") != 0)
                        System.out.println("Invalid Input");
                }
                catch(ClassNotFoundException e) { System.err.println(e); }
            } while (fromUser.compareToIgnoreCase("exit") != 0);
            out.close();
            in.close();
            stdIn.close();
            clientSocket.close();
        }
        catch(IOException e){System.err.println(e);}
    }
    
    
    public Properties readPropFile(String fileName)
    {
        Properties props = new Properties();
        try
        {
            FileInputStream in = new FileInputStream(fileName);
            props.load(in);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }
        catch(IOException e)
        {
            System.out.println("Error opening File");
        }
        return props;
    }
}

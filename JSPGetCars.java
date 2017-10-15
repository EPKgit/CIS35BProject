package webApp;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.net.*;

public class JSPGetCars
{
	
	String listOfCars;
	
	public JSPGetCars()
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
        
        try
        {
	        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	        String fromServer;
	        String fromUser;
	        out.writeObject(new String("listrequest"));
	        
	        listOfCars = (String)in.readObject();
	        
	        out.close();
	        in.close();
	        clientSocket.close();
        }
        catch(ClassNotFoundException e) { System.err.println(e); }
        catch(IOException e) { System.err.println(e); }

    }
	
	public String toString() { return listOfCars; }
	
	
	
}

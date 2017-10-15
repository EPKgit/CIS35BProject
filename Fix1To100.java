/*
Lab 2
Eric Kim
4/26/17
Windows 8 Netbeans
Prof. Sukhjit Singh 
 
This class is a
*/
package Exception;

import java.util.Scanner;

public class Fix1To100 
{
    private final Scanner KB = new Scanner(System.in);
    
    public void fix1()
    {
        System.out.println("There was an issue with the file reading, please prrof-read the file,\n"
                           + "save it, then press any key to try and read the file again.");
       
        KB.next();
    }
    
    public double fix5()
    {
        System.out.println("Error setting car's base price, please enter new price :");
        return KB.nextDouble();        
    }
    
    public String fix6()
    {
        System.out.println("Error setting car's name, please enter new name :");
        return KB.nextLine();  
    }
}

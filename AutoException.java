/*
Lab 2
Eric Kim
4/26/17
Windows 8 Netbeans
Prof. Sukhjit Singh 
 
This class is an exception
*/

package Exception;

import Util.*;
import Automotive.*;

public class AutoException extends Exception
{
    private int error;
    private String name;
    
    public AutoException(int e, String n)
    {
        FileIO temp = new FileIO();
        error = e;
        name = n;
        temp.LogError(e, n);
    }
    
    public void fix(int errorno, Automobile car)
    {
        Fix1To100 temp = new Fix1To100();
        try
        {
            switch(errorno)
            {
                case 1: { temp.fix1(); } break;
                case 5: { car.setBasePrice(temp.fix5()); } break;
                case 6: { car.setModel(temp.fix6()); } break;
            }
        }
        catch(AutoException e)
        {
            System.out.println("Repeated Error Please Restart");
        }
    }
    
}

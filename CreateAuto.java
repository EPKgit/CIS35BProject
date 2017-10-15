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

public interface CreateAuto 
{
    public void buildAuto(String fileName, boolean isProperties);
    public void printAuto(String modelName);
    public boolean readyToPrint();
}

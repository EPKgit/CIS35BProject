/*
Lab 2
Eric Kim
4/26/17
Windows 8 Netbeans
Prof. Sukhjit Singh 
 
This class 
*/
package Adapter;

public interface UpdateAuto 
{
    public void updateOptionSetName(String modelName, String optionSetName, String newName);
    public void updateOptionPrice(String modelName, String optionSetName, String optionName, double price);
}

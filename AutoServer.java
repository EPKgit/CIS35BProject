/*
Lab 4
Eric Kim
6/6/17
Windows 8 Netbeans
Prof. Sukhjit Singh 
 
This class is the api that exposes server
methods and sets protocol for interacting
with the server
*/
package Server;

import Automotive.Automobile;

public interface AutoServer
{
    public void addAuto(Automobile a);
    public Automobile getAuto(String name);
}

/*
Lab 2
Eric Kim
4/26/17
Windows 8 Netbeans
Prof. Sukhjit Singh 
 
This class is an empty class acting as a base for RBA, inheriting from an
abstract class that implements the methods required in the iterfaces
*/
package Adapter;

import Server.*;

public class BuildAuto extends ProxyAuto implements CreateAuto, UpdateAuto, FixAuto, AutoServer{ }

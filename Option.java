/*
Lab 3
Eric Kim
5/13/17
Windows 8 Netbeans
Prof. Sukhjit Singh 
 
This class represents options chosen by OptionSet
*/
package Automotive;

import java.io.Serializable;

public class Option implements Comparable, Serializable
    {
        private String name;
        private double price;
        
        protected Option(String n, double p)
        {
            name = n; price = p;
        }
        
        protected String getName() { return name; }
        protected void setName(String s) { name = s; }
        protected double getPrice() { return price; }
        protected void setPrice(double d) { price = d; }
        
        protected String print()
        {
            StringBuilder temp = new StringBuilder(name);
            temp.append(": ");
            temp.append(price);
            return temp.toString();
        }
        
        public int compareTo(Object obj)
        {
            if(obj instanceof Option)
            {
                Option temp = (Option)obj;
                return this.getName().compareTo(temp.getName());
            }
            else
                return 0;
        }
    }
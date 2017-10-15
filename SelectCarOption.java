package Client;

import Automotive.*;
import java.io.*;

public class SelectCarOption 
{
    private Automobile toConfig;
    
    public SelectCarOption(Automobile a)
    {
        toConfig = a;
    }
    
    public void userInteraction()
    {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        String opSetChoice;
        String opChoice;
        try
        {
            do
            {
                System.out.println("Your car looks like this so far\n" + toConfig +"\n"+ toConfig.getOptionChoices()+"\nTotal Price = "+toConfig.getTotalPrice());
                System.out.println("Which optionSet would you like to choose for?");
                opSetChoice = stdIn.readLine();
                System.out.println("Which option would you like to choose?");
                opChoice = stdIn.readLine();
                toConfig.setOptionChoice(opSetChoice, opChoice);
                System.out.println("Your choice has been set, enter done if you would not like to continue.");
                input = stdIn.readLine();
            } while(input.compareToIgnoreCase("done") != 0);
        }
        catch(IOException e) {}
    }
    
}

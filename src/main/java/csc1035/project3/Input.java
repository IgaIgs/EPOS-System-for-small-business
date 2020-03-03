package csc1035.project3;
import java.util.Scanner;
public class Input{
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("What would you like to do today?" + "\n" +
                "1. Sell item"  + "\n" +
                "2. Edit stock"  + "\n" +
                "3. Check stock" + "\n" +
                "4. Exit");

        String theCommand = userInput.nextLine();
        if (theCommand.equals("1")) {
            System.out.println("What would you like to sell?" + "\n" +
                     "1. Enter product name:" + "\n" +
                    "2. Back");

            String newCommand = userInput.nextLine();
            if (newCommand.equals("1")){
                //Run Code from other team members
            }

            if (newCommand.equals("2")){

            }


        }

        if (theCommand.equals("2")){
            System.out.println("1.Add Stock" + "\n" +
                    "2. Remove Stock" + "\n" +
                    "3. Edit product attributes");
            String newCommand = userInput.nextLine();
            if (newCommand.equals("1")){
                //Run Code from other team members
            }
        }
        if (theCommand.equals("3")) {

        }
        if (theCommand.equals("4")) {
            System.exit(0);
        }
    }
}
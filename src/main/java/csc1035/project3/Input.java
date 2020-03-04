package csc1035.project3;
import java.util.Scanner;
public class Input {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String menuStatus = "0";
        while (menuStatus.equals("0")) {
            System.out.println("What would you like to do today?" + "\n" +
                    "1. Sell item" + "\n" +
                    "2. Edit stock" + "\n" +
                    "3. Check stock" + "\n" +
                    "4. Exit");
            String theCommand = userInput.nextLine();
            while (theCommand.equals("1")) {
                System.out.println("What would you like to sell?" + "\n" +
                        "1. Enter product number:" + "\n" +
                        "2. Display product categories" + "\n" +
                        "3. Back");

                String newCommand = userInput.nextLine();
                if (newCommand.equals("1")) {
                    //Blah Blah Blah
                }
                if (newCommand.equals("2")) {
                    //Blah Blah Blah
                } else if(newCommand.equals("3")){
                    break;
                }
            }
            while (theCommand.equals("2")) {
                System.out.println("1. Add Stock" + "\n" +
                        "2. Remove Stock" + "\n" +
                        "3. Edit product attributes" + "\n" +
                        "4. Back");
                String newCommand = userInput.nextLine();
                if(newCommand.equals("1")){
                    //Blah Blah Blah
                }
                if (newCommand.equals("2")){
                    //Blah Blah Blah
                }
                if(newCommand.equals("3")){
                    //Blah Blah Blah
                }else if(newCommand.equals("4")){
                    break;
                }

            }
            while(theCommand.equals("3")) {

            }
            while (theCommand.equals("4")) {
                System.exit(0);
            }
        }
    }
}
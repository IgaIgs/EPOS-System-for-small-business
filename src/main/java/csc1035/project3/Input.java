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
                    "4. Checkout" + "\n" +
                    "5. Exit");
            String theCommand = userInput.nextLine();

            while (theCommand.equals("1")) {
                System.out.println("What would you like to sell?" + "\n" +
                        "1. Enter product number:" + "\n" +
                        "2. Choose product from categories" + "\n" +
                        "3. Back");

                String newCommand = userInput.nextLine();
                if (newCommand.equals("1")) {
                    System.out.println("Please enter the product number:");
                    String theProductNumber = userInput.nextLine();
                    //Return product from database based on user input
                    //Mark product with flag so that when the user selects 'checkout' from the main menu it is sold
                }
                if (newCommand.equals("2")) {
                    //Display all categories
                    //User selects product to sell
                    //Ask user for quantity to be sold
                    //Mark product with flag so that when 'checkout' is selected the product is sold
                    //Return to main menu

                } else if(newCommand.equals("3")){
                    //Return to main menu / previous menu
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
                    //Prompt user to input the appropriate details for each product attribute.
                    //Eg; name, price and so on
                    //Code for inserting product into database
                }
                if (newCommand.equals("2")){
                    //Prompt user to input name or product number
                    //Display the product and ask if they are sure they want to remove it
                    //Go back to main menu
                }
                if(newCommand.equals("3")){
                    // insert code for selecting product
                    // Ask user which attributes need to be changed
                }else if(newCommand.equals("4")){
                    break;
                }

            }
            while(theCommand.equals("3")) {
                System.out.println("1. Display product categories:" + "\n" +
                    "2. Enter product number");
                 String newCommand = userInput.nextLine();
                 if(newCommand.equals("1")){
                     //Display the categories
                 }else if(newCommand.equals("2")){
                     //Prompt user to input product number and search database to display attributes
                }

            }
            while(theCommand.equals("4")){
                System.out.println("Are you sure you want to checkout? Y/N");
                String YorN = userInput.nextLine();
                if(YorN.equals("y") || YorN.equals("Y")){
                    //Code for printing receipt and stuff goes here

                }else if(YorN.equals("N") || YorN.equals("n")){
                    break;
                }
                //Code for checking out and printing receipt goes here
            }
            while (theCommand.equals("5")) {
                //This closes the program
                System.exit(0);
            }
        }
    }
}
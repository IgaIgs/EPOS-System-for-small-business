package csc1035.project3;
import CRUDInterface.CRUDInterface;
import CRUDInterface.CRUDTeamDb;
import java.util.Scanner;
public class Input {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String menuStatus = "0";
        CRUDInterface cI = new CRUDTeamDb();
        while (menuStatus.equals("0")) {
            System.out.println("What would you like to do today?" + "\n" +
                    "1. Sell item" + "\n" +
                    "2. Edit stock" + "\n" +
                    "3. Check stock" + "\n" +
                    "4. Checkout" + "\n" +
                    "5. Exit");

            int theCommand = userInput.nextInt();

            while (theCommand == 1) {
                System.out.println("What would you like to sell?");
                int id = getID();
                System.out.println("How much would you like to sell?");
                int qty = userInput.nextInt();
                cI.addToBasket(id, qty);
                System.out.println("You have added " + qty + " " + id + "to the basket");
                break;
            }

            while (theCommand == 2) {
                System.out.println("1. Add Stock" + "\n" +
                        "2. Remove Stock" + "\n" +
                        "3. Edit product attributes" + "\n" +
                        "4. Back");

                String newCommand = userInput.nextLine();

                if(newCommand.equals("1")){

                }

                if (newCommand.equals("2")){
                    System.out.println("What would you like to remove?");
                    int id  = getID();
                    cI.delete(id);
                }

                if(newCommand.equals("3")){
                    int id = getID();
                    System.out.println("What attribute would you like to update? 'Name' or 'Category'?");
                    String field = userInput.nextLine();
                    System.out.println("Input the new value");
                    String attribute = userInput.nextLine();
                    cI.updateProduct(id, field, attribute);

                }else if(newCommand.equals("4")){
                    break;
                }
            }

            while(theCommand == 3) {
                int id = getID();
                // cI.getStock(id);
            }

            while(theCommand == 4){
                System.out.println("Are you sure you want to checkout? Y/N");
                String YorN = userInput.nextLine();
                if(YorN.equals("y") || YorN.equals("Y")){

                    //Code for printing receipt and stuff goes here

                }else if(YorN.equals("N") || YorN.equals("n")){
                    break;
                }
            }
            while (theCommand == 5) {
                //This closes the program
                System.exit(0);
            }
        }
    }


    private static int getID(){
        // runs the code showing categories etc or the search feature to get ID
        // then return the ID
        Scanner userInput = new Scanner(System.in);
        System.out.println("1. Display product categories:" + "\n" +
                "2. Enter product number");
        int firstChoice = userInput.nextInt();
        CRUDInterface cI = new CRUDTeamDb();
        if(firstChoice == 1){
            cI.printCategories(cI.getCategories());
            int secondChoice = userInput.nextInt();
            cI.readProduct(secondChoice);
            return userInput.nextInt();
        }
        // TODO this is only temporary so that we can run it before we're finished
        return 0;
    }

}
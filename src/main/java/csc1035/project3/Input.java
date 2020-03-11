package csc1035.project3;
import CRUDInterface.CRUDInterface;
import CRUDInterface.CRUDTeamDb;
import java.util.Scanner;

import DbTables.Product;
import csc1035.project3.HibernateUtil;
import org.hibernate.*;
public class Input {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String menuStatus = "0";
        CRUDInterface cI = new CRUDTeamDb();
        while (menuStatus.equals("0")) {
            System.out.println("What would you like to do today?" + "\n" +
                    "1. Add item to basket" + "\n" +
                    "2. Remove item from basket" + "\n" +
                    "3. Edit stock" + "\n" +
                    "4. Check stock" + "\n" +
                    "5. Checkout" + "\n" +
                    "6. Exit");

            int theCommand = userInput.nextInt();

            while (theCommand == 1) {
                System.out.println("What would you like to add to basket?");
                int id = getID();
                System.out.println("Quantity to add to basket");
                int qty = userInput.nextInt();
                cI.addToBasket(id, qty);
                System.out.println("You have added " + qty + " of " + id + " to the basket");
                break;
            }
            while(theCommand == 2){
                System.out.println("What would you like to remove?");
                int id = getID();
                System.out.println("What quantity would you like to remove?");
                int qty = userInput.nextInt();
                cI.removeFromBasket(id, qty);
                System.out.println("You have removed " + qty + " " + id + " from the basket");
                break;
            }

            while (theCommand == 3) {
                System.out.println("1. Add Stock" + "\n" +
                        "2. Remove Stock" + "\n" +
                        "3. Edit product attributes" + "\n" +
                        "4. Back");

                String newCommand = userInput.nextLine();
                while (newCommand.equals("1")){
                    System.out.println("Please enter the product name");
                            String name = userInput.nextLine();
                    System.out.println("Please enter the product category");
                    String category = userInput.nextLine();
                    System.out.println("Is it perishable? true/false");
                    Boolean perishable = userInput.nextBoolean();
                    System.out.println("What is the cost of the item:?");
                    double cost = userInput.nextDouble();
                    System.out.println("How much stock do you have?");
                    int stock = userInput.nextInt();
                    System.out.println("What is the sell price of the item?");
                    double sellPrice = userInput.nextDouble();
                    Product newitem = new Product(name, category, perishable, cost, stock , sellPrice);
                    cI.generalUpdate(newitem);

                }

                while (newCommand.equals("2")){
                    System.out.println("What would you like to delete from the database?");
                    int id  = getID();
                    cI.delete(id);
                    break;
                }

                while(newCommand.equals("3")){
                    int id = getID();
                    System.out.println("What attribute would you like to update? 'Name',\n 'Category' \n 'Perishable' \n 'Cost' \n 'Stock' \n 'Sell_price'?");
                    String fieldToUpdate = userInput.nextLine();
                    System.out.println("Input the new value");
                    String newValue = userInput.nextLine();
                    cI.updateProduct(id, fieldToUpdate, newValue);
                    break;

                }while (newCommand.equals("4")){
                    break;
                }
            }

            while(theCommand == 4) {
                int id = getID();
                cI.getStock(id);
            }

            while(theCommand == 5){
                System.out.println("Are you sure you want to checkout? Y/N");
                String YorN = userInput.nextLine();

                while (YorN.equals("y") || YorN.equals("Y")){
                    System.out.println("The basket total is " + cI.getBasketTotal());
                    double amountPaid = userInput.nextDouble();
                    cI.checkout(amountPaid);
                    break;

                }if (YorN.equals("N") || YorN.equals("n")){
                    break;
                }

            }
            while (theCommand == 6) {
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
        }else if(firstChoice == 2){
            System.out.println("Enter ID: ");
            return userInput.nextInt();
        }
        System.out.println("Error: invalid input");
        return -1;
    }

}
package csc1035.project3;

public class Transactions {

    private int getID (String description){
        /*
        Take a complete name (for example if user picked out item from a list)
        Or take a partially complete name (for example if user searched for "jam")
        Search the database and return the ID for that item
         */
        return 0;
    }

    public void updateStock (String description, int changeAmount){
        /*
        Called when user wants to update stock manually, or used as part of selling process
        Will use getID method to find position in database
        Then adjust stock according to changeAmount (selling an item would be -1, or ordering new stock could be +20)
         */
    }

    public void updateCost (String description, Double newCost) {
        /*
        Called when user wants to update cost
        Will use getID method to find position in database
        Then adjust to new cost
         */
    }

    public void updatePrice (String description, Double newPrice) {
        /*
        Called when user wants to update price
        Will use getID method to find position in database
        Then adjust to new price
         */
    }

    public void addProduct (String description, String category, Boolean perishable, Double cost, int stock, Double price){
        /*
        Used when user would like to add new product to database
         */
    }

    public int getStock (String description){
        /*
        Looks up item ID then returns stock for that item
         */
        return 0;
    }

    public void dropProduct (String description) {
        /*
        Find product ID then drop from database
         */
    }
}

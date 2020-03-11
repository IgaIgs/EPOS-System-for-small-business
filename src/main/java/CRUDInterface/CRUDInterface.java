package CRUDInterface;

import java.util.List;

public  interface CRUDInterface<E>  {

    /**
     * create entries in the database
     * @param e - entry to be created in a table
     */
    void create(E e);

    /**
     * update entries via previous instantiation
     * @param e - update the entry in a table
     */
    void generalUpdate(E e);

    /**
     * Method to update an attribute for given product
     * @param id - id of item to be updated
     * @param field - the field to be updated (to update stock, use "Stock")
     * @param newValue - the new value to be inserted
     */
    void updateProduct(int id, String field, String newValue);

    /**
     * Returns product's id and name by category given by user
     * @param userCat - product category specified by the user
     */
    void readProduct(int userCat);

    /**
     * This method takes a full or partial name of a product and prints out the FULL product name
     * followed by this product's ID
     * @param name - the full or partial name of a product the user looks for
     */
    void readByProdNameReturnId(String name);

    /**
     * get a list of categories so the user choose which one they want to browse items for
     * @return - results - the list of categories
     */
    List<String> getCategories();

    /**
     * Prints categories and their corresponding numbers in the menu to console to prompt user input
     * @param categories - a list of all categories in products table
     */
    void printCategories(List<String> categories);

    /**
     * This method 'deletes' a product from the database by setting its stock to 0 (cuz key constraints)
     * @param id - product id
     */
    void delete(int id);

    /**
     * When items from basket are sold, updates stock in database accordingly
     * Also generates receipt in receipts table
     * Then calls generatePurchaseHistoryRecord to make record in link table
     * @param paid - amount of cash paid
     */
    void checkout(double paid);

    /**
     * adds an item to the basket in order to sell multiple items at once
     * @param id - id of product to add to basket
     * @param qty - quantity of that product to add to basket
     */
    void addToBasket(int id, int qty);

    void removeFromBasket(int id, int qty);
}


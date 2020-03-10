package Tests;
import CRUDInterface.*;
import csc1035.project3.HibernateUtil;
import org.hibernate.*;
import DbTables.Product;

public class Tests {
    public static void main(String[] args) {

        //We will open our session at the start of this main so all our products access our DB.
        Session session = HibernateUtil.getSessionFactory().openSession();
        CRUDInterface cI = new CRUDTeamDb();

        /**
        //Create new product
        Product createProduct = new Product("TestOneCreate", "NewCat", false, 10.0, 10, 10.0);
        cI.create(createProduct);


        //Update the values of a test product - The first parameter needs to be updated to the respective product ID
        cI.updateProduct(10, "cost", "420");
        cI.updateProduct(10, "perishable", "false");
        cI.updateProduct(10, "prodCat", "newCat");
        cI.updateProduct(10, "prodName", "newName");
        cI.updateProduct(10, "sell_price", "777");
        cI.updateProduct(10, "stock", "9001");


        //Return the ID's of products whose name contains our parameter (this can be changed as required)
        cI.readByProdNameReturnId("a");


        //Update stock levels of a few products - First parameter is our product ID, third parameter our new value
        cI.updateProduct(10, "stock", "141");


        //Delete a product - Only parameter is product ID
        cI.delete(32);


        //Sell an item and produce a receipt.
        cI.sellItem(4, 1, 100);


        //Selling wrong amounts of items (negative)

        //TODO To be implemented - Awaiting UI to start this test


        //IDs that don't exist - No error messages will be thrown and no updates will occur in DB
        cI.updateProduct(10000, "stock", "420");


        //Not enough money
        cI.sellItem(4, 1, 70);


        //Add items to our basket and buy all of them in one purchase. Parameters for add to basket are product ID
        //and amount, parameter for checkout is money given.
        cI.addToBasket(11, 3);
        cI.addToBasket(10, 1);
        cI.addToBasket(8, 2);
        cI.checkout(1000);


        //Not enough stock to sell
        cI.sellItem(4, 10000, 1000000);


        //We will close our session after all our tests have ran.
        session.close();
        **/
    }
}

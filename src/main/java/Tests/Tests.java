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

        /*
        //Create new product - Works fine
        Product createProduct = new Product("TestOneCreate", "NewCat", false, 10.0, 10, 10.0);
        cI.create(createProduct);


        //Update the values of a test product
        cI.updateProduct(10, "cost", "420");
        cI.updateProduct(10, "perishable", "false");
        //cI.updateProduct(10, "prodCat", "newCat");
        //cI.updateProduct(10, "prodName", "newName");
        cI.updateProduct(10, "sell_price", "777");
        cI.updateProduct(10, "stock", "9001");


        //Return the ID's of products whose name contains our parameter - Works fine
        cI.readByProdNameReturnId("a");


        //Update stock levels of a few products - Works fine
        cI.updateProduct(32, "stock", "141909");


        //Delete the product whose values we just updated - Works fine
        cI.delete(32);
        */


        //Selling an item works, and produces a receipt. - Works fine

        //cI.sellItem(4, 1, 100);

        //Selling wrong amounts of items (negative)

        //TODO To be implemented - Awaiting UI to start this test


        //IDs that don't exist - Works fine


        //cI.updateProduct(10000, "stock", "420");


        //Not enough money

        //TODO To be implemented -  NEEDS TO BE FIXED, transaction still goes through

        //cI.sellItem(4, 1, 70);

        //Being able to sell multiple items at once


        //TODO To be implemented


        //Not enough stock to sell

        //TODO To be implemented - NEEDS TO BE FIXED, To be fixed by changing line 233 in CRUDTeamDB

        //cI.sellItem(4, 10000, 1000000);

        //We will close our session after all our tests have ran.
        session.close();
    }
}

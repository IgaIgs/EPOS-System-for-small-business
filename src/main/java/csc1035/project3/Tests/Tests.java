package csc1035.project3.Tests;
import csc1035.project3.HibernateUtil;
import csc1035.project3.Products;
import org.hibernate.*;

public class Tests {
    public static void main(String[] args) {
        //We will open our session at the start of this main so all our products access our DB.
        Session session = HibernateUtil.getSessionFactory().openSession();
        //Create new product
        Products createProduct = new Products(0,"TestProduct","Testing",false,4.50,10,10.0);
        //We can replace this code below with a call to our create method in our CRUD class and parse our product as an argument once it's implemented.
        /**
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(createProduct);
        session.getTransaction().commit();
        session.close();
         */
        //Read the database and find our new product

        /**
         * Code in here will again be implementing our read method from our CRUD class. Our variable we will parse here
         * will be our search query string (?)
         */

        //Update some of the attributes of our new product

        /**
         * Code in here will get an item from our DB, create an object from this. Use setter methods to update some fields
         * and then commit our changes to our DB.
         */

        //Delete our new product
        //We can replace this code below with
        /**
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Products deleteProduct = session.get(Products.class, 3);
            session.delete(deleteProduct);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        }
*/
        //Generate a receipt from a simulated transaction

        /**
         * Here we will need to have a few calls to our transaction method simulating a customer buying a few items,
         * and then we will call our output method where we produce our reciept.
         */

        //Update stock levels of a few products

        /**
         * Here we will have most of the same code as our update attributes method, with our only setter methods being
         * used on stock levels.
         * We will need to call it several times to prove we can update the stock levels of various items.
         */
        
        //Selling wrong amounts of items (negative)

        //IDs that don't exist

        //Not enough money

        //Being able to sell multiple items at once

        //Not enough stock to sell

        //We will close our session after all our tests have ran.
        session.close();
    }
}

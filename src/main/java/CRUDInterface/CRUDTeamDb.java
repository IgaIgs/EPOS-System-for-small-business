package CRUDInterface;

import DbTables.Product;
import DbTables.PurchaseHistory;
import DbTables.Receipt;
import Output.Basket;
import Output.ReceiptUtil;
import csc1035.project3.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This class implements the CRUDInterface and contains all the CRUD methods used to query the database, as well as
 * some other supporting methods to enable the interaction between classes and the functionality our system offers.
 * @param <E> - the object to be passed into some of the methods
 */
public class CRUDTeamDb<E> implements CRUDInterface<E> {

    private Session session = null;
    private Basket basket = new Basket();

    /**
     * create entries in the database
     * @param e - entry to be created in a table
     */
    @Override
    public void create(E e) {
        try {
            // open a Hibernate session to allow for querying of the database - this will be repeated in many of the methods
            session = HibernateUtil.getSessionFactory().openSession();
            // begin transaction
            session.beginTransaction();
            // add an item
            session.persist(e);
            // commit the changed to the database
            session.getTransaction().commit();
            // close the session or throw an exception if there is an error
        } catch (HibernateException ex) {
            if (session != null) session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * update entries via previous instantiation
     * @param e - update the entry in a table
     */
    @Override
    public void generalUpdate(E e) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            // add or update the item in the database
            session.saveOrUpdate(e);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            if (session != null) session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Method to update an attribute for given product
     * @param id - id of item to be updated
     * @param field - the field to be updated (to update stock, use "Stock")
     * @param newValue - the new value to be inserted
     */
    @Override
    public void updateProduct(int id, String field, String newValue) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // create query using strings, so that fields can be supplied by user
            String queryString;

            /*
             in order for query to work for various data types, following if statement is used
             this was chosen as an alternative to pulling the record and finding specific type then casting etc
             */
            if (field == "Name" || field == "Category") {
                queryString = "UPDATE PRODUCTS p SET p." + field + " = ?1 WHERE p.id = ?2";
                Query update = session.createQuery(queryString);
                // allow user to specify which fields/attributes they want to change and to what value
                update.setParameter(1, newValue);
                // choose the product which will be updated
                update.setParameter(2, id);
                update.executeUpdate();
            } else {
                queryString = "UPDATE PRODUCTS p SET p." + field + " = " + newValue + " WHERE p.id = " + id;
                Query update = session.createQuery(queryString);
                update.executeUpdate();
            }

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            if (session != null) session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Returns product's id and name by category given by user
     * @param userCat - product category specified by the user
     */
    @Override
    public void readProduct(int userCat) {
        try {
            // get the name of the category the user wants to look up the items in
            String cat = getCategories().get(userCat);
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // get the names and IDs of the products in chosen category
            Query query = session.createQuery("select p.id, p.Name from PRODUCTS p where p.Category = :new_cat");
            query.setParameter("new_cat", cat);

            // put results in a list
            List results = query.getResultList();
            Object[] items = results.toArray();

            // print a user-friendly mapping of names and IDs of items in a category
            System.out.println("Items in category " + cat + ":");
            System.out.println("ID     --> Product Name");
            for (int i = 0; i < items.length; i++) {
                Object[] tmp = (Object[]) items[i];
                System.out.println(tmp[0] + "\t   --> " + tmp[1]);
            }
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            if (session != null) session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * get a list of categories so the user choose which one they want to browse items for
     * @return - results - the list of categories
     */
    @Override
    public List<String> getCategories() {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            List results;
            // get list of categories so user can select a category to then browse items from
            Query query = session.createQuery("select distinct p.Category from PRODUCTS p");
            results = query.getResultList();

            session.getTransaction().commit();
            return results;
        } catch (HibernateException ex) {
            if (session != null) session.getTransaction().rollback();
            ex.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Prints categories and their corresponding numbers in the menu to console to prompt user input
     * @param categories - a list of all categories in products table
     */
    @Override
    public void printCategories(List<String> categories) {
        // print a user-friendly mapping of menu option numbers and category names
        System.out.println("Available categories:");
        System.out.println("Number --> Category");
        for (int i = 0; i < categories.size(); i++) {
            // format consistently to help user quickly find correct ID
            System.out.println(i + " \t   --> " + categories.get(i));
        }
    }

    /**
     * This method takes a full or partial name of a product and prints out the FULL product name
     * followed by this product's ID
     * @param name - the full or partial name of a product the user looks for
     */
    @Override
    public void readByProdNameReturnId(String name) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            // find the id's of all the products whose names contain what the user inputted
            Query query = session.createQuery("SELECT P.id FROM PRODUCTS P WHERE P.Name LIKE :try_name");
            // find the full names of all the products whose names contain what the user inputted
            Query query1 = session.createQuery("SELECT P.Name FROM PRODUCTS P WHERE P.Name LIKE :try_name");
            // set the variable try_name from both queries to user input
            query.setParameter("try_name", "%" + name + "%");
            query1.setParameter("try_name", "%" + name + "%");
            // save results of both queries in 2 different lists (of the same length)
            List productNms = query1.getResultList();
            List productIDs = query.getResultList();
            session.getTransaction().commit();
            // iterate through the result lists
            System.out.println("ID     --> Category");
            for (int i = 0; i < productIDs.size(); i++) {
                // print out the full names and IDs of found products
                System.out.println(productIDs.get(i) + " \t   --> " + productNms.get(i));
            }
        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * This method 'deletes' a product from the database by setting its stock to 0 (cuz key constraints)
     * @param id - product id
     */
    @Override
    public void delete(int id) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            // set the stock of the product to be deleted to zero (to prevent further sales but to keep the record)
            Query query = session.createQuery("update PRODUCTS p set p.Stock = 0 where p.id = ?1");
            // choose the ID of the product to be deleted
            query.setParameter(1, id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            if (session != null) session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * remove an item from basket (in event customer no longer wants to purchase)
     * @param id - id of item
     * @param qty - quantity to remove from basket
     */
    @Override
    public void removeFromBasket(int id, int qty){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // find the product to be deleted from the basket by ID
            Product tempProd = session.get(Product.class, id);

            // remove the product from the basket using a remove method from Basket.java
            basket.remove(tempProd, qty);

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            if (session!=null) session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * When items from basket are sold, updates stock in database accordingly
     * Also generates receipt in receipts table
     * Then calls generatePurchaseHistoryRecord to make record in link table
     * @param paid - amount of cash paid
     */
    @Override
    public void checkout(double paid) {
        Map<Product, Integer> basketCopy = basket.getBasket();
        double runningTotal = basket.priceTotal();
        int newStock;

        // exit if basket is empty
        if (basketCopy == null) {
            System.out.println("Error: basket is empty");
            return;
        }

        // check enough has been paid, exit if not
        if (runningTotal > paid) {
            System.out.println("Error: cash provided does not cover cost of basket (£" + runningTotal);
            return;
        }

        // add receipt to receipts table
        Receipt tempReceipt = generateReceipt(runningTotal, paid);

        for (Product product : basketCopy.keySet()) {
            try {
                // calculate what the new stock level will be
                newStock = getStock(product.getProductID()) - basketCopy.get(product);
                updateProduct(product.getProductID(), "Stock", String.valueOf(newStock));

                // call method to add record in link table
                generatePurchaseHistoryRecord(product, tempReceipt, basketCopy.get(product));


            } catch (HibernateException ex) {
                if (session != null) session.getTransaction().rollback();
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        // print receipt in console
        System.out.println(ReceiptUtil.toString(tempReceipt));
    }

    /**
     * Used to return cost of basket so user can be prompted how much money is needed
     * @return - cost of basket
     */
    @Override
    public double getBasketTotal(){
        return basket.priceTotal();
    }

    /**
     * generate a new receipt, and save it to receipts table
     * @param runningTotal - the total of the transaction
     * @param paid - the amount of money paid by the customer
     * @return returns the receipt object to be used in link table
     */
    private Receipt generateReceipt(double runningTotal, double paid){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // get the current date to be put on the receipt
            String date = new Date().toString();

            // create a receipt
            Receipt tempReceipt = new Receipt(runningTotal, date, paid);

            // add the receipt to the database
            session.saveOrUpdate(tempReceipt);
            session.getTransaction().commit();
            session.close();
            return tempReceipt;
        } catch (HibernateException ex) {
            if (session != null) session.getTransaction().rollback();
            ex.printStackTrace();
            throw ex;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Checks stock of item, and adds to basket if there is enough available stock
     * @param id  - id of item to add to basket
     * @param qty - quantity of that item to add to basket
     */
    @Override
    public void addToBasket(int id, int qty) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // get the product with the ID specified
            Product tempProduct = session.get(Product.class, id);
            // create a basket object
            Map<Product, Integer> basketCopy = basket.getBasket();

            // set the quantity of items in the basket to zero at the beginning of transcation
            int qtyAlreadyInBasket = 0;

            // find if item is already in basket and get stock if it is - to ensure enough is there for additional items
            if (basketCopy != null && basketCopy.containsKey(tempProduct)) {
                qtyAlreadyInBasket = basketCopy.get(tempProduct);
            }

            // query to get current stock of given item and then check there is enough to sell quantity given
            int productStock = getStock(id);
            if (productStock - qty - qtyAlreadyInBasket <= 0) {
                System.out.println("Error: " + tempProduct.getProdName() + " has insufficient stock.");
                return;
            }

            // add to basket if enough stock is present
            basket.add(tempProduct, qty);
        } catch (HibernateException ex) {
            if (session != null) session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Creates record in link table from a transaction
     * This allows for receipts to be reconstructed later
     *
     * @param product - the product involved in transaction
     * @param receipt - the receipt involved in transaction
     * @param qty     - the number sold in that transaction
     */
    private void generatePurchaseHistoryRecord(Product product, Receipt receipt, int qty) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // create a new object of the purchase history
            PurchaseHistory purchaseHistory = new PurchaseHistory(product, receipt, qty);

            session.saveOrUpdate(purchaseHistory);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            if (session != null) session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * retrieve stock of given ID
     * @param id - ID of item to look up in products table
     * @return int value for stock quantity (or -1 if item is out of stock)
     */
    @Override
    public int getStock(int id) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // find the product with the ID given
            Product tempProduct = session.get(Product.class, id);

            // get the stock of this product
            Query getStock = session.createQuery("SELECT p.Stock FROM PRODUCTS p WHERE p.id = :prod_id");
            getStock.setParameter("prod_id", id);

            // put the stock results in a list
            List stockResults = getStock.getResultList();
            int productStock = (int) stockResults.get(0);
            if (productStock == 0) {
                // do not return ID if item is out of stock
                System.out.println("Error: " + tempProduct.getProdName() + " is no longer in stock.");
                return -1;
            }
            return productStock;
        } catch (HibernateException ex) {
            if (session != null) session.getTransaction().rollback();
            ex.printStackTrace();
            throw ex;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * get name from ID for printing in UI
     * @param id - ID of product
     * @return - name of product
     */
    @Override
    public String getName(int id){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Product tempProduct = session.get(Product.class, id);
            return tempProduct.getProdName();
        } catch (HibernateException ex) {
            if (session != null) session.getTransaction().rollback();
            ex.printStackTrace();
            throw ex;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * print receipt in console from given ID
     * @param id - ID of receipt to look up
     */
    @Override
    public void printReceiptByID (int id){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Receipt tempReceipt = session.get(Receipt.class, id);
            System.out.println(ReceiptUtil.toString(tempReceipt));
        } catch (HibernateException ex) {
            if (session != null) session.getTransaction().rollback();
            ex.printStackTrace();
            throw ex;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
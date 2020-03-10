package CRUDInterface;

import DbTables.Product;
import DbTables.PurchaseHistory;
import DbTables.Receipt;
import Output.Basket;
import csc1035.project3.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

// This class will contain all the CRUD methods and use the CRUD interface
// TODO add feature to remove item(s) from basket
public class CRUDTeamDb<E> implements CRUDInterface<E> {

    private Session session = null;
    private Basket basket = null;
    /**
     * create entries in the database
     * @param e - entry to be created in a table
     */
    @Override
    public void create(E e) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.persist(e);
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
     * update entries via previous instantiation
     * @param e - update the entry in a table
     */
    @Override
    public void generalUpdate(E e) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(e);
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

            String queryString = "";

            /*
             in order for query to work for various data types, following if statement is used
             this was chosen as an alternative to pulling the record and finding specific type then casting etc
             */
            if (field == "Name" || field == "Category"){
                queryString = "UPDATE PRODUCTS p SET p."+field+" = ?1 WHERE p.id = ?2";
                Query update = session.createQuery(queryString);
                update.setParameter(1, newValue);
                update.setParameter(2, id);
                update.executeUpdate();
            } else {
                queryString = "UPDATE PRODUCTS p SET p."+field+" = "+newValue+" WHERE p.id = " + id;
                Query update = session.createQuery(queryString);
                update.executeUpdate();
            }

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
     * Returns product's id and name by category given by user
     * @param userCat - product category specified by the user
     */
    @Override
    public void readProduct(int userCat) {
        try {
            String cat = getCategories().get(userCat);
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            // use list of categories to convert ID from user to category name for query
            // get all items in category
            Query query = session.createQuery("select p.id, p.Name from PRODUCTS p where p.Category = :new_cat");
            query.setParameter("new_cat", cat);

            List results = query.getResultList();
            Object[] items = results.toArray();

            System.out.println("Items in category " + cat + ":");
            System.out.println("Number --> Category" );
            for (int i = 0; i <items.length ; i++) {
                Object[] tmp = (Object[]) items[i];
                System.out.println(tmp[0] + "\t   --> " + tmp[1]);
            }
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
     * get a list of categories so the user choose which one they want to browse items for
     * @return - results - the list of categories
     */
    @Override
    public List<String> getCategories(){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            List results = null;
            // get list of categories so user can select a category to then browse items from
            Query query = session.createQuery("select distinct p.Category from PRODUCTS p");
            results = query.getResultList();

            session.getTransaction().commit();
            return results;
        } catch (HibernateException ex) {
            if (session!=null) session.getTransaction().rollback();
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
    public void printCategories(List<String> categories){
        System.out.println("Available categories:");
        System.out.println("Number --> Category" );
        for (int i = 0; i<categories.size(); i++){
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
            System.out.println("ID     --> Category" );
            for (int i = 0; i < productIDs.size(); i++){
                // print out the full names and IDs of found products
                System.out.println(productIDs.get(i) + " \t   --> " + productNms.get(i));
            }
        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Iga: This method deletes a product from the database by setting its stock to 0 (cuz key constraints)
     * @param id - product id
     */
    @Override
    public void delete(int id) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("update PRODUCTS p set p.stock = 0 where p.id = ?1");
            query.setParameter(1, id);
            query.executeUpdate();
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

    public void removeFromBasket(){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Map<Product, Integer> basketCopy = basket.getBasket();

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
     * When an item is sold, updates stock in database accordingly
     * Also generates receipt in receipts table
     * Then calls generatePurchaseHistoryRecord to make record in link table
     * @param paid - amount of money given in transaction
     */
    @Override
    public void checkout(double paid){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Map<Product, Integer> basketCopy = basket.getBasket();
            int runningTotal = 0;

            for (Product product : basketCopy.keySet()){
                // increase total for receipt by multiplying sell price by quantity
                runningTotal += product.getSell_price() * basketCopy.get(product);
            }

            // check enough has been paid, exit if not
            if (runningTotal > paid){
                System.out.println("Error: cash provided does not cover cost of basket (£"+runningTotal);
                return;
            }

            for (Product product : basketCopy.keySet()){
                // update stock by subtracting the quantity of items sold from amount currently in stock
                Query updateStock = session.createQuery("UPDATE PRODUCTS p SET p.stock = :new_stock WHERE p.id = :prod_id");
                updateStock.setParameter("prod_id", product.getProductID());
                updateStock.setParameter("new_stock", getStock(product.getProductID()) - basketCopy.get(product));
                updateStock.executeUpdate();

                // add receipt to receipts table
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String date = new Date().toString();
                Receipt tempReceipt = new Receipt(basketCopy.get(product) * product.getSell_price(), date, paid);
                session.saveOrUpdate(tempReceipt);
                session.getTransaction().commit();

                // call method to add record in link table
                generatePurchaseHistoryRecord(product, tempReceipt, basketCopy.get(product));
            }
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
     * Checks stock of item, and adds to basket if there is enough available stock
     * @param id - id of item to add to basket
     * @param qty - quantity of that item to add to basket
     */
    public void addToBasket(int id, int qty){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Product tempProduct = session.get(Product.class, id);
            Map<Product, Integer> basketCopy = basket.getBasket();
            int qtyAlreadyInBasket = 0;

            // find if item is already in basket and get stock if it is - to ensure enough is there for additional items
            if (basketCopy.containsKey(tempProduct)) {
                qtyAlreadyInBasket = basketCopy.get(tempProduct);
            }

            // query to get current stock of given item
            Query getStock = session.createQuery("SELECT p.stock FROM PRODUCTS p WHERE p.id = :prod_id");
            getStock.setParameter("prod_id", id);
            List stockResults = getStock.getResultList();
            int productStock = (int) stockResults.get(0);
            if (productStock - qty - qtyAlreadyInBasket >= 0){
                System.out.println("Error: " + tempProduct.getProdName() + " has insufficient stock.");
                return;
            }

            // add to basket if enough stock is present
            basket.add(tempProduct, qty);

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
     * Creates record in link table from a transaction
     * This allows for receipts to be reconstructed later
     * @param product - the product involved in transaction
     * @param receipt - the receipt involved in transaction
     * @param qty - the number sold in that transaction
     */
    private void generatePurchaseHistoryRecord (Product product, Receipt receipt, int qty){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            PurchaseHistory purchaseHistory = new PurchaseHistory(product, receipt, qty);

            session.saveOrUpdate(purchaseHistory);
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
     * retrieve stock of given ID
     * @param id - ID of item to look up in products table
     * @return int value for stock quantity
     */
    public int getStock(int id){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Product tempProduct = session.get(Product.class, id);

        Query getStock = session.createQuery("SELECT p.stock FROM PRODUCTS p WHERE p.id = :prod_id");
        getStock.setParameter("prod_id", id);
        List stockResults = getStock.getResultList();
        int productStock = (int) stockResults.get(0);
        if (productStock == 0){
            // do not return ID if item is out of stock
            System.out.println("Error: " + tempProduct.getProdName() + " is no longer in stock.");
            return -1;
        }
        return productStock;
    }

}
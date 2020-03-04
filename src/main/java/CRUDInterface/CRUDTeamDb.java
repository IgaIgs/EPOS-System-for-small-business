package CRUDInterface;

import DbTables.Product;
import DbTables.PurchaseHistory;
import DbTables.Receipt;
import csc1035.project3.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.util.List;

// This class will contain all the CRUD methods and use the CRUD interface
public class CRUDTeamDb<E> implements CRUDInterface<E> {

    private Session session = null;

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

    @Override
    public void updateProduct(List<E> list, String id) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("update PRODUCTS p set p.prodName = :new_name, p.prodCat = :new_cat, " +
                "p.cost = :new_cost, p.stock = :new_stock, p.sell_price = :new_sellP where p.id = :prod_id ");
            query.setParameter("new_name",list.get(1));
            query.setParameter("new_cat",list.get(2));
            query.setParameter("new_cost",list.get(4));
            query.setParameter("new_stock",list.get(5));
            query.setParameter("new_sellP",list.get(6));
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

    /**
     * Returns product's id and name by category given by user
     */
    @Override
    public void readProduct(int userCat) {
        // TODO make sure to remind Yayu input class provides int  using next.int
        try {
            String cat = getCategories().get(userCat);
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            // use list of categories to convert ID from user to category name for query
            // get all items in category
            Query query = session.createQuery("select p.id, p.prodName from PRODUCTS p where p.prodCat = :new_cat");
            query.setParameter("new_cat", cat);

            List results = query.getResultList();
            Object[] items = results.toArray();

            System.out.println("Items in category " + cat + ":");
            for (int i = 0; i <items.length ; i++) {
                Object[] tmp = (Object[]) items[i];
                for (int j = 0; j < tmp.length ; j++) {
                    System.out.print(tmp[j]+" ");
                }
                System.out.println();
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

    @Override
    public List<String> getCategories(){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            List results = null;
            // get list of categories so user can select a category to then browse items from
            Query query = session.createQuery("select distinct p.prodCat from PRODUCTS p");
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

    // TODO 4 Ben: add some comments
    @Override
    public void printCategories(List<String> categories){
        System.out.println("Available categories:");
        for (int i = 0; i<categories.size(); i++){
            System.out.println(i + " - " + categories.get(i).toString());
        }
    }


    @Override
    public void readProdById(String id) {
        Product product1 = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            product1 = session.get(Product.class, id);
            //List product = session.createQuery("FROM PRODUCTS").list();
            //for (Iterator<Product> iterator = product.iterator(); iterator.hasNext();){
                //Product product1 = iterator.next();
                System.out.println("Name: " + product1.getProdName());
                System.out.println("Category: " + product1.getProdCat());
                System.out.println("Is perishable?: " + product1.isPerishable());
                System.out.println("Stock: " + product1.getStock());
                System.out.println("Cost: " + product1.getCost());
                System.out.println("Selling price: " + product1.getSell_price());
            //}
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public E readByIdReturnE(Class<E> c, String id) {
        E entry = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            System.out.println(session.get(c, id));
            entry = c.cast(session.get(c, id));
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            if (session!=null) session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return entry;

    }

    @Override
    public String readByProdNameReturnId(String name) {
        return null;
//        Product prod = null;
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            List products = session.createQuery("FROM PRODUCTS P WHERE P.prodName = name").list();
//            System.out.println("Name: " + prod.getProductID());
//            session.getTransaction().commit();
//        } catch (HibernateException e) {
//            if (session!=null) session.getTransaction().rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
    }

    @Override
    public List<E> readAll(Class c) {

        List entries = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            entries = session.createQuery("FROM "+ c.getCanonicalName()).list();
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            if (session!=null) session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return entries;
    }

    @Override
    public void delete(Class c, String id) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Object entry = c.cast(session.get(c, id));
            session.delete(entry);
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

    public void sellItem(int id, int qty, double paid){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // query to get current stock of given item
            Query getStock = session.createQuery("SELECT p.stock FROM PRODUCTS p WHERE p.id = :prod_id");
            getStock.setParameter("prod_id", id);
            List stockResults = getStock.getResultList();
            int productStock = (int) stockResults.get(0);
            // TODO 4 Ben: don't sell an item if stock = 0

            // then update stock by subtracting the quantity of items sold
            Query updateStock = session.createQuery("UPDATE PRODUCTS p SET p.stock = :new_stock WHERE p.id = :prod_id");
            updateStock.setParameter("prod_id", id);
            updateStock.setParameter("new_stock", productStock - qty);
            updateStock.executeUpdate();

            // TODO 4 Ben: I think we need to execute transactions all at once, otherwise it might perform half and adjust stock but fail something else etc

            // add receipt to receipts table
            Product tempProduct = session.get(Product.class, id);
            Receipt tempReceipt = new Receipt(qty * tempProduct.getSell_price(), "2020-03-03", paid);
            session.saveOrUpdate(tempReceipt);
            session.getTransaction().commit();

            // TODO 4 Ben: need to check calculation in param4 actually works, I'm not certain but we'll see
            // TODO 4 Ben: get rid of ugly hard coded values already
            generatePurchaseHistoryRecord(tempProduct, tempReceipt, qty);

        } catch (HibernateException ex) {
            if (session!=null) session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    private void generatePurchaseHistoryRecord (Product product, Receipt receipt, int qty){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            PurchaseHistory purchaseHistory = new PurchaseHistory(product, receipt, qty);
            // TODO 4 Ben:  please run a few basic tests to be sure that qty is working

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



}
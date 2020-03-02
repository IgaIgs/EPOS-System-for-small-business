package CRUDInterface;

import DbTables.Product;
import csc1035.project3.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    public void update(E e) {

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
     * Returns a hashMap with names of product as keys and product IDs as values
     */
    @Override
    public HashMap<String, String> readProductsByName(Class<E> c, String name) {
        /*HashMap<String, String> prodNameID = new HashMap<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            prodNameID.putIfAbsent(name, c.cast(session.get(c, id)));
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            if (session!=null) session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }*/
        return null;
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
}
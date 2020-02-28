package CRUDInterface;

import csc1035.project3.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Override
    public HashMap<String, String> readByName(Class<E> c, String name) {
        return null;
    }

    @Override
    public ArrayList<E> readById(Class c, int id) {
        return null;
       /*ArrayList<E> entries = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            entries = c.cast(session.get(c, id));
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            if (session!=null) session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return (E) entries;*/

    }

    @Override
    public List<E> readAll(Class c) {

        List<E> entries = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            entries = session.createQuery("FROM "+ c.getSimpleName()).list();
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
    public void delete(Class c, int id) {

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
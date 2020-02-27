package csc1035.project3;
import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {


        Products p = new Products(0,"NewProduct2","Products",false,4.50,10,10.0);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();

}

}

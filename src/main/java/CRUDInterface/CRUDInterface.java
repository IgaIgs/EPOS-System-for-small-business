package CRUDInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public  interface CRUDInterface<E>  {

    /**
     * create entries
     * @param e
     */
    void create(E e);

    /**
     * update entries
     * @param e - update the entry in a table
     */
    void update(E e);

    /**
     * Returns a hashMap with names of product as keys and product IDs as values
     */
    HashMap<String, String> readByName(Class<E> c, String name);

    /**
     * Return an arraylist with all entry's fields in order given its ID
     * @param c - which class/table is the entry from
     * @param id - what is the ID of the entry
     * @return - an arraylist
     */
    ArrayList<E> readById(Class<E> c, int id);

    List<E> readAll(Class<E> c);

    /**
     * delete by id
     * @param c - which classs/table to delete from
     * @param id - id of the entry to be deleted
     */
    void delete(Class<E> c, int id);

}


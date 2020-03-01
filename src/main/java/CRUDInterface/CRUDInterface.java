package CRUDInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public  interface CRUDInterface<E>  {

    /**
     * create entries
     * @param e - entry to be created in a table
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
    HashMap<String, String> readProductsByName(Class<E> c, String name);

    /**
     * Return an arraylist with all entry's fields in order given its ID
     * @param c - which class/table is the entry from
     * @param id - what is the ID of the entry
     * @return - an arraylist
     */
    ArrayList<E> readById(Class<E> c, String id);

    /**
     *  read by Id from Jordan which returns object representations of entries in the table
     * @param c - which table
     * @param id - entry id
     * @return - the entry
     */
    E readByIdReturnE(Class<E> c, String id);

    /**
     * method for product table to enable looking for product iDs using their names
     * @param name - entry id
     * @return - name of the product
     */
    String readByProdNameReturnId(String name);

    List<E> readAll(Class<E> c);

    /**
     * delete by id
     * @param c - which classs/table to delete from
     * @param id - id of the entry to be deleted
     */
    void delete(Class<E> c, String id);


}


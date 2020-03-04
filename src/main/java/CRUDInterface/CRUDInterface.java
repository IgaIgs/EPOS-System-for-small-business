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
    void generalUpdate(E e);

    void updateProduct(List<E> list, String id);

    /**
     * Returns product's id and name by category given by user
     */
    void readProduct(int userCat);

    /**
     * Return an arraylist with all entry's fields in order given its ID
     * @param id - what is the ID of the entry
     * @return - an arraylist
     */
    void readProdById(String id);

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
    List<String> getCategories();
    void printCategories(List<String> categories);

    /**
     * delete by id. Doesn't work on Purchase History table where there is no id but two foreign keys
     * @param c - which classs/table to delete from
     * @param id - id of the entry to be deleted
     */
    void delete(Class<E> c, String id);

    void sellItem(int id, int qty, double paid);

}


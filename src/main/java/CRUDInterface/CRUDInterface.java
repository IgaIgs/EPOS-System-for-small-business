package CRUDInterface;

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

    void updateProduct(int id, String field, String newValue);

    /**
     * Returns product's id and name by category given by user
     */
    void readProduct(int userCat);


    /**
     * method for product table to enable looking for product iDs using their names
     * @param name - entry id
     * @return - name of the product
     */
    void readByProdNameReturnId(String name);

    List<String> getCategories();

    void printCategories(List<String> categories);

    /**
     * "delete" a product from the database by setting its stock to 0
     * @param id - product id
     */
    void delete(int id);

    void sellItem(int id, int qty, double paid);

}


package Output;

import DbTables.Product;
import DbTables.Receipt;
import DbTables.PurchaseHistory;
import java.util.*;
import java.text.*;

/**
 * Basket object stores a basket of products in a HashMap with products as keys and quantity purchased as values
 */
public class Basket {
    private Map<Product, Integer> basket;

    /**
     *  Creates an empty HashMap as the container for the receipt
     */
    Basket(){
        basket = new HashMap<>();
    }

    /**
     * Return the contents of the basket
     *
     * @return the HashMap of products and quantities
     */
    public Map<Product, Integer> getBasket() {
        return basket;
    }

    /**
     * Add one item to the basket
     *
     * @param item a product to be added to the basket
     */
    public void add(Product item) {
        if (basket.containsKey(item)) {
            basket.put(item, basket.get(item) + 1);
        } else {
            basket.put(item, 1);
        }
        // TODO update stock method - decrement stock by 1
    }

    /**
     * Add multiple of the same item to the basket
     *
     * @param item a product to be added to the basket
     * @param quantity the quantity of that item to be added to the basket
     */
    public void add(Product item, int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException(String.format("You attempted to add %d %ss to the basket, quantities must be >= 1", quantity, item.getProdName()));
        } else {
            if (basket.containsKey(item)) {
                basket.put(item, basket.get(item) + quantity);
            } else {
                basket.put(item, quantity);
            }
        }
        // TODO update stock method - decrement stock by quantity
    }

    /**
     * Add multiple different items to the basket
     *
     * @param items a list of singular products to be added to the basket
     */
    public void add(List<Product> items) {
        for (Product item: items) {
            this.add(item);
        }
    }

    /**
     * Remove one item from the basket
     *
     * @param item a product to be removed from the basket
     */
    public void remove(Product item) {
        if (basket.containsKey(item) && (basket.get(item) > 1)) {
            basket.put(item, basket.get(item) - 1);
        } else if (basket.containsKey(item) && (basket.get(item) == 1)) {
            basket.remove(item);
        }
        // TODO update stock method - increment stock by 1
    }

    /**
     * Remove multiple of the same item from the basket
     *
     * @param item a product to be removed from the basket
     * @param quantity the quantity of that item to be removed from the basket
     */
    public void remove(Product item, int quantity) {
        if ((basket.get(item) - quantity) < 0) {
            throw new IllegalArgumentException(String.format("There are only %d %ss in the basket and you attempted to remove %d from the basket", basket.get(item), item.getProdName(), quantity));
        }
        if (basket.containsKey(item) && ((basket.get(item) - quantity) >= 1)) {
            basket.put(item, basket.get(item) - quantity);
        } else if (basket.containsKey(item) && ((basket.get(item) - quantity) == 0)) {
            basket.remove(item);
        }
        // TODO update stock method - increment stock by quantity
    }

    /**
     * Return the current total price of the basket
     *
     * @return the total price of the current basket
     */
    public double priceTotal() {
        double total = 0;
        for (Product item: basket.keySet()) {
            for (int i = 0; i < basket.get(item); i++){
                total += item.getCost();
            }
        }
        return priceTotal();
    }

    /**
     * Return the date and time of when the receipt string is constructed
     *
     * @return the date and time of when the receipt string is constructed
     */
    private static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(new Date());
    }

    /**
     * Convert to a receipt object in order to write to the database
     *
     * @param cash the money taken from the customer
     * @return the basket in the form of a receipt
     */
    public Receipt toReceipt(double cash){
        Receipt receipt = new Receipt();
        double total = priceTotal();
        receipt.setTotalc(total);
        receipt.setDate(getDate());
        receipt.setPaid(cash);
        Set<PurchaseHistory> itemQuantityObjectSet = new HashSet<>();
        for (Product item: basket.keySet()){
            PurchaseHistory itemQuantityObject = new PurchaseHistory();
            itemQuantityObject.setProduct(item);
            itemQuantityObject.setReceipt(receipt);
            itemQuantityObject.setQuantity(basket.get(item));
            itemQuantityObjectSet.add(itemQuantityObject);
        }
        receipt.setProducts(itemQuantityObjectSet);
        receipt.setChange(cash - total);
        return receipt;
    }
}

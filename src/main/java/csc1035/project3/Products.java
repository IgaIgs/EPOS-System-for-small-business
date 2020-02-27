package csc1035.project3;

import javax.persistence.*;

@Entity(name = "Product")
public class Products {
    /**
     * Attributes
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false, unique = true)
    private int product_ID;
    @Column
    private String name;
    @Column
    private String category;
    @Column
    private boolean perishable;
    @Column
    private double cost;
    @Column
    private int stock;
    @Column
    private double sell_price;
    /**
     * Constructor
     */
    public Products(int product_ID, String name, String category, boolean perishable, double cost, int stock, double sell_price) {
        this.product_ID = product_ID;
        this.name = name;
        this.category = category;
        this.perishable = perishable;
        this.cost = cost;
        this.stock = stock;
        this.sell_price = sell_price;
    }

    public Products(){

    }
    /**
     * Getters
     */
    public int getID() {
        return product_ID;
    }
    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public boolean getPerishable() {
        return perishable;
    }
    public double getCost() {
        return cost;
    }
    public int getStock() {
        return stock;
    }
    public double getPrice() {
        return  sell_price;
    }
    /**
     * Setters
     */
    public void setID(int newID) {
        product_ID = newID;
    }
    public void setName(String newName) {
        name = newName;
    }
    public void setCategory(String newCategory) {
        category = newCategory;
    }
    public void setPerishable(boolean newPerishable) {
        perishable = newPerishable;
    }
    public void setCost(double newCost) {
        cost = newCost;
    }
    public void setStock(int newStock) {
        stock = newStock;
    }
    public void setPrice(double newPrice) {
        sell_price = newPrice;
    }
}

package DbTables;

import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the PRODUCTS table in the database.
 * It stores information about the products sold by the "Tricky Trinkets" store; their names, category, whether they're
 * perishable, their cost, stock level and selling price.
 * The primary key is an auto-generated integer ID.
 * This table has a many-to-many relationship with the RECEIPTS table facilitated by the link-table PURCHASE HISTORY.
 */
@Entity(name = "PRODUCTS")
@DynamicUpdate
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ProductID", updatable = false, nullable = false)
    private int productID;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Category")
    private String Category;

    @Column(name = "Perishable")
    private boolean Perishable;

    @Column(name = "Cost")
    private double Cost;

    @Column(name = "Stock")
    private int Stock;

    @Column(name = "Selling_price")
    private double Sell_price;

    @OneToMany(mappedBy = "product")
    private Set<PurchaseHistory> receipts = new HashSet<>();


    public Product(String Name, String Cat, boolean perishable, double cost, int stock, double sell_price) {
        this.Name = Name;
        this.Category = Cat;
        this.Perishable = perishable;
        this.Cost = cost;
        this.Stock = stock;
        this.Sell_price = sell_price;
    }

    public Product(){}

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        productID = productID;
    }

    public String getProdName() {
        return Name;
    }

    public void setProdName(String Name) {
        this.Name = Name;
    }

    public String getProdCat() {
        return Category;
    }

    public void setProdCat(String Cat) {
        this.Category = Cat;
    }

    public boolean isPerishable() {
        return Perishable;
    }

    public void setPerishable(boolean perishable) {
        this.Perishable = perishable;
    }

    public double getCost() {
        return Cost;
    }

    public void setCost(double cost) {
        this.Cost = cost;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        this.Stock = stock;
    }

    public double getSell_price() {
        return Sell_price;
    }

    public void setSell_price(double sell_price) {
        this.Sell_price = sell_price;
    }

    public Set<PurchaseHistory> getReceipts() {
        return receipts;
    }

    public void setReceipts(Set<PurchaseHistory> receipts) {
        this.receipts = receipts;
    }
}

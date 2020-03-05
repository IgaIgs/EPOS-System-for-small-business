package DbTables;

import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "PRODUCTS")
@DynamicUpdate
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ProductID", updatable = false, nullable = false)
    private int productID;

    @Column(name = "Name")
    private String prodName;

    @Column(name = "Category")
    private String prodCat;

    @Column(name = "Perishable")
    private boolean perishable;

    @Column(name = "Cost")
    private double cost;

    @Column(name = "Stock")
    private int stock;

    @Column(name = "Selling_price")
    private double sell_price;

    @OneToMany(mappedBy = "product")
    private Set<PurchaseHistory> receipts = new HashSet<>();


    public Product(String prodName, String prodCat, boolean perishable, double cost, int stock, double sell_price) {
        this.prodName = prodName;
        this.prodCat = prodCat;
        this.perishable = perishable;
        this.cost = cost;
        this.stock = stock;
        this.sell_price = sell_price;
    }

    public Product(){}

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        productID = productID;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdCat() {
        return prodCat;
    }

    public void setProdCat(String prodCat) {
        this.prodCat = prodCat;
    }

    public boolean isPerishable() {
        return perishable;
    }

    public void setPerishable(boolean perishable) {
        this.perishable = perishable;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getSell_price() {
        return sell_price;
    }

    public void setSell_price(double sell_price) {
        this.sell_price = sell_price;
    }

    public Set<PurchaseHistory> getReceipts() {
        return receipts;
    }

    public void setReceipts(Set<PurchaseHistory> receipts) {
        this.receipts = receipts;
    }
}

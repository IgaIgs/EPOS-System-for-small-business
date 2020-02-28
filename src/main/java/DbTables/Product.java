package DbTables;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "PRODUCTS")
public class Product {

    @Id
    @Column(name = "ProductID", updatable = false)
    private String productID;

    @Column(name = "Name")
    private String prodName;

    @Column(name = "Category")
    private String prodCat;

    @Column(name = "Perishable")
    private boolean perishable;

    @Column(name = "Cost")
    private float cost;

    @Column(name = "Stock")
    private int stock;

    @Column(name = "Selling price")
    private float sell_price;

    @OneToMany(mappedBy = "product")
    private Set<PurchaseHistory> receipts = new HashSet<>();


    public Product(String productID, String prodName, String prodCat, boolean perishable, float cost, int stock, float sell_price) {
        this.productID = productID;
        this.prodName = prodName;
        this.prodCat = prodCat;
        this.perishable = perishable;
        this.cost = cost;
        this.stock = stock;
        this.sell_price = sell_price;
    }

    public Product(){}

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
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

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getSell_price() {
        return sell_price;
    }

    public void setSell_price(float sell_price) {
        this.sell_price = sell_price;
    }

    public Set<PurchaseHistory> getReceipts() {
        return receipts;
    }

    public void setReceipts(Set<PurchaseHistory> receipts) {
        this.receipts = receipts;
    }
}

package DbTables;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "RECEIPTS")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ReceiptID", updatable = false, nullable = false)
    private int receiptID;

    @Column(name = "Total_cost")
    private double totalc;

    @Column(name = "Date")
    private String date;

    @Column(name = "Money_given")
    private double paid;


    @OneToMany(mappedBy = "receipt")
    private Set<PurchaseHistory> products = new HashSet<>();

    public Receipt(double totalc, String date, double paid) {
        this.totalc = totalc;
        this.date = date;
        this.paid = paid;
    }

    public Receipt() {
    }

    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    public double getTotalc() {
        return totalc;
    }

    public void setTotalc(double totalc) {
        this.totalc = totalc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public Set<PurchaseHistory> getProducts() {
        return products;
    }

    public void setProducts(Set<PurchaseHistory> products) {
        this.products = products;
    }
}

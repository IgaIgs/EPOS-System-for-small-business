package DbTables;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "RECEIPTS")
public class Receipt {

    @Id
    @Column(name = "ReceiptID", updatable = false, nullable = false)
    private String receiptID;

    @Column(name = "Total_cost")
    private double totalc;

    //@Temporal(TemporalType.DATE)
    @Column(name = "Date")
    private String date;

    @Column(name = "Money_given")
    private double paid;

    @OneToMany(mappedBy = "receipt")
    private Set<PurchaseHistory> products = new HashSet<>();

    public Receipt(String receiptID, double totalc, String date, double paid) {
        this.receiptID = receiptID;
        this.totalc = totalc;
        this.date = date;
        this.paid = paid;
    }

    public Receipt() {
    }

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
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

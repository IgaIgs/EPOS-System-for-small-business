package DbTables;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "RECEIPTS")
public class Receipt {

    @Id
    @Column(name = "ReceiptID", updatable = false)
    private String receiptID;

    @Column(name = "Total cost")
    private int totalc;

    @Temporal(TemporalType.DATE)
    @Column(name = "Date")
    private Date date;

    @Column(name = "Money given")
    private float paid;

    @OneToMany(mappedBy = "receipt")
    private Set<PurchaseHistory> products = new HashSet<>();

    public Receipt(String receiptID, int totalc, Date date, float paid) {
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

    public int getTotalc() {
        return totalc;
    }

    public void setTotalc(int totalc) {
        this.totalc = totalc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPaid() {
        return paid;
    }

    public void setPaid(float paid) {
        this.paid = paid;
    }

    public Set<PurchaseHistory> getProducts() {
        return products;
    }

    public void setProducts(Set<PurchaseHistory> products) {
        this.products = products;
    }
}

package DbTables;

import javax.persistence.*;


/**
 * This is a class for the association table between Products and Receipts which represents their many-to-many relationship
 * which also has an additional attribute called Quantity
 * Both Products and Receipts connect to it via a one to many relationship so the PurchaseHistory has two many to one annotations
 * It will have an embeddable id as its composite primary key created from FKs coming from ProductID and ReceiptsID
 */
@Entity(name = "PURCHASE_HISTORY")
public class PurchaseHistory {

    @EmbeddedId
    private PurchaseHistoryID id;

    @ManyToOne
    @JoinColumn(name = "ProductID", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ReceiptID", insertable = false, updatable = false)
    private Receipt receipt;

    @Column(name = "Quantity")
    private int quantity;

    public PurchaseHistory(Product p, Receipt r, int quantity){
        // create primary key
        this.id = new PurchaseHistoryID(p.getProductID(), r.getReceiptID());

        // initialize attributes
        this.product = p;
        this.receipt = r;
        this.quantity = quantity;

        // update relationships to assure referential integrity
        p.getReceipts().add(this);
        r.getProducts().add(this);
    }

    public PurchaseHistory() {
    }

    public PurchaseHistoryID getId() {
        return id;
    }

    public void setId(PurchaseHistoryID id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

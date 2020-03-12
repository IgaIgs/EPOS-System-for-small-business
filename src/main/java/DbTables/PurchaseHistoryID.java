package DbTables;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * This is an embeddable class holding the composite primary key of the Purchase History table, which consists of
 * 2 foreign keys: the ID of the Product on a certain receipt and the ID of that receipt.
 */
@Embeddable
public class PurchaseHistoryID implements Serializable {

    private int productID;

    private int receiptID;

    public PurchaseHistoryID(){}

    public PurchaseHistoryID(int productID, int receiptID){
        this.productID = productID;
        this.receiptID = receiptID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseHistoryID that = (PurchaseHistoryID) o;
        return productID == that.productID &&
                receiptID == that.receiptID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, receiptID);
    }

}
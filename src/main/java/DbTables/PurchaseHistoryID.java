package DbTables;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

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
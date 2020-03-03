package DbTables;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PurchaseHistoryID implements Serializable {

    //Jordan doesn't have these @Column
    //@Column(name = "ProductID")
    private int productID;

    //@Column(name = "ReceiptID")
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

    //    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result
//                + ((productID == null) ? 0 : productID.hashCode());
//        result = prime * result
//                + ((receiptID == null) ? 0 : receiptID.hashCode());
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//
//        PurchaseHistoryID other = (PurchaseHistoryID) obj;
//
//        if (productID == null) {
//            if (other.productID != null)
//                return false;
//        } else if (!productID.equals(other.productID))
//            return false;
//
//        if (receiptID == null) {
//            if (other.receiptID != null)
//                return false;
//        } else if (!receiptID.equals(other.receiptID))
//            return false;
//
//        return true;
//    }
}
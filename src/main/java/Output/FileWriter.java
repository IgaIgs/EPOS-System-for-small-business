package Output;

import DbTables.Product;
import DbTables.PurchaseHistory;
import DbTables.Receipt;
import java.util.*;
import java.io.*;

/**
 * Class used for writing to csv files
 */
public class FileWriter {

    /**
     * A method for writing products from the database to a backup csv file
     *
     * @param products the list of products being written to the csv file
     * @param fileLocation the relative filepath of the csv where the product database is being written to
     * @throws IOException if the file location doesn't exist
     */
    public static void writeProductToCSV(List<Product> products, String fileLocation) throws IOException {
        PrintWriter backupCSV = new PrintWriter(new File(fileLocation));
        backupCSV.println("id,name,category,perishable,cost,stock,sell_price");
        for (Product item: products) {
            backupCSV.println(String.format("%s,%s,%s,%s,%f,%d,%f", item.getProductID(), item.getProdName(), item.getProdCat(), item.isPerishable(), item.getCost(), item.getStock(), item.getSell_price()));
        }
        backupCSV.close();
    }

    /**
     * A method for writing receipts from the database to a backup csv file
     *
     * @param receipts the list of receipts being written to the csv file
     * @param fileLocation the relative filepath of the csv where the product database is being written to
     * @throws IOException if the file location doesn't exist
     */
    public static void writeReceiptToCSV(List<Receipt> receipts, String fileLocation) throws IOException {
        PrintWriter backupCSV = new PrintWriter(new File(fileLocation));
        backupCSV.println("id,total_cost,date,paid");
        for (Receipt item: receipts) {
            backupCSV.println(String.format("%s,%f,%s,%s", item.getReceiptID(), item.getTotalc(), item.getDate(), item.getPaid()));
        }
        backupCSV.close();
    }

    /**
     * A method for writing purchase histories from the database to a backup csv file
     *
     * @param purchaseHistories the list of purchase histories being written to the csv file
     * @param fileLocation the relative filepath of the csv where the product database is being written to
     * @throws IOException if the file location doesn't exist
     */
    public static void writePurchaseHistoryToCSV(List<PurchaseHistory> purchaseHistories, String fileLocation) throws IOException {
        PrintWriter backupCSV = new PrintWriter(new File(fileLocation));
        backupCSV.println("product_id,receipt_id,quantity");
        for (PurchaseHistory item: purchaseHistories) {
            backupCSV.println(String.format("%s,%s,%s", item.getProduct().getProductID(), item.getReceipt().getReceiptID(), item.getQuantity()));
        }
        backupCSV.close();
    }

    /**
     * A method for writing receipts to a txt file
     *
     * @param receipt the list of products being written to the csv file
     * @throws IOException if the hardcoded file location doesn't exist (if this is thrown something is wrong)
     */
    public static void writetoTXT(Receipt receipt) throws IOException {
        PrintWriter receiptTXT = new PrintWriter(new File("./src/main/resources/receipt.txt"));
        receiptTXT.println(ReceiptUtil.toString(receipt));
        receiptTXT.close();
    }
}

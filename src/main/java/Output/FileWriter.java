package Output;

import DbTables.Product;
import DbTables.Receipt;
import java.util.*;
import java.io.*;

/**
 * Class used for writing to csv files
 */
public class FileWriter {

    /**
     * A method for writing products from the database to the backup csv file
     *
     * @param products the list of products being written to the csv file
     * @throws IOException if the hardcoded file location doesn't exist (if this is thrown something is wrong)
     */
    public static void writetoCSV(List<Product> products) throws IOException {
        PrintWriter backupCSV = new PrintWriter(new File(".\\main\\resources\\database_backup.csv"));
        backupCSV.println("id,name,category,perishable,cost,stock,sell_price");
        for (Product item: products) {
            backupCSV.println(String.format("%s,%s,%s,%s,%f,%d,%f", item.getProductID(), item.getProdName(), item.getProdCat(), item.isPerishable(), item.getCost(), item.getStock(), item.getSell_price()));
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
        PrintWriter receiptTXT = new PrintWriter(new File(".\\main\\resources\\receipt.txt"));
        receiptTXT.println(ReceiptUtil.toString(receipt));
        receiptTXT.close();
    }
}

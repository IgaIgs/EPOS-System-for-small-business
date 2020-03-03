package Output;

import DbTables.Product;
import java.util.*;
import java.io.*;

/**
 * Class used for writing to csv files
 */
public class CSVWriter {

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
            backupCSV.println(String.format("%d,%s,%s,%s,%d,%d,%d", item.getProductID(), item.getProdName(), item.getProdCat(), item.isPerishable(), item.getCost(), item.getStock(), item.getSellPrice()));
        }
        backupCSV.close();
    }
}

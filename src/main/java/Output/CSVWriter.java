package Output;

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
    public static void writetoCSV(List<Products> products) throws IOException {
        PrintWriter backupCSV = new PrintWriter(new File(".\\main\\resources\\database_backup.csv"));
        backupCSV.println("id,name,category,perishable,cost,stock,sell_price");
        for (Products item: products) {
            backupCSV.println(String.format("%d,%s,%s,%s,%d,%d,%d", item.getID(), item.getName(), item.getCategory(), item.getPerishable(), item.getCost(), item.getStock(), item.getPrice()));
        }
        backupCSV.close();
    }
}

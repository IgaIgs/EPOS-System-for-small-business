package Output;

import DbTables.Receipt;
import DbTables.PurchaseHistory;
import java.lang.*;


public class ReceiptUtil {
    /**
     * Produce a string representation of the receipt
     *
     * @param receipt a receipt object from receipt table needed to be converted to a string
     * @return a receipt as a string ready for printing
     */
    public static String toString(Receipt receipt){
        // Create a string builder object to store the receipt transcript in
        StringBuilder transcript = new StringBuilder();

        // Set shop name which will be printed at the top of the receipt
        String shopName = "Tricky Trinkets";

        // Set closing sentiment which will be printed at the top of the receipt
        String[] closingSentiment = {"Thank you for shopping at", "the world's greatest trinket shop", " ",
                "Tell us how we did today", "Visit www.trickytrinkets.com to complete",
                "our survey and be in for the chance", "of winning some free RammMerch"};

        // Get current date and time to display on the receipt
        String currentDateTime = receipt.getDate();

        // Set currency symbol
        char currency = '£';

        // Create character strings to use in the formatting of the receipt
        String dashChar = "-";
        String spaceChar = " ";

        // Initialise default widths for formatting the receipt
        int lineLength = 45;
        int priceSpaceLength = 7;
        int productSpaceLength = lineLength - priceSpaceLength - 1;

        // Calculate width required to fit the price if the price is longer than 7 characters
        if ((Math.floor(Math.log(receipt.getPaid())) + 1) > priceSpaceLength) {
            priceSpaceLength = (int) Math.floor(Math.log(receipt.getPaid())) + 1;
        }
        for (PurchaseHistory item: receipt.getProducts()) {
            if ((Math.floor(Math.log(item.getProduct().getSell_price())) + 1) > priceSpaceLength) {
                priceSpaceLength = (int) Math.floor(Math.log(item.getProduct().getSell_price())) + 1;
            }
        }

        // Calculate width required to fit the product name if the product name is longer than 37 characters
        for (PurchaseHistory item: receipt.getProducts()) {
            if (item.getProduct().getProdName().length() > (productSpaceLength - 2)) {
                productSpaceLength = item.getProduct().getProdName().length() + 2;
            }
        }

        // Re-calculate line length
        lineLength = productSpaceLength + priceSpaceLength + 1;

        // Create formats for format strings using the variable widths
        String productSpace = "%-" + productSpaceLength + "s";
        String priceSpace = "%" + priceSpaceLength + ".2f";
        String configurableLineFormatTitle = productSpace + " %" + priceSpaceLength +  "s \n\n";
        String configurableLineFormat = productSpace + currency + priceSpace + " \n";

        // Calculate total price to display on the receipt
        double total = receipt.getTotalc();

        // Calculate the number of items in the basket
        int basketSize = 0;
        for (PurchaseHistory item: receipt.getProducts()) {
            basketSize += item.getQuantity();
        }

        // Create the divider line string which will be used to divide sections of the receipt
        String divider = String.format("%s\n", dashChar.repeat(lineLength));

        // Top of the receipt with the shop name, address, phone number and current date and time
        transcript.append(divider);
        transcript.append(String.format("%1$s%2$s%1$s\n", spaceChar.repeat(centerLineBuffer(lineLength, shopName.length())), shopName));
        transcript.append(String.format("%1$s%2$s%1$s\n\n", spaceChar.repeat(centerLineBuffer(lineLength, currentDateTime.length())), currentDateTime));
        transcript.append(String.format("Address: %s\n", "1 Village Road"));
        transcript.append(String.format("Tel No: %s\n", "0123 456 7890"));

        // Middle of receipt displaying products with their names on the left and prices on the right
        transcript.append(divider);
        transcript.append(String.format(configurableLineFormatTitle, "Description:", "Price:"));
        for (PurchaseHistory item: receipt.getProducts()) {
            for (int i = 0; i < item.getQuantity(); i++) {
                transcript.append(String.format(configurableLineFormat, item.getProduct().getProdName(), item.getProduct().getSell_price()));
            }
        }

        // Bottom of the receipt shows total cost, cash given by customer and change given to customer
        transcript.append(divider);
        transcript.append(String.format(configurableLineFormat, "Total:", total));
        transcript.append(String.format("%d Items\n\n", basketSize));
        transcript.append(String.format(configurableLineFormat, "Cash:", receipt.getPaid()));
        transcript.append(String.format(configurableLineFormat, "Change:", (receipt.getPaid() - total)));

        // Closing sentiments on the bottom of the receipt, thanking for shopping today
        transcript.append(divider);
        for (String s: closingSentiment) {
            transcript.append(String.format("%1$s%2$s%1$s\n", spaceChar.repeat(centerLineBuffer(lineLength, s.length())), s));
        }

        transcript.append(divider);

        // Convert to string and return
        return transcript.toString();
    }

    /**
     * Calculate the white space needed either side of a center-aligned word on the receipt
     *
     * @param lineLength the number of characters that can fit on one line in the receipt
     * @param stringLength the length of the word that is being center-aligned
     * @return the number of white space characters that need to be place before and after the word on the line in the receipt
     */
    private static int centerLineBuffer(int lineLength, int stringLength){
        return (lineLength - stringLength) / 2;
    }
}

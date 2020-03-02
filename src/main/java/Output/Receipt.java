//package Output;
//
//import java.util.*;
//import java.text.*;
//
///**
// * Receipt object stores a basket of products in a HashMap with products as keys and quantity purchased as values
// */
//public class Receipt {
//    private Map<Products, Integer> basket;
//
//    /**
//     *  Creates an empty HashMap as the container for the receipt
//     */
//    Receipt(){
//        basket = new HashMap<>();
//    }
//
//    /**
//     * Return the contents of the basket
//     *
//     * @return the HashMap of products and quantities
//     */
//    public Map<Products, Integer> getBasket() {
//        return basket;
//    }
//
//    /**
//     * Add one item to the basket
//     *
//     * @param item a product to be added to the basket
//     */
//    public void add(Products item) {
//        if (basket.containsKey(item)) {
//            basket.get(item) += 1;
//        } else {
//            basket.put(item, 1);
//        }
//        // update stock method decrement stock by 1
//    }
//
//    /**
//     * Add multiple of the same item to the basket
//     *
//     * @param item a product to be added to the basket
//     * @param quantity the quantity of that item to be added to the basket
//     */
//    public void add(Products item, int quantity) {
//        if (quantity < 1) {
//            throw new IllegalArgumentException("You attempted to add %d %ss to the basket, quantities must be >= 1", quantity, item.getName());
//        } else {
//            if (basket.containsKey(item)) {
//                basket.get(item) += quantity;
//            } else {
//                basket.put(item, quantity);
//            }
//        }
//        // update stock method decrement stock by quantity
//    }
//
//    /**
//     * Add multiple different items to the basket
//     *
//     * @param items a list of singular products to be added to the basket
//     */
//    public void add(List<Products> items) {
//        for (Products item: items) {
//            this.add(item);
//        }
//    }
//
//    /**
//     * Remove one item from the basket
//     *
//     * @param item a product to be removed from the basket
//     */
//    public void remove(Products item) {
//        if (basket.containsKey(item) && (basket.get(item) > 1)) {
//            basket.get(item) -= 1;
//        } else if (basket.containsKey(item) && (basket.get(item) == 1)) {
//            basket.remove(item);
//        }
//        // update stock method increment stock by 1
//    }
//
//    /**
//     * Remove multiple of the same item from the basket
//     *
//     * @param item a product to be removed from the basket
//     * @param quantity the quantity of that item to be removed from the basket
//     */
//    public void remove(Products item, int quantity) {
//        if ((basket.get(item) - quantity) < 0) {
//            throw new IllegalArgumentException("There are only %d %ss in the basket and you attempted to remove %d from the basket", basket.get(item), item.getName(), quantity);
//        }
//        if (basket.containsKey(item) && ((basket.get(item) - quantity) >= 1)) {
//            basket.get(item) -= quantity;
//        } else if (basket.containsKey(item) && ((basket.get(item) - quantity) == 0)) {
//            basket.remove(item);
//        }
//        // update stock method increment stock by 1
//    }
//
//    /**
//     * Return the current total price of the basket
//     *
//     * @return the total price of the current basket
//     */
//    public double priceTotal() {
//        double total = 0;
//        for (Products item: basket.keySet()) {
//            for (int i = 0; i < basket.get(item); i++){
//                total += item.getCost();
//            }
//        }
//        return priceTotal();
//    }
//
//    /**
//     * Produce a string representation of the receipt
//     *
//     * @param cash the money taken from the customer
//     * @return a receipt as a string ready for printing
//     */
//    public String toString(double cash){
//        // Create a string builder object to store the receipt transcript in
//        StringBuilder transcript = new StringBuilder();
//
//        // Set shop name which will be printed at the top of the receipt
//        String shopName = "Tricky Trinkets";
//
//        // Get current date and time to display on the receipt
//        String currentDateTime = getDate();
//
//        // Initialise constants for formatting the receipt
//        String dash = "-";
//        String space = " ";
//        int lineLength = 43;
//
//        // Calculate totsl price to display on the receipt
//        double total = priceTotal();
//
//        // Create the divider line string which will be used to divide sections of the receipt
//        String divider = String.format("%s\n", dash.repeat(lineLength));
//
//        // Top of the receipt with the shop name, address, phone number and current date and time
//        transcript.append(divider);
//        transcript.append(String.format("%1$s%2$s%1$s\n", space.repeat(centerLineBuffer(lineLength, shopName.length())), shopName));
//        transcript.append(String.format("%1$s%2$s%1$s\n\n", space.repeat(centerLineBuffer(lineLength, currentDateTime.length())), currentDateTime));
//        transcript.append(String.format("Address: %s\n", "1 Village Road"));
//        transcript.append(String.format("Tel No: %s\n", "0123 456 7890"));
//
//        // Middle of receipt displaying products with their names on the left and prices on the right
//        transcript.append(divider);
//        transcript.append(String.format("%-36s %6s \n\n", "Description:", "Price:"));
//        for (Products item: basket.keySet()) {
//            for (int i = 0; i < basket.get(item); i++) {
//                transcript.append(String.format("%-36s %6.2d \n", item.getName(), item.getCost()));
//            }
//        }
//
//        // Bottom of the receipt shows total cost, cash given by customer and change given to customer
//        transcript.append(divider);
//        transcript.append(String.format("%-36s %6.2f \n", "Total:", total));
//        transcript.append(String.format("%d Items\n\n", basket.size()));
//        transcript.append(String.format("%-36s %6.2f \n", "Cash:", cash));
//        transcript.append(String.format("%-36s %6.2f \n", "Change:", (cash - total));
//
//
//        transcript.append(divider);
//
//        // Convert to string and return
//        return transcript.toString();
//    }
//
//    /**
//     * Calculate the white space needed either side of a center-aligned word on the receipt
//     *
//     * @param lineLength the number of characters that can fit on one line in the receipt
//     * @param stringLength the length of the word that is being center-aligned
//     * @return the number of white space characters that need to be place before and after the word on the line in the receipt
//     */
//    private static int centerLineBuffer(int lineLength, int stringLength){
//        return (lineLength - stringLength) / 2;
//    }
//
//    /**
//     * Return the date and time of when the receipt string is constructed
//     *
//     * @return the date and time of when the receipt string is constructed
//     */
//    private static String getDate() {
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        return dateFormat.format(new Date());
//    }
//}

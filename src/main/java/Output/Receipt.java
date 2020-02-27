package Output;

import java.util.*;
import java.text.*;

public class Receipt {
    private Map<Products, Integer> basket;

    Receipt(){
        basket = new HashMap<>();
    }

    public Map<Products, Integer> getBasket() {
        return basket;
    }

    public Products add(Products item) {
        if (basket.containsKey(item)) {
            basket.get(item) += 1;
        } else {
            basket.put(item, 1);
        }
        // crud method decrement stock by 1
    }

    public void remove(Products item) {
        if (basket.containsKey(item) && (basket.get(item) > 1)) {
            basket.get(item) -= 1;
        } else if (basket.containsKey(item) && (basket.get(item) == 1)) {
            basket.remove(item);
        }
        // crud method increment stock by 1
    }

    public double priceTotal() {
        double total = 0;
        for (Products item: basket.keySet()) {
            for (int i = 0; i < basket.get(item); i++){
                total += item.getCost();
            }
        }
        return priceTotal();
    }

    public String toString(double cash){
        StringBuilder transcript = new StringBuilder();
        String shopName = "Tricky Trinkets";
        String currentDateTime = getDate();
        String dash = "-";
        String space = " ";
        int lineLength = 43;
        double total = priceTotal();
        String divider = String.format("%s\n", dash.repeat(lineLength));
        transcript.append(divider);
        transcript.append(String.format("%1$s%2$s%1$s\n", space.repeat(centerLineBuffer(lineLength, shopName.length())), shopName));
        transcript.append(String.format("%1$s%2$s%1$s\n\n", space.repeat(centerLineBuffer(lineLength, currentDateTime.length())), currentDateTime));
        transcript.append(String.format("Address: %s\n", "1 Village Road"));
        transcript.append(String.format("Tel No: %s\n", "0123 456 7890"));
        transcript.append(divider);
        transcript.append(String.format("%-36s %6s \n\n", "Description:", "Price:"));
        for (Products item: basket.keySet()) {
            for (int i = 0; i < basket.get(item); i++) {
                transcript.append(String.format("%-36s %6.2d \n", item.getName(), item.getCost()));
            }
        }
        transcript.append(divider);
        transcript.append(String.format("%-36s %6.2f \n", "Total:", total));
        transcript.append(String.format("%d Items\n\n", basket.size()));
        transcript.append(String.format("%-36s %6.2f \n", "Cash:", cash));
        transcript.append(String.format("%-36s %6.2f \n", "Change:", (cash - total));
        transcript.append(divider);
        return transcript.toString();
    }

    private static int centerLineBuffer(int lineLength, int stringLength){
        return (lineLength - stringLength) / 2;
    }

    private static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(new Date());
    }
}

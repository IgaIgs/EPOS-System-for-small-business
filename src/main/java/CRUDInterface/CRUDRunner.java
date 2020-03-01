package CRUDInterface;

import DbTables.Product;
import DbTables.PurchaseHistory;
import DbTables.Receipt;


public class CRUDRunner {


    public static void main(String[] args) {
        CRUDInterface<Product> cI = new CRUDTeamDb<Product>();


        Receipt ringNteq = new Receipt("r1", 259.95, "01/03/2020",260.00);
        Product rammTequilla = new Product("p1","Rammstein Tequilla", "RammMerch",true,68.95,10,79.95);
        Product goldRing = new Product("p2", "Gold Ring", "Jewellery", false, 120.00, 4, 180.00);
        Product rammBotOpen = new Product("p3","Rammstein Bottle Opener", "RammMerch", false, 12.00, 20,14.00 );
        PurchaseHistory purchaseHistory = new PurchaseHistory(rammBotOpen, ringNteq, 2);


        //cI.create(rammTequilla);
        //cI.create(goldRing);
        //cI.create(ringNteq);
        //cI.update(purchaseHistory);
        //cI.create(rammBotOpen);

        System.out.println(cI.readAll(Product.class));
        System.out.println(cI.readByIdReturnE(Product.class, "p2"));


    }
}

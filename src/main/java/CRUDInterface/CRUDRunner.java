package CRUDInterface;

import DbTables.Product;
import DbTables.PurchaseHistory;
import DbTables.Receipt;

public class CRUDRunner {


    public static void main(String[] args) {
        CRUDInterface cI = new CRUDTeamDb();

        /*
        I've commented this out while I test other stuff

        Receipt ringNteq = new Receipt(287.95, "01/03/2020",290.00);
        Product rammTequilla = new Product("Rammstein Tequilla", "RammMerch",true,68.95,10,79.95);
        Product goldRing = new Product("Gold Ring", "Jewellery", false, 120.00, 4, 180.00);
        Product rammBotOpen = new Product("Rammstein Bottle Opener", "RammMerch", false, 12.00, 19,14.00 );
        PurchaseHistory purchaseHistory = new PurchaseHistory(goldRing, ringNteq, 1);
        */

        //TODO for Ben: automatic update of purchaseHistory after selling transaction

//        cI.generalUpdate(rammTequilla);
//        cI.generalUpdate(goldRing);
//        cI.generalUpdate(rammBotOpen);
//        cI.generalUpdate(ringNteq);
//        cI.generalUpdate(purchaseHistory);


        //cI.readProdById("rammTeq");

        //cI.readProduct(0);

        cI.sellItem(5, 1, 1000);


        //System.out.println(cI.readAll(Product.class));
        //System.out.println(cI.readByIdReturnE(Product.class, "p2"));


    }
}

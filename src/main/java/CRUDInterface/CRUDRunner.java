package CRUDInterface;

import DbTables.Product;
import DbTables.PurchaseHistory;
import DbTables.Receipt;

public class CRUDRunner {


    public static void main(String[] args) {
        CRUDInterface cI = new CRUDTeamDb();

        /*
        I've commented this out while I test other stuff


        Product rammTequilla = new Product("Rammstein Tequilla", "RammMerch",true,68.95,10,79.95);
        Product goldRing = new Product("Gold Ring", "Jewellery", false, 120.00, 4, 180.00);
        Product rammBotOpen = new Product("Rammstein Bottle Opener", "RammMerch", false, 12.00, 19,14.00 );
        PurchaseHistory purchaseHistory = new PurchaseHistory(goldRing, ringNteq, 1);
        */

//        cI.generalUpdate(rammTequilla);
//        cI.generalUpdate(goldRing);
//        cI.generalUpdate(rammBotOpen);
//        cI.generalUpdate(ringNteq);
//        cI.generalUpdate(purchaseHistory);

        // TODO 4 Iga: add a bunch of rammstein products to the database
        // TODO 4 Iga and Ben: come up with a celebration idea for after the project :D

        //cI.printCategories(cI.getCategories());
        //cI.readProduct(0);
        //cI.readProduct(0);
        //cI.readByProdNameReturnId("ramm");
        //cI.updateProduct(4, "p.stock", "69");

        // it works with "Name" and "Category" and "cost"
        // but "Cost" wouldn't work
        cI.updateProduct(10, "Sell_price", "20");

        //Receipt ringNteq = new Receipt(287.95, "01/03/2020",290.00);
        //cI.generalUpdate(ringNteq);
        //cI.sellItem(4, 1, 1000);

        cI.addToBasket(4, 3);
        //cI.addToBasket(5, 1);
        cI.addToBasket(5, 2);
        //cI.checkout(10000);


        cI.removeFromBasket(4, 2);
        cI.checkout(10000);



    }
}

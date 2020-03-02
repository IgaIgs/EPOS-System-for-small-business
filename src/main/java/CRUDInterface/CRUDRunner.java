package CRUDInterface;

import DbTables.Product;
import DbTables.PurchaseHistory;
import DbTables.Receipt;

public class CRUDRunner {


    public static void main(String[] args) {
        CRUDInterface cI = new CRUDTeamDb();


        Receipt ringNteq = new Receipt("r1", 287.95, "01/03/2020",290.00, 2.05);
        Product rammTequilla = new Product("rammTeq","Rammstein Tequilla", "RammMerch",true,68.95,10,79.95);
        Product goldRing = new Product("goldRng", "Gold Ring", "Jewellery", false, 120.00, 4, 180.00);
        Product rammBotOpen = new Product("rammBotOp","Rammstein Bottle Opener", "RammMerch", false, 12.00, 19,14.00 );
        PurchaseHistory purchaseHistory = new PurchaseHistory(goldRing, ringNteq, 1);

        rammBotOpen.setStock(19);
        //cI.update(rammTequilla);
        //cI.update(goldRing);
        //cI.update(ringNteq);
        //cI.update(purchaseHistory);
        //cI.update(rammBotOpen);

        cI.readProdById("rammTeq");

        //System.out.println(cI.readAll(Product.class));
        //System.out.println(cI.readByIdReturnE(Product.class, "p2"));


    }
}

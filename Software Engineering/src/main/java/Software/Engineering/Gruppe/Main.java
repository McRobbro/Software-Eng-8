package Software.Engineering.Gruppe;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.*;
import Software.Engineering.Gruppe.Repository.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


public class Main {



    public static void main(String[] args) throws IOException {


        String userDir = System.getProperty("user.dir");
        String databaseDir = "\\db\\group8dbftw.db";
        SqliteDatabase sqliteDatabase = new SqliteDatabase("jdbc:sqlite:" + userDir + databaseDir);

        StoreRepository storeRepository = new StoreRepository(sqliteDatabase);
        ProductRepository productRepository = new ProductRepository(sqliteDatabase, storeRepository);
        UserRepository userRepository = new UserRepository(sqliteDatabase);
        OrderRepository orderRepository = new OrderRepository(sqliteDatabase, userRepository, storeRepository);
        AuctionRepository auctionRepository = new AuctionRepository(sqliteDatabase, storeRepository, productRepository);
        BidRepository bidRepository = new BidRepository(sqliteDatabase, userRepository, auctionRepository);

/*
        System.out.println("\nfunctionality for store");
        //System.out.println(storeRepository.createStore("Marius-butikk", "Marius", "null","lorem ipsum"));
        //System.out.println(storeRepository.updateStore(5,"Andreas-butikk", "Andreas", "null", "lorem ipsum"));
        //System.out.println(storeRepository.deleteStore("Marcus-butikk"));
        System.out.println(storeRepository.getAllStores());
        System.out.println(storeRepository.getSpecificStoreBySlug("Fredriks-butikk").getSlug());
        System.out.println(storeRepository.getSpecificStoreBySlug("Heidi-butikk"));
        System.out.println(storeRepository.getSpecificStoreById(2));



 */

       // System.out.println("\nfunctionality for products");
        // System.out.println(productRepository.createProduct(storeRepository.getSpecificStoreBySlug("johansens-butikk"), "bor", "null", "lorem ipsum", "Belysning", "nlbaqba", 100));
        //System.out.println(storeRepository.getSpecificStoreBySlug("philips-butikk").addProductBySlug("LUX-taklampe"));
        /*
        System.out.println(productRepository.getAllProducts());
        System.out.println(productRepository.getSpecificProduct("johansens-butikk", "LUX-taklampe"));
        System.out.println(productRepository.getSpecificProduct("Fredriks-butikk", "LUX-taklampe"));
        System.out.println(productRepository.getSpecificProduct("philips-butikk", "Da-Vinci-Barglob-modell-102"));
        System.out.println(productRepository.getSpecificProductById( 1));
        System.out.println(productRepository.updateProduct(1, "LUX-taklampe", "LUX taklampe", "bilde", "descript", "Belysning"));



        System.out.println("dummy");
        Store testStore = storeRepository.createStore("DummyStSlug", "DummyStName", "DummyStImg", "DummyStDesc");
        productRepository.createProduct(testStore, "DummySlug", "DummyName", "DummyImg",
                "DummyDesc", "DummyCat", 12.09);
       // System.out.println(productRepository.getSpecificProductBySlug("DummySlug"));

        System.out.println("*");
        System.out.println(productRepository.getProductsFromStore("DummyStSlug"));



        System.out.println("\nfunctionality for user");
        System.out.println("userID");
        System.out.println(userRepository.getSpecificUser(100).getUserId());
        System.out.println(userRepository.getSpecificUser(100));
        */

        LocalDateTime startDate = LocalDateTime.of(2021,
                Month.NOVEMBER, 16, 17, 0, 0);

        LocalDateTime endDate = LocalDateTime.of(2021,
                Month.NOVEMBER, 18, 20, 30, 0);

        //Period period = Period.between(startDate.toLocalDate(), endDate.toLocalDate());


        System.out.println("\nfunctionality for auction");
        Auction auction1 = auctionRepository.createAuction(storeRepository.getSpecificStoreBySlug("Heidi-butikk"),
                productRepository.getSpecificProductBySlug("LUX-taklampe"), 800, startDate, endDate);
        System.out.println(auction1);

       // System.out.println(auctionRepository.getAuctionById(71));
        //System.out.println(auction1.getAuctionId());









/*
        System.out.println("\nfunctionality for order");
        System.out.println(orderRepository.createOrder(LocalDateTime.now(), userRepository.getSpecificUser(100), storeRepository.getSpecificStoreBySlug("philips-butikk")));
        System.out.println(orderRepository.getOrderById(31));
        Order order1 = orderRepository.createOrder(endDate, userRepository.getSpecificUser(100), storeRepository.getSpecificStoreById(3));
        System.out.println(order1);
        System.out.println(order1.getOrderId());
        //orderRepository.deleteOrder(7);



 */
        /*
        System.out.println("functionality for bid");
        bidRepository.makeBid(userRepository.getSpecificUser(100), auctionRepository.getAuctionById(81), 100);
        */

        /*
        List<Bid> bidList = bidRepository.getBidFromAuctionId(90);
        for (Bid oneBid: bidList) {
            System.out.println(oneBid);
        }

        System.out.println("\nEnd of main");


         */

/*
        for (Auction oneAuction: auctionRepository.getAllAuctionsFromSpecificStore("johansens-butikk")
             ) {
            System.out.println(oneAuction);

        }
        System.out.println("***********************");
        System.out.println(auctionRepository.getAuctionById(95));

        System.out.println(auctionRepository.getSpecificAuction("johansens-butikk", "LUX-taklampe"));


 */
/*

        System.out.println("***************");
        System.out.println(auctionRepository.getAuctionById(95));
       // System.out.println(bidRepository.getWinner(95));


 */
        System.out.println(orderRepository.createOrder(LocalDateTime.now(), userRepository.getSpecificUser(100), storeRepository.getSpecificStoreById(1), productRepository.getSpecificProductById(1) ));

        System.out.println(bidRepository.getAllBidsFromSpecificAuction(95));

        System.out.println(bidRepository.getWinner(95));


    }
}
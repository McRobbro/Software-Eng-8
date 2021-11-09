package Software.Engineering.Gruppe;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Auction;
import Software.Engineering.Gruppe.Repository.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.util.Date;


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
        BidRepository bidRepository = new BidRepository(sqliteDatabase);

        System.out.println(storeRepository.getAllStores());
        System.out.println(storeRepository.getSpecificStoreBySlug("Fredriks-butikk").getSlug());
        System.out.println(storeRepository.getSpecificStoreBySlug("johansens-butikk").getSlug());
        System.out.println(storeRepository.getSpecificStoreBySlug("philips-butikk").getSlug());

        // System.out.println(storeRepository.createStore("Marius-butikk", "Marius", "null","lorem ipsum"));
        // System.out.println(storeRepository.updateStore(5,"Andreas-butikk", "Andreas", "null", "lorem ipsum"));
        // System.out.println(storeRepository.deleteStore("Marcus-butikk"));

        //Cornelia is checking functionality
        //System.out.println(storeRepository.createStore("Heidi-butikk", "Heidi", "null","lorem ipsum"));
        //System.out.println(storeRepository.createStore("Zofie-butikk", "Zofie", "null","vintagevarer"));
        //System.out.println(storeRepository.getSpecificStoreBySlug("Heidi-butikk"));
        //System.out.println(storeRepository.updateStore(11, "Zofies-butikk", "Zofie", "null", "vintagevarer"));
        //System.out.println(storeRepository.getSpecificStoreBySlug("Zofies-butikk").getSlug());

        //System.out.println(storeRepository.createStore("Maia-butikk", "Maia", "null","lorem ipsum"));
        System.out.println(storeRepository.getAllStores());

        //creating products
       // System.out.println(productRepository.createProduct("LUX-taklampe", "LUX taklampe", "null", "lorem ipsum", "Belysning"));
        //System.out.println(productRepository.getAllProducts());
        //System.out.println(storeRepository.getSpecificStoreBySlug("philips-butikk").addProductBySlug("LUX-taklampe"));
        System.out.println("\nDivider\n");
        //System.out.println(productRepository.getSpecificProduct("johansens-butikk", "LUX-taklampe"));
        //System.out.println(productRepository.getSpecificProduct("Fredriks-butikk", "LUX-taklampe"));
        //System.out.println(productRepository.getSpecificProduct("philips-butikk", "Da-Vinci-Barglob-modell-102"));


       // System.out.println(userRepository.getSpecificUser(100));
        System.out.println("product1: ");
        System.out.println(productRepository.getSpecificProductById( 1));

       // System.out.println(orderRepository.createOrder(LocalDate.now(), userRepository.getSpecificUser(100),
        //        storeRepository.getSpecificStoreBySlug("philips-butikk")));
        LocalDateTime startDate = LocalDateTime.of(2021,
                Month.JULY, 11, 17, 0, 0);

        LocalDateTime endDate = LocalDateTime.of(2021,
                Month.JULY, 11, 18, 30, 0);

       // System.out.println(storeRepository.getAllStores());

        Auction auction1 = auctionRepository.createAuction(storeRepository.getSpecificStoreBySlug("philips-butikk"),
                                                            productRepository.getSpecificProductBySlug("LUX-taklampe"), startDate, endDate);


       System.out.println(auction1);
        System.out.println(auction1.getAuctionTimeDurationInMin());
       // System.out.println(auction1.getAuctionTimeDurationInMin());


        Period period = Period.between(startDate.toLocalDate(), endDate.toLocalDate());

        System.out.println(userRepository.getSpecificUser(100).getUserId());

        System.out.println("Skal hente produkt 3");
        System.out.println(productRepository.getSpecificProduct("philips-butikk", "Skuvseng"));
        System.out.println("Skal hente produkt 1");
        System.out.println(productRepository.getSpecificProduct("", "LUX-taklampe"));

        System.out.println("************************");
       System.out.println(auctionRepository.getAuctionById(71));

        System.out.println(storeRepository.getSpecificStoreById(2));


        System.out.println("**************************************");
        //bidRepository.makeBid(userRepository.getSpecificUser(100), auctionRepository.getAuctionById(81), 100);

        System.out.println(auctionRepository.getAuctionById(81));

    }
}
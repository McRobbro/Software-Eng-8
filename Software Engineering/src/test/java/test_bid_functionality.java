import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Auction;
import Software.Engineering.Gruppe.Model.Product;
import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Model.User;
import Software.Engineering.Gruppe.Repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;


public class test_bid_functionality {
    Path userDir = Paths.get(System.getProperty("user.dir")).getParent();
    String databaseDir = "\\db\\FakeDatabase.db";
    String url = "jdbc:sqlite:" + userDir + databaseDir;
    SqliteDatabase sqliteDatabase = new SqliteDatabase(url);

    UserRepository userRepository = new UserRepository(sqliteDatabase);
    StoreRepository storeRepository = new StoreRepository(sqliteDatabase);
    ProductRepository productRepository = new ProductRepository(sqliteDatabase, storeRepository);
    AuctionRepository auctionRepository = new AuctionRepository(sqliteDatabase, storeRepository, productRepository);
    BidRepository bidRepository = new BidRepository(sqliteDatabase, userRepository, auctionRepository);

    @BeforeEach
    public void data_setup() {
        LocalDateTime dummyDateStart = LocalDateTime.of(2021, Month.NOVEMBER, 10, 18, 0, 0);
        LocalDateTime dummyDateEnd = LocalDateTime.of(2030, Month.NOVEMBER, 10, 19, 15, 0);
        User dummyUser = userRepository.createUser(5,"dummyEmail","dummyUserName", "dummyPassword");
        storeRepository.createStore("dummySlug", "dummyName", "dummyImage", "dummyBio");
        productRepository.createProduct(storeRepository.getSpecificStoreBySlug("dummySlug"), "prodSlug", "prodName", "prodImage", "prodBio", "prodCat",800);

        auctionRepository.createAuction(
                storeRepository.getSpecificStoreBySlug("dummySlug"),
                productRepository.getSpecificProduct("dummySlug", "prodSlug"),
                850,
                dummyDateStart,
                dummyDateEnd
            );

        System.out.println(auctionRepository.getSpecificAuction("dummySlug", "prodSlug"));
        bidRepository.makeBid(dummyUser, auctionRepository.getSpecificAuction("dummySlug", "prodSlug"), 900);

    }

    @AfterEach
    public void tear_down() {
        

    }

    @Test
    public void test_create_bid() {
        assertThat(bidRepository.getBidFromAuctionId(5), null);

    }



}
import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.User;
import Software.Engineering.Gruppe.Repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class test_cannot_bid_lower_than_current_bid_price {

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
        LocalDateTime dummyDateStart = LocalDateTime.of(2021, Month.NOVEMBER, 10, 13, 0, 0);
        LocalDateTime dummyDateEnd = LocalDateTime.of(2100, Month.NOVEMBER, 15, 13, 0, 0);
        storeRepository.createStore("dummySlug", "dummyName", "dummyImage", "dummyBio");
        productRepository.createProduct(storeRepository.getSpecificStoreBySlug("dummySlug"), "prodSlug", "prodName", "prodImage", "prodBio", "prodCat", 600);

        // auction object
        auctionRepository.createAuction(100,
                storeRepository.getSpecificStoreBySlug("dummySlug"),
                productRepository.getSpecificProduct("dummySlug", "prodSlug"),
                500,
                dummyDateStart,
                dummyDateEnd
        );

    }

    @AfterEach
    public void tearDown() {
        bidRepository.deleteBid(1);
        bidRepository.deleteBid(2);
        storeRepository.deleteStore("dummySlug");
        productRepository.deleteProduct("prodSlug");
        userRepository.deleteUser(15);
        userRepository.deleteUser(16);
        auctionRepository.deleteAuction(100);
    }


    @Test
    public void test_can_not_bid_lower_than_the_auction_current_bid_price() {
        User dummyUser1 = userRepository.createUser(15,"dummyEmail1","dummyUserName1", "dummyPassword1");
        User dummyUser2 = userRepository.createUser(16,"dummyEmail1","dummyUserName1", "dummyPassword1");
        bidRepository.makeBid(1, dummyUser1, auctionRepository.getSpecificAuction("dummySlug", "prodSlug"), 800);

        assertNull(bidRepository.makeBid(2, dummyUser2, auctionRepository.getSpecificAuction("dummySlug", "prodSlug"), 600));

    }

}

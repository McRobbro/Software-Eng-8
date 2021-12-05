import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Auction;
import Software.Engineering.Gruppe.Model.User;
import Software.Engineering.Gruppe.Repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class test_can_get_auction_bid_winner {

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
        LocalDateTime dummyDateStart = LocalDateTime.of(2010, Month.NOVEMBER, 10, 18, 0, 0);
        LocalDateTime dummyDateEnd = LocalDateTime.of(2030, Month.NOVEMBER, 10, 19, 15, 0);



        storeRepository.createStore("dummySlug", "dummyName", "dummyImage", "dummyBio");
        productRepository.createProduct(storeRepository.getSpecificStoreBySlug("dummySlug"), "prodSlug", "prodName", "prodImage", "prodBio", "prodCat",800);


        // auction object
        auctionRepository.createAuction( 100,
                storeRepository.getSpecificStoreBySlug("dummySlug"),
                productRepository.getSpecificProduct("dummySlug", "prodSlug"),
                850,
                dummyDateStart,
                dummyDateEnd
        );

        // user objects
        User dummyUser1 = userRepository.createUser(1,"dummyEmail1","dummyUserName1", "dummyPassword1");
        User dummyUser2 = userRepository.createUser(2,"dummyEmail2","dummyUserName2", "dummyPassword2");
        User dummyUser3 = userRepository.createUser(3,"dummyEmail3","dummyUserName3", "dummyPassword3");

        // making bid objects
        bidRepository.makeBid(10, dummyUser1, auctionRepository.getSpecificAuction("dummySlug", "prodSlug"), 900);
        bidRepository.makeBid(11, dummyUser2, auctionRepository.getSpecificAuction("dummySlug", "prodSlug"), 1000);
        bidRepository.makeBid(12, dummyUser3, auctionRepository.getSpecificAuction("dummySlug", "prodSlug"), 1500);



    }



    @AfterEach
    public void tearDown() {
        userRepository.deleteUser(1);
        userRepository.deleteUser(2);
        userRepository.deleteUser(3);
        bidRepository.deleteBid(10);
        bidRepository.deleteBid(11);
        bidRepository.deleteBid(12);
        storeRepository.deleteStore("dummySlug");
        productRepository.deleteProduct("prodSlug");
        auctionRepository.deleteAuction(100);



    }

    @Test
    public void test_can_fetch_winner_from_auction_when_auction_has_ended() {
        // auction closed
        LocalDateTime updatedStartDate = LocalDateTime.of(2010, Month.NOVEMBER, 10, 18, 0, 0);
        LocalDateTime updatedEndDate = LocalDateTime.of(2020, Month.NOVEMBER, 10, 19, 15, 0);
        auctionRepository.updateAuction(100,
                storeRepository.getSpecificStoreBySlug("dummySlug"),
                productRepository.getSpecificProduct("dummySlug", "prodSlug"),
                850,
                updatedStartDate,
                updatedEndDate
        );

        assertEquals(1500, bidRepository.getWinner(100).getAmount());

    }
    
    @Test
    public void test_cannot_fetch_winner_when_auction_has_not_started() {
        // auction not started
        LocalDateTime updatedStartDate = LocalDateTime.of(2030, Month.NOVEMBER, 10, 18, 0, 0);
        LocalDateTime updatedEndDate = LocalDateTime.of(2030, Month.NOVEMBER, 11, 19, 15, 0);
        auctionRepository.updateAuction(100,
                storeRepository.getSpecificStoreBySlug("dummySlug"),
                productRepository.getSpecificProduct("dummySlug", "prodSlug"),
                850,
                updatedStartDate,
                updatedEndDate
        );

        assertNull(bidRepository.getWinner(100));
    }

    @Test
    public void test_cant_fetch_winner_when_bid_list_is_empty() {
        bidRepository.deleteBid(10);
        bidRepository.deleteBid(11);
        bidRepository.deleteBid(12);
        assertNull(bidRepository.getWinner(100));

    }

}

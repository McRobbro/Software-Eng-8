import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Bid;
import Software.Engineering.Gruppe.Model.User;
import Software.Engineering.Gruppe.Repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class test_can_get_all_bids_from_specific_auction {

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

    /* Dette er ikke en test tilknyttet et spesifikt krav. Det tester kun at man kan hente ut fra databasen
       alle bud tilknyttet en spesifikk auksjon, sjekker generelt at opprettelse av bud fungerer som det skal */
    @Test
    public void test_can_fetch_three_bids_from_a_specific_auction() {
        List<Bid> bidListFromSpecificAuction = bidRepository.getAllBidsFromSpecificAuction(100);
        assertEquals(3, bidListFromSpecificAuction.size());

    }

}

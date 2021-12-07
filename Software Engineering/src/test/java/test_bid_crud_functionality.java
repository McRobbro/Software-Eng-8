import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Bid;
import Software.Engineering.Gruppe.Model.User;
import Software.Engineering.Gruppe.Repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


public class test_bid_crud_functionality {
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
        LocalDateTime dummyDateEnd = LocalDateTime.of(2050, Month.NOVEMBER, 10, 19, 15, 0);
        storeRepository.createStore("dummySlug", "dummyName", "dummyImage", "dummyBio");
        productRepository.createProduct(storeRepository.getSpecificStoreBySlug("dummySlug"),
                "prodSlug",
                "prodName",
                "prodImage",
                "prodBio",
                "prodCat",
                800);



        // auction object
        auctionRepository.createAuction( 100,
                storeRepository.getSpecificStoreBySlug("dummySlug"),
                productRepository.getSpecificProduct("dummySlug", "prodSlug"),
                850,
                dummyDateStart,
                dummyDateEnd
            );

        User dummyUser = userRepository.createUser(5,"dummyEmail","dummyUserName", "dummyPassword");
        bidRepository.makeBid(50, dummyUser, auctionRepository.getSpecificAuction("dummySlug", "prodSlug"), 900);

    }

    @AfterEach
    public void tear_down() {
        userRepository.deleteUser(5);
        storeRepository.deleteStore("dummySlug");
        productRepository.deleteProduct("prodSlug");
        bidRepository.deleteBid(50);
        auctionRepository.deleteAuction(100);
    }


    /* Tester krav "Auksjon.Bud.FørsteBud" og "Auksjon.Bud.NesteBud"*/
    @ParameterizedTest
    @MethodSource("graphPath")
    public void test_create_bid(String expected) {
        Bid bid = bidRepository.getSpecificBidById(50);
        assertThat(bid, test_helper_class.hasGraph(expected, notNullValue()));

    }
    /* Tester krav "Auksjon.Bud.FørsteBud" og "Auksjon.Bud.NesteBud"*/
    @Test
    public void test_read_bid() {
        assertNotNull(bidRepository.getSpecificBidById(50));

    }

    /* Dette er ikke en test av et direkte krav, for det skal ikke gå an å slette et aktivt bud, men vi tok med en
    delete_bid metode + test for å ha muligheten til å teste at et bud kan slettes fra bud-tabellen i databasen. */
    @Test
    public void test_delete_bid() {
        bidRepository.deleteBid(50);
        assertNull(bidRepository.getSpecificBidById(50));
    }



    public static Stream graphPath() {
        return Stream.of(
                Arguments.of("bidId")
        );

    }


}


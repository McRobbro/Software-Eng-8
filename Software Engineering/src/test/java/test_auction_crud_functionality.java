import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Auction;
import Software.Engineering.Gruppe.Repository.ProductRepository;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import Software.Engineering.Gruppe.Repository.AuctionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;

import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static com.fasterxml.jackson.core.io.NumberInput.parseInt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



public class test_auction_crud_functionality {

    Path userDir = Paths.get(System.getProperty("user.dir")).getParent();
    String databaseDir = "\\db\\FakeDatabase.db";
    String url = "jdbc:sqlite:" + userDir + databaseDir;
    SqliteDatabase sqliteDatabase = new SqliteDatabase(url);
    StoreRepository storeRepository = new StoreRepository(sqliteDatabase);
    ProductRepository productRepository = new ProductRepository(sqliteDatabase, storeRepository);
    AuctionRepository auctionRepository = new AuctionRepository(sqliteDatabase, storeRepository, productRepository);


    @BeforeEach
    public void data_setup() {
        LocalDateTime dummyDateStart = LocalDateTime.of(2021, Month.NOVEMBER, 10, 18, 0, 0);
        LocalDateTime dummyDateEnd = LocalDateTime.of(2050, Month.NOVEMBER, 10, 19, 15, 0);
        storeRepository.createStore("dummySlug", "dummyName", "dummyImage", "dummyBio");
        productRepository.createProduct(storeRepository.getSpecificStoreBySlug("dummySlug"), "prodSlug", "prodName", "prodImage", "prodBio", "prodCat", 800);


        // auction object
        auctionRepository.createAuction(100,
                storeRepository.getSpecificStoreBySlug("dummySlug"),
                productRepository.getSpecificProduct("dummySlug", "prodSlug"),
                850.0,
                dummyDateStart,
                dummyDateEnd
        );

    }



    @AfterEach
    public void tear_down() {
        auctionRepository.deleteAuction(100);
        storeRepository.deleteStore("dummySlug");
        productRepository.deleteProduct("prodSlug");
    }



    @ParameterizedTest
    @MethodSource("graphPath")
    public void test_create_auction(String expected, String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Auction auction = auctionRepository.getAuctionById(100);
        LocalDateTime auctionStartTime = LocalDateTime.parse(auction.getStartTime().format(formatter).replace("T", " "), formatter);

        assertThat(auction, test_helper_class.hasGraph(expected, equalTo(value)));

        assertThat(auction, allOf(
           hasProperty("startPrice", equalTo(850.0)),
           hasProperty("startTime", equalTo(auctionStartTime))
        ));
    }


    public static Stream graphPath() {
        return Stream.of(
                //  Arguments.of("auctionId"),
                // Arguments.of("store.storeId"),
                Arguments.of("store.slug", "dummySlug"),
                Arguments.of("store.storeName", "dummyName"),
                Arguments.of("store.storeImage", "dummyImage"),
                Arguments.of("store.storeDescription", "dummyBio")
        );

    }


}





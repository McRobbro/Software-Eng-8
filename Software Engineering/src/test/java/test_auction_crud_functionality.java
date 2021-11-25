import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Product;
import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Repository.ProductRepository;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import Software.Engineering.Gruppe.Repository.AuctionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        LocalDateTime dummyStartDate = LocalDateTime.of(2021,
                Month.NOVEMBER, 23, 16, 30, 0);
        LocalDateTime dummyEndDate = LocalDateTime.of(2021,
                Month.NOVEMBER, 23, 17, 0, 0);
        Store dummyStore = storeRepository.createStore("DummyStSlug", "DummyStName", "DummyStImg", "dummyStDesc");
        Product dummyProduct = productRepository.createProduct(dummyStore, "DummyPrSlug", "DummyPrName", "DummyPrImg",
                "DummyPrDesc", "DummyPrCat", 20.00 );
        auctionRepository.createAuction(dummyStore, dummyProduct, dummyStartDate, dummyEndDate);
    }

    @AfterEach
    public void tear_down() {
        storeRepository.deleteStore("DummyStSlug");
        productRepository.deleteProduct("DummyPrSlug");
        //auctionRepository.deleteAuction();
    }


}

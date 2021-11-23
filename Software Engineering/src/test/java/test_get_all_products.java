import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Repository.ProductRepository;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* is this class necessary? */
public class test_get_all_products {
    Path userDir = Paths.get(System.getProperty("user.dir")).getParent();
    String databaseDir = "\\db\\FakeDatabase.db";
    String url = "jdbc:sqlite:" + userDir + databaseDir;
    SqliteDatabase sqliteDatabase = new SqliteDatabase(url);
    StoreRepository storeRepository = new StoreRepository(sqliteDatabase);
    ProductRepository productRepository = new ProductRepository(sqliteDatabase, storeRepository);

    @BeforeEach
    public void data_setup() {
        Store testStore = storeRepository.createStore("DummyStSlug", "DummyStName", "DummyStImg", "DummyStDesc");
        productRepository.createProduct(testStore, "DummySlug", "DummyName", "DummyImg",
                "DummyDesc", "DummyCat", 12.09);
    }

    @AfterEach
    public void tear_down() {
        storeRepository.deleteStore("DummyStSlug");
        productRepository.deleteProduct("DummySlug");
    }


    @Test
    public void get_all_products() {
        assertEquals(1, productRepository.getAllProducts().size());
        // just to check
        // assertEquals(2, productRepository.getAllProducts().size());
    }
}

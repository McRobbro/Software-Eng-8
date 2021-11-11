/*import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Repository.ProductRepository;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNull;

public class test_product_crud_functionality {
    Path userDir = Paths.get(System.getProperty("user.dir")).getParent();
    String databaseDir = "\\db\\FakeDatabase.db";
    String url = "jdbc:sqlite:" + userDir + databaseDir;
    SqliteDatabase sqliteDatabase = new SqliteDatabase(url);
    StoreRepository storeRepository = new StoreRepository(sqliteDatabase);
    ProductRepository productRepository = new ProductRepository(sqliteDatabase, storeRepository);

    @BeforeEach
    public void data_setup() {
        Store testStore = new Store("DummyStSlug", "DummyStName", "DummyStImg", "DummyStDesc");
        productRepository.createProduct(testStore, "DummySlug", "DummyName", "DummyImg",
                "DummyDesc", "DummyCat", 12.09);
    }

    @AfterEach
    public void tear_down() {
        productRepository.deleteProduct("DummySlug");
    }

    @Test
    public void test_create_product() {
        assertThat(productRepository.getSpecificProduct("DummyStSlug", "DummySlug"), allOf(
                //hasProperty("productId", is("DummyId")),
                //hasProperty("storeId", is("DummyStId")),
                hasProperty("productSlug", is("DummySlug")),
                hasProperty("productName", is("DummyName")),
                hasProperty("productImage", is("DummyImg")),
                hasProperty("productDescription", is("DummyDesc")),
                hasProperty("productCategory", is("DummyCat")),
                hasProperty("productPrice", is(12.09))
        ));

    }
}
*/
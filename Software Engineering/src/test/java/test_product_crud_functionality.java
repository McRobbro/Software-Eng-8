import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Model.Product;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

//crud: create, read, update and delete
public class test_product_crud_functionality {
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
    public void test_create_product() {
        assertThat(productRepository.getSpecificProductBySlug("DummySlug"), allOf(
                hasProperty("productSlug", is("DummySlug")),
                hasProperty("productName", is("DummyName")),
                hasProperty("productImage", is("DummyImg")),
                hasProperty("productDescription", is("DummyDesc")),
                hasProperty("productCategory", is("DummyCat"))
        ));
    }

    @Test
    public void get_all_products() {
        assertEquals(1, productRepository.getAllProducts().size());
    }

    @Test
    public void test_get_specific_product_by_Id(){
        int productId = productRepository.getSpecificProductBySlug("DummySlug").getProductId();
        assertThat(productRepository.getSpecificProductById(productId), allOf(
                hasProperty("productSlug", is("DummySlug")),
                hasProperty("productName", is("DummyName")),
                hasProperty("productImage", is("DummyImg")),
                hasProperty("productDescription", is("DummyDesc")),
                hasProperty("productCategory", is("DummyCat"))
        ));
    }

    @Test
    public void test_update_product() {
        int productId = productRepository.getSpecificProductBySlug("DummySlug").getProductId();
        productRepository.updateProduct(productId, "DummySlugUpdate", "DummyNameUpdate",
                "DummyImgUpdate", "DummyDescUpdate", "DummyCatUpdate", 12.09);
       /* assertThat(productRepository.getSpecificProductBySlug("DummySlugUpdate"), allOf(
                hasProperty("productSlug", is("DummySlugUpdate")),
                hasProperty("productName", is("DummyNameUpdate")),
                hasProperty("productImage", is("DummyImgUpdate")),
                hasProperty("productDescription", is("DummyDescUpdate")),
                hasProperty("productCategory", is("DummyCatUpdate"))
        ));
        */
    }

    @Test
    public void test_delete_product() {
        productRepository.deleteProduct("DummySlug");
        assertNull(productRepository.getSpecificProductBySlug("DummySlug"));
    }
}
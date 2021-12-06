import Software.Engineering.Gruppe.Config.SqliteDatabase;
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


public class test_store_crud_functionality {
    Path userDir = Paths.get(System.getProperty("user.dir")).getParent();
    String databaseDir = "\\db\\FakeDatabase.db";
    String url = "jdbc:sqlite:" + userDir + databaseDir;
    SqliteDatabase sqliteDatabase = new SqliteDatabase(url);
    StoreRepository storeRepository = new StoreRepository(sqliteDatabase);



    @BeforeEach
    public void data_setup() {
        storeRepository.createStore("DummySlug", "DummyName", "DummyUrl", "dummyBio");
    }



    @AfterEach
    public void tear_down() {
        storeRepository.deleteStore("DummySlug");
        storeRepository.deleteStore("DummySlugDummySlug");
    }


    /* Tester krav "Butikk.Registrere" */
    @Test
    public void test_create_store() {
        assertThat(storeRepository.getSpecificStoreBySlug("DummySlug"), allOf(
                hasProperty("slug", is("DummySlug")),
                hasProperty("storeName", is("DummyName")),
                hasProperty("storeImage", is("DummyUrl")),
                hasProperty("storeDescription", is("dummyBio"))
        ));
    }


    /* Tester krav "Butikk.Registrere" */
    @Test
    public void test_read_store() {
        assertThat(storeRepository.getSpecificStoreBySlug("DummySlug"), allOf(
                hasProperty("slug", is("DummySlug")),
                hasProperty("storeName", is("DummyName")),
                hasProperty("storeImage", is("DummyUrl")),
                hasProperty("storeDescription", is("dummyBio"))
        ));

    }


    /* Tester krav "Butikk.Endre" */
    @Test
    public void test_update_store() {
        int testStoreId = storeRepository.getSpecificStoreBySlug("DummySlug").getStoreId();
        storeRepository.updateStore(testStoreId, "DummySlugDummySlug", "DummyNameDummyName", "DummyUrl2", "newDummyBio");
        assertThat(storeRepository.getSpecificStoreBySlug("DummySlugDummySlug"), allOf(
                hasProperty("slug", is("DummySlugDummySlug")),
                hasProperty("storeName", is("DummyNameDummyName")),
                hasProperty("storeImage", is("DummyUrl2")),
                hasProperty("storeDescription", is("newDummyBio"))
        ));
    }

    /* Tester krav "Butikk.Slette" */
    @Test
    public void test_delete_store() {
        storeRepository.deleteStore("DummySlug");
        assertNull(storeRepository.getSpecificStoreBySlug("DummySlug"));
    }


}

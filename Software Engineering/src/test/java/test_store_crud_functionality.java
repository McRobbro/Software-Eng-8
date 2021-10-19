import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class test_store_crud_functionality {
    Path userDir = Paths.get(System.getProperty("user.dir")).getParent();
    String databaseDir = "\\db\\group8dbftw.db";
    String url = "jdbc:sqlite:" + userDir + databaseDir;

    SqliteDatabase sqliteDatabase = new SqliteDatabase(url);
    StoreRepository storeRepository = new StoreRepository(sqliteDatabase);


    @Test
    public void test_create_store() {
        storeRepository.createStore("DummySlug", "DummyName", "DummyUrl", "dummyBio");
        assertEquals("DummySlug", storeRepository.getSpecificStoreBySlug("DummySlug").getSlug());
        storeRepository.deleteStore("DummySlug");
    }

    @Test
    public void test_read_store() {
        storeRepository.createStore("DummySlug", "DummyName", "DummyUrl", "dummyBio");
        assertEquals("DummySlug", storeRepository.getSpecificStoreBySlug("DummySlug").getSlug());
        storeRepository.deleteStore("DummySlug");

    }

    @Test
    public void test_update_store() {
        storeRepository.createStore("DummySlug", "DummyName", "DummyUrl", "dummyBio");
        int testStoreId = storeRepository.getSpecificStoreBySlug("DummySlug").getStoreId();
        storeRepository.updateStore(testStoreId, "DummySlugDummySlug", "DummyNameDummyName", "DummyUrl2", "newDummyBio");
        assertEquals("DummySlugDummySlug", storeRepository.getSpecificStoreBySlug("DummySlugDummySlug").getSlug());
        storeRepository.deleteStore("DummySlugDummySlug");
    }

    @Test
    public void test_delete_store() {
        storeRepository.createStore("DummySlug", "DummyName", "DummyUrl", "dummyBio");
        storeRepository.deleteStore("DummySlug");
        assertNull(storeRepository.getSpecificStoreBySlug("DummySlug"));
    }

   /* @Test
    public void test_create_read_update_delete() {
        //create dummy
        storeRepository.createStore("DummySlug", "DummyName", "DummyUrl", "dummyBio");

        //read the object that we just created "DummySlug" witch means that the store was successfully created.
        assertEquals("DummySlug", storeRepository.getSpecificStoreBySlug("DummySlug").getSlug());

        //update
        int testStoreId = storeRepository.getSpecificStoreBySlug("DummySlug").getStoreId();
        storeRepository.updateStore(testStoreId, "DummySlugDummySlug", "DummyNameDummyName", "DummyUrl2", "newDummyBio");

        //checks if update was successfully
        assertEquals("DummySlugDummySlug", storeRepository.getSpecificStoreBySlug("DummySlugDummySlug").getSlug());

        //delete dummy object
        storeRepository.deleteStore("DummySlugDummySlug");

        //checks if deleted was successfully
        assertNull(storeRepository.getSpecificStoreBySlug("DummySlugDummySlug"));

    }

    */


}

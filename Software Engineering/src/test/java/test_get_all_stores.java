import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test_get_all_stores {
    Path userDir = Paths.get(System.getProperty("user.dir")).getParent();
    String databaseDir = "\\db\\FakeDatabase.db";
    String url = "jdbc:sqlite:" + userDir + databaseDir;
    SqliteDatabase sqliteDatabase = new SqliteDatabase(url);
    StoreRepository storeRepository = new StoreRepository(sqliteDatabase);


    @BeforeEach
    public void data_setup() {
        storeRepository.createStore("DummySlug1", "DummyName1", "DummyUrl1", "dummyBio1");
        storeRepository.createStore("DummySlug2", "DummyName2", "DummyUrl2", "dummyBio2");
        storeRepository.createStore("DummySlug3", "DummyName3", "DummyUrl3", "dummyBio3");

    }



    @AfterEach
    public void tear_down() {
        storeRepository.deleteStore("DummySlug1");
        storeRepository.deleteStore("DummySlug2");
        storeRepository.deleteStore("DummySlug3");

    }
    /* Tester krav "Butikk.Presentere" */
    @Test
    public void test_can_get_all_stores() {
        assertEquals(3, storeRepository.getAllStores().size());
    }






}

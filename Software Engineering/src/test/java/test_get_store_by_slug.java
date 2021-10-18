import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class test_get_store_by_slug {

    Path userDir = Paths.get(System.getProperty("user.dir")).getParent();
    String databaseDir = "\\db\\group8dbftw.db";
    String url = "jdbc:sqlite:" + userDir + databaseDir;

    SqliteDatabase sqliteDatabase = new SqliteDatabase(url);
    StoreRepository storeRepository = new StoreRepository(sqliteDatabase);

    @Test
    public void get_store() {
        assertEquals("philips-butikk", storeRepository.getSpecificStoreBySlug("philips-butikk").getSlug());
        assertEquals("Andreas-butikk", storeRepository.getSpecificStoreBySlug("Andreas-butikk").getSlug());

        // just to check the test
        //assertEquals("Andreas-butikk", storeRepository.getSpecificStoreBySlug("philips-butikk").getSlug());
        //assertEquals("Sabella", storeRepository.getSpecificStoreBySlug("philips-butikk").getSlug());
    }

}

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Test_get_store {


    String url = "jdbc:sqlite:C:\\Users\\phili\\code\\SOE2021-gruppeOppgave-prototype\\Software-Eng-8\\db\\group8dbftw.db";
    SqliteDatabase sqliteDatabase = new SqliteDatabase(url);

    StoreRepository storeRepository = new StoreRepository(sqliteDatabase);

    @Test
    public void get_store() {
        assertEquals("philips-butikk",storeRepository.getSpecificStoreBySlug("philips-butikk").getSlug());

    }

}

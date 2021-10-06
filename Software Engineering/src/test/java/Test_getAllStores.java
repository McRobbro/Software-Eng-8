import Software.Engineering.Gruppe.Config.SQLDatabase;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_getAllStores {

    SQLDatabase SQLdatabase = new SQLDatabase(
            "jdbc:mysql://34.88.134.36:3306/soe_group_8",
            "root",
            "group8ftw"
    );


    @Test
    public void shouldGetAllStores() {

        StoreRepository storeRepository = new StoreRepository(SQLdatabase);

        assertEquals(3, storeRepository.getAllStores().size());

    }
}

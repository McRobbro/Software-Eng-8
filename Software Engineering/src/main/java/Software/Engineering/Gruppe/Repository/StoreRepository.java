package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Config.SQLDatabase;
import Software.Engineering.Gruppe.Model.Store;

import java.util.ArrayList;

public class StoreRepository implements StoreInterface {


    private final SQLDatabase database;

    public StoreRepository(SQLDatabase database) {
        this.database = database;
    }


    @Override
    public ArrayList<Store> getAllStores() {
        return null;
    }

    public SQLDatabase getDatabase() {
        return database;
    }

}

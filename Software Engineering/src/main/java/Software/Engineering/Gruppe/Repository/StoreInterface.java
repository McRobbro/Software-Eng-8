package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Model.Store;

import java.util.List;

public interface StoreInterface {
    List<Store> getAllStores();

    Store getSpecificStoreByID(int slug);
}

package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Model.Store;

import java.util.List;

public interface StoreInterface {
    List<Store> getAllStores();

    Store getSpecificStoreBySlug(String SLUG);

    Store createStore(String slug, String storeName, String storeImage, String storeDescription);
}

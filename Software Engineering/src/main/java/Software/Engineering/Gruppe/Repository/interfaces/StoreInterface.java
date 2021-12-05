package Software.Engineering.Gruppe.Repository.interfaces;

import Software.Engineering.Gruppe.Model.Store;

import java.util.List;

public interface StoreInterface {
    List<Store> getAllStores();


    Store getSpecificStoreBySlug(String SLUG);

    Store getSpecificStoreById(int storeId);


    Store createStore(String slug, String storeName, String storeImage, String storeDescription);


    Store updateStore(int storeId, String slug, String storeName, String storeImage, String storeDescription);


    boolean deleteStore(String slug);

}

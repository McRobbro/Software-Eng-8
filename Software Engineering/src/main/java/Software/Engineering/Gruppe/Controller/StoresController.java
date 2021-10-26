package Software.Engineering.Gruppe.Controller;

import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import io.javalin.http.Context;

import java.util.List;

public class StoresController {


    private final StoreRepository storeRepository;

    public StoresController(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }


    public void getAllStores(Context context) {
        List<Store> allStores = storeRepository.getAllStores();

        context.json(allStores);
    }


    public void getSpecificStore(Context context) {

        String slug = context.pathParam("{storeSlug}");
        Store getStoreBySlug = storeRepository.getSpecificStoreBySlug(slug);
        context.json(getStoreBySlug);


    }



}

package Software.Engineering.Gruppe.Controller;

import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Repository.StoreInterface;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import io.javalin.http.Context;

import java.util.ArrayList;
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


}

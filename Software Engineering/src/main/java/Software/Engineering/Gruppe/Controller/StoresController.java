package Software.Engineering.Gruppe.Controller;

import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Repository.StoreInterface;
import Software.Engineering.Gruppe.Repository.StoreRepository;

import java.util.ArrayList;

public class StoresController {


    private StoreRepository storeRepository;

    public StoresController(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }


}

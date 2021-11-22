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


    public void createStore(Context context) {
        String storeSlug = context.formParam("slug");
        String storeName = context.formParam("storeName");
        String storeImage = context.formParam("storeImage");
        String storeDescription = context.formParam("storeDescription");
        Store createdStore = storeRepository.createStore(storeSlug, storeName, storeImage, storeDescription);
        if(createdStore != null) {
            context.redirect("/stores");
        }
        else {
            context.status(400);
        }

    }

    public void updateStore(Context context) {
        String slug = context.pathParam("storeSlug");
        System.out.println(slug);
        int storeId = storeRepository.getSpecificStoreBySlug(slug).getStoreId();
        String storeSlug = context.formParam("slug");
        String storeName = context.formParam("storeName");
        String storeImage = context.formParam("storeImage");
        String storeDescription = context.formParam("storeDescription");
        Store updatedStore = storeRepository.updateStore(storeId, storeSlug, storeName, storeImage, storeDescription);
        if(updatedStore != null) {
            context.redirect("/stores");
        }
        else {
            context.status(400);
        }

    }

    public void deleteStore(Context ctx) {
        String storeSelected = ctx.formParam("store-delete");
        storeRepository.deleteStore(storeSelected);
        ctx.redirect("/stores");


    }
}



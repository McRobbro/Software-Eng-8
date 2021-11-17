package Software.Engineering.Gruppe.Controller;

import Software.Engineering.Gruppe.Model.Product;
import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Repository.ProductRepository;

import Software.Engineering.Gruppe.Repository.StoreRepository;
import io.javalin.http.Context;
import java.util.List;

import static java.lang.Double.parseDouble;


public class ProductController {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    public ProductController(ProductRepository productRepository, StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }

    public void getAllProducts(Context context) {
        List<Product> allStores = productRepository.getAllProducts();
        context.json(allStores);
    }

    public void getProductsFromStore(Context context) {
        String storeSlug = context.pathParam("storeSlug");
        List<Product> getProducts = productRepository.getProductsFromStore(storeSlug);
        context.json(getProducts);
    }

    public void getSpecificProduct(Context context) {
        String storeSlug = context.pathParam("storeSlug");
        String prodSlug = context.pathParam("prodSlug");

        Product product = productRepository.getSpecificProduct(storeSlug, prodSlug);
        context.json(product);
    }

    public void deleteProduct(Context context) {
        String storeSlug = context.pathParam("storeSlug");
        context.redirect("/stores/" + storeSlug);
    }

    public void createProduct(Context ctx) {
        String storeSlug = ctx.pathParam("storeSlug");
        Store currentStore = storeRepository.getSpecificStoreBySlug(storeSlug);
        String productName = ctx.formParam("productSlug");
        String productImage = ctx.formParam("productImage");
        String productDescription = ctx.formParam("productDescription");
        String productCategory = ctx.formParam("productCategory");
        String productPrice = ctx.formParam("productPrice");
        Product newProduct = productRepository.createProduct(
               currentStore,
               productName,
               productName,
               productImage,
               productDescription,
               productCategory, parseDouble(productPrice));

        if(newProduct != null) {
            ctx.redirect("/stores/" + storeSlug);
        }
        else {
            ctx.status(400);
        }

    }

}

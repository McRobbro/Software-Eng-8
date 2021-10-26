package Software.Engineering.Gruppe.Controller;

import Software.Engineering.Gruppe.Model.Product;
import Software.Engineering.Gruppe.Repository.ProductRepository;

import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.List;


public class ProductController {

    private final ProductRepository productRepository;
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void getAllProducts(Context context) {
        List<Product> allStores = productRepository.getAllProducts();
        context.json(allStores);
    }

    public void getProductsFromStore(Context context) {
        String storeSlug = context.pathParam("{storeSlug}");
        List<Product> getProducts = productRepository.getProductsFromStore(storeSlug);
        context.json(getProducts);
    }

    public void getSpecificProduct(Context context) {
        String storeSlug = context.pathParam("{storeSlug}");
        String prodSlug = context.pathParam("{prodSlug}");

        Product product = productRepository.getSpecificProduct(storeSlug, prodSlug);
        context.json(product);
    }

    public void deleteProduct(Context context) {
        String storeSlug = context.pathParam("{storeSlug}");
        context.redirect("/stores/" + storeSlug);
    }

}

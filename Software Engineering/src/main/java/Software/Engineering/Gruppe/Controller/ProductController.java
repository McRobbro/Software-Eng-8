package Software.Engineering.Gruppe.Controller;

import Software.Engineering.Gruppe.Model.Product;
import Software.Engineering.Gruppe.Repository.ProductRepository;

import io.javalin.http.Context;
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

    public void getSpecificProduct(Context context) {
        String slug = context.pathParam("{slug}");
        Product getProductBySlug = productRepository.getSpecificProductBySlug(slug);
        context.json(getProductBySlug);
    }

}

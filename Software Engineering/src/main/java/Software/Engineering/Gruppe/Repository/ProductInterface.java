package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Model.Product;
import Software.Engineering.Gruppe.Model.Store;

import java.util.List;

public interface ProductInterface {

    List<Product> getAllProducts();

    List<Product> getProductsFromStore(String storeSlug);

    Product getSpecificProduct(String storeSlug, String prodSlug);

    Product getSpecificProductBySlug(String SLUG);

    Product createProduct(String productSlug, String productName, String productImage, String productDescription, String productCategory);

    Product updateProduct(int productId, String productSlug, String productName, String productImage, String productDescription, String productCategory);

    boolean deleteProduct(String productSlug);

    // boolean uodatePrice(int pris);

}


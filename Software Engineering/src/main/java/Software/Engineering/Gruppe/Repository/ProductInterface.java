package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Model.Product;

import java.util.List;

public interface ProductInterface {

    List<Product> getAllProducts();

    Product getSpecificProductBySlug(String slug);

    Product createProduct(String productSlug, String productName, String productImage, String productDescription, String productCategory);

    Product updateProduct(int productId, String productSlug, String productName, String productImage, String productDescription, String productCategory);

    boolean deleteProduct(String slug);

}


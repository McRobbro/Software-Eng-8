package Software.Engineering.Gruppe.Model;

public class Product {

    private int productId;
    private int storeId;
    private String productSlug;
    private String productName;
    private String productImage;
    private String productDescription;
    private String productCategory;
    private long pris;

    public Product(int productId, String productSlug, String productName, String productImage, String productDescription, String productCategory) {
        this.productId = productId;
        this.productSlug = productSlug;
        this.productName = productName;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
    }

    public Product(String productSlug, String productName, String productImage, String productDescription, String productCategory) {
        this.productSlug = productSlug;
        this.productName = productName;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
    }

    public Product(int productId, int storeId, String productSlug, String productName, String productImage, String productDescription, String productCategory) {
        this.productId = productId;
        this.storeId = storeId;
        this.productSlug = productSlug;
        this.productName = productName;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
    }

    public Product(int productId, int storeId, String productSlug, String productName, String productImage, String productDescription, String productCategory, long pris) {
        this.productId = productId;
        this.storeId = storeId;
        this.productSlug = productSlug;
        this.productName = productName;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
        this.pris = pris;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductSlug() {
        return productSlug;
    }

    public void setProductSlug(String productSlug) {
        this.productSlug = productSlug;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public String toString() {
        return "productName{" +
                "productId=" + productId +
                ", storeId=" + storeId +
                ", productSlug='" + productSlug + '\'' +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", pris=" + pris +
                '}';
    }
}

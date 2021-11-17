package Software.Engineering.Gruppe.Model;

public class Product {

    private int productId;
    private Store store;
    private String storeSlug;
    private String productSlug;
    private String productName;
    private String productImage;
    private String productDescription;
    private String productCategory;
    private double price;

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

    public Product(int productId, Store store, String productSlug, String productName, String productImage, String productDescription, String productCategory) {
        this.productId = productId;
        this.store = store;
        this.productSlug = productSlug;
        this.productName = productName;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
    }

    public Product(int productId, Store store, String productSlug, String productName, String productImage, String productDescription, String productCategory, double price) {
        this.productId = productId;
        this.store = store;
        this.productSlug = productSlug;
        this.productName = productName;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
        this.price = price;
    }

    public Product(String storeSlug, String productSlug, String productName, String productImage, String productDescription, String productCategory, long price) {
        this.storeSlug = storeSlug;
        this.productSlug = productSlug;
        this.productName = productName;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
        this.price = price;
    }

    // dont remove!
    public Product(Store store, String productSlug, String productName, String productImage, String productDescription, String productCategory, double price) {
        this.store = store;
        this.productSlug = productSlug;
        this.productName = productName;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
        this.price = price;
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getStoreSlug() {
        return storeSlug;
    }

    public void setStoreSlug(String storeSlug) {
        this.storeSlug = storeSlug;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", store=" + store +
                ", storeSlug='" + storeSlug + '\'' +
                ", productSlug='" + productSlug + '\'' +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", pris=" + price +
                '}';
    }
}

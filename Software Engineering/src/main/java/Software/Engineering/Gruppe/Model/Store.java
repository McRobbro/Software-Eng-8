package Software.Engineering.Gruppe.Model;

import java.util.ArrayList;

public class Store {

    private int storeId;
    private String slug;
    private String storeName;
    private String storeImage;
    private String storeDescription;

    //private final SqliteDatabase database;
    private ArrayList<Product> ProductList = new ArrayList<>();


    public Store(int storeId, String slug, String storeName, String storeImage, String storeDescription) {
        this.storeId = storeId;
        this.slug = slug;
        this.storeName = storeName;
        this.storeImage = storeImage;
        this.storeDescription = storeDescription;
    }

    public Store(String slug, String storeName, String storeImage, String storeDescription) {
        this.slug = slug;
        this.storeName = storeName;
        this.storeImage = storeImage;
        this.storeDescription = storeDescription;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    public String getStoreDescription() {
        return storeDescription;
    }

    public void setStoreDescription(String storeDescription) {
        this.storeDescription = storeDescription;
    }

    public void addProductBySlug(String SLUG) {
        Product specificProduct = null;
        String query = "select * from product where slug = ?";
/*
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, SLUG);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int productId = resultSet.getInt("productId");
                String productSlug = resultSet.getString("slug");
                String productName = resultSet.getString("productName");
                String productImage = resultSet.getString("productImage");
                String productDescription = resultSet.getString("productDescription");
                String productCategory = resultSet.getString("productCategory");
                specificProduct = new Product(productId, productSlug, productName, productImage, productDescription, productCategory);
            }
            ProductList.add(specificProduct);
            System.out.println("added product");

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        System.out.println("add product not successful");
 */
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeId=" + storeId +
                ", slug='" + slug + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeImage='" + storeImage + '\'' +
                ", storeDescription='" + storeDescription + '\'' +
                '}';
    }
}

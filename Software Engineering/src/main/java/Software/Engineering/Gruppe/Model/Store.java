package Software.Engineering.Gruppe.Model;

public class Store {

    private int storeId;
    private String slug;
    private String storeName;
    private String storeImage;
    private String storeDescription;



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

package Software.Engineering.Gruppe;

public class Shop {
    private int id;
    private String shopName, description;

    public Shop(int id, String shopName, String description) {
        this.id = id;
        this.shopName = shopName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "shop{" +
                "id=" + id +
                ", shopName='" + shopName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

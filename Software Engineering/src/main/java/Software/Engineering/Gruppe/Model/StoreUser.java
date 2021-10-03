package Software.Engineering.Gruppe.Model;

public class StoreUser {
    private int storeUserId;
    private User user;
    private Store store;
    private String userRole;


    public StoreUser(int storeUserId, User user, Store store, String userRole) {
        this.storeUserId = storeUserId;
        this.user = user;
        this.store = store;
        this.userRole = userRole;
    }


    public int getStoreUserId() {
        return storeUserId;
    }

    public void setStoreUserId(int storeUserId) {
        this.storeUserId = storeUserId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "Store_user{" +
                "storeUserId=" + storeUserId +
                ", user=" + user +
                ", store=" + store +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}

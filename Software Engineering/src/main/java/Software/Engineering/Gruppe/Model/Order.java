package Software.Engineering.Gruppe.Model;

import java.time.LocalDate;

public class Order {

    private int orderId;
    private LocalDate orderDate;
    private User userId;
    private Store storeId;

    public Order(int orderId, LocalDate orderDate, User userId, Store storeId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.userId = userId;
        this.storeId = storeId;
    }

    public Order(LocalDate orderDate, User userId, Store storeId) {
        this.orderDate = orderDate;
        this.userId = userId;
        this.storeId = storeId;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Store getStoreId() {
        return storeId;
    }

    public void setStoreId(Store storeId) {
        this.storeId = storeId;
    }
}

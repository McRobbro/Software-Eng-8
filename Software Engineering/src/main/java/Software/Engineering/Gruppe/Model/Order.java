package Software.Engineering.Gruppe.Model;

import java.time.LocalDateTime;

public class Order {

    private int orderId;
    private LocalDateTime orderDate;
    private User userId;
    private Store storeId;

    public Order(int orderId, LocalDateTime orderDate, User userId, Store storeId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.userId = userId;
        this.storeId = storeId;
    }

    public Order(LocalDateTime orderDate, User userId, Store storeId) {
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

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
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

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", userId=" + userId +
                ", storeId=" + storeId +
                '}';
    }
}

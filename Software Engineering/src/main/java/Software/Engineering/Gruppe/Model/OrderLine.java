package Software.Engineering.Gruppe.Model;

public class OrderLine {
    private Order order;
    private Product product;
    private Store store;

    public OrderLine(Order order, Product product, Store store) {
        this.order = order;
        this.product = product;
        this.store = store;
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}

package Software.Engineering.Gruppe.Repository.interfaces;

import Software.Engineering.Gruppe.Model.Order;
import Software.Engineering.Gruppe.Model.Product;
import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderInterface {

    //List<Order> getAllOrders();

    //Order getSpecificOrderBySlug(String SLUG);
    Order getOrderById(int id);

    boolean deleteOrder(int orderId);

    Order createOrder(LocalDateTime orderDate, User user, Store store, Product product);


}


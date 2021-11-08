package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Model.Order;
import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderInterface {

    List<Order> getAllOrders();

    //Order getSpecificOrderBySlug(String SLUG);

    Order createOrder(LocalDateTime orderDate, User userId, Store storeId);

    Order getOrderById(int id);

    //Oppdatere ordre - trenger vi det?
    //Order updateOrder();


    //Slette ordre?
    boolean deleteOrder(String slug);

}


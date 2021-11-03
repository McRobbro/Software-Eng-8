package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Model.Order;
import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Model.User;

import java.time.LocalDate;
import java.util.List;

public interface OrderInterface {

    List<Order> getAllOrders();

    //Order getSpecificOrderBySlug(String SLUG);

    Order createOrder(LocalDate orderDate, User userId, Store storeId);

    //Oppdatere ordre - trenger vi det?
    //Order updateOrder();


    //Slette ordre?
    boolean deleteOrder(String slug);

}


package Software.Engineering.Gruppe.Controller;

import Software.Engineering.Gruppe.Model.Order;
import Software.Engineering.Gruppe.Model.Product;
import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Model.User;
import Software.Engineering.Gruppe.Repository.OrderRepository;
import Software.Engineering.Gruppe.Repository.ProductRepository;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import Software.Engineering.Gruppe.Repository.UserRepository;
import io.javalin.http.Context;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderController {

    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderController(OrderRepository orderRepository, StoreRepository storeRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public void orderTheProduct(Context context) {
        LocalDateTime orderDate = LocalDateTime.now();
        String storeSlug = context.pathParam("storeSlug");
        Store currentStore = storeRepository.getSpecificStoreBySlug(storeSlug);
        String prodSlug = context.pathParam("prodSlug");
        Product pickedProduct = productRepository.getSpecificProductBySlug(prodSlug);

        //set userid 100 as default since currently a working login system is not complete.
        User user = userRepository.getSpecificUser(100);

        Order order = orderRepository.createOrder(orderDate, user, currentStore, pickedProduct);
        if(order != null) {
            context.redirect("/stores/" + storeSlug);
        }else {
            context.status(400);
        }
    }
}

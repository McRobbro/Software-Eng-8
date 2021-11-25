import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Model.User;
import Software.Engineering.Gruppe.Repository.OrderRepository;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import Software.Engineering.Gruppe.Repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class test_order_crud_functionality {
    Path userDir = Paths.get(System.getProperty("user.dir")).getParent();
    String databaseDir = "\\db\\FakeDatabase.db";
    String url = "jdbc:sqlite:" + userDir + databaseDir;
    SqliteDatabase sqliteDatabase = new SqliteDatabase(url);
    StoreRepository storeRepository = new StoreRepository(sqliteDatabase);
    UserRepository userRepository = new UserRepository(sqliteDatabase);
    OrderRepository orderRepository = new OrderRepository(sqliteDatabase, userRepository, storeRepository);


    @BeforeEach
    public void data_setup() {
        LocalDateTime dummyDate = LocalDateTime.of(2021, Month.NOVEMBER, 23, 15, 0, 0);
        Store dummyStore = storeRepository.createStore("DummyStSlug", "DummyStName", "DummyStImg", "dummyStDesc");
        User dummyUser = userRepository.createUser(5,"dummyEmail","DummyName", "dummyPassword");
        orderRepository.createOrder(1, dummyDate, dummyUser, dummyStore);
    }

    @AfterEach
    public void tear_down() {
        userRepository.deleteUser(5);
        storeRepository.deleteStore("DummyStSlug");
        orderRepository.deleteOrder(1);
     }

    @Test
    public void test_create_order() {
        LocalDateTime dummyTime = orderRepository.getOrderById(1).getOrderDate();
        int storeId = storeRepository.getSpecificStoreBySlug("DummyStSlug").getStoreId();
        assertEquals(orderRepository.getOrderById(1), allOf(
                hasProperty("orderId", is(1)),
                hasProperty("orderDate", is(dummyTime)),
                hasProperty("userId", is(5)),
                hasProperty("storeId", is(storeId))
        ));

    }

    @Test
    public void test_get_order_by_id() {
        LocalDateTime dummyTime = orderRepository.getOrderById(1).getOrderDate();
        int storeId = storeRepository.getSpecificStoreBySlug("DummyStSlug").getStoreId();
        assertEquals(orderRepository.getOrderById(1), allOf(
                hasProperty("orderId", is(1)),
                hasProperty("orderDate", is(dummyTime)),
                hasProperty("userId", is(5)),
                hasProperty("storeId", is(storeId))
        ));
    }

    /*
    @Test
    public void test_update_order() {}
    there is no method updateOrder()
    */

    @Test
    public void test_delete_order() {
        orderRepository.deleteOrder(1);
        assertNull(orderRepository.getOrderById(1));
    }








}

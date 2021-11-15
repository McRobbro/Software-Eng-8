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
import static org.hamcrest.MatcherAssert.assertThat;
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
        LocalDateTime testDate = LocalDateTime.of(2021,
                Month.JULY, 11, 17, 0, 0);
        Store testStore = storeRepository.createStore("DummySlug", "DummyName", "DummyUrl", "dummyBio");
        User testUser = userRepository.createUser(5,"email1","name1", "password1");
        orderRepository.createOrder(1, testDate, testUser, testStore);
        System.out.println("setup");

    }

    @AfterEach
    public void tear_down() {

        userRepository.deleteUser(5);
        storeRepository.deleteStore("DummySlug");
        orderRepository.deleteOrder(1);
        System.out.println("teardown");
     }

    @Test
    public void test_create_order() {
        System.out.println(orderRepository.getOrderById(1));
        assertEquals(orderRepository.getOrderById(1).getUserId().getEmail(), "email1");
    }









}

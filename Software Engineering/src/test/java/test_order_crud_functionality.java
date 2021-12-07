import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Order;
import Software.Engineering.Gruppe.Repository.OrderRepository;
import Software.Engineering.Gruppe.Repository.ProductRepository;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import Software.Engineering.Gruppe.Repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class test_order_crud_functionality {
    Path userDir = Paths.get(System.getProperty("user.dir")).getParent();
    String databaseDir = "\\db\\FakeDatabase.db";
    String url = "jdbc:sqlite:" + userDir + databaseDir;
    SqliteDatabase sqliteDatabase = new SqliteDatabase(url);
    UserRepository userRepository = new UserRepository(sqliteDatabase);
    StoreRepository storeRepository = new StoreRepository(sqliteDatabase);
    ProductRepository productRepository = new ProductRepository(sqliteDatabase, storeRepository);
    OrderRepository orderRepository = new OrderRepository(sqliteDatabase, userRepository, storeRepository);

    @BeforeEach
    public void data_setup() {
        LocalDateTime dummyDate = LocalDateTime.of(2021, Month.NOVEMBER, 10, 18, 0, 0);
        storeRepository.createStore("dummySlug", "dummyName", "dummyImage", "dummyBio");
        userRepository.createUser(100, "dummyEmail", "dummyUsername", "dummyPassword");
        productRepository.createProduct(storeRepository.getSpecificStoreBySlug("dummySlug"),
                "prodSlug",
                "prodName",
                "prodImage",
                "prodBio",
                "prodCat",
                800);

        //order object
        orderRepository.createOrder(1,
                dummyDate,
                userRepository.getSpecificUser(100),
                storeRepository.getSpecificStoreBySlug("dummySlug"),
                productRepository.getSpecificProduct("dummySlug", "prodSlug"));
    }


    @AfterEach
    public void tear_down() {
        userRepository.deleteUser(100);
        storeRepository.deleteStore("dummySlug");
        productRepository.deleteProduct("prodSlug");
        orderRepository.deleteOrder(1);
    }



    /* Tester krav "Ordre.Opprettelse" */
    @ParameterizedTest
    @MethodSource("graphPath")
    public void test_create_order(String expected) {
        Order order = orderRepository.getOrderById(1);
        assertThat(order, test_helper_class.hasGraph(expected, notNullValue()));
    }

    /* Tester krav "Ordre.Opprettelse" */
    @Test
    public void test_read_order() {
        assertNotNull(orderRepository.getOrderById(1));
    }

    /* Dette er ikke en test av et direkte krav, for det skal ikke gå an å slette en ordre, men vi tok med en
    delete_order metode + test for å ha muligheten til å teste at en ordre kan slettes fra ordre-tabellen i databasen. */
    @Test
    public void test_delete_order() {
        orderRepository.deleteOrder(1);
        assertNull(orderRepository.getOrderById(1));
    }

    public static Stream graphPath() {
        return Stream.of(
                Arguments.of("orderId")
        );

    }
}

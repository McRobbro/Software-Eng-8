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

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
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
        LocalDateTime testDate = LocalDateTime.now();
        Store testStore = storeRepository.createStore("DummySlug_Order", "DummyName_Order", "DummyUrl_Order", "dummyBio_Order");
        User testUser = userRepository.createUser("email1","name1", "password1");
        orderRepository.createOrder(testDate, testUser, testStore);
    }







}

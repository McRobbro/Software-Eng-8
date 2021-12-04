import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Path;
import java.nio.file.Paths;

public class test_can_only_bid_when_auction_is_activ {

    Path userDir = Paths.get(System.getProperty("user.dir")).getParent();
    String databaseDir = "\\db\\FakeDatabase.db";
    String url = "jdbc:sqlite:" + userDir + databaseDir;
    SqliteDatabase sqliteDatabase = new SqliteDatabase(url);

    UserRepository userRepository = new UserRepository(sqliteDatabase);
    StoreRepository storeRepository = new StoreRepository(sqliteDatabase);
    ProductRepository productRepository = new ProductRepository(sqliteDatabase, storeRepository);
    AuctionRepository auctionRepository = new AuctionRepository(sqliteDatabase, storeRepository, productRepository);
    BidRepository bidRepository = new BidRepository(sqliteDatabase, userRepository, auctionRepository);


    @BeforeEach
    public void data_setup() {

    }


    @AfterEach
    public void tearDown() {

    }
}

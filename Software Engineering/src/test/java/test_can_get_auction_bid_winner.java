import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.User;
import Software.Engineering.Gruppe.Repository.*;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertNull;

public class test_can_get_auction_bid_winner {

    Path userDir = Paths.get(System.getProperty("user.dir")).getParent();
    String databaseDir = "\\db\\FakeDatabase.db";
    String url = "jdbc:sqlite:" + userDir + databaseDir;
    SqliteDatabase sqliteDatabase = new SqliteDatabase(url);

    UserRepository userRepository = new UserRepository(sqliteDatabase);
    StoreRepository storeRepository = new StoreRepository(sqliteDatabase);
    ProductRepository productRepository = new ProductRepository(sqliteDatabase, storeRepository);
    AuctionRepository auctionRepository = new AuctionRepository(sqliteDatabase, storeRepository, productRepository);
    BidRepository bidRepository = new BidRepository(sqliteDatabase, userRepository, auctionRepository);




    @AfterEach
    public void tearDown() {

        auctionRepository.getAuctionById(100);


    }

    @Test
    public void test_can_fetch_winner_from_auction_when_auction_has_ended() {
        assertThat(bidRepository.getWinner(100), hasProperty(""));

    }
    
    @Test
    public void test_cannot_fetch_winner_when_auction_has_not_started() {
        assertNull(bidRepository.getWinner(100));

    }

    @Test
    public void test_cant_fetch_winner_when_bid_list_is_empty() {

    }

}

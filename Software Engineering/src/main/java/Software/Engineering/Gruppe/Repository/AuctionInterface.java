package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Model.Auction;
import Software.Engineering.Gruppe.Model.Product;
import Software.Engineering.Gruppe.Model.Store;

import java.time.LocalDateTime;

public interface AuctionInterface {

    Auction createAuction(Store store, Product product, LocalDateTime startTime, LocalDateTime endTime);

    Auction getAuctionById(int auctionId);


}

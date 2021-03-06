package Software.Engineering.Gruppe.Repository.interfaces;

import Software.Engineering.Gruppe.Model.Auction;
import Software.Engineering.Gruppe.Model.Product;
import Software.Engineering.Gruppe.Model.Store;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface AuctionInterface {

    Auction createAuction(Store store, Product product, double startPrice, LocalDateTime startTime, LocalDateTime endTime);

    Auction createAuction(int auctionId, Store store, Product product, double startPrice, LocalDateTime startTime, LocalDateTime endTime);

    Auction getAuctionById(int auctionId);

    List<Auction> getAllAuctionsFromSpecificStore(String storeSlug);

    Auction getSpecificAuction(String storeSlug, String prodSlug);

    boolean deleteAuction(int auctionId);

    Auction updateAuction(int auctionId, Store store, Product product, double startPrice, LocalDateTime startTime, LocalDateTime endTime);
}

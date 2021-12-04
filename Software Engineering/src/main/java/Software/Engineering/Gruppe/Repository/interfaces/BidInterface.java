package Software.Engineering.Gruppe.Repository.interfaces;

import Software.Engineering.Gruppe.Model.Auction;
import Software.Engineering.Gruppe.Model.Bid;
import Software.Engineering.Gruppe.Model.User;
import java.util.List;

public interface BidInterface {

    Bid makeBid(User user, Auction auction, double amount);

    Bid makeBid(int bidId, User user, Auction auction, double amount);

    Bid getSpecificBidById(int bidId);

    List<Bid> getAllBidsFromSpecificAuction(int id);

    Bid getWinner(int id);

    Boolean deleteBid(int auctionId);

}

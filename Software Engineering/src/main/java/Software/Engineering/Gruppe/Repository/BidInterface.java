package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Model.Auction;
import Software.Engineering.Gruppe.Model.Bid;
import Software.Engineering.Gruppe.Model.User;

public interface BidInterface {

    Bid makeBid(User user, Auction auction, double amount);
}

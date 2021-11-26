package Software.Engineering.Gruppe.Controller;

import Software.Engineering.Gruppe.Model.Auction;
import Software.Engineering.Gruppe.Model.Bid;
import Software.Engineering.Gruppe.Model.User;
import Software.Engineering.Gruppe.Repository.AuctionRepository;
import Software.Engineering.Gruppe.Repository.BidRepository;
import Software.Engineering.Gruppe.Repository.UserRepository;
import io.javalin.http.Context;

import static java.lang.Double.parseDouble;

public class BidController {

    private BidRepository bidRepository;
    private UserRepository userRepository;
    private AuctionRepository auctionRepository;

    public BidController(BidRepository bidRepository, UserRepository userRepository, AuctionRepository auctionRepository) {
        this.bidRepository = bidRepository;
        this.userRepository = userRepository;
        this.auctionRepository = auctionRepository;
    }


    public void createBid(Context ctx) {
        String storeSlug = ctx.pathParam("storeSlug");
        String prodSlug = ctx.pathParam("auctionProd");
        User user = userRepository.getSpecificUserByUsername("Alejandra");
        Auction currentAuction = auctionRepository.getSpecificAuction(storeSlug, prodSlug);
        String bidAmount = ctx.formParam("bid");
        Bid createBid = bidRepository.makeBid(user, currentAuction, parseDouble(bidAmount));
        if(createBid != null) {
            ctx.redirect("/stores/" + storeSlug + "/auctions/" + prodSlug);
        }
        else {
            ctx.status(400);
        }

    }
}

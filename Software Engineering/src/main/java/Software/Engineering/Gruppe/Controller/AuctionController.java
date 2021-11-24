package Software.Engineering.Gruppe.Controller;

import Software.Engineering.Gruppe.Model.Auction;
import Software.Engineering.Gruppe.Repository.AuctionRepository;
import Software.Engineering.Gruppe.Repository.ProductRepository;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import io.javalin.http.Context;

import java.util.List;

public class AuctionController {

    private final AuctionRepository auctionRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;


    public AuctionController(AuctionRepository auctionRepository, StoreRepository storeRepository, ProductRepository productRepository) {
        this.auctionRepository = auctionRepository;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }

    public void getAllAuctionsFromStore(Context context) {
        String storeSlug = context.pathParam("storeSlug");
        List<Auction> getAuction = auctionRepository.getAllAuctionsFromSpecificStore(storeSlug);
        context.json(getAuction);

    }
}

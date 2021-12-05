package Software.Engineering.Gruppe.Controller;

import Software.Engineering.Gruppe.Model.Auction;
import Software.Engineering.Gruppe.Model.Product;
import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Repository.AuctionRepository;
import Software.Engineering.Gruppe.Repository.ProductRepository;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import io.javalin.http.Context;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.lang.Double.parseDouble;


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

    public void getSpecificAuction(Context context) {
        String storeSlug = context.pathParam("storeSlug");
        String prodSlug = context.pathParam("auctionProd");

        Auction auction = auctionRepository.getSpecificAuction(storeSlug, prodSlug);
        context.json(auction);


    }

    public void createAuction(Context context) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String storeSlug = context.pathParam("storeSlug");
        Store currentStore = storeRepository.getSpecificStoreBySlug(storeSlug);
        String auctionProduct = context.formParam("auctionProduct");
        Product pickedProduct = productRepository.getSpecificProductBySlug(auctionProduct);
        String startPrice = context.formParam("startPrice");
        String startDate = context.formParam("startDate");
        String endDate = context.formParam("endDate");
        LocalDateTime ldtStart = LocalDateTime.parse(startDate.replace("T", " "), formatter);
        LocalDateTime ldtEnd = LocalDateTime.parse(endDate.replace("T"," "), formatter);
         Auction newAuction = auctionRepository.createAuction(
               currentStore,
               pickedProduct,
              parseDouble(startPrice),
                 ldtStart,
                 ldtEnd
                );

         if(newAuction != null) {
            context.redirect("/stores/" + storeSlug);
        }
         else{
            context.status(400);
        }



    }


}

package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuctionRepository implements AuctionInterface {

    private final SqliteDatabase database;
    private StoreRepository storeRepository;
    private ProductRepository productRepository;

    public AuctionRepository(SqliteDatabase database, StoreRepository storeRepository, ProductRepository productRepository) {
        this.database = database;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Auction createAuction(Store store, Product product, LocalDateTime startTime, LocalDateTime endTime) {
        String query = "INSERT INTO auction(storeId, productId, start_time, end_time) VALUES(?,?,?,?)";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, store.getStoreId());
            preparedStatement.setInt(2, product.getProductId());
            preparedStatement.setString(3, startTime.format(format));
            preparedStatement.setString(4, endTime.format(format));
            preparedStatement.executeUpdate();
            return new Auction(store, product, startTime, endTime);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public Auction getAuctionById(int auctionId) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        Auction spesificAuction = null;
        String query = "select * from auction where auctionId = ?";
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, auctionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int auction = resultSet.getInt("auctionId");
                int storeId = resultSet.getInt("storeId");
                int productId = resultSet.getInt("productId");
                String startDate = resultSet.getString("start_time");
                String endDate = resultSet.getString("end_time");
                Store store = storeRepository.getSpecificStoreById(storeId);
                Product product = productRepository.getSpecificProductById(productId);
                LocalDateTime ldtStart = LocalDateTime.parse(startDate, format);
                LocalDateTime ldtEnd = LocalDateTime.parse(endDate, format);
                spesificAuction = new Auction(auction, store, product, ldtStart, ldtEnd);
                return spesificAuction;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return null;
    }

    @Override
    public List<Auction> getAllAuctionsFromSpecificStore(String storeSlug) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        List<Auction> auctionList = new ArrayList<>();
        String query = "SELECT * FROM auction where storeId = ?";

        try (Connection cn = database.getConnection()) {
            PreparedStatement st = cn.prepareStatement(query);
            int storeId = storeRepository.getSpecificStoreBySlug(storeSlug).getStoreId();
            st.setInt(1, storeId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int auctionId = rs.getInt("auctionId");
                int shopId = rs.getInt("storeId");
                Store store = storeRepository.getSpecificStoreById(shopId);
                int productId = rs.getInt("productId");
                String prodSlug = productRepository.getSpecificProductById(productId).getProductSlug();
                Product product = productRepository.getSpecificProduct(store.getSlug(), prodSlug);
                String startDate = rs.getString("start_time");
                String endDate = rs.getString("end_time");
                LocalDateTime ldtStart = LocalDateTime.parse(startDate, format);
                LocalDateTime ldtEnd = LocalDateTime.parse(endDate, format);
                auctionList.add(new Auction(auctionId, store, product, ldtStart, ldtEnd));

            }
            return auctionList;

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }
}



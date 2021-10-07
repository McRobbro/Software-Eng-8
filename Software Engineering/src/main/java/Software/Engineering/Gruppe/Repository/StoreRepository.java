package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Config.SQLDatabase;
import Software.Engineering.Gruppe.Model.Store;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreRepository implements StoreInterface {
    private final SQLDatabase database;

    public StoreRepository(SQLDatabase database) {
        this.database = database;
    }

    @Override
    public List<Store> getAllStores() {
        List<Store> storeList = new ArrayList<>();
        String query = "select * from store";

        try (Connection connection = database.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int storeId = resultSet.getInt("storeId");
                String slug = resultSet.getString("slug");
                String storeName = resultSet.getString("storeName");
                String storeImage = resultSet.getString("storeImage");
                String storeDescription = resultSet.getString("storeDescription");
                storeList.add(new Store(storeId, slug, storeName, storeImage, storeDescription));
            }
            return storeList;

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return null;
    }


    @Override
    public Store getSpecificStoreBySlug(String SLUG) {
        String query = "select * from " + "store where slug = ?";
        Store spesificStore = null;

        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, SLUG);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int storeId = resultSet.getInt("storeId");
                String slug = resultSet.getString("slug");
                String storeName = resultSet.getString("storeName");
                String storeImage = resultSet.getString("storeImage");
                String storeDescription = resultSet.getString("storeDescription");
                spesificStore = new Store(storeId, slug, storeName, storeImage, storeDescription);
            }
            return spesificStore;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}

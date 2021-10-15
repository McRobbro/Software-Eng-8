package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Store;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreRepository implements StoreInterface {

    private final SqliteDatabase database;
    public StoreRepository(SqliteDatabase database) {
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

    @Override
    public Store createStore(String slug, String storeName, String storeImage, String storeDescription) {
        String query = "INSERT INTO store(slug, storeName, storeImage, storeDescription) VALUES(?,?,?,?)";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, slug);
            preparedStatement.setString(2, storeName);
            preparedStatement.setString(3, storeImage);
            preparedStatement.setString(4, storeDescription);
            preparedStatement.executeUpdate();
            return new Store(slug, storeName, storeImage, storeDescription);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public Store updateStore(int storeId, String slug, String storeName, String storeImage, String storeDescription) {
        String query = "UPDATE store SET slug = ?, " +
                "storeName = ?, " +
                "storeImage = ?, " +
                "storeDescription = ?" +
                "WHERE storeId = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, slug);
            preparedStatement.setString(2, storeName);
            preparedStatement.setString(3, storeImage);
            preparedStatement.setString(4, storeDescription);
            preparedStatement.setInt(5, storeId);
            preparedStatement.executeUpdate();
            System.out.println("Update success");
            return new Store(storeId, slug, storeName, storeImage, storeDescription);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deleteStore(String slug) {
        String query = "DELETE FROM store WHERE slug = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, slug);
            preparedStatement.executeUpdate();
            System.out.println("deleted success");
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

}
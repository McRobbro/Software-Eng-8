package Software.Engineering.Gruppe.Repository;

import
        Software.Engineering.Gruppe.Config.SqliteDatabase;
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
        String query = "SELECT * FROM store";

        try (Connection cn = database.getConnection()) {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int storeId = rs.getInt("storeId");
                String slug = rs.getString("slug");
                String storeName = rs.getString("storeName");
                String storeImage = rs.getString("storeImage");
                String storeDescription = rs.getString("storeDescription");
                storeList.add(new Store(storeId, slug, storeName, storeImage, storeDescription));
            }
            return storeList;

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return null;
    }

    @Override
    public Store getSpecificStoreBySlug(String storeSlug) {
        String query = "SELECT * FROM store WHERE slug = ?";
        Store specificStore = null;

        try (Connection cn = database.getConnection()) {
            PreparedStatement st = cn.prepareStatement(query);
            st.setString(1, storeSlug);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int storeId = rs.getInt("storeId");
                String slug = rs.getString("slug");
                String storeName = rs.getString("storeName");
                String storeImage = rs.getString("storeImage");
                String storeDescription = rs.getString("storeDescription");
                specificStore = new Store(storeId, slug, storeName, storeImage, storeDescription);
            }
            return specificStore;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Store createStore(String slug, String storeName, String storeImage, String storeDescription) {
        String query = "INSERT INTO store(slug, storeName, storeImage, storeDescription) VALUES(?,?,?,?)";

        try (Connection cn = database.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1, slug);
            st.setString(2, storeName);
            st.setString(3, storeImage);
            st.setString(4, storeDescription);
            st.executeUpdate();
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

        try (Connection cn = database.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1, slug);
            st.setString(2, storeName);
            st.setString(3, storeImage);
            st.setString(4, storeDescription);
            st.setInt(5, storeId);
            st.executeUpdate();
            return new Store(storeId, slug, storeName, storeImage, storeDescription);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deleteStore(String storeSlug) {
        String query = "DELETE FROM store WHERE slug = ?";
        try (Connection cn = database.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1, storeSlug);
            st.executeUpdate();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

}
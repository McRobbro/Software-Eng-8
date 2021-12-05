package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Product;
import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Repository.interfaces.ProductInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements ProductInterface {

    private final SqliteDatabase database;
    StoreRepository storeRepository;

    public ProductRepository(SqliteDatabase database, StoreRepository storeRepository) {
        this.database = database;
        this.storeRepository = storeRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> ProductList = new ArrayList<>();
        String query = "select * from product";

        try (Connection cn = database.getConnection()) {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            addProductsToList(ProductList, rs);
            return ProductList;

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Product> getProductsFromStore(String storeSlug) {
        try (Connection cn = database.getConnection()) {
            List<Product> ProductList = new ArrayList<>();

            int storeId = getStoreIdFromSlug(storeSlug);
            getStoreProducts(ProductList, cn, storeId);

            return ProductList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private int getStoreIdFromSlug(String storeSlug){
        String query = "SELECT storeId FROM store WHERE slug = ?";
        try (Connection cn = database.getConnection()) {
            PreparedStatement st = cn.prepareStatement(query);
            st.setString(1, storeSlug);
            st.executeQuery();
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                return rs.getInt("storeId");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    private void getStoreProducts(List<Product> ProductList, Connection cn, int storeId) throws SQLException {
        String query = "SELECT * FROM product WHERE storeId = ?";
        try (PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1, storeId);
            ResultSet resultSet = st.executeQuery();
            addProductsToList(ProductList, resultSet);
        }
    }

    private void addProductsToList(List<Product> ProductList, ResultSet rs) throws SQLException {
        while (rs.next()) {
            int productId = rs.getInt("productId");
            int store = rs.getInt("storeId");
            Store storeId = storeRepository.getSpecificStoreById(store);
            String productSlug = rs.getString("productSlug");
            String productName = rs.getString("productName");
            String productImage = rs.getString("productImage");
            String productDescription = rs.getString("productDescription");
            String productCategory = rs.getString("productCategory");
            long productPrice = rs.getLong("productPrice");
            ProductList.add(new Product(productId, storeId, productSlug, productName, productImage, productDescription, productCategory, productPrice));
        }
    }

    public Product getSpecificProduct(String storeSlug, String prodSlug) {
        List<Product> ProductList = getProductsFromStore(storeSlug);
        for ( Product oneProduct : ProductList ) {
            if (oneProduct.getProductSlug().equals(prodSlug)) {
                return oneProduct;
            }
        }
        return null;
    }

    @Override
    public Product getSpecificProductBySlug(String prodSlug){
        Product specificProd = null;
        String query = "SELECT * FROM product WHERE productSlug = ?";
        try (Connection cn = database.getConnection()) {
            PreparedStatement st = cn.prepareStatement(query);
            st.setString(1, prodSlug);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("productId");
                String pSlug = rs.getString("productSlug");
                String productName = rs.getString("productName");
                String productImage = rs.getString("productImage");
                String productDescription = rs.getString("productDescription");
                String productCategory = rs.getString("productCategory");
                specificProd = new Product(productId, pSlug, productName, productImage, productDescription, productCategory);
            }
            return specificProd;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Product getSpecificProductById(int prodId) {
        Product specificProduct = null;
        String query = "SELECT * FROM product WHERE productId = ?";

        try (Connection cn = database.getConnection()) {
            PreparedStatement st = cn.prepareStatement(query);
            st.setInt(1, prodId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("productId");
                String pSlug = rs.getString("productSlug");
                String productName = rs.getString("productName");
                String productImage = rs.getString("productImage");
                String productDescription = rs.getString("productDescription");
                String productCategory = rs.getString("productCategory");
                specificProduct = new Product(productId, pSlug, productName, productImage, productDescription, productCategory);
            }
            return specificProduct;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

/* start her */
    @Override
    public Product createProduct(Store store, String productSlug, String productName, String productImage, String productDescription, String productCategory, double price) {
        String query = "INSERT INTO product(storeid, productSlug, productName, productImage, productDescription, productCategory, productPrice) VALUES(?,?,?,?,?,?,?)";

        try (Connection cn = database.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            //st.set
            st.setInt(1, store.getStoreId());
            st.setString(2, productSlug);
            st.setString(3, productName);
            st.setString(4, productImage);
            st.setString(5, productDescription);
            st.setString(6, productCategory);
            st.setDouble(7, price);
            st.executeUpdate();
            return new Product(store ,productSlug, productName, productImage, productDescription, productCategory, price);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public Product updateProduct(int productId, String productSlug, String productName, String productImage, String productDescription, String productCategory, double price) {
        String query = "UPDATE product SET productSlug = ?, " +
                "productName = ?, " +
                "productImage = ?, " +
                "productDescription = ?," +
                "productCategory = ?," +
                "productPrice = ?" +
                "WHERE productId = ?";

        try (Connection cn = database.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1, productSlug);
            st.setString(2, productName);
            st.setString(3, productImage);
            st.setString(4, productDescription);
            st.setString(5, productCategory);
            st.setDouble(6, price);
            st.setInt(7, productId);
            st.executeUpdate();
            return new Product(productId, productSlug, productName, productImage, productDescription, productCategory);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deleteProduct(String productSlug) {
        String query = "DELETE FROM product WHERE productSlug = ?";
        if (productSlug != null)
            try (Connection cn = database.getConnection();
                 PreparedStatement st = cn.prepareStatement(query)) {
                st.setString(1, productSlug);
                st.executeUpdate();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        System.out.println("delete failed");
        return false;
    }

}

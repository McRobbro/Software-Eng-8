package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Product;
import Software.Engineering.Gruppe.Model.Store;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements ProductInterface {

    private final SqliteDatabase database;

    public ProductRepository(SqliteDatabase database) {
        this.database = database;
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

    private void addProductsToList(List<Product> ProductList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int productId = resultSet.getInt("productId");
            String productSlug = resultSet.getString("productSlug");
            String productName = resultSet.getString("productName");
            String productImage = resultSet.getString("productImage");
            String productDescription = resultSet.getString("productDescription");
            String productCategory = resultSet.getString("productCategory");
            ProductList.add(new Product(productId, productSlug, productName, productImage, productDescription, productCategory));
        }
    }

    @Override
    public Product getSpecificProduct(String storeSlug, String prodSlug) {
        Product specificProduct = null;
        //String query = "SELECT * FROM product WHERE storeId = ? AND productSlug = ?";
        String query = "SELECT * FROM product WHERE productSlug = ?";
        try (Connection cn = database.getConnection()) {
            PreparedStatement st = cn.prepareStatement(query);
            //int storeIdd = getStoreIdFromSlug(storeSlug);
            //st.setInt(1, storeIdd);
            st.setString(1, prodSlug);
            //st.setString(2, prodSlug);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("productId");
                int storeId = rs.getInt("storeId");
                String productSlug = rs.getString("productSlug");
                String productName = rs.getString("productName");
                String productImage = rs.getString("productImage");
                String productDescription = rs.getString("productDescription");
                String productCategory = rs.getString("productCategory");
                long productPrice = rs.getLong("productPrice");
                specificProduct = new Product(productId, storeId, productSlug, productName, productImage, productDescription, productCategory, productPrice);
            }
            return specificProduct;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return specificProduct;
    }

    @Override
    public Product getSpecificProductBySlug(String prodSLUG){
        String query = "SELECT * FROM product WHERE slug = ?";
        Product specificProd = null;

        try (Connection cn = database.getConnection()) {
            PreparedStatement st = cn.prepareStatement(query);
            st.setString(1, prodSLUG);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int prodId = rs.getInt("prodId");
                String prodSlug = rs.getString("slug");
                String prodName = rs.getString("product name");
                String prodImage = rs.getString("product image");
                String prodDescription = rs.getString("description");
                String productCategory = rs.getString("category");
                specificProd = new Product(prodId, prodSlug, prodName, prodImage, prodDescription, productCategory);
            }
            return specificProd;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Product createProduct(String productSlug, String productName, String productImage, String productDescription, String productCategory) {
        String query = "INSERT INTO product(productSlug, productName, productImage, productDescription, productCategory) VALUES(?,?,?,?,?)";

        try (Connection cn = database.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1, productSlug);
            st.setString(2, productName);
            st.setString(3, productImage);
            st.setString(4, productDescription);
            st.setString(5, productCategory);
            st.executeUpdate();
            return new Product(productSlug, productName, productImage, productDescription, productCategory);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public Product updateProduct(int productId, String productSlug, String productName, String productImage, String productDescription, String productCategory) {
        String query = "UPDATE product SET productSlug = ?, " +
                "productName = ?, " +
                "productImage = ?, " +
                "productDescription = ?," +
                "productCategory = ?" +
                "WHERE storeId = ?";

        try (Connection cn = database.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1, productSlug);
            st.setString(2, productName);
            st.setString(3, productImage);
            st.setString(4, productDescription);
            st.setString(5, productCategory);
            st.setInt(6, productId);
            st.executeUpdate();
            System.out.println("Update success");
            return new Product(productId, productSlug, productName, productImage, productDescription, productCategory);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deleteProduct(String productSlug) {
        String query = "DELETE FROM product WHERE productSlug = ?";
        try (Connection cn = database.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1, productSlug);
            st.executeUpdate();
            System.out.println("deleted success");
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }
}

package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements ProductInterface {

    private final SqliteDatabase database;

    public ProductRepository(SqliteDatabase database) { this.database = database; }

    @Override
    public List<Product> getAllProducts() {
        List<Product> ProductList = new ArrayList<>();
        String query = "select * from products";

        try (Connection connection = database.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int productId = resultSet.getInt("productId");
                String productSlug = resultSet.getString("productSlug");
                String productName = resultSet.getString("productName");
                String productImage = resultSet.getString("productImage");
                String productDescription = resultSet.getString("productDescription");
                String productCategory = resultSet.getString("productCategory");
                ProductList.add(new Product(productId, productSlug, productName, productImage, productDescription, productCategory));
            }
            return ProductList;

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return null;
    }

    @Override
    public Product getSpecificProductBySlug(String SLUG)  {
        Product specificProduct = null;
        String query = "select * from product where slug = ?";

        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, SLUG);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int productId = resultSet.getInt("productId");
                String productSlug = resultSet.getString("slug");
                String productName = resultSet.getString("productName");
                String productImage = resultSet.getString("productImage");
                String productDescription = resultSet.getString("productDescription");
                String productCategory = resultSet.getString("productCategory");
                specificProduct = new Product(productId, productSlug, productName, productImage, productDescription, productCategory);
            }
            return specificProduct;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public Product createProduct(String productSlug, String productName, String productImage, String productDescription, String productCategory) {
        String query = "INSERT INTO product(productSlug, productName, productImage, productDescription, productCategory) VALUES(?,?,?,?,?)";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             preparedStatement.setString(1, productSlug);
             preparedStatement.setString(2, productName);
             preparedStatement.setString(3, productImage);
             preparedStatement.setString(4, productDescription);
             preparedStatement.setString(5, productCategory);
             preparedStatement.executeUpdate();
             return new Product(productSlug, productName, productImage, productDescription, productCategory);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public Product updateProduct(int productId, String productSlug, String productName, String productImage, String productDescription, String productCategory) {
        String query = "UPDATE product SET slug = ?, " +
                "productName = ?, " +
                "productImage = ?, " +
                "productDescription = ?," +
                "productCategory = ?" +
                "WHERE storeId = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             preparedStatement.setString(1, productSlug);
             preparedStatement.setString(2, productName);
             preparedStatement.setString(3, productImage);
             preparedStatement.setString(4, productDescription);
             preparedStatement.setString(5, productCategory);
             preparedStatement.setInt(6, productId);
             preparedStatement.executeUpdate();
             System.out.println("Update success");
             return new Product(productId, productSlug, productName, productImage, productDescription, productCategory);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deleteProduct(String productSlug) {
        String query = "DELETE FROM product WHERE slug = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             preparedStatement.setString(1, productSlug);
             preparedStatement.executeUpdate();
             System.out.println("deleted success");
             return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }
}

package com.example.shopapp.dao;

import com.example.shopapp.entity.Product;
import com.example.shopapp.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDao extends AbstractDao<Product> {

    private static ProductDao instance;

    public static ProductDao getInstance() {
        if (instance == null) {
            instance = new ProductDao();
        }
        return instance;
    }

    public void delete(int productId) {
        DBManager dbManager = DBManager.getInstance();
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLConstants.DELETE_PRODUCT_BY_ID)) {
            connection.setAutoCommit(true);
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(String category, String name, String description, String madeIn, String color, String price) {
        DBManager dbManager = DBManager.getInstance();
        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLConstants.ADD_NEW_PRODUCT)) {
            connection.setAutoCommit(true);
            stmt.setString(1, category);
            stmt.setString(2, name);
            stmt.setString(3, color);
            stmt.setInt(4, Integer.parseInt(price));
            stmt.setString(5, madeIn);
            stmt.setString(6, description);
            stmt.setTimestamp(7, timestamp);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateById(int id, String name, String description, String color, String price, String madeIn, String category) throws DaoException {
        DBManager dbManager = DBManager.getInstance();
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLConstants.UPDATE_PRODUCT_BY_ID)) {
            connection.setAutoCommit(true);
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, color);
            stmt.setInt(4, Integer.parseInt(price));
            stmt.setString(5, madeIn);
            stmt.setString(6, category);
            stmt.setInt(7, id);
            int row = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("error while trying to update information about product", e);
        }
    }

    public Product findById(int productId) {
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        Product product = new Product();
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLConstants.FIND_PRODUCT_BY_ID)) {
            stmt.setInt(1, productId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                product = createProductFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
        }
        return product;
    }

    public List<Product> findAll() {
        DBManager dbManager = DBManager.getInstance();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Product> allProducts = new ArrayList<>();
        try (Connection connection = dbManager.getConnection()) {
            stmt = connection.prepareStatement(SQLConstants.FIND_ALL_PRODUCTS);
            rs = stmt.executeQuery();
            while (rs.next()) {
                allProducts.add(createProductFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(stmt);
            close(rs);
        }
        return allProducts;
    }

    private Product createProductFromResultSet(ResultSet rs) {
        Product product = new Product();
        try {
            product.setId(rs.getInt("id"));
            product.setCategory_id(rs.getInt("category_id"));
            product.setName(rs.getString("name"));
            product.setColor(rs.getString("color"));
            product.setPrice(rs.getInt("price"));
            product.setMadeIn(rs.getString("made_in"));
            product.setDescription(rs.getString("description"));
            product.setTimestamp(rs.getTimestamp("timestamp"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}

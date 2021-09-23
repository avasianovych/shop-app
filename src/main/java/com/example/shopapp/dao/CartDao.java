package com.example.shopapp.dao;

import com.example.shopapp.entity.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDao extends AbstractDao<Cart>{

    private static CartDao instance;

    public static CartDao getInstance() {
        if (instance == null) {
            instance = new CartDao();
        }
        return instance;
    }

    public List<Cart> findCartProducts(List<Cart> cartList) {
        DBManager dbManager = DBManager.getInstance();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cart> cartProducts = new ArrayList<>();
        try (Connection connection = dbManager.getConnection()) {
            stmt = connection.prepareStatement(SQLConstants.FIND_CART_PRODUCTS);
            for (Cart item : cartList) {

                stmt.setInt(1, item.getId());
                rs = stmt.executeQuery();
                while (rs.next()) {
                    Cart cart = new Cart();
                    cart.setId(rs.getInt("id"));
                    cart.setCategory_id(rs.getInt("category_id"));
                    cart.setName(rs.getString("name"));
                    cart.setColor(rs.getString("color"));
                    cart.setPrice(rs.getInt("price") * item.getQuantity());
                    cart.setMadeIn(rs.getString("made_in"));
                    cart.setDescription(rs.getString("description"));
                    cart.setTimestamp(rs.getTimestamp("timestamp"));
                    cart.setQuantity(item.getQuantity());
                    cartProducts.add(cart);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(stmt);
            close(rs);
        }
        return cartProducts;
    }
}
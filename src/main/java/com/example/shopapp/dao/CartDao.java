package com.example.shopapp.dao;

import com.example.shopapp.entity.Cart;
import com.example.shopapp.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDao extends AbstractDao<Cart>{
    private static final Logger LOGGER = LogManager.getLogger(CartDao.class);
    private static final CartDao INSTANCE = new CartDao();

    public static CartDao getInstance() {
        return INSTANCE;
    }

    private CartDao(){}

    public List<Cart> findCartProducts(List<Cart> cartList) throws DaoException {
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
            LOGGER.log(Level.ERROR, e);
            throw new DaoException("an error occurred while trying to find cart products", e);
        } finally {
            close(stmt);
            close(rs);
        }
        return cartProducts;
    }
}

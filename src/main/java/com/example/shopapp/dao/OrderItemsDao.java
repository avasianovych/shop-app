package com.example.shopapp.dao;

import com.example.shopapp.entity.Order;
import com.example.shopapp.entity.OrderItems;
import com.example.shopapp.entity.Product;
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

public class OrderItemsDao {
    private static final Logger LOGGER = LogManager.getLogger(OrderItems.class);
    private static final OrderItemsDao INSTANCE = new OrderItemsDao();

    public static OrderItemsDao getInstance() {
        return INSTANCE;
    }

    private OrderItemsDao() {
    }

    public List<OrderItems> getOrderItemsByOrderId(int orderId) throws DaoException {
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        List<OrderItems> orderItemsList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLConstants.FIND_ORDER_ITEMS_BY_ORDER_ID)) {
            stmt.setInt(1, orderId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                OrderItems orderItems = new OrderItems();
                Order order = new Order();
                Product product = new Product();
                order.setId(rs.getInt("Order_id"));
                orderItems.setQuantity(rs.getInt("quantity"));
                product.setId(rs.getInt("Product_id"));
                product.setName(rs.getString("name"));
                product.setColor(rs.getString("color"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                product.setMadeIn(rs.getString("made_in"));
                orderItems.setOrder(order);
                orderItems.setProduct(product);
                orderItemsList.add(orderItems);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
            throw new DaoException("and error occurred while trying to get order items by order id", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, e);
            }
        }
        return orderItemsList;
    }
}

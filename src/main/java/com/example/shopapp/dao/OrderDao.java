package com.example.shopapp.dao;

import com.example.shopapp.entity.Cart;
import com.example.shopapp.entity.Order;
import com.example.shopapp.entity.State;
import com.example.shopapp.entity.User;
import com.example.shopapp.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDao extends AbstractDao<Order> {

    private static final Logger LOGGER = LogManager.getLogger(OrderDao.class);
    private static final OrderDao INSTANCE = new OrderDao();

    public static OrderDao getInstance() {
        return INSTANCE;
    }

    private OrderDao() {
    }

    public void update(int orderId, String stateName) throws DaoException {
        DBManager dbManager = DBManager.getInstance();
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLConstants.UPDATE_ORDER_STATE_BY_ORDER_ID_AND_STATE_NAME)) {
            connection.setAutoCommit(true);
            stmt.setString(1, stateName);
            stmt.setInt(2, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
            throw new DaoException("an error occurred while trying to update order state", e);
        }
    }

    public String findOrderStateById(int orderId) throws DaoException {
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        String orderState = null;
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLConstants.FIND_ORDER_STATE_BY_ORDER_ID)) {
            stmt.setInt(1, orderId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                orderState = rs.getString("name");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
            throw new DaoException("an error occurred while trying to find order state by id", e);
        } finally {
            close(rs);
        }
        return orderState;
    }

    public List<Order> findAll() throws DaoException {
        List<Order> orderList = new ArrayList<>();
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLConstants.FIND_ALL_ORDERS)) {
            rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                State state = new State();
                User user = new User();
                order.setId(rs.getInt("id"));
                user.setId(rs.getInt("user_id"));
                state.setId(rs.getInt("state_id"));
                order.setTotalPrice(rs.getInt("total_price"));
                order.setTimestamp(rs.getTimestamp("create_date"));
                state.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                order.setUser(user);
                order.setState(state);
                orderList.add(order);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
            throw new DaoException("an error occurred while trying to find all orders", e);
        } finally {
            close(rs);
        }
        return orderList;
    }

    public List<Order> getOrdersByUser(User user) throws DaoException {
        List<Order> orderList = new ArrayList<>();
        ResultSet rs = null;
        DBManager dbManager = DBManager.getInstance();
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLConstants.FIND_ORDERS_BY_USER_ID)) {
            stmt.setInt(1, user.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                orderList.add(createOrderFromResultSet(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
            throw new DaoException("an error occurred while trying to find user orders", e);
        } finally {
            close(rs);
        }
        return orderList;
    }

    private Order createOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        User user = new User();
        State state = new State();
        try {
            order.setId(resultSet.getInt("id"));
            user.setId(resultSet.getInt("user_id"));
            state.setId(resultSet.getInt("state_id"));
            order.setTotalPrice(resultSet.getInt("total_price"));
            order.setTimestamp(resultSet.getTimestamp("create_date"));
            state.setName(resultSet.getString("name"));
            order.setUser(user);
            order.setState(state);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return order;
    }

    public void add(User user, List<Cart> cartList, int totalPrice) throws DaoException {
        DBManager dbManager = DBManager.getInstance();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        Order order = new Order();
        try {
            connection = dbManager.getConnection();
            stmt = connection.prepareStatement(SQLConstants.REGISTER_ORDER, Statement.RETURN_GENERATED_KEYS);
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            stmt.setInt(1, user.getId());
            stmt.setInt(2, totalPrice);
            stmt.setTimestamp(3, timestamp);
            stmt.execute();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                order.setId(rs.getInt(1));
            }
            stmt = connection.prepareStatement(SQLConstants.ADD_TO_ORDER_HAS_PRODUCTS);
            for (Cart item : cartList) {
                stmt.setInt(1, order.getId());
                stmt.setInt(2, item.getId());
                stmt.setInt(3, item.getQuantity());
                stmt.addBatch();
            }
            stmt.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.ERROR, e);
            }
            throw new DaoException("an error occurred while trying to add order", e);
        } finally {
            close(rs);
            close(stmt);
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, e);
            }
        }
    }
}

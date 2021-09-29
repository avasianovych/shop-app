package com.example.shopapp.service;

import com.example.shopapp.dao.OrderDao;
import com.example.shopapp.entity.Cart;
import com.example.shopapp.entity.Order;
import com.example.shopapp.entity.User;
import com.example.shopapp.exception.DaoException;
import com.example.shopapp.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);
    OrderDao orderDao = OrderDao.getInstance();
    private static final OrderService INSTANCE = new OrderServiceImpl();

    public static OrderService getInstance() {
        return INSTANCE;
    }

    @Override
    public void registerOrder(User user, List<Cart> cartList, int totalPrice) throws ServiceException {
        try {
            orderDao.add(user, cartList, totalPrice);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e);
            throw new ServiceException("an error occurred while trying to register new order", e);
        }
    }

    public List<Order> getUserOrders(User user) throws ServiceException {
        try {
            return orderDao.getOrdersByUser(user);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e);
            throw new ServiceException("an error occurred while trying to find user orders", e);
        }
    }

    public List<Order> findAll() throws ServiceException {
        try {
            return orderDao.findAll();
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e);
            throw new ServiceException("an error occurred while trying to find all orders");
        }
    }

    public String findOrderStateById(int orderId) throws ServiceException {
        try {
            return orderDao.findOrderStateById(orderId);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e);
            throw new ServiceException("an error occurred while trying to find order state by id", e);
        }
    }

    @Override
    public void setPaidState(int orderId, String stateName) throws ServiceException {
        try {
            orderDao.update(orderId, stateName);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e);
            throw new ServiceException("an error occurred while trying to set paid state", e);
        }
    }

    @Override
    public void setCancelledState(int orderId, String stateName) throws ServiceException {
        try {
            orderDao.update(orderId, stateName);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e);
            throw new ServiceException("an error occurred while trying to set cancelled state", e);
        }
    }
}

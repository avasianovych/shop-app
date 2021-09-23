package com.example.shopapp.service;

import com.example.shopapp.command.LoginCommand;
import com.example.shopapp.dao.OrderItemsDao;
import com.example.shopapp.entity.Order;
import com.example.shopapp.entity.OrderItems;
import com.example.shopapp.exception.DaoException;
import com.example.shopapp.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OrderItemsServiceImpl implements OrderItemsService{
    private static final Logger LOGGER = LogManager.getLogger(OrderItemsServiceImpl.class);
    OrderItemsDao orderItemsDao = OrderItemsDao.getInstance();
    private static OrderItemsServiceImpl instance;

    public static OrderItemsServiceImpl getInstance() {
        if (instance == null) {
            instance = new OrderItemsServiceImpl();
        }
        return instance;
    }

    @Override
    public List<OrderItems> getOrderItemsByOrderId(int orderId) throws ServiceException {
        try {
            return orderItemsDao.getOrderItemsByOrderId(orderId);
        }catch (DaoException e){
            LOGGER.log(Level.ERROR, e);
            throw new ServiceException("an error occurred while trying to get order items by order id" ,e);
        }
    }
}

package com.example.shopapp.service;

import com.example.shopapp.dao.OrderItemsDao;
import com.example.shopapp.entity.Order;
import com.example.shopapp.entity.OrderItems;

import java.util.List;

public class OrderItemsServiceImpl implements OrderItemsService{

    OrderItemsDao orderItemsDao = OrderItemsDao.getInstance();
    private static OrderItemsServiceImpl instance;

    public static OrderItemsServiceImpl getInstance() {
        if (instance == null) {
            instance = new OrderItemsServiceImpl();
        }
        return instance;
    }

    @Override
    public List<OrderItems> getOrderItemsByOrderId(int orderId) {
        return orderItemsDao.getOrderItemsByOrderId(orderId);
    }
}

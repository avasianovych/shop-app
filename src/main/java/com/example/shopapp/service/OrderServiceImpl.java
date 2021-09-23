package com.example.shopapp.service;

import com.example.shopapp.dao.OrderDao;
import com.example.shopapp.entity.Cart;
import com.example.shopapp.entity.Order;
import com.example.shopapp.entity.User;

import java.util.List;

public class OrderServiceImpl implements OrderService{
    OrderDao orderDao = OrderDao.getInstance();
    private static OrderServiceImpl instance;

    public static OrderServiceImpl getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    @Override
    public void registerOrder(User user, List<Cart> cartList, int totalPrice) {
        orderDao.add(user,cartList,totalPrice);
    }

    public List<Order> getUserOrders(User user){
        return orderDao.getOrdersByUser(user);
    }

    public List<Order> findAll(){
        return orderDao.findAll();
    }

    public String findOrderStateById(int orderId){
        return orderDao.findOrderStateById(orderId);
    }

    @Override
    public void setPaidState(int orderId, String stateName) {
        orderDao.update(orderId, stateName);
    }

    @Override
    public void setCancelledState(int orderId, String stateName) {
        orderDao.update(orderId,stateName);
    }
}

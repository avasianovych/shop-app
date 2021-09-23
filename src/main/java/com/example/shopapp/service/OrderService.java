package com.example.shopapp.service;

import com.example.shopapp.entity.Cart;
import com.example.shopapp.entity.Order;
import com.example.shopapp.entity.User;

import java.util.List;

public interface OrderService {

     void registerOrder(User user, List<Cart> cartList, int totalPrice);
     List<Order> getUserOrders(User user);
     List<Order> findAll();
     String findOrderStateById(int orderId);
     void setPaidState(int orderId, String stateName);
     void setCancelledState(int orderId, String stateName);
}

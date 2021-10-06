package com.example.shopapp.service;

import com.example.shopapp.entity.Cart;
import com.example.shopapp.entity.Order;
import com.example.shopapp.entity.Product;
import com.example.shopapp.entity.User;
import com.example.shopapp.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {

    void registerOrder(User user, List<Cart> cartList, int totalPrice) throws ServiceException;

    List<Order> getUserOrders(User user) throws ServiceException;

    List<Order> findAll() throws ServiceException;

    String findOrderStateById(int orderId) throws ServiceException;

    void setPaidState(int orderId, String stateName) throws ServiceException;

    void setCancelledState(int orderId, String stateName) throws ServiceException;

    List<Order> getCurrentPageRecords(List<Order> totalList, int currentPageNo);

    void calculateRecords(HttpServletRequest req,
                                    int page,
                                    List<Order> allOrdersList);
}

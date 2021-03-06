package com.example.shopapp.service;

import com.example.shopapp.entity.OrderItems;
import com.example.shopapp.exception.ServiceException;

import java.util.List;

public interface OrderItemsService {

    List<OrderItems> getOrderItemsByOrderId(int orderId) throws ServiceException;
}

package com.example.shopapp.service;

import com.example.shopapp.entity.Order;
import com.example.shopapp.entity.OrderItems;

import java.util.List;

public interface OrderItemsService {

    List<OrderItems> getOrderItemsByOrderId(int orderId);
}

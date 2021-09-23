package com.example.shopapp.service;

import com.example.shopapp.entity.Cart;
import com.example.shopapp.exception.ServiceException;

import java.util.List;

public interface CartService {

    List<Cart> findCartProducts(List<Cart> list) throws ServiceException;
    int getTotalPrice(List<Cart> cartList);
}

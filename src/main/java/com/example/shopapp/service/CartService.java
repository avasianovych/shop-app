package com.example.shopapp.service;

import com.example.shopapp.entity.Cart;

import java.util.List;

public interface CartService {

    List<Cart> findCartProducts(List<Cart> list);
    int getTotalPrice(List<Cart> cartList);
}

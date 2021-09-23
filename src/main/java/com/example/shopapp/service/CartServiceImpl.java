package com.example.shopapp.service;

import com.example.shopapp.dao.CartDao;
import com.example.shopapp.dao.ProductDao;
import com.example.shopapp.entity.Cart;

import java.util.List;

public class CartServiceImpl implements CartService {

    CartDao cartDao = CartDao.getInstance();
    private static CartServiceImpl instance;

    public static CartServiceImpl getInstance() {
        if (instance == null) {
            instance = new CartServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Cart> findCartProducts(List<Cart> list) {
        return cartDao.findCartProducts(list);
    }

    @Override
    public int getTotalPrice(List<Cart> cartList) {
        int totalPrice = 0;
        for (Cart item : cartList) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
}


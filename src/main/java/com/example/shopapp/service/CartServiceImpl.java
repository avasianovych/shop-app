package com.example.shopapp.service;

import com.example.shopapp.dao.CartDao;
import com.example.shopapp.entity.Cart;
import com.example.shopapp.exception.DaoException;
import com.example.shopapp.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CartServiceImpl implements CartService {
    private static final Logger LOGGER = LogManager.getLogger(CartServiceImpl.class);
    CartDao cartDao = CartDao.getInstance();
    private static final CartService INSTANCE = new CartServiceImpl();

    public static CartService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Cart> findCartProducts(List<Cart> list) throws ServiceException {
        try {
            return cartDao.findCartProducts(list);
        }catch (DaoException e){
            LOGGER.log(Level.ERROR, e);
            throw new ServiceException("an error occurred while trying to find cart products", e);
        }
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


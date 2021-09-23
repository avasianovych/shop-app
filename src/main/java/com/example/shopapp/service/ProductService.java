package com.example.shopapp.service;

import com.example.shopapp.entity.Cart;
import com.example.shopapp.entity.Product;
import com.example.shopapp.exception.ServiceException;

import java.util.List;
import java.util.Set;

public interface ProductService {

    List<Product> findAll();
    Product findById(int productId);
    void updateById(int id, String name, String description, String color, String price, String madeIn, String category) throws ServiceException;
    void add(String category, String name, String description, String madeIn, String color, String price) throws ServiceException;
    void delete(int productId);
    List<Product> getCurrentPageRecords(List<Product> totalList,int currentPageNo);

}

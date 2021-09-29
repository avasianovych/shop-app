package com.example.shopapp.service;

import com.example.shopapp.entity.Product;
import com.example.shopapp.exception.ServiceException;

import java.util.List;

public interface ProductService {

    List<Product> findAll() throws ServiceException;

    Product findById(int productId) throws ServiceException;

    void updateById(int id, String name, String description, String color, String price, String madeIn, String category) throws ServiceException;

    void add(String category, String name, String description, String madeIn, String color, String price) throws ServiceException;

    void delete(int productId) throws ServiceException;

    List<Product> getCurrentPageRecords(List<Product> totalList, int currentPageNo);
}

package com.example.shopapp.service;

import com.example.shopapp.entity.Category;
import com.example.shopapp.exception.ServiceException;

import java.util.List;

public interface CategoryService {

    List<Category> findAll() throws ServiceException;
}

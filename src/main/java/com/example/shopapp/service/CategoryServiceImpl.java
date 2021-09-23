package com.example.shopapp.service;

import com.example.shopapp.dao.CategoryDao;
import com.example.shopapp.entity.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService{

    CategoryDao categoryDao = CategoryDao.getInstance();
    private static CategoryServiceImpl instance;

    public static CategoryServiceImpl getInstance() {
        if (instance == null) {
            instance = new CategoryServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}

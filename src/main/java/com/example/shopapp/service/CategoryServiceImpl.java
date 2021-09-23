package com.example.shopapp.service;

import com.example.shopapp.command.LoginCommand;
import com.example.shopapp.dao.CategoryDao;
import com.example.shopapp.entity.Category;
import com.example.shopapp.exception.DaoException;
import com.example.shopapp.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CategoryServiceImpl implements CategoryService{

    private static final Logger LOGGER = LogManager.getLogger(CategoryServiceImpl.class);
    CategoryDao categoryDao = CategoryDao.getInstance();
    private static CategoryServiceImpl instance;

    public static CategoryServiceImpl getInstance() {
        if (instance == null) {
            instance = new CategoryServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Category> findAll() throws ServiceException {
        try {
            return categoryDao.findAll();
        } catch (DaoException e){
            LOGGER.log(Level.ERROR, e);
            throw new ServiceException("an error occurred while trying to find all categories", e);
        }
    }
}

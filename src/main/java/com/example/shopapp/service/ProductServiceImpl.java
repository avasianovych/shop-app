package com.example.shopapp.service;


import com.example.shopapp.dao.ProductDao;

import com.example.shopapp.entity.Product;
import com.example.shopapp.exception.DaoException;
import com.example.shopapp.exception.ServiceException;
import com.example.shopapp.util.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);
    ProductDao productDao = ProductDao.getInstance();
    private static ProductServiceImpl instance;

    public static ProductServiceImpl getInstance() {
        if (instance == null) {
            instance = new ProductServiceImpl();
        }
        return instance;
    }

    public Product findById(int productId) throws ServiceException {
        try {
            return productDao.findById(productId);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e);
            throw new ServiceException("an error occurred while trying to find product by id", e);
        }
    }

    @Override
    public void updateById(int id, String name, String description, String color, String price, String madeIn, String category) throws ServiceException {
        if (!Validator.validateProductNameWithRegex(name)) {
            throw new ServiceException("1");
        } else if (!Validator.validateDescriptionWithRegex(description)) {
            throw new ServiceException("2");
        } else if (!Validator.validateProductColorWithRegex(color)) {
            throw new ServiceException("3");
        } else if (!Validator.validateProductPriceWithRegex(price)) {
            throw new ServiceException("4");
        } else if (!Validator.validateProductMadeInWithRegex(madeIn)) {
            throw new ServiceException("5");
        } else if (category == null) {
            throw new ServiceException("6");
        }
        try {
            productDao.updateById(id, name, description, color, price, madeIn, category);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("an error while trying to update information about product", e);
        }
    }

    @Override
    public void add(String category, String name, String description, String madeIn, String color, String price) throws ServiceException {
        if (!Validator.validateProductNameWithRegex(name)) {
            throw new ServiceException("1");
        } else if (!Validator.validateDescriptionWithRegex(description)) {
            throw new ServiceException("2");
        } else if (!Validator.validateProductColorWithRegex(color)) {
            throw new ServiceException("3");
        } else if (!Validator.validateProductPriceWithRegex(price)) {
            throw new ServiceException("4");
        } else if (!Validator.validateProductMadeInWithRegex(madeIn)) {
            throw new ServiceException("5");
        } else if (category == null) {
            throw new ServiceException("6");
        }
        try {
            productDao.add(category, name, description, madeIn, color, price);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e);
            throw new ServiceException("an error occurred while trying to add new product", e);
        }
    }

    @Override
    public void delete(int productId) throws ServiceException {
        try {
            productDao.delete(productId);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e);
            throw new ServiceException("an error occurred while trying to delete product", e);
        }
    }

    @Override
    public List<Product> findAll() throws ServiceException {
        try {
            return productDao.findAll();
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e);
            throw new ServiceException("an error occurred while trying to find all product", e);
        }
    }


    @Override
    public List<Product> getCurrentPageRecords(List<Product> totalList, int currentPageNo) {
        if (totalList == null) {
            return null;
        }
        int startIndex = (currentPageNo - 1) * 8;
        int endIndex = (startIndex + 8 > totalList.size() ? totalList.size() : startIndex + 8);
        return totalList.subList(startIndex, endIndex);
    }
}


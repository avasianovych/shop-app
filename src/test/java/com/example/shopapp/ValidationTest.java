package com.example.shopapp;

import com.example.shopapp.entity.Product;
import com.example.shopapp.entity.User;
import com.example.shopapp.exception.ServiceException;
import com.example.shopapp.service.ProductService;
import com.example.shopapp.service.ProductServiceImpl;
import com.example.shopapp.service.UserService;
import com.example.shopapp.service.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ValidationTest {

    ProductService productService;
    UserService userService;
    User user;
    Product product;

    @Before
    public void setUp() {
        productService = new ProductServiceImpl();
        userService = new UserServiceImpl();
        user = new User();
        product = new Product();
    }

    @Test(expected = ServiceException.class)
    public void shouldReturnExceptionWhileCheckLogin() throws ServiceException {
        user.setLogin("andrey#@gmail.com");
        userService.add(user);
    }

    @Test(expected = ServiceException.class)
    public void shouldReturnExceptionWhileCheckPassword() throws ServiceException {
        user.setLogin("andrey@gmail.com");
        user.setPassword("1234");
        userService.add(user);
    }

    @Test(expected = ServiceException.class)
    public void shouldReturnExceptionWhileCheckProductName() throws ServiceException {
        productService.add("1", "Standart#", "asdf", "asdf", "red", "5");
    }

    @Test(expected = ServiceException.class)
    public void shouldReturnExceptionWhileCheckProductMadeIn() throws ServiceException {
        productService.add("1", "Standart", "asdf", "1", "red", "5");
    }

    @Test(expected = ServiceException.class)
    public void shouldReturnExceptionWhileCheckProductColor() throws ServiceException {
        productService.add("1", "Standart", "asdf", "asdf", "red1", "5");
    }

    @Test(expected = ServiceException.class)
    public void shouldReturnExceptionWhileCheckProductPrice() throws ServiceException {
        productService.add("1", "Standart", "asdf", "asdf", "red", "A5");
    }

    @Test(expected = ServiceException.class)
    public void shouldReturnExceptionWhileCheckProductDescr() throws ServiceException {
        productService.add("1", "Standart", "asdf#", "asdf", "red", "5");
    }

    @Test(expected = ServiceException.class)
    public void shouldReturnExceptionWhileCheckProductCategory() throws ServiceException {
        productService.add(null, "Standart", "asdf", "asdf", "red", "5");
    }

    @After
    public void after() {
        userService = null;
        user = null;
        product = null;
        productService = null;
    }

}

package com.example.shopapp.command;


import com.example.shopapp.exception.ServiceException;
import com.example.shopapp.service.ProductService;
import com.example.shopapp.service.ProductServiceImpl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddNewProductCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(AddNewProductCommand.class);
    ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String categoryName = req.getParameter("category");
        String productName = req.getParameter("name");
        String productColor = req.getParameter("color");
        String productDescription = req.getParameter("description");
        String productMadeIn = req.getParameter("madeIn");
        String productPrice = req.getParameter("price");
        try {
            productService.add(categoryName, productName, productDescription, productMadeIn, productColor, productPrice);
            req.getSession().setAttribute("errorAddNewProduct", "7");
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
            req.getSession().setAttribute("errorAddNewProduct", e.getMessage());
        }
        return "addProduct.jsp";
    }
}

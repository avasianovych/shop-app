package com.example.shopapp.command;

import com.example.shopapp.entity.Product;
import com.example.shopapp.exception.ServiceException;
import com.example.shopapp.service.ProductService;
import com.example.shopapp.service.ProductServiceImpl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ModifyProductCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(ModifyProductCommand.class);
    ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int productId = Integer.parseInt(req.getParameter("id"));
        String productName = req.getParameter("name");
        String productDescription = req.getParameter("description");
        String productColor = req.getParameter("color");
        String productPrice = req.getParameter("price");
        String productMadeIn = req.getParameter("madeIn");
        String categoryName = req.getParameter("category");
        try {
            productService.updateById(productId, productName, productDescription, productColor, productPrice, productMadeIn, categoryName);
            req.getSession().setAttribute("errorModifyProduct", "7");
            Product product = productService.findById(productId);
            req.getSession().setAttribute("product", product);
        } catch (ServiceException e) {
            LOGGER.log(Level.INFO, e);
            req.getSession().setAttribute("errorModifyProduct", e.getMessage());
        }
        return "modifyProduct.jsp";
    }
}

package com.example.shopapp.command;

import com.example.shopapp.entity.Category;
import com.example.shopapp.entity.Product;
import com.example.shopapp.service.CategoryService;
import com.example.shopapp.service.CategoryServiceImpl;
import com.example.shopapp.service.ProductService;
import com.example.shopapp.service.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class OpenModifyFormCommand implements ICommand {
    ProductService productService = ProductServiceImpl.getInstance();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int productId = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(productId);

        req.getSession().setAttribute("product", product);

        return "modifyProduct.jsp";
    }
}

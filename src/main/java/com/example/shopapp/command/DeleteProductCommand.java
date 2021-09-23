package com.example.shopapp.command;

import com.example.shopapp.entity.Product;
import com.example.shopapp.service.ProductService;
import com.example.shopapp.service.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteProductCommand implements ICommand {
    ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int productId = Integer.parseInt(req.getParameter("id"));
        productService.delete(productId);
//        List<Product> allProducts = productService.findAll();
//        req.getSession().setAttribute("allProducts", allProducts);
        return "index.jsp";
    }
}

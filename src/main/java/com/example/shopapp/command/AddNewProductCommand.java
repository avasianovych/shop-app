package com.example.shopapp.command;

import com.example.shopapp.entity.Product;
import com.example.shopapp.exception.ServiceException;
import com.example.shopapp.service.ProductService;
import com.example.shopapp.service.ProductServiceImpl;
import com.example.shopapp.util.ConstantNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AddNewProductCommand implements ICommand {
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
        }catch (ServiceException e){
            e.printStackTrace();
            req.getSession().setAttribute("errorAddNewProduct", e.getMessage());
        }
//        List<Product> allProducts = productService.findAll();
//        req.getSession().setAttribute("allProducts", allProducts);
        return "addProduct.jsp";
    }
}

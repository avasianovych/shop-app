package com.example.shopapp.command;

import com.example.shopapp.entity.Product;
import com.example.shopapp.entity.User;
import com.example.shopapp.service.ProductService;
import com.example.shopapp.service.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PaginationCommand implements ICommand {
    ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int page =1;
        int recordsPerPage = 8;
        User user = (User) req.getSession().getAttribute("user");
        if(req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        @SuppressWarnings("unchecked")
        List<Product> allProducts = (List<Product>) req.getSession().getAttribute("allProducts");
        int noOfRecords = allProducts.size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        List<Product> currentPageRecords =  productService.getCurrentPageRecords(allProducts,page);
        req.getSession().setAttribute("noOfPages", noOfPages);
        req.getSession().setAttribute("currentPage", page);
        req.getSession().setAttribute("currentPageRecords", currentPageRecords);
        if(user != null && user.getRole_id() == 1){
            return "admin.jsp";
        }

        return "bikeShop.jsp";
    }
}

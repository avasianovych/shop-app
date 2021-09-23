package com.example.shopapp.command;

import com.example.shopapp.entity.Category;
import com.example.shopapp.entity.Product;
import com.example.shopapp.entity.User;
import com.example.shopapp.service.CategoryService;
import com.example.shopapp.service.CategoryServiceImpl;
import com.example.shopapp.service.ProductService;
import com.example.shopapp.service.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

public class FindAllProductsCommand implements ICommand {

    ProductService productService = ProductServiceImpl.getInstance();
    CategoryService categoryService = CategoryServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int page =1;
        int recordsPerPage = 8;
        User user = (User) req.getSession().getAttribute("user");
        if(req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        List<Product> allProducts = productService.findAll();
        int noOfRecords = allProducts.size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        List<Product> currentPageRecords =  productService.getCurrentPageRecords(allProducts,page);
        req.getSession().setAttribute("noOfPages", noOfPages);
        req.getSession().setAttribute("currentPage", page);
        req.getSession().setAttribute("currentPageRecords", currentPageRecords);




        List<String> distinctColor = allProducts.stream()
                .map(Product::getColor).distinct().collect(Collectors.toList());
        List<Category> categoryList = categoryService.findAll();
        req.getSession().setAttribute("categoryList", categoryList);
        HttpSession session = req.getSession();
        session.setAttribute("allColors", distinctColor);
        session.setAttribute("allProducts", allProducts);
        if(user != null && user.getRole_id() == 1){
            return "admin.jsp";
        }
        return "bikeShop.jsp";
    }
}

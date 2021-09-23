package com.example.shopapp.command;

import com.example.shopapp.entity.Product;
import com.example.shopapp.entity.User;
import com.example.shopapp.service.ProductService;
import com.example.shopapp.service.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortProductsCommand implements ICommand {
    ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int page = 1;
        int recordsPerPage = 8;

User user = (User) req.getSession().getAttribute("user");
        @SuppressWarnings("unchecked")
        List<Product> products = (List<Product>) req.getSession().getAttribute("allProducts");
        int noOfRecords = products.size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        String sortBy = req.getParameter("sortBy");
        List<Product> currentPageRecords = null;
        if (sortBy.equals("nameAZ")) {
            List<Product> sortingNameAZ = products.stream()
                    .sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", sortingNameAZ);
            currentPageRecords = productService.getCurrentPageRecords(sortingNameAZ, 1);


        }
        if (sortBy.equals("nameZA")) {
            List<Product> sortingNameZA = products.stream()
                    .sorted(Comparator.comparing(Product::getName).reversed()).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", sortingNameZA);
            currentPageRecords = productService.getCurrentPageRecords(sortingNameZA, 1);

        }
        if (sortBy.equals("priceL2H")) {
            List<Product> sortingPriceLowToHigh = products.stream()
                    .sorted(Comparator.comparingInt(Product::getPrice)).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", sortingPriceLowToHigh);
            currentPageRecords = productService.getCurrentPageRecords(sortingPriceLowToHigh, 1);

        }
        if (sortBy.equals("priceH2L")) {
            List<Product> sortingPriceHighToLow = products.stream()
                    .sorted((o1, o2) -> o2.getPrice() - o1.getPrice()).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", sortingPriceHighToLow);
            currentPageRecords = productService.getCurrentPageRecords(sortingPriceHighToLow, 1);

        }
        if (sortBy.equals("NewOld")) {
            List<Product> sortingNewestToOldest = products.stream()
                    .sorted(Comparator.comparing(Product::getTimestamp).reversed()).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", sortingNewestToOldest);
            currentPageRecords = productService.getCurrentPageRecords(sortingNewestToOldest, 1);

        }

        req.getSession().setAttribute("currentPage", page);
        req.getSession().setAttribute("noOfPages", noOfPages);
        req.getSession().setAttribute("currentPageRecords", currentPageRecords);
        if(user != null && user.getRole_id() == 1){
            return "admin.jsp";
        }
        return "bikeShop.jsp";
    }
}

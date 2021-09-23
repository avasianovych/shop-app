package com.example.shopapp.command;

import com.example.shopapp.entity.Product;
import com.example.shopapp.entity.User;
import com.example.shopapp.service.ProductService;
import com.example.shopapp.service.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class FilterProductsCommand implements ICommand {
    ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int page = 1;
        int recordsPerPage = 8;
        int noOfRecords;
        int noOfPages = 0;
        List<Product> currentPageRecords = null;

        String color = req.getParameter("color");

        int category = 0;
        int priceFrom = 0;
        int priceTo = 0;
        User user = (User) req.getSession().getAttribute("user");
        try {
            category = Integer.parseInt(req.getParameter("category"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            priceFrom = Integer.parseInt(req.getParameter("priceFrom"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            priceTo = Integer.parseInt(req.getParameter("priceTo"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        @SuppressWarnings("unchecked")
        List<Product> products = (List<Product>) req.getSession().getAttribute("allProducts");
        if (color == null && category == 0 && priceFrom == 0 && priceTo == 0) {
            if (user != null && user.getRole_id() == 1) {
                return "admin.jsp";
            }
            return "bikeShop.jsp";
        }
        if (color != null && category == 0 && priceFrom == 0 && priceTo == 0) {
            List<Product> colorProducts = products.stream()
                    .filter(a -> a.getColor().equals(color)).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", colorProducts);

            noOfRecords = colorProducts.size();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            currentPageRecords = productService.getCurrentPageRecords(colorProducts, 1);


        }
        if (category != 0 && color == null && priceFrom == 0 && priceTo == 0) {
            int finalCategory = category;
            List<Product> categoryProducts = products.stream()
                    .filter(a -> a.getCategory_id() == finalCategory).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", categoryProducts);

            noOfRecords = categoryProducts.size();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            currentPageRecords = productService.getCurrentPageRecords(categoryProducts, 1);


        }
        if (category != 0 && color != null && priceFrom == 0 && priceTo == 0) {
            int finalCategory = category;
            List<Product> colorCategoryProducts = products.stream()
                    .filter(a -> a.getCategory_id() == finalCategory)
                    .filter(a -> a.getColor().equals(color))
                    .collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", colorCategoryProducts);

            noOfRecords = colorCategoryProducts.size();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            currentPageRecords = productService.getCurrentPageRecords(colorCategoryProducts, 1);


        }
        if (priceFrom != 0 && priceTo == 0 && color == null && category == 0) {
            int finalPriceFrom = priceFrom;
            List<Product> priceFromProducts = products.stream()
                    .filter(a -> a.getPrice() >= finalPriceFrom).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceFromProducts);

            noOfRecords = priceFromProducts.size();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            currentPageRecords = productService.getCurrentPageRecords(priceFromProducts, 1);


        }
        if (priceTo != 0 && priceFrom == 0 && color == null && category == 0) {
            int finalPriceTo = priceTo;
            List<Product> priceToProducts = products.stream()
                    .filter(a -> a.getPrice() <= finalPriceTo).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceToProducts);

            noOfRecords = priceToProducts.size();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            currentPageRecords = productService.getCurrentPageRecords(priceToProducts, 1);


        }
        if (priceTo != 0 && priceFrom != 0 && color == null && category == 0) {
            int finalPriceTo = priceTo;
            int finalPriceFrom = priceFrom;
            List<Product> priceFromToProducts = products.stream()
                    .filter(a -> a.getPrice() <= finalPriceTo)
                    .filter(a -> a.getPrice() >= finalPriceFrom).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceFromToProducts);

            noOfRecords = priceFromToProducts.size();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            currentPageRecords = productService.getCurrentPageRecords(priceFromToProducts, 1);


        }
        if (priceTo != 0 && priceFrom != 0 && color != null && category == 0) {
            int finalPriceTo = priceTo;
            int finalPriceFrom = priceFrom;
            List<Product> priceFromToWithColorProducts = products.stream()
                    .filter(a -> a.getPrice() <= finalPriceTo)
                    .filter(a -> a.getPrice() >= finalPriceFrom)
                    .filter(a -> a.getColor().equals(color)).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceFromToWithColorProducts);

            noOfRecords = priceFromToWithColorProducts.size();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            currentPageRecords = productService.getCurrentPageRecords(priceFromToWithColorProducts, 1);


        }
        if (priceTo != 0 && priceFrom != 0 && color == null && category != 0) {
            int finalPriceTo = priceTo;
            int finalPriceFrom = priceFrom;
            int finalCategory = category;
            List<Product> priceFromToWithCategoryProducts = products.stream()
                    .filter(a -> a.getPrice() <= finalPriceTo)
                    .filter(a -> a.getPrice() >= finalPriceFrom)
                    .filter(a -> a.getCategory_id() == finalCategory).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceFromToWithCategoryProducts);

            noOfRecords = priceFromToWithCategoryProducts.size();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            currentPageRecords = productService.getCurrentPageRecords(priceFromToWithCategoryProducts, 1);


        }
        if (priceTo != 0 && priceFrom != 0 && color != null && category != 0) {
            int finalPriceTo = priceTo;
            int finalPriceFrom = priceFrom;
            int finalCategory = category;
            List<Product> priceFromToWithCategoryAndColorProducts = products.stream()
                    .filter(a -> a.getPrice() <= finalPriceTo)
                    .filter(a -> a.getPrice() >= finalPriceFrom)
                    .filter(a -> a.getCategory_id() == finalCategory)
                    .filter(a -> a.getColor().equals(color)).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceFromToWithCategoryAndColorProducts);

            noOfRecords = priceFromToWithCategoryAndColorProducts.size();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            currentPageRecords = productService.getCurrentPageRecords(priceFromToWithCategoryAndColorProducts, 1);


        }
        if (priceTo != 0 && priceFrom == 0 && color != null && category == 0) {
            int finalPriceTo = priceTo;
            List<Product> priceToWithColor = products.stream()
                    .filter(a -> a.getPrice() <= finalPriceTo)
                    .filter(a -> a.getColor().equals(color)).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceToWithColor);

            noOfRecords = priceToWithColor.size();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            currentPageRecords = productService.getCurrentPageRecords(priceToWithColor, 1);


        }
        if (priceTo == 0 && priceFrom != 0 && color != null && category == 0) {
            int finalPriceFrom = priceFrom;
            List<Product> priceFromWithColor = products.stream()
                    .filter(a -> a.getPrice() >= finalPriceFrom)
                    .filter(a -> a.getColor().equals(color)).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceFromWithColor);

            noOfRecords = priceFromWithColor.size();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            currentPageRecords = productService.getCurrentPageRecords(priceFromWithColor, 1);


        }
        if (priceTo != 0 && priceFrom == 0 && color == null && category != 0) {
            int finalPriceTo = priceTo;
            int finalCategory = category;
            List<Product> priceToWithCategory = products.stream()
                    .filter(a -> a.getPrice() <= finalPriceTo)
                    .filter(a -> a.getCategory_id() == finalCategory).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceToWithCategory);

            noOfRecords = priceToWithCategory.size();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            currentPageRecords = productService.getCurrentPageRecords(priceToWithCategory, 1);


        }
        if (priceTo == 0 && priceFrom != 0 && color == null && category != 0) {
            int finalPriceFrom = priceFrom;
            int finalCategory = category;
            List<Product> priceFromWithCategory = products.stream()
                    .filter(a -> a.getPrice() >= finalPriceFrom)
                    .filter(a -> a.getCategory_id() == finalCategory).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceFromWithCategory);

            noOfRecords = priceFromWithCategory.size();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            currentPageRecords = productService.getCurrentPageRecords(priceFromWithCategory, 1);


        }
        if (priceTo != 0 && category != 0 && color != null && priceFrom == 0) {
            int finalPriceTo = priceTo;
            int finalCategory = category;
            List<Product> priceToWithColorAndCategory = products.stream()
                    .filter(a -> a.getPrice() <= finalPriceTo)
                    .filter(a -> a.getColor().equals(color))
                    .filter(a -> a.getCategory_id() == finalCategory).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceToWithColorAndCategory);

            noOfRecords = priceToWithColorAndCategory.size();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            currentPageRecords = productService.getCurrentPageRecords(priceToWithColorAndCategory, 1);


        }
        if (priceTo == 0 && category != 0 && color != null && priceFrom != 0) {
            int finalPriceFrom = priceFrom;
            int finalCategory = category;
            List<Product> priceFromWithColorAndCategory = products.stream()
                    .filter(a -> a.getPrice() >= finalPriceFrom)
                    .filter(a -> a.getColor().equals(color))
                    .filter(a -> a.getCategory_id() == finalCategory).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceFromWithColorAndCategory);

            noOfRecords = priceFromWithColorAndCategory.size();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            currentPageRecords = productService.getCurrentPageRecords(priceFromWithColorAndCategory, 1);


        }
        req.getSession().setAttribute("currentPageRecords", currentPageRecords);
        req.getSession().setAttribute("currentPage", page);
        req.getSession().setAttribute("noOfPages", noOfPages);
        if (user != null && user.getRole_id() == 1) {
            return "admin.jsp";
        }
        return "bikeShop.jsp";
    }
}

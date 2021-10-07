package com.example.shopapp.command;

import com.example.shopapp.entity.Product;
import com.example.shopapp.entity.User;
import com.example.shopapp.service.ProductService;
import com.example.shopapp.service.ProductServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code FilterProductsCommand} class, responsible for filtering products
 * by color, price and product category.
 *
 * @author Andrii Vasianovych
 * @version 1.0
 */
public class FilterProductsCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(FilterProductsCommand.class);
    ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int page = 1;
        int category = 0;
        int priceFrom = 0;
        int priceTo = 0;
        String color = req.getParameter("color");
        User user = (User) req.getSession().getAttribute("user");
        try {
            category = Integer.parseInt(req.getParameter("category"));
        } catch (NumberFormatException e) {
            LOGGER.log(Level.INFO, e);
        }
        try {
            priceFrom = Integer.parseInt(req.getParameter("priceFrom"));
        } catch (NumberFormatException e) {
            LOGGER.log(Level.INFO, e);
        }
        try {
            priceTo = Integer.parseInt(req.getParameter("priceTo"));
        } catch (NumberFormatException e) {
            LOGGER.log(Level.INFO, e);
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
            productService.calculateRecords(req, colorProducts, page);
        }
        if (category != 0 && color == null && priceFrom == 0 && priceTo == 0) {
            int finalCategory = category;
            List<Product> categoryProducts = products.stream()
                    .filter(a -> a.getCategory_id() == finalCategory).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", categoryProducts);
            productService.calculateRecords(req, categoryProducts, page);
        }
        if (category != 0 && color != null && priceFrom == 0 && priceTo == 0) {
            int finalCategory = category;
            List<Product> colorCategoryProducts = products.stream()
                    .filter(a -> a.getCategory_id() == finalCategory)
                    .filter(a -> a.getColor().equals(color))
                    .collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", colorCategoryProducts);
            productService.calculateRecords(req, colorCategoryProducts, page);
        }
        if (priceFrom != 0 && priceTo == 0 && color == null && category == 0) {
            int finalPriceFrom = priceFrom;
            List<Product> priceFromProducts = products.stream()
                    .filter(a -> a.getPrice() >= finalPriceFrom).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceFromProducts);
            productService.calculateRecords(req, priceFromProducts, page);
        }
        if (priceTo != 0 && priceFrom == 0 && color == null && category == 0) {
            int finalPriceTo = priceTo;
            List<Product> priceToProducts = products.stream()
                    .filter(a -> a.getPrice() <= finalPriceTo).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceToProducts);
            productService.calculateRecords(req, priceToProducts, page);
        }
        if (priceTo != 0 && priceFrom != 0 && color == null && category == 0) {
            int finalPriceTo = priceTo;
            int finalPriceFrom = priceFrom;
            List<Product> priceFromToProducts = products.stream()
                    .filter(a -> a.getPrice() <= finalPriceTo)
                    .filter(a -> a.getPrice() >= finalPriceFrom).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceFromToProducts);
            productService.calculateRecords(req, priceFromToProducts, page);
        }
        if (priceTo != 0 && priceFrom != 0 && color != null && category == 0) {
            int finalPriceTo = priceTo;
            int finalPriceFrom = priceFrom;
            List<Product> priceFromToWithColorProducts = products.stream()
                    .filter(a -> a.getPrice() <= finalPriceTo)
                    .filter(a -> a.getPrice() >= finalPriceFrom)
                    .filter(a -> a.getColor().equals(color)).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceFromToWithColorProducts);
            productService.calculateRecords(req, priceFromToWithColorProducts, page);
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
            productService.calculateRecords(req, priceFromToWithCategoryProducts, page);
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
            productService.calculateRecords(req, priceFromToWithCategoryAndColorProducts, page);
        }
        if (priceTo != 0 && priceFrom == 0 && color != null && category == 0) {
            int finalPriceTo = priceTo;
            List<Product> priceToWithColor = products.stream()
                    .filter(a -> a.getPrice() <= finalPriceTo)
                    .filter(a -> a.getColor().equals(color)).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceToWithColor);
            productService.calculateRecords(req, priceToWithColor, page);
        }
        if (priceTo == 0 && priceFrom != 0 && color != null && category == 0) {
            int finalPriceFrom = priceFrom;
            List<Product> priceFromWithColor = products.stream()
                    .filter(a -> a.getPrice() >= finalPriceFrom)
                    .filter(a -> a.getColor().equals(color)).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceFromWithColor);
            productService.calculateRecords(req, priceFromWithColor, page);
        }
        if (priceTo != 0 && priceFrom == 0 && color == null && category != 0) {
            int finalPriceTo = priceTo;
            int finalCategory = category;
            List<Product> priceToWithCategory = products.stream()
                    .filter(a -> a.getPrice() <= finalPriceTo)
                    .filter(a -> a.getCategory_id() == finalCategory).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceToWithCategory);
            productService.calculateRecords(req, priceToWithCategory, page);
        }
        if (priceTo == 0 && priceFrom != 0 && color == null && category != 0) {
            int finalPriceFrom = priceFrom;
            int finalCategory = category;
            List<Product> priceFromWithCategory = products.stream()
                    .filter(a -> a.getPrice() >= finalPriceFrom)
                    .filter(a -> a.getCategory_id() == finalCategory).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceFromWithCategory);
            productService.calculateRecords(req, priceFromWithCategory, page);
        }
        if (priceTo != 0 && category != 0 && color != null && priceFrom == 0) {
            int finalPriceTo = priceTo;
            int finalCategory = category;
            List<Product> priceToWithColorAndCategory = products.stream()
                    .filter(a -> a.getPrice() <= finalPriceTo)
                    .filter(a -> a.getColor().equals(color))
                    .filter(a -> a.getCategory_id() == finalCategory).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceToWithColorAndCategory);
            productService.calculateRecords(req, priceToWithColorAndCategory, page);
        }
        if (priceTo == 0 && category != 0 && color != null && priceFrom != 0) {
            int finalPriceFrom = priceFrom;
            int finalCategory = category;
            List<Product> priceFromWithColorAndCategory = products.stream()
                    .filter(a -> a.getPrice() >= finalPriceFrom)
                    .filter(a -> a.getColor().equals(color))
                    .filter(a -> a.getCategory_id() == finalCategory).collect(Collectors.toList());
            req.getSession().setAttribute("allProducts", priceFromWithColorAndCategory);
            productService.calculateRecords(req, priceFromWithColorAndCategory, page);
        }
        if (user != null && user.getRole_id() == 1) {
            return "admin.jsp";
        }
        return "bikeShop.jsp";
    }
}

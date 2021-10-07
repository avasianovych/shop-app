package com.example.shopapp.command;

import com.example.shopapp.entity.Category;
import com.example.shopapp.entity.Product;
import com.example.shopapp.entity.User;
import com.example.shopapp.exception.CommandException;
import com.example.shopapp.exception.ServiceException;
import com.example.shopapp.service.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code FindAllProductsCommand} class, responsible for finding all products
 * and all product categories.
 *
 * @author Andrii Vasianovych
 * @version 1.0
 */
public class FindAllProductsCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(FindAllProductsCommand.class);
    ProductService productService = ProductServiceImpl.getInstance();
    CategoryService categoryService = CategoryServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int page = 1;
        List<Product> allProducts;
        User user = (User) req.getSession().getAttribute("user");
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        try {
            allProducts = productService.findAll();
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
            throw new CommandException("an error occurred while trying to open page with products");
        }
        productService.calculateRecords(req, allProducts, page);

        List<String> distinctColor = allProducts.stream()
                .map(Product::getColor).distinct().collect(Collectors.toList());

        try {
            List<Category> categoryList = categoryService.findAll();
            req.getSession().setAttribute("categoryList", categoryList);
        } catch (ServiceException e) {
            LOGGER.log(Level.INFO, e);
        }
        req.getSession().setAttribute("allColors", distinctColor);
        req.getSession().setAttribute("allProducts", allProducts);
        req.getSession().removeAttribute("errorModifyProduct");
        req.getSession().removeAttribute("errorAddNewProduct");
        if (user != null && user.getRole_id() == 1) {
            return "admin.jsp";
        }
        return "bikeShop.jsp";
    }
}

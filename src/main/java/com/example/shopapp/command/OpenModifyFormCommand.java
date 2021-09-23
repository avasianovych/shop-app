package com.example.shopapp.command;

import com.example.shopapp.entity.Product;
import com.example.shopapp.exception.CommandException;
import com.example.shopapp.exception.ServiceException;
import com.example.shopapp.service.ProductService;
import com.example.shopapp.service.ProductServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class OpenModifyFormCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(OpenModifyFormCommand.class);
    ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int productId = Integer.parseInt(req.getParameter("id"));
        try {
            Product product = productService.findById(productId);
            req.getSession().setAttribute("product", product);
        }catch (ServiceException e){
            LOGGER.log(Level.ERROR, e);
            throw new CommandException("an error occurred while trying to modify product");
        }
        return "modifyProduct.jsp";
    }
}

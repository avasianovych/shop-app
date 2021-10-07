package com.example.shopapp.command;

import com.example.shopapp.entity.Cart;
import com.example.shopapp.exception.CommandException;
import com.example.shopapp.exception.ServiceException;
import com.example.shopapp.service.CartService;
import com.example.shopapp.service.CartServiceImpl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code QuantityIncDecCommand} class, responsible for increasing or decreasing
 * product quantity into cart.
 *
 * @author Andrii Vasianovych
 * @version 1.0
 */
public class QuantityIncDecCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(QuantityIncDecCommand.class);
    CartService cartService = CartServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int totalPrice;
        int id = Integer.parseInt(req.getParameter("id"));
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        @SuppressWarnings("unchecked")
        ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("fillCart");
        if (action.equals("inc")) {
            for (Cart item : cart_list) {
                if (item.getId() == id) {
                    item.setQuantity(item.getQuantity() + 1);
                }
            }
        } else if (action.equals("dec")) {
            for (Cart item : cart_list) {
                if (item.getId() == id && item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                }
            }
        }
        try {
            List<Cart> cartListWithNewPrice = cartService.findCartProducts(cart_list);
            totalPrice = cartService.getTotalPrice(cartListWithNewPrice);
            session.setAttribute("fillCart", cartListWithNewPrice);
            session.setAttribute("totalPrice", totalPrice);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
            throw new CommandException("an error occurred while trying to inc or dec quantity");
        }
        return "cart.jsp";
    }
}

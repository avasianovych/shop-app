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
 * The {@code AddToCartCommand} class, responsible for adding product to the cart.
 *
 * @author Andrii Vasianovych
 * @version 1.0
 */
public class AddToCartCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(AddToCartCommand.class);
    CartService cartService = CartServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        List<Cart> newCartList = new ArrayList<>();
        int totalPrice;
        int id = Integer.parseInt(req.getParameter("id"));
        Cart cart = new Cart();
        cart.setId(id);
        cart.setQuantity(1);
        HttpSession session = req.getSession();

        @SuppressWarnings("unchecked")
        ArrayList<Cart> existCartList = (ArrayList<Cart>) session.getAttribute("fillCart");
        if (existCartList != null) {
            newCartList = existCartList;
            for (Cart c : existCartList) {
                if (c.getId() == id) {
                    session.setAttribute("cart-list", newCartList);
                    return "cart.jsp";
                }
            }
        }
        totalPrice = fillCartAndGetTotalPrice(newCartList, cart, session);
        session.setAttribute("totalPrice", totalPrice);
        return "bikeShop.jsp";
    }

    private int fillCartAndGetTotalPrice(List<Cart> newCartList, Cart cart, HttpSession session) throws CommandException {
        int totalPrice;
        newCartList.add(cart);
        List<Cart> fillCart;
        try {
            fillCart = cartService.findCartProducts(newCartList);
        }catch (ServiceException e){
            LOGGER.log(Level.ERROR, e);
            throw new CommandException("an error happened while trying to add product to the cart");
        }
        totalPrice = cartService.getTotalPrice(fillCart);
        session.setAttribute("cart-list", newCartList);
        session.setAttribute("fillCart", fillCart);
        return totalPrice;
    }
}

package com.example.shopapp.command;

import com.example.shopapp.entity.Cart;
import com.example.shopapp.service.CartService;
import com.example.shopapp.service.CartServiceImpl;
import com.example.shopapp.service.ProductService;
import com.example.shopapp.service.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class QuantityIncDecCommand implements ICommand {

    CartService cartService = CartServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
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
        List<Cart> cartListWithNewPrice = cartService.findCartProducts(cart_list);
        totalPrice = cartService.getTotalPrice(cartListWithNewPrice);
        session.setAttribute("fillCart", cartListWithNewPrice);
        session.setAttribute("totalPrice", totalPrice);

        return "cart.jsp";
    }
}

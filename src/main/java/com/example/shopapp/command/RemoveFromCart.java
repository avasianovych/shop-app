package com.example.shopapp.command;

import com.example.shopapp.entity.Cart;

import com.example.shopapp.service.CartService;
import com.example.shopapp.service.CartServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


public class RemoveFromCart implements ICommand {
    CartService cartService = CartServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int totalPrice;
        int id = Integer.parseInt(req.getParameter("id"));

        @SuppressWarnings("unchecked")
        ArrayList<Cart> existCartList = (ArrayList<Cart>) req.getSession().getAttribute("fillCart");
        if (existCartList != null) {
            existCartList.removeIf(a -> a.getId() == id);
            req.getSession().setAttribute("fillCart", existCartList);
            totalPrice = cartService.getTotalPrice(existCartList);
            req.getSession().setAttribute("totalPrice", totalPrice);
        }
        if(existCartList != null && existCartList.size() == 0){
            req.getSession().removeAttribute("fillCart");
        }
        return "cart.jsp";
    }
}

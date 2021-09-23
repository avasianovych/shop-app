package com.example.shopapp.command;

import com.example.shopapp.entity.Cart;
import com.example.shopapp.entity.User;
import com.example.shopapp.service.CartService;
import com.example.shopapp.service.CartServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveFromCart implements ICommand {
    CartService cartService = CartServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int totalPrice;
List<Cart> newCartList;
        int id = Integer.parseInt(req.getParameter("id"));
//        @SuppressWarnings("unchecked")
//        List<User> listUser = (List<User>) req.getSession().getAttribute("user");
        @SuppressWarnings("unchecked")
        ArrayList<Cart> existCartList = (ArrayList<Cart>) req.getSession().getAttribute("fillCart");
        if (existCartList != null) {
            existCartList.removeIf(a -> a.getId() == id);
        }

//           existCartList.removeIf(item -> item.getId() == id);

            totalPrice = cartService.getTotalPrice(existCartList);
            req.getSession().setAttribute("fillCart", existCartList);
            req.getSession().setAttribute("totalPrice", totalPrice);

        return "cart.jsp";
    }
}

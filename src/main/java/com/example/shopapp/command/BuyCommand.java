package com.example.shopapp.command;

import com.example.shopapp.entity.Cart;
import com.example.shopapp.entity.Order;
import com.example.shopapp.entity.User;
import com.example.shopapp.service.OrderService;
import com.example.shopapp.service.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BuyCommand implements ICommand {
    OrderService orderService = OrderServiceImpl.getInstance();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
//        @SuppressWarnings("unchecked")
//        List<User> user = (List<User>) req.getSession().getAttribute("user");
        User user = (User) req.getSession().getAttribute("user");
        @SuppressWarnings("unchecked")
        List<Cart> existCartList = (List<Cart>) req.getSession().getAttribute("fillCart");
        int totalPrice = (int) req.getSession().getAttribute("totalPrice");
        if (user == null) {
            return "login.jsp";
        }
        orderService.registerOrder(user, existCartList, totalPrice);
        List<Order> orderList = orderService.getUserOrders(user);
        req.getSession().setAttribute("orderList", orderList);
        req.getSession().removeAttribute("fillCart");
        req.getSession().removeAttribute("totalPrice");
        return "account.jsp";
    }
}

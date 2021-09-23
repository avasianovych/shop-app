package com.example.shopapp.command;

import com.example.shopapp.entity.OrderItems;
import com.example.shopapp.entity.User;
import com.example.shopapp.service.OrderItemsService;
import com.example.shopapp.service.OrderItemsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetOrderItemsCommand implements ICommand {
    OrderItemsService orderItemsService = OrderItemsServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        int orderId = Integer.parseInt(req.getParameter("id"));
//        @SuppressWarnings("unchecked")
//        List<User> user = (List<User>) session.getAttribute("user");
        User user = (User) session.getAttribute("user");
        if(user != null){
            List<OrderItems> orderItemsList = orderItemsService.getOrderItemsByOrderId(orderId);
            session.setAttribute("orderItemsList", orderItemsList);
            session.setAttribute("orderId", orderId);
        }
        return "orderItems.jsp";
    }
}

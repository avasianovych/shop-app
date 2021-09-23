package com.example.shopapp.command;

import com.example.shopapp.entity.Order;
import com.example.shopapp.service.OrderService;
import com.example.shopapp.service.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ChangeOrderStateCommand implements ICommand {
    OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int orderId = Integer.parseInt(req.getParameter("id"));
        String futureState = req.getParameter("state");
        String currentState = orderService.findOrderStateById(orderId);
        if (currentState != null && (!currentState.equals("cancelled "))) {
            if (futureState.equals("paid")) {
                orderService.setPaidState(orderId, futureState);
            } else if (futureState.equals("cancelled")) {
                orderService.setCancelledState(orderId, futureState);
            }
            List<Order> allOrdersList = orderService.findAll();
            req.getSession().setAttribute("allOrdersList", allOrdersList);
        }
        return "allOrders.jsp";
    }
}

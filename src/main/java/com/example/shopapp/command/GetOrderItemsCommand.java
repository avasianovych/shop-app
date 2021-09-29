package com.example.shopapp.command;

import com.example.shopapp.entity.OrderItems;
import com.example.shopapp.entity.User;
import com.example.shopapp.exception.CommandException;
import com.example.shopapp.exception.ServiceException;
import com.example.shopapp.service.OrderItemsService;
import com.example.shopapp.service.OrderItemsServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetOrderItemsCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(GetOrderItemsCommand.class);
    OrderItemsService orderItemsService = OrderItemsServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        int orderId = Integer.parseInt(req.getParameter("id"));
        User user = (User) session.getAttribute("user");
        if (user != null) {
            try {
                List<OrderItems> orderItemsList = orderItemsService.getOrderItemsByOrderId(orderId);
                session.setAttribute("orderItemsList", orderItemsList);
                session.setAttribute("orderId", orderId);
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
                throw new CommandException("an error occurred while trying to open order items page");
            }
        }
        return "orderItems.jsp";
    }
}

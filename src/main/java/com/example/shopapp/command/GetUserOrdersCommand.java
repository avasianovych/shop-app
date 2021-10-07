package com.example.shopapp.command;

import com.example.shopapp.entity.Order;
import com.example.shopapp.entity.User;
import com.example.shopapp.exception.CommandException;
import com.example.shopapp.exception.ServiceException;
import com.example.shopapp.service.OrderService;
import com.example.shopapp.service.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The {@code GetUserOrdersCommand} class, responsible for finding all orders
 * for user.
 *
 * @author Andrii Vasianovych
 * @version 1.0
 */
public class GetUserOrdersCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(GetUserOrdersCommand.class);
    OrderService orderService = OrderServiceImpl.getInstance();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null && user.getRole_id() == 2) {
            try {
                List<Order> orderList = orderService.getUserOrders(user);
                req.getSession().setAttribute("orderList", orderList);
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
                throw new CommandException("an error occurred while trying to open your account");
            }
        }
        return "account.jsp";
    }
}
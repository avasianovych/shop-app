package com.example.shopapp.command;

import com.example.shopapp.entity.Order;
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
 * The {@code GetAllOrdersCommand} class, responsible for finding all orders
 * for admin role.
 *
 * @author Andrii Vasianovych
 * @version 1.0
 */
public class GetAllOrdersCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(GetAllOrdersCommand.class);
    OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int page = 1;
        List<Order> allOrdersList;
        String role = (String) req.getSession().getAttribute("role");
        if (role != null && role.equals("admin")) {
            try {
                allOrdersList = orderService.findAll();
                req.getSession().setAttribute("allOrdersList", allOrdersList);
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
                throw new CommandException("an error occurred while trying to show all orders");
            }
            orderService.calculateRecords(req, page, allOrdersList);
            req.getSession().removeAttribute("errorModifyProduct");
            req.getSession().removeAttribute("errorAddNewProduct");
        }
        return "allOrders.jsp";
    }
}

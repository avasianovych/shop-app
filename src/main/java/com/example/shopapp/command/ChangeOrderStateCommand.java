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

public class ChangeOrderStateCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(ChangeOrderStateCommand.class);
    OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int orderId = Integer.parseInt(req.getParameter("id"));
        String currentState;
        String futureState = req.getParameter("state");
        int page = 1;
        List<Order> allOrdersList = null;
        try {
            currentState = orderService.findOrderStateById(orderId);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
            throw new CommandException("an error occurred while trying to change order state", e);
        }
        if (currentState != null && (!currentState.equals("cancelled "))) {
            if (futureState.equals("paid")) {
                try {
                    orderService.setPaidState(orderId, futureState);
                } catch (ServiceException e) {
                    LOGGER.log(Level.ERROR, e);
                    throw new CommandException("an error occurred while trying to set paid state");
                }
            } else if (futureState.equals("cancelled")) {
                try {
                    orderService.setCancelledState(orderId, futureState);
                } catch (ServiceException e) {
                    LOGGER.log(Level.ERROR, e);
                    throw new CommandException("an error occurred while trying to set cancelled state");
                }
            }
            try {
                allOrdersList = orderService.findAll();
                req.getSession().setAttribute("allOrdersList", allOrdersList);
            } catch (ServiceException e) {
                LOGGER.log(Level.INFO, e);
            }
            if (allOrdersList != null) {
                orderService.calculateRecords(req, page, allOrdersList);
            }
        }
        return "allOrders.jsp";
    }
}

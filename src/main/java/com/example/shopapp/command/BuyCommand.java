package com.example.shopapp.command;

import com.example.shopapp.entity.Cart;
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

public class BuyCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(BuyCommand.class);
    OrderService orderService = OrderServiceImpl.getInstance();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
//        @SuppressWarnings("unchecked")
//        List<User> user = (List<User>) req.getSession().getAttribute("user");
        User user = (User) req.getSession().getAttribute("user");
        @SuppressWarnings("unchecked")
        List<Cart> existCartList = (List<Cart>) req.getSession().getAttribute("fillCart");
        int totalPrice = (int) req.getSession().getAttribute("totalPrice");
        if (user == null) {
            return "login.jsp";
        }
        if (existCartList != null && existCartList.size() > 0) {
            try {
                orderService.registerOrder(user, existCartList, totalPrice);
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
                throw new CommandException("an error occurred while trying to register your order");
            }
            try {
                List<Order> orderList = orderService.getUserOrders(user);
                req.getSession().setAttribute("orderList", orderList);
            } catch (ServiceException e) {
                LOGGER.log(Level.INFO, e);
                throw new CommandException("your order has been created, but some error happened and we can't display order page");
            }
            req.getSession().removeAttribute("fillCart");
            req.getSession().removeAttribute("totalPrice");
            return "account.jsp";
        }
        return "cart.jsp";
    }
}

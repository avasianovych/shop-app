package com.example.shopapp.command;

import com.example.shopapp.entity.Category;
import com.example.shopapp.entity.Order;
import com.example.shopapp.entity.User;
import com.example.shopapp.exception.CommandException;
import com.example.shopapp.exception.ServiceException;
import com.example.shopapp.service.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    UserService userService = UserServiceImpl.getInstance();
    RoleService roleService = RoleServiceImpl.getInstance();
    OrderService orderService = OrderServiceImpl.getInstance();
    CategoryService categoryService = CategoryServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String role;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user;
        try{
            user = userService.findByLogin(login);
        }catch (ServiceException e){
            LOGGER.log(Level.ERROR, e);
            throw new CommandException("an error occurred while trying to log you in");
        }
        if (user.getId() == 0) {
            req.getSession().setAttribute("loginError", "1");
            return "login.jsp";
        } else if (user.isBlock()) {
            req.getSession().setAttribute("loginError", "2");
            return "login.jsp";
        } else if (password.equals(user.getPassword())) {
            role = roleService.findByLogin(login);
            req.getSession().setAttribute("role", role);
        } else {
            req.getSession().setAttribute("loginError", "3");
            return "login.jsp";
        }
        req.getSession().setAttribute("user", user);

        if (role != null && role.equals("admin")) {
            List<Category> categoryList = categoryService.findAll();
            req.getSession().setAttribute("categoryList", categoryList);
            List<Order> allOrdersList = orderService.findAll();
            req.getSession().setAttribute("allOrdersList", allOrdersList);
            List<User> allUsersList = userService.findAll();
            req.getSession().setAttribute("allUsersList", allUsersList);
            return "admin.jsp";
        }
        List<Order> orderList = orderService.getUserOrders(user);
        req.getSession().setAttribute("orderList", orderList);
        return "bikeShop.jsp";
    }
}

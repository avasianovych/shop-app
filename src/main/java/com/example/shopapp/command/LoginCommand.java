package com.example.shopapp.command;

import com.example.shopapp.entity.User;
import com.example.shopapp.exception.CommandException;
import com.example.shopapp.exception.ServiceException;
import com.example.shopapp.service.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    UserService userService = UserServiceImpl.getInstance();
    RoleService roleService = RoleServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String role;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = null;
        try {
            user = userService.findByLogin(login);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }
        if (user != null && user.getId() == 0) {
            req.getSession().setAttribute("loginError", "1");
            return "login.jsp";
        } else if (user != null && user.isBlock()) {
            req.getSession().setAttribute("loginError", "2");
            return "login.jsp";
        } else if (user != null && password.equals(user.getPassword())) {
            try {
                role = roleService.findByLogin(login);
                req.getSession().setAttribute("role", role);
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
                throw new CommandException("an error occurred while trying to log you in");
            }
        } else {
            req.getSession().setAttribute("loginError", "3");
            return "login.jsp";
        }
        req.getSession().setAttribute("user", user);

        if (role != null && role.equals("admin"))
            return "admin.jsp";

        return "bikeShop.jsp";
    }
}

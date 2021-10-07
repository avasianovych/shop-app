package com.example.shopapp.command;

import com.example.shopapp.entity.User;
import com.example.shopapp.exception.ServiceException;
import com.example.shopapp.service.UserService;
import com.example.shopapp.service.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code RegistrationCommand} class, responsible for registration new user.
 *
 * @author Andrii Vasianovych
 * @version 1.0
 */
public class RegistrationCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);
    UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = "registration.jsp";
        User user = new User();
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        user.setPassword(password);
        try {
            if (userService.isLoginExist(login)) {
                req.getSession().setAttribute("registrationError", "3");
                return page;
            }
            userService.add(user);
            req.getSession().setAttribute("registrationError", "4");
        } catch (ServiceException e) {
            LOGGER.log(Level.INFO, "an error occurred during registration", e);
            req.getSession().setAttribute("registrationError", e.getMessage());
        }
        return page;
    }
}

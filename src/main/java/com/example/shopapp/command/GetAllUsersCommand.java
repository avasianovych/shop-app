package com.example.shopapp.command;

import com.example.shopapp.entity.User;
import com.example.shopapp.exception.CommandException;
import com.example.shopapp.exception.ServiceException;
import com.example.shopapp.service.UserService;
import com.example.shopapp.service.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAllUsersCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(GetAllUsersCommand.class);
    UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String role = (String) req.getSession().getAttribute("role");
        if (role != null && role.equals("admin")) {
            try {
                List<User> allUsersList = userService.findAll();
                req.getSession().setAttribute("allUsersList", allUsersList);
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
                throw new CommandException("an error occurred while trying to show all users");
            }
        }
        req.getSession().removeAttribute("errorModifyProduct");
        req.getSession().removeAttribute("errorAddNewProduct");
        return "allUsers.jsp";
    }
}

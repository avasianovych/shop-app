package com.example.shopapp.command;

import com.example.shopapp.entity.User;
import com.example.shopapp.service.UserService;
import com.example.shopapp.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BlockUnblockUserCommand implements ICommand {
    UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int userId = Integer.parseInt(req.getParameter("id"));
        String action = req.getParameter("action");
        userService.update(userId, action);
        List<User> allUsersList = userService.findAll();
        req.getSession().setAttribute("allUsersList", allUsersList);
        return "allUsers.jsp";
    }
}

package com.example.shopapp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The {@code LogoutCommand} class, responsible for logout user from the system.
 *
 * @author Andrii Vasianovych
 * @version 1.0
 */
public class LogoutCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        session.invalidate();
        return "index.jsp";
    }
}

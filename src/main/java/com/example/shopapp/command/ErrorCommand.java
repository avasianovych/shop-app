package com.example.shopapp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code ErrorCommand} class, represent path to error page.
 *
 * @author Andrii Vasianovych
 * @version 1.0
 */
public class ErrorCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "error500.jsp";
    }
}

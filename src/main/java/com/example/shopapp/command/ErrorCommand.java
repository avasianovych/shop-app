package com.example.shopapp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "error500.jsp";
    }
}

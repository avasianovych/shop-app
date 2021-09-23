package com.example.shopapp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ICommand{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        session.removeAttribute("fillCart");
        session.removeAttribute("totalPrice");
        session.removeAttribute("role");
        return "bikeShop.jsp";
    }
}

package com.example.shopapp;

import com.example.shopapp.command.CommandFactory;
import com.example.shopapp.command.ICommand;

import com.example.shopapp.exception.CommandException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/controller")
public class FrontController extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(FrontController.class);

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String forward = handleRequest(request, response);
        request.getRequestDispatcher(forward).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String redirect = handleRequest(request, response);
        response.sendRedirect(redirect);
    }

    public void destroy() {
    }

    private String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        ICommand iCommand = CommandFactory.getCommand(req);
        try {
            return iCommand.execute(req, resp);
        } catch (CommandException e) {
            LOGGER.log(Level.ERROR, e);
            req.getSession().setAttribute("error500", e.getMessage());
            return "error500.jsp";
        }
    }
}
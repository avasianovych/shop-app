package com.example.shopapp.filter;

import com.example.shopapp.util.RoleAccess;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import java.util.Set;

@WebFilter(urlPatterns = {"/*"})
public class RoleFilter extends HttpFilter {
    private static final Logger LOGGER = LogManager.getLogger(RoleFilter.class);

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String commandName = httpRequest.getParameter("command");
        if (commandName != null) {
            Set<String> commands;
            String roleName = (String) httpRequest.getSession().getAttribute("role");
            if (roleName != null && roleName.equals("user")) {
                commands = RoleAccess.getUserCommands();
            } else if (roleName != null && roleName.equals("admin")) {
                commands = RoleAccess.getAdminCommands();
            } else {
                commands = RoleAccess.getGuestCommands();
            }

            if (commands != null && !(commands.contains(commandName))) {
                LOGGER.log(Level.ERROR, "Role " + roleName + " has no access to command " + commandName);
                RequestDispatcher dispatcher = ((HttpServletRequest) request).getSession().getServletContext().getRequestDispatcher("/error403.jsp");
                dispatcher.forward(request, response);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}

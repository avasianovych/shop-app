package com.example.shopapp.filter;

import com.example.shopapp.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(urlPatterns = { "/admin.jsp","/addProduct.jsp","/allOrders.jsp","/allUsers.jsp","/modifyProduct.jsp"})
public class AuthAdminFilter extends HttpFilter {

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        String role = (String) session.getAttribute("role");
        if (nonNull(role) && role.equals("admin"))
            filterChain.doFilter(request, response);
        else {
            RequestDispatcher dispatcher = ((HttpServletRequest) request).getSession().getServletContext().getRequestDispatcher("/error404.jsp"); // вызов страницы ответа на запрос
            dispatcher.forward(request, response);
        }
    }
}

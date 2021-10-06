package com.example.shopapp.command;

import com.example.shopapp.entity.Order;
import com.example.shopapp.entity.Product;
import com.example.shopapp.entity.User;
import com.example.shopapp.service.OrderService;
import com.example.shopapp.service.OrderServiceImpl;
import com.example.shopapp.service.ProductService;
import com.example.shopapp.service.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PaginationCommand implements ICommand {
    ProductService productService = ProductServiceImpl.getInstance();
    OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int page = 1;
        User user = (User) req.getSession().getAttribute("user");
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        String action = req.getParameter("action");
        if (user != null && user.getRole_id() == 1 && action.equals("orders")) {
            @SuppressWarnings("unchecked")
            List<Order> allOrdersList = (List<Order>) req.getSession().getAttribute("allOrdersList");
            orderService.calculateRecords(req, page, allOrdersList);
            return "allOrders.jsp";
        } else {
            @SuppressWarnings("unchecked")
            List<Product> allProducts = (List<Product>) req.getSession().getAttribute("allProducts");
            productService.calculateRecords(req, allProducts, page);
        }
        if (user != null && user.getRole_id() == 1)
            return "admin.jsp";
        return "bikeShop.jsp";
    }
}

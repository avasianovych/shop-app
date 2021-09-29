package com.example.shopapp.command;

import com.example.shopapp.command.ICommand;
import com.example.shopapp.command.RemoveFromCartCommand;
import com.example.shopapp.entity.Cart;
import com.example.shopapp.exception.CommandException;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class RemoveFromCartCommandCommandTest {

    HttpServletRequest request;
    HttpServletResponse response;
    HttpSession session;
    ICommand removeFromCart;
    List<Cart> cartList;
    Cart cart1;
    Cart cart2;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        removeFromCart = new RemoveFromCartCommand();
        when(request.getParameter("id")).thenReturn("1");
        cartList = new ArrayList<>();
        cart1 = new Cart();
        cart1.setId(1);
        cart1.setPrice(10);
        cart2 = new Cart();
        cart2.setId(2);
        cart2.setPrice(50);
        cartList.add(cart1);
        cartList.add(cart2);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("fillCart")).thenReturn(cartList);
    }

    @Test
    public void shouldReturn1ProductInTheCart() throws CommandException {
        removeFromCart.execute(request, response);
        List<Cart> newCartList = new ArrayList<>();
        newCartList.add(cart2);
        verify(session).setAttribute("fillCart", newCartList);
        verify(session).setAttribute("totalPrice", 50);
    }
}

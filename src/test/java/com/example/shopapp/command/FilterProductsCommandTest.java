package com.example.shopapp.command;

import com.example.shopapp.command.FilterProductsCommand;
import com.example.shopapp.command.ICommand;
import com.example.shopapp.entity.Product;
import com.example.shopapp.exception.CommandException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class FilterProductsCommandTest {

    HttpServletResponse response;
    HttpServletRequest request;
    HttpSession session;
    ICommand filterProductsCommand;
    List<Product> products;
    Product product1;
    Product product2;
    Product product3;

    @Before
    public void setUp(){
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        products = new ArrayList<>();
        filterProductsCommand = new FilterProductsCommand();
        when(request.getSession()).thenReturn(session);
        product1 = new Product();
        product1.setColor("yellow");
        product1.setCategory_id(1);
        product1.setPrice(5);
        products.add(product1);
        product2 = new Product();
        product2.setColor("yellow");
        product2.setCategory_id(2);
        product2.setPrice(10);
        products.add(product2);
        product3 = new Product();
        product3.setColor("red");
        product3.setCategory_id(3);
        product3.setPrice(15);
        products.add(product3);
        when(request.getSession().getAttribute("allProducts")).thenReturn(products);

    }

    @Test
    public void shouldReturnProductsOnlyWithYellowColor() throws CommandException {
        when(request.getParameter("color")).thenReturn("yellow");
        when(request.getParameter("category")).thenReturn("0");
        when(request.getParameter("priceFrom")).thenReturn("0");
        when(request.getParameter("priceTo")).thenReturn("0");
        List<Product> yellowList = new ArrayList<>();
        yellowList.add(product1);
        yellowList.add(product2);
        filterProductsCommand.execute(request, response);
        verify(session).setAttribute("allProducts", yellowList);
    }

    @Test
    public void shouldReturnProductsWithYellowColorAndCategory1() throws CommandException {
        when(request.getParameter("color")).thenReturn("yellow");
        when(request.getParameter("category")).thenReturn("1");
        when(request.getParameter("priceFrom")).thenReturn("0");
        when(request.getParameter("priceTo")).thenReturn("0");
        List<Product> yellowAndCategory1List = new ArrayList<>();
        yellowAndCategory1List.add(product1);
        filterProductsCommand.execute(request, response);
        verify(session).setAttribute("allProducts", yellowAndCategory1List);
    }

    @Test
    public void shouldReturnProductsWithPriceMoreThan11() throws CommandException {
        when(request.getParameter("category")).thenReturn("0");
        when(request.getParameter("priceFrom")).thenReturn("11");
        when(request.getParameter("priceTo")).thenReturn("0");
        List<Product> priceList = new ArrayList<>();
        priceList.add(product3);
        filterProductsCommand.execute(request, response);
        verify(session).setAttribute("allProducts", priceList);
    }

    @Test
    public void shouldReturnProductsWithPriceTo11() throws CommandException {
        when(request.getParameter("category")).thenReturn("0");
        when(request.getParameter("priceFrom")).thenReturn("0");
        when(request.getParameter("priceTo")).thenReturn("11");
        List<Product> priceToList = new ArrayList<>();
        priceToList.add(product1);
        priceToList.add(product2);
        filterProductsCommand.execute(request, response);
        verify(session).setAttribute("allProducts", priceToList);
    }

    @Test
    public void shouldReturnProductsWithPriceTo6AndYellowColor() throws CommandException {
        when(request.getParameter("color")).thenReturn("yellow");
        when(request.getParameter("category")).thenReturn("0");
        when(request.getParameter("priceFrom")).thenReturn("0");
        when(request.getParameter("priceTo")).thenReturn("6");
        List<Product> ColorAndPriceToList = new ArrayList<>();
        ColorAndPriceToList.add(product1);
        filterProductsCommand.execute(request, response);
        verify(session).setAttribute("allProducts", ColorAndPriceToList);
    }

    @Test
    public void shouldReturnProductsWithCategory3() throws CommandException {
        when(request.getParameter("category")).thenReturn("3");
        when(request.getParameter("priceFrom")).thenReturn("0");
        when(request.getParameter("priceTo")).thenReturn("0");
        List<Product> category3List = new ArrayList<>();
        category3List.add(product3);
        filterProductsCommand.execute(request, response);
        verify(session).setAttribute("allProducts", category3List);
    }

    @Test
    public void shouldReturnProductsWithPriceFrom12To16() throws CommandException {
        when(request.getParameter("category")).thenReturn("0");
        when(request.getParameter("priceFrom")).thenReturn("12");
        when(request.getParameter("priceTo")).thenReturn("16");
        List<Product> priceFromToList = new ArrayList<>();
        priceFromToList.add(product3);
        filterProductsCommand.execute(request, response);
        verify(session).setAttribute("allProducts", priceFromToList);
    }

    @After
    public void after(){
        response = null;
        request = null;
        session = null;
        products = null;
        product1 = null;
        product2 = null;
        product3 = null;
    }
}

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--<html>--%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources"/>
<nav class="navbar navbar-expand-lg navbar-light bg-secondary">
    <div class="container">
        <a class="navbar-brand text-white" href="index.jsp">BikeShop</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="switchLang"/>
            <input type="hidden" name="locale" value="en"/>
            <button class="btn btn-secondary nav-link button-margin"><fmt:message key="header.en"/></button>
        </form>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="switchLang"/>
            <input type="hidden" name="locale" value="ua"/>
            <button class="btn btn-secondary nav-link button-margin"><fmt:message key="header.ua"/></button>
        </form>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link text-white" href="admin.jsp"><fmt:message key="header.products"/></a></li>
                <li class="nav-item"><a class="nav-link text-white" href="/controller?command=getAllUsers"><fmt:message key="header.users"/></a></li>
                <li class="nav-item"><a class="nav-link text-white" href="/controller?command=getAllOrders"><fmt:message key="header.orders"/></a></li>
                <li class="nav-item">
                    <form action="controller" method="post">
                        <input name="command" type="hidden" value="logout">
                        <button type="submit" style="text-align: center" class="nav-link btn btn-secondary text-white"><fmt:message key="header.logout"/></button>
                    </form></li>
            </ul>
        </div>
    </div>
</nav>

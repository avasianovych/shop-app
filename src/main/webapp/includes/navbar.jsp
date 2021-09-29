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
                <li class="nav-item"><a class="nav-link text-white" href="bikeShop.jsp"><fmt:message key="header.main_button"/></a></li>
                <li class="nav-item"><a class="nav-link text-white" href="cart.jsp"><fmt:message key="header.cart"/> <span class="badge badge-danger">${fillCart.size()}</span></a></li>
                <c:if test="${sessionScope.user == null}">
                    <li class="nav-item"><a class="nav-link text-white" href="login.jsp"><fmt:message key="header.login"/></a></li>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <li class="nav-item"><a class="nav-link text-white" href="account.jsp"><fmt:message key="header.account"/></a></li>
                    <li class="nav-item"><a class="nav-link text-white" href="/controller?command=logout" ><fmt:message key="header.logout"/></a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<%--</html>--%>

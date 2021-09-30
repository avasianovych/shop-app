<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <%@include file="/includes/head.jsp"%>
</head>
<body>
<%@include file="/includes/navbar.jsp"%>
<div class="container">
    <h4 style="color:red" class="text-center"><c:choose>
<%--        <c:when test="${loginError eq '1'}">--%>
<%--        <p><fmt:message key="login.error"/></p>--%>
<%--        </c:when>--%>
        <c:when test="${loginError eq '1'}">
        <p><fmt:message key="login.notExist"/></p>
        </c:when>
        <c:when test="${loginError eq '2'}">
            <p><fmt:message key="login.block"/></p>
        </c:when>
        <c:when test="${loginError eq '3'}">
            <p><fmt:message key="login.incorrectPassword"/></p>
        </c:when>
    </c:choose></h4>
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center"><fmt:message key="login.signIn"/></div>
        <div class="card-body">
            <form action="controller" method="post">
                <input name="command" type="hidden" value="login">
                <div class="form-group">
                    <label><fmt:message key="login.login"/></label>
                    <input type="email" name="login" value="" class="form-control">
                </div>
                <div class="form-group">
                    <label><fmt:message key="login.password"/></label>
                    <input type="password" name="password" value="" class="form-control">
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-success"><fmt:message key="bikeShop.submit"/></button>
                </div>
            </form>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item text-primary" href="registration.jsp"><fmt:message key="login.registration"/></a>
        </div>
        </div>
    </div>
</div>
<%@include file="/includes/footer.jsp"%>
</body>
</html>
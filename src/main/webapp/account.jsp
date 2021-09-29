<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My account</title>
    <%@include file="/includes/head.jsp"%>
</head>
<body>
<%@include file="/includes/navbar.jsp"%>
<div class="container my-3 w-80">
    <div class="d-flex py-3"><h3>My orders</h3></div>

    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col">Order №</th>
            <th scope="col">Total price</th>
            <th scope="col">State</th>
            <th scope="col">Creation date</th>
        </tr>
        </thead>
        <tbody>
<c:forEach var="order" items="${orderList}">
        <tr>
            <td><a class="nav-link" href="/controller?command=getOrderItems&id=${order.id}">${order.id}</a></td>
            <td>$${order.totalPrice}</td>
            <td>${order.getState().getName()}</td>
            <td>${order.timestamp}</td>

        </tr>
</c:forEach>
        </tbody>
    </table>
</div>
<%@include file="/includes/footer.jsp"%>
</body>
</html>

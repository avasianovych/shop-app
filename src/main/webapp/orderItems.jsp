<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
  <title>My order items</title>
  <%@include file="/includes/head.jsp"%>
</head>
<body>
<%@include file="/includes/navbar.jsp"%>
<div class="container my-3">
  <div class="d-flex py-3"><h3><fmt:message key="orderItems.order"/>${orderId}</h3></div>

  <table class="table table-light">
    <thead>
    <tr>
      <th scope="col"><fmt:message key="orderItems.name"/></th>
      <th scope="col"><fmt:message key="orderItems.price"/></th>
      <th scope="col"><fmt:message key="orderItems.color"/></th>
      <th scope="col"><fmt:message key="orderItems.madeIn"/></th>
      <th scope="col"><fmt:message key="orderItems.description"/></th>
      <th scope="col"><fmt:message key="orderItems.quantity"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="orderItems" items="${orderItemsList}">
      <tr>
        <td>${orderItems.getProduct().getName()}</td>
        <td>$${orderItems.getProduct().getPrice()}</td>
        <td>${orderItems.getProduct().getColor()}</td>
        <td>${orderItems.getProduct().getMadeIn()}</td>
        <td>${orderItems.getProduct().getDescription()}</td>
        <td>${orderItems.quantity}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
<%@include file="/includes/footer.jsp"%>
</body>
</html>

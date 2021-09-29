<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>All orders</title>
  <%@include file="/includes/head.jsp"%>
</head>
<body>
<%@include file="/includes/navbarForAdmin.jsp"%>
<div class="container my-3 w-80">
  <div class="d-flex py-3"><h3>All orders</h3></div>

  <table class="table table-light">
    <thead>
    <tr>
      <th scope="col">Order №</th>
      <th scope="col">Total price</th>
      <th scope="col">State</th>
      <th scope="col">Login</th>
      <th scope="col">Creation date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${currentPageRecordsOrders}">
      <tr>
        <td>${order.id}</td>
        <td>$${order.totalPrice}</td>
        <td>${order.getState().getName()}</td>
        <td>${order.getUser().getLogin()}</td>
        <td>${order.timestamp}</td>
        <td>
          <div class="mt-3 d-flex justify-content-between">
          <form action="controller" method="post">
          <input name="command" type="hidden" value="changeOrderState">
          <input name="state" type="hidden" value="paid">
          <input name="id" type="hidden" value="${order.id}">
          <button type="submit" class="btn btn-primary">Paid</button>
        </form>
            <form action="controller" method="post">
              <input name="command" type="hidden" value="changeOrderState">
              <input name="state" type="hidden" value="cancelled">
              <input name="id" type="hidden" value="${order.id}">
              <button type="submit" class="btn btn-danger">Cancelled</button>
            </form>
          </div>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <table border="1" cellpadding="5" cellspacing="5">
    <tr>
      <c:forEach begin="1" end="${noOfPagesOrders}" var="i">
        <c:choose>
          <c:when test="${currentPageOrders eq i}">
            <td>${i}</td>
          </c:when>
          <c:otherwise>
            <td><a href="/controller?command=pagination&page=${i}&action=orders">${i}</a></td>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </tr>
  </table>
</div>
<%@include file="/includes/footer.jsp"%>
</body>
</html>

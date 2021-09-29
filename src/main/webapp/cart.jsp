<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Cart</title>
    <%@include file="/includes/head.jsp"%>
</head>
<body>
<%@include file="/includes/navbar.jsp"%>

<div class="container my-3">
    <div class="d-flex py-3"><h3>Total Price: $${totalPrice > 0?totalPrice:0}</h3><c:if test="${sessionScope.fillCart != null}"> <form action="controller" method="post"><button type="submit" name="command" value="buy" class="mx-3 btn btn-primary">Buy</button></form></c:if></div>
    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Cancel</th>
        </tr>
        </thead>
        <tbody>
<c:forEach var="cart" items="${fillCart}">
        <tr>
            <td>${cart.name}</td>
            <td>${cart.description}</td>
            <td>$${cart.price}</td>
            <td>
                <form action="controller" method="post">
                    <input name="command" type="hidden" value="quanityIncDec">
                    <input type="hidden" name="id" value="${cart.id}" class="form-input">
                    <div class="form-group d-flex justify-content-between w-50">
                        <button type="submit" class="fas fa-plus-square btn-sm btn-primary" name="action" value="inc"></button>
                        <input type="text" class="form-control w-50" size="1" value="${cart.quantity}" readonly>
                        <button type="submit" class="fas fa-minus-square btn-sm btn-primary" name="action" value="dec"></button>
                    </div>
                </form>
            </td>
            <td>
                <form action="controller" method="post">
                    <input name="command" type="hidden" value="removeFromCart">
                    <input name="id" type="hidden" value="${cart.id}">
                <button type="submit" class="btn btn-sm btn-danger">Remove</button>
                </form>
            </td>
        </tr>
    </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="/includes/footer.jsp"%>
</body>
</html>

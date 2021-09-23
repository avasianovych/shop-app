<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>BikeShop</title>
    <%@include file="/includes/head.jsp"%>
</head>
<body>
<%@include file="/includes/navbarForAdmin.jsp"%>
<div class="container">
<%--    <h4 style="color:red" class="text-center"><c:out value="${errorModifyProduct}" /> </h4>--%>
    <h4 style="color:red" class="text-center"><c:choose>
        <c:when test="${errorModifyProduct eq '1'}">
            <p><fmt:message key="modifyProduct.nameError"/></p>
        </c:when>
        <c:when test="${errorModifyProduct eq '2'}">
            <p><fmt:message key="modifyProduct.descriptionError"/></p>
        </c:when>
        <c:when test="${errorModifyProduct eq '3'}">
            <p><fmt:message key="modifyProduct.colorError"/></p>
        </c:when>
        <c:when test="${errorModifyProduct eq '4'}">
            <p><fmt:message key="modifyProduct.priceError"/></p>
        </c:when>
        <c:when test="${errorModifyProduct eq '5'}">
            <p><fmt:message key="modifyProduct.madeInError"/></p>
        </c:when>
        <c:when test="${errorModifyProduct eq '6'}">
            <p><fmt:message key="modifyProduct.categoryError"/></p>
        </c:when>
        <c:when test="${errorModifyProduct eq '7'}">
            <p><fmt:message key="modifyProduct.success"/></p>
        </c:when>
    </c:choose></h4>
    <div class="card w-50 mx-auto my-3">
        <div class="card-body">
            <form action="controller" method="post">
                <input name="command" type="hidden" value="modifyProduct">
                <input name="id" type="hidden" value="${product.id}">
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" name="name" value="" class="form-control" placeholder="${product.name}">
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <input type="text" name="description" value="" class="form-control" placeholder="${product.description}">
                </div>
                <div class="form-group">
                    <label>Color</label>
                    <input type="text" name="color" value="" class="form-control" placeholder="${product.color}">
                </div>
                <div class="form-group">
                    <label>Price</label>
                    <input type="text" name="price" value="" class="form-control" placeholder="${product.price}">
                </div>
                <div class="form-group">
                    <label>Made in</label>
                    <input type="text" name="madeIn" value="" class="form-control" placeholder="${product.madeIn}">
                </div>
                <div class="form-group">
                    <label>Category</label>
                    <select class="custom-select" name="category">
                        <option selected disabled>Choose category</option>
                    <c:forEach var="category" items="${categoryList}">
                        <option>${category.name}</option>
                    </c:forEach>
                    </select>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>



<%@include file="/includes/footer.jsp"%>
</body>
</html>

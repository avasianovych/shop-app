<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Add product</title>
    <%@include file="/includes/head.jsp"%>
</head>
<body>
<%@include file="/includes/navbarForAdmin.jsp"%>
<div class="container">
<%--    <h4 style="color:red" class="text-center"><c:out value="${errorAddNewProduct}" /> </h4>--%>
    <h4 style="color:red" class="text-center"><c:choose>
    <c:when test="${errorAddNewProduct eq '1'}">
        <p><fmt:message key="newProduct.nameError"/></p>
    </c:when>
        <c:when test="${errorAddNewProduct eq '2'}">
            <p><fmt:message key="newProduct.descriptionError"/></p>
        </c:when>
        <c:when test="${errorAddNewProduct eq '3'}">
            <p><fmt:message key="newProduct.colorError"/></p>
        </c:when>
        <c:when test="${errorAddNewProduct eq '4'}">
            <p><fmt:message key="newProduct.priceError"/></p>
        </c:when>
        <c:when test="${errorAddNewProduct eq '5'}">
            <p><fmt:message key="newProduct.madeInError"/></p>
        </c:when>
        <c:when test="${errorAddNewProduct eq '6'}">
            <p><fmt:message key="newProduct.categoryError"/></p>
        </c:when>
        <c:when test="${errorAddNewProduct eq '7'}">
            <p><fmt:message key="newProduct.success"/></p>
        </c:when>
    </c:choose></h4>
    <div class="card w-50 mx-auto my-3">
        <div class="card-body">
            <form action="controller" method="post">
                <input name="command" type="hidden" value="addNewProduct">
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" name="name" value="" class="form-control" placeholder="name">
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <input type="text" name="description" value="" class="form-control" placeholder="description">
                </div>
                <div class="form-group">
                    <label>Color</label>
                    <input type="text" name="color" value="" class="form-control" placeholder="color">
                </div>
                <div class="form-group">
                    <label>Price</label>
                    <input type="text" name="price" value="" class="form-control"  placeholder="price">
                </div>
                <div class="form-group">
                    <label>Made in</label>
                    <input type="text" name="madeIn" value="" class="form-control" placeholder="made in">
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

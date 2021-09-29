<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin</title>
    <%@include file="/includes/head.jsp"%>
    <style>
        .container{
            float: left;
        }
        .container1{
            float: right;
            margin-right: 100px;
            margin-top: 70px;
        }
    </style>
</head>
<body>
<%@include file="/includes/navbarForAdmin.jsp"%>
<div class="container1">
    <h5>Sort by:</h5>
    <form action="controller" method="get">
        <input name="command" type="hidden" value="sortProducts">
        <select name="sortBy" class="form-select">
            <option value="nameAZ">Name: A-Z</option>
            <option value="nameZA">Name: Z-A</option>
            <option value="priceL2H">Price: Low to High</option>
            <option value="priceH2L">Price: High to Low</option>
            <option value="NewOld">Newest to Oldest</option>
        </select>
        <button type="submit" style="text-align: center" class="btn btn-warning">Submit</button>
    </form>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h5>Filter by:</h5>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="filterProducts">
        <select name="color" class="form-select">
            <option selected disabled>Color</option>
            <c:forEach var="color" items="${allColors}">
                <option>${color}</option>
            </c:forEach>
        </select>
        <br>
        <br>
        <select name="category" class="form-select">
            <option selected disabled>Category</option>
            <c:forEach var="category" items="${categoryList}">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>
        <br>
        <br>
        <h6>Price:</h6>
        <div style="display: inline-block">
            <p>from <input type="text" value="" name="priceFrom" size="2"> to <input type="text" value="" name="priceTo" size="2"></p>
        </div>
        <br>
        <button type="submit" style="text-align: center" class="btn btn-success">Submit</button>
    </form>
</div>
<div class="container">
    <a class="mx-3 btn btn-success" href="addProduct.jsp">Add new product</a>
    <div class="row">
        <c:forEach var="products" items="${currentPageRecords}">
            <div class="col-md-3 my-3">
                <div class="card border-primary w-80">
                    <div class="card-body">
                        <h5 class="card-title" style="text-align: center">Title: ${products.name}</h5>
                        <h6 class="price">Price: $${products.price}</h6>
                        <h6 class="category">Description: ${products.description}</h6>
                        <h6 class="color">Color: ${products.color}</h6>
                        <h6 class="madeIn">Made in: ${products.madeIn}</h6>
                        <div class="mt-3 d-flex justify-content-between">
                            <form action="controller" method="get">
                                <input name="command" type="hidden" value="openModifyForm">
                                <input name="id" type="hidden" value="${products.id}">
                                <button type="submit" class="btn btn-primary">Modify</button>
                            </form>
                            <form action="controller" method="post">
                                <input name="command" type="hidden" value="deleteProduct">
                                <input name="id" type="hidden" value="${products.id}">
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="/controller?command=pagination&page=${i}&action=products">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
</div>
<%@include file="/includes/footer.jsp"%>
</body>
</html>
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
            margin-right: 80px;
            margin-top: 70px;
        }
    </style>
</head>
<body>
<%@include file="/includes/navbarForAdmin.jsp"%>
<div class="container1">
    <h5><fmt:message key="bikeShop.sortBy"/>:</h5>
    <form action="controller" method="get">
        <input name="command" type="hidden" value="sortProducts">
        <select name="sortBy" class="form-select">
            <option value="nameAZ"><fmt:message key="sort.nameAz"/></option>
            <option value="nameZA"><fmt:message key="sort.nameZa"/></option>
            <option value="priceL2H"><fmt:message key="sort.priceL2H"/></option>
            <option value="priceH2L"><fmt:message key="sort.priceH2L"/></option>
            <option value="NewOld"><fmt:message key="sort.newest2oldest"/></option>
        </select>
        <button type="submit" style="text-align: center" class="btn btn-warning"><fmt:message key="bikeShop.submit"/></button>
    </form>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h5><fmt:message key="bikeShop.filterBy"/>:</h5>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="filterProducts">
        <select name="color" class="form-select">
            <option selected disabled><fmt:message key="bikeShop.color"/></option>
            <c:forEach var="color" items="${allColors}">
                <option>${color}</option>
            </c:forEach>
        </select>
        <br>
        <br>
        <select name="category" class="form-select">
            <option selected disabled><fmt:message key="filter.category"/></option>
            <c:forEach var="category" items="${categoryList}">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>
        <br>
        <br>
        <h6><fmt:message key="bikeShop.price"/>:</h6>
        <div style="display: inline-block">
            <p><fmt:message key="filter.from"/> <input type="text" value="" name="priceFrom" size="2"> <fmt:message key="filter.to"/> <input type="text" value="" name="priceTo" size="2"></p>
        </div>
        <br>
        <button type="submit" style="text-align: center" class="btn btn-success"><fmt:message key="bikeShop.submit"/></button>
    </form>
</div>
<div class="container">
    <a class="mx-3 btn btn-success" href="addProduct.jsp"><fmt:message key="admin.addNewProduct"/></a>
    <div class="row">
        <c:forEach var="products" items="${currentPageRecords}">
            <div class="col-md-3 my-3">
                <div class="card border-primary w-80">
                    <div class="card-body">
                        <h5 class="card-title" style="text-align: center"><fmt:message key="bikeShop.title"/>: ${products.name}</h5>
                        <h6 class="price"><fmt:message key="bikeShop.price"/>: $${products.price}</h6>
                        <h6 class="category"><fmt:message key="bikeShop.description"/>: ${products.description}</h6>
                        <h6 class="color"><fmt:message key="bikeShop.color"/>: ${products.color}</h6>
                        <h6 class="madeIn"><fmt:message key="bikeShop.madeIn"/>: ${products.madeIn}</h6>
                        <div class="mt-3 d-flex justify-content-between">
                            <form action="controller" method="get">
                                <input name="command" type="hidden" value="openModifyForm">
                                <input name="id" type="hidden" value="${products.id}">
                                <button type="submit" class="btn btn-primary"><fmt:message key="admin.modify"/></button>
                            </form>
                            <form action="controller" method="post">
                                <input name="command" type="hidden" value="deleteProduct">
                                <input name="id" type="hidden" value="${products.id}">
                                <button type="submit" class="btn btn-danger"><fmt:message key="admin.delete"/></button>
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
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <%@include file="/includes/head.jsp"%>
</head>
<body>
<c:if test="${role eq 'user' || role == null}">
    <%@include file="/includes/navbar.jsp"%>
</c:if>
<c:if test="${role eq 'admin'}">
    <%@include file="/includes/navbarForAdmin.jsp"%>
</c:if>
<br/>
<h3 class="text-center">Oops! =( </h3>
<h3 class="text-center">Error: 403 - Forbidden</h3>
<h3 class="text-center">Access to this resource on the server is denied!</h3>
<br/>
<%@include file="/includes/footer.jsp"%>
</body>
</html>

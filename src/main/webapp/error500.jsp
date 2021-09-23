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
<h3 class="text-center">
    <c:out value="${error500}" />
<%--<c:choose>--%>
<%--    <c:when test="${error500 eq '1'}">--%>
<%--        <p><fmt:message key="error500.login"/></p>--%>
<%--    </c:when>--%>
<%--</c:choose>--%>
</h3>
<br/>
<%@include file="/includes/footer.jsp"%>
</body>
</html>

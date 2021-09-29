<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>All users</title>
    <%@include file="/includes/head.jsp"%>
</head>
<body>
<%@include file="/includes/navbarForAdmin.jsp"%>
<div class="container my-3 w-80">
    <div class="d-flex py-3"><h3><fmt:message key="allUsers.allUsers"/></h3></div>

    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="allUsers.name"/></th>
            <th scope="col"><fmt:message key="allUsers.surname"/></th>
            <th scope="col"><fmt:message key="allUsers.login"/></th>
            <th scope="col"><fmt:message key="allUsers.block"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${allUsersList}">
            <tr>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.login}</td>
                <td>${user.isBlock()}</td>
                <td>
                    <div class="mt-3 d-flex justify-content-between">
                        <form action="controller" method="post">
                            <input name="command" type="hidden" value="blockUnblockUser">
                            <input name="action" type="hidden" value="unblock">
                            <input name="id" type="hidden" value="${user.id}">
                            <button type="submit" class="btn btn-primary"><fmt:message key="button.unblock"/></button>
                        </form>
                        <form action="controller" method="post">
                            <input name="command" type="hidden" value="blockUnblockUser">
                            <input name="action" type="hidden" value="block">
                            <input name="id" type="hidden" value="${user.id}">
                            <button type="submit" class="btn btn-danger"><fmt:message key="button.block"/></button>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="/includes/footer.jsp"%>
</body>
</html>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
    <%@include file="/includes/head.jsp"%>
</head>
<body>
<%@include file="/includes/navbarForAdmin.jsp"%>
<div class="container my-3 w-80">
    <div class="d-flex py-3"><h3>All users</h3></div>

    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Surname</th>
            <th scope="col">Login</th>
            <th scope="col">Block</th>
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
                            <button type="submit" class="btn btn-primary">Unblock</button>
                        </form>
                        <form action="controller" method="post">
                            <input name="command" type="hidden" value="blockUnblockUser">
                            <input name="action" type="hidden" value="block">
                            <input name="id" type="hidden" value="${user.id}">
                            <button type="submit" class="btn btn-danger">Block</button>
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


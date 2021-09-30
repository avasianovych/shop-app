<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <%@include file="/includes/head.jsp"%>
</head>
<body>
<%@include file="/includes/navbar.jsp"%>
<div class="mask d-flex align-items-center h-60 gradient-custom-3">
        <div class="container h-60">
<%--            <h4 style="color:red" class="text-center"><c:out value="${registrationError}" /> </h4>--%>
<h4 style="color:red" class="text-center"><c:choose>
    <c:when test="${registrationError eq '1'}">
        <p><fmt:message key="registration.loginError"/></p>
    </c:when>
    <c:when test="${registrationError eq '2'}">
        <p><fmt:message key="registration.passwordError"/></p>
    </c:when>
    <c:when test="${registrationError eq '3'}">
        <p><fmt:message key="registration.loginExists"/></p>
    </c:when>
    <c:when test="${registrationError eq '4'}">
        <p><fmt:message key="registration.success"/></p>
    </c:when>
</c:choose></h4>
            <div class="row d-flex justify-content-center align-items-center h-60">
                <div class="col-10 col-md-8 col-lg-6 col-xl-5">
                    <div class="card" style="border-radius: 10px;">
                        <div class="card-body p-3">
                            <h2 class="text-uppercase text-center mb-2"><fmt:message key="registration.create"/></h2>

                            <form action="controller" method="post">
<input type="hidden" name="command" value="registration">
                                <div class="form-outline mb-2">
                                    <input type="text" name="name" value="" class="form-control form-control-lg" />
                                    <label class="form-label" ><fmt:message key="registration.name"/></label>
                                </div>
                                <div class="form-outline mb-2">
                                    <input type="text" name="surname" value="" class="form-control form-control-lg" />
                                    <label class="form-label" ><fmt:message key="registration.surname"/></label>
                                </div>

                                <div class="form-outline mb-2">
                                    <input type="email" name="login" value="" class="form-control form-control-lg" />
                                    <label class="form-label" ><fmt:message key="registration.login"/></label>
                                </div>

                                <div class="form-outline mb-2">
                                    <input type="password" name="password" value="" class="form-control form-control-lg" />
                                    <label class="form-label" ><fmt:message key="registration.password"/></label>
                                </div>

                                <div class="d-flex justify-content-center">
                                    <button type="submit" class="btn btn-success btn-block btn-lg gradient-custom-4 text-body"><fmt:message key="button.register"/></button>
                                </div>

                                <p class="text-center text-muted mt-2 mb-0"><fmt:message key="registration.link1"/> <a href="login.jsp" class="fw-bold text-body"><u><fmt:message key="registration.link2"/></u></a></p>

                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@include file="/includes/footer.jsp"%>
</body>
</html>

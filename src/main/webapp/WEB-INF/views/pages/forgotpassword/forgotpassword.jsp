<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/bootstrap/css/bootstrap.min.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/admin-lte2/css/AdminLTE.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/bootstrap-validator/css/bootstrapValidator.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/inputmark/css/inputmask.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/waitme/css/waitMe.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/sweetalert2/css/sweetalert2.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/jquery-confirm/css/jquery-confirm.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/commons/fonts.css">

<script src="${pageContext.request.contextPath}/resources/libs/bootstrap/js/jquery-3.2.1.min.js" ></script>
<script src="${pageContext.request.contextPath}/resources/libs/bootstrap/js/bootstrap.min.js" ></script>
<script src="${pageContext.request.contextPath}/resources/libs/admin-lte2/js/app.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/bootstrap-validator/js/bootstrapValidator.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/inputmark/js/jquery.inputmask.bundle.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/waitme/js/waitMe.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/sweetalert2/js/sweetalert2.all.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/jquery-confirm/js/jquery-confirm.min.js"></script>

<title><spring:message code="app.name" /></title>

</head>
<body class="hold-transition">
    <div class="login-box">
        <div class="login-logo">
            <a href=""><b>Spring Boot</b> 1.5</a>
        </div>
        <div class="login-box-body with-body-white">
            <p class="login-box-msg font-kanit-regular">Forgot Password</p>
            <form  id="formForgotPassword" action="login" method="post">
            	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />	
                <div class="form-group has-feedback">
                	<span class="fa fa-user form-control-feedback"></span>
                    <input name="email" type="text" class="form-control" placeholder="Email">
                </div>
                <div class="row">
                    <div class="col-xs-12">
                		<button type="submit" class="btn btn-warning btn-block btn-flat">Send</button>
                    </div>
                </div>
            </form>
            <div class="social-auth-links">
               	<a class="pull-right" href="login">Go To Login</a>
            </div>
            <br>
        </div>
    </div>
<script src="${pageContext.request.contextPath}/resources/scripts/commons/Commons.AjaxManager.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/forgotpassword.js"></script>
<script>
$(document).ready(function() {
	ForgotPasswordModule.init();
});
</script>
</body>
</html>
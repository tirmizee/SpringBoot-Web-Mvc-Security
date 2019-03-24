<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        	<c:if test="${not empty error}">
				<div id="errorMsg"  class="alert alert-danger" align="center">
				<a href="#" class="close" onclick="$('#errorMsg').hide()"> &times;</a>
					${error}
				</div>
				<br>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
            <p class="login-box-msg font-kanit-regular">Force password change first login</p>
            <form  id="formFirstLogin" action="" method="post">
            	<div class="form-group">
				 	<div class="input-group">
						<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span> </span>
						<input name="password" type="password" class="form-control" placeholder="Password" aria-describedby="sizing-addon2">
					</div>
				</div>
				<div class="form-group">
				 	<div class="input-group">
						<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span> </span>
						<input name="confirmPassword" type="password" class="form-control"  placeholder="Confirm Password" aria-describedby="sizing-addon2">
					</div>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-danger btn-block btn-flat" style="width: 100%;font-weight: 800;">Reset Password</button>
			    </div>	
			    <br>
            </form>
        </div>
    </div>
<script src="${pageContext.request.contextPath}/resources/scripts/commons/Commons.AjaxManager.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/pages/PG00_PasswordFirstLogin.js"></script>
<script>
$(document).ready(function(){
	FirstLoginModule.init();
}); 
</script>
</body>
</html>;
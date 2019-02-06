<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/bootstrap/css/bootstrap.min.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/ionicons/css/ionicons.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/admin-lte2/css/AdminLTE.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/admin-lte2/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/validate-form-master/css/formValidation.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/viewer/css/viewer.css">

  <script src="${pageContext.request.contextPath}/resources/libs/bootstrap/js/jquery-3.2.1.min.js" ></script>
  <script src="${pageContext.request.contextPath}/resources/libs/bootstrap/js/bootstrap.min.js" ></script>
  <script src="${pageContext.request.contextPath}/resources/libs/admin-lte2/js/app.js"></script>
  <script src="${pageContext.request.contextPath}/resources/libs/validate-form-master/js/formValidation.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/libs/validate-form-master/js/formValidation.popular.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/libs/validate-form-master/js/framework/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/libs/viewer/js/viewer.js"></script>
<title><spring:message code="app.name" /></title>
</head>
<body class="hold-transition skin-purple sidebar-mini">
	<div class="wrapper">
		<jsp:include page="../layout/header.jsp" />
   		<jsp:include page="../layout/sidebar.jsp" />
   		<div class="content-wrapper">
   			 <section class="content-header">
	   			 <h1>
				     TROO1 <small>main page</small>
				 </h1>
				 <ol class="breadcrumb">
				    <li><a href="#"><i class="fa fa-dashboard"></i> Home ${pageContext.response.locale}</a></li>
				    <li class="active">Dashboard</li>
				 </ol>
   			 </section>
   			 <section class="content">
   			 	<jsp:include page="conponent/main_body.jsp" />
   			 </section>
   		</div>
   		<jsp:include page="../layout/footer.jsp" />
  		<div class="control-sidebar-bg"></div>
	</div>
</body>
</html>
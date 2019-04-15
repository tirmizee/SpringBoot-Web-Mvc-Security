<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/bootstrap/css/bootstrap.min.css" >
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/ionicons/css/ionicons.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/select2/css/select2.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/bootstrap-datepicker/css/bootstrap-datepicker.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/admin-lte2/css/AdminLTE.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/admin-lte2/css/skins/_all-skins.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/validate-form-master/css/formValidation.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/viewer/css/viewer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/flag-icon/css/flag-icon.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Extend-AdminLTE2.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/datatables/css/jquery.dataTables.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/datatables/css/buttons.dataTables.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/datatables/css/colReorder.dataTables.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/datatables/css/responsive.dataTables.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/datatables/css/select.dataTables.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/waitme/css/waitMe.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/commons/fonts.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/sweetalert2/css/sweetalert2.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/jquery-confirm/css/jquery-confirm.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/inputmark/css/inputmask.css">  
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/bootstrap-toggle/css/bootstrap-toggle.min.css">
	
	  
	<script src="${pageContext.request.contextPath}/resources/libs/bootstrap/js/jquery-3.2.1.min.js" ></script>
	<script src="${pageContext.request.contextPath}/resources/libs/bootstrap/js/bootstrap.min.js" ></script>
	<script src="${pageContext.request.contextPath}/resources/libs/select2/js/select2.full.min.js" ></script>
	<script src="${pageContext.request.contextPath}/resources/libs/moment/js/moment.min.js" ></script>
	<script src="${pageContext.request.contextPath}/resources/libs/bootstrap-datepicker/js/bootstrap-datepicker.min.js" ></script>
	<script src="${pageContext.request.contextPath}/resources/libs/admin-lte2/js/app.js"></script>
	<script src="${pageContext.request.contextPath}/resources/libs/validate-form-master/js/formValidation.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/libs/validate-form-master/js/formValidation.popular.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/libs/validate-form-master/js/framework/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/libs/datatables/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/libs/datatables/js/dataTables.buttons.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/libs/datatables/js/dataTables.colReorder.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/libs/datatables/js/dataTables.responsive.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/libs/datatables/js/dataTables.select.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/libs/viewer/js/viewer.js"></script>
	<script src="${pageContext.request.contextPath}/webjars/sockjs-client/sockjs.min.js"></script>
	<script src="${pageContext.request.contextPath}/webjars/stomp-websocket/stomp.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/libs/waitme/js/waitMe.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/libs/sweetalert2/js/sweetalert2.all.js"></script>
	<script src="${pageContext.request.contextPath}/resources/libs/jquery-confirm/js/jquery-confirm.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/libs/inputmark/js/jquery.inputmask.bundle.js"></script>
	<script src="${pageContext.request.contextPath}/resources/libs/bootstrap-toggle/js/bootstrap-toggle.min.js"></script>

  <style type="text/css">
  	.raduis {
	    border-radius: 1em !important;
	}
	table.th {
		border-top: 1px solid black;
		background-color: #f4f4f4;
	}
	.readonly {
		background-color: #eee !important;
	}
	.modal-header-color {
		background-color: #f9f9f9;
	}
  </style>
  
<title><spring:message code="app.name"/></title>
</head> 
<body class="hold-transition skin-purple sidebar-mini">
	<div class="wrapper">
		
		<!-- HEADER -->
		<jsp:include page="../../layout/header.jsp" />
   		
   		<!-- MENU SIDEBAR -->
   		<jsp:include page="../../layout/sidebar.jsp" />
   		
   		<!-- CONTENT-->
   		<div class="content-wrapper">
   		
   			<!-- CONTENT HEADER -->
			<section class="content-header">
				<h1>P003 <small>manage users</small></h1>
				<jsp:include page="../../commons/servertime.jsp" />
			</section>
			
			<!-- CONTENT BODY -->
   			<section class="content">
				<jsp:include page="component/body.jsp" />
   			</section>
   			 
   		</div>
   	
   		<!-- FOOTER -->	
   		<jsp:include page="../../layout/footer.jsp" />
  		<div class="control-sidebar-bg"></div>
	
	</div>
<jsp:include page="component/modal_edit_user.jsp" />
<script src="${pageContext.request.contextPath}/resources/scripts/commons/Commons.AjaxManager.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/P002_manage_user.js"></script>
<script>
$(document).ready(function() {
	ManageUserModule.init();
});
</script>
</body>
</html>
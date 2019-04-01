<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>

  <meta charset="UTF-8">
  <meta name="_csrf" content="${_csrf.token}"/>
  <meta name="_csrf_header" content="${_csrf.headerName}"/>
  <jsp:include page="../../commons/imports.jsp" />
  <script>
  	var sessionid = '${sessionid}';
  </script>
  
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
				<h1>P003 <small>manage sessions page</small></h1>
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
<script src="${pageContext.request.contextPath}/resources/scripts/commons/Commons.AjaxManager.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/P003_manage_session.js"></script>
<script>
$(document).ready(function() {
	ManageSessionModule.init(); 
});
</script>
</body>
</html>
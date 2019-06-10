<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/bootstrap/css/bootstrap.min.css" >
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/ionicons/css/ionicons.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/select2/css/select2.min.css">
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
	  
	<script src="${pageContext.request.contextPath}/resources/libs/bootstrap/js/jquery-3.2.1.min.js" ></script>
	<script src="${pageContext.request.contextPath}/resources/libs/bootstrap/js/bootstrap.min.js" ></script>
	<script src="${pageContext.request.contextPath}/resources/libs/select2/js/select2.full.min.js" ></script>
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
    <script src="${pageContext.request.contextPath}/resources/libs/chart/js/Chart.bundle.js"></script>
    <script src="${pageContext.request.contextPath}/resources/libs/jquery-flot/js/jquery.flot.js"></script>
    <script src="${pageContext.request.contextPath}/resources/libs/jquery-flot/js/jquery.flot.resize.js"></script>
    <script src="${pageContext.request.contextPath}/resources/libs/jquery-flot/js/jquery.flot.pie.js"></script>
    <script src="${pageContext.request.contextPath}/resources/libs/jquery-flot/js/jquery.flot.categories.js"></script>
    <script src="${pageContext.request.contextPath}/resources/libs/chart/js/Chart.bundle.js"></script>
    

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
				<h1>POO1 <small>report page</small></h1>
				<jsp:include page="../../commons/servertime.jsp" />
			</section>
			
			<!-- CONTENT BODY -->
   			<section class="content">
   			
   			
   			
				<div class="row">
					<div class="col-md-6">
						<div class="box box-primary">
						    <div class="box-header with-border">
						        <i class="fa fa-bar-chart-o"></i>
						        <h3 class="box-title">Donut Chart</h3>
						        <div class="box-tools pull-right">
						            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
						            </button>
						        </div>
						    </div>
						    <div class="box-body">
						        <div id="donut-chart" style="height: 260px;"></div>
						    </div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="box box-primary">
						    <div class="box-header with-border">
						        <i class="fa fa-bar-chart-o"></i>
						        <h3 class="box-title">Chart</h3>
						        <div class="box-tools pull-right">
						            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
						            </button>
						        </div>
						    </div>
						    <div class="box-body">
						        <canvas id="chart"></canvas>
						    </div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="box box-primary">
						    <div class="box-header with-border">
						        <i class="fa fa-bar-chart-o"></i>
						        <h3 class="box-title">Line Chart</h3>
						        <div class="box-tools pull-right">
						            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
						            </button>
						        </div>
						    </div>
						    <div class="box-body">
						    	<div class="wrapper"><canvas id="chart-0" style="height: 260px;"></canvas></div>
						    </div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="box box-primary">
						    <div class="box-header with-border">
						        <i class="fa fa-bar-chart-o"></i>
						        <h3 class="box-title">Bar Chart</h3>
						        <div class="box-tools pull-right">
						            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
						            </button>
						        </div>
						    </div>
						    <div class="box-body">
						    	<div class="chart">
			                	 	<canvas id="barChart" style="height:255px"></canvas>
					        	</div>
						    </div>
						</div>
					</div>
				</div>
				
   			</section>
   			 
   		</div>
   	
   		<!-- FOOTER -->	
   		<jsp:include page="../../layout/footer.jsp" />
  		<div class="control-sidebar-bg"></div>
	
	</div>
<script src="${pageContext.request.contextPath}/resources/scripts/commons/Commons.ChartUtils.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/commons/Commons.AjaxManager.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/P001_report.js"></script>
<script>
$(document).ready(function() {
	ReportModule.init();
});
</script>
</body>
</html>
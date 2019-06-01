<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags"          prefix="spring"%>
<security:authentication var="profile" property="principal" />

<style>
	aside.main-sidebar {font-family: kanit-medium}
</style>
<aside class="main-sidebar" style="font-family: kanit-medium;" >
    <section class="sidebar">
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${profile.profileImage}" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${profile.username}</p>
                <a href="">${profile.firstName}  ${profile.lastName}</a>
            </div>
        </div>
        <ul class="sidebar-menu">
            <li class="header">MAIN NAVIGATION</li>
            <li class="menu-main"><a href="${pageContext.request.contextPath}/main"><i class="fa fa-book"></i> <span><spring:message code="app.menu.main"/></span></a></li>
        	<security:authorize access="hasAnyAuthority('P001')">
        	<li class="treeview-report">
			    <a href="#">
					<i class="fa fa-edit"></i> 
					<span><spring:message code="app.menu.report"/></span>
			   		<span class="pull-right-container">
			   			<i class="fa fa-angle-left pull-right"></i>
			   		</span>
			    </a>
				<ul class="treeview-menu" style="display: none;">
					<security:authorize access="hasAnyAuthority('P001')">
				   	<li><a href="${pageContext.request.contextPath}/report/chart"><i class="fa fa-pie-chart"></i><spring:message code="app.menu.report.chart"/></a></li>
				   	<li><a href="${pageContext.request.contextPath}/report/pdf"><i class="fa fa-file-pdf-o"></i><spring:message code="app.menu.report.pdf"/></a></li>
					</security:authorize>
				</ul>
			</li>
			</security:authorize>
			<li class="treeview-file">
			    <a href="#">
					<i class="fa fa-file"></i> 
					<span>Document</span>
			   		<span class="pull-right-container">
			   			<i class="fa fa-angle-left pull-right"></i>
			   		</span>
			    </a>
				<ul class="treeview-menu" style="display: none;">
					<security:authorize access="hasAnyAuthority('P004')">
					<li class="menu-log"><a href="${pageContext.request.contextPath}/log"><i class="fa fa-book"></i> <span><spring:message code="app.menu.viewlog"/></span></a></li>
					</security:authorize>
					<li class="menu-file"><a href="${pageContext.request.contextPath}/file"><i class="fa fa-book"></i> <span>Upload Excel</span></a></li>
				</ul>
			</li>
			
			<security:authorize access="hasAnyAuthority('P002,P003,P005')">
			<li class="treeview-setting">
			    <a href="#">
					<i class="fa fa-cog"></i> 
					<span><spring:message code="app.menu.setting"/></span>
			   		<span class="pull-right-container">
			   			<i class="fa fa-angle-left pull-right"></i>
			   		</span>
			    </a>
				<ul class="treeview-menu" style="display: none;">
					<security:authorize access="hasAnyAuthority('P002')">
				   	<li><a href="${pageContext.request.contextPath}/manageuser"><i class="fa fa-users"></i><spring:message code="app.menu.manageuser"/></a></li>
				   	</security:authorize>
				   	<security:authorize access="hasAnyAuthority('P003')">
				   	<li><a href="${pageContext.request.contextPath}/managesession"><i class="fa fa-users"></i><spring:message code="app.menu.managesession"/></a></li>
				   	</security:authorize>
				    <security:authorize access="hasAnyAuthority('P005')">
				   	<li><a href="${pageContext.request.contextPath}/managerole"><i class="fa fa-users"></i><spring:message code="app.menu.permission"/></a></li>
					</security:authorize>
					<li><a href="${pageContext.request.contextPath}/managerole"><i class="fa fa-users"></i><spring:message code="app.menu.appsetting"/></a></li>
				</ul>
			</li>
			</security:authorize>
        </ul>
    </section>
</aside>
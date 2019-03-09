<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags"          prefix="spring"%>
<security:authentication var="profile" property="principal" />
<aside class="main-sidebar">
    <section class="sidebar">
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${pageContext.request.contextPath}/resources/libs/admin-lte2/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${profile.username}</p>
                <a href="">${profile.firstName}  ${profile.lastName}</a>
            </div>
        </div>
        <ul class="sidebar-menu">
            <li class="header">MAIN NAVIGATION</li>
            <li class="menu-main"><a href="${pageContext.request.contextPath}/main"><i class="fa fa-book"></i> <span>Main</span></a></li>
        	<security:authorize access="hasAnyAuthority('P001')">
        	<li class="treeview-report">
			    <a href="#">
					<i class="fa fa-edit"></i> 
					<span>Report</span>
			   		<span class="pull-right-container">
			   			<i class="fa fa-angle-left pull-right"></i>
			   		</span>
			    </a>
				<ul class="treeview-menu" style="display: none;">
					<security:authorize access="hasAnyAuthority('P001')">
				   	<li><a href="${pageContext.request.contextPath}/report"><i class="fa fa-pie-chart"></i>Chart</a></li>
					</security:authorize>
				</ul>
			</li>
			</security:authorize>
			<security:authorize access="hasAnyAuthority('P002')">
			<li class="treeview-setting">
			    <a href="#">
					<i class="fa fa-cog"></i> 
					<span>Setting</span>
			   		<span class="pull-right-container">
			   			<i class="fa fa-angle-left pull-right"></i>
			   		</span>
			    </a>
				<ul class="treeview-menu" style="display: none;">
					<security:authorize access="hasAnyAuthority('P002')">
				   	<li><a href="${pageContext.request.contextPath}/manageuser"><i class="fa fa-users"></i>Manage Users</a></li>
				   	</security:authorize>
				   	<security:authorize access="hasAnyAuthority('P003')">
				   	<li><a href="${pageContext.request.contextPath}/managesession"><i class="fa fa-users"></i>Manage Sessions</a></li>
				   	</security:authorize>
				</ul>
			</li>
			</security:authorize>
        </ul>
    </section>
</aside>
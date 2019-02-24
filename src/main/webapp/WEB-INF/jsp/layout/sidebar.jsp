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
                <a href="">${profile.fistName}  ${profile.lastName}</a>
            </div>
        </div>
        <ul class="sidebar-menu">
            <li class="header">MAIN NAVIGATION</li>
            <li class="menu-main"><a href="${pageContext.request.contextPath}/main"><i class="fa fa-book"></i> <span>Main</span></a></li>
        	<li class="treeview-report">
			   <a href="#">
					<i class="fa fa-edit"></i> 
					<span>Report</span>
			   		<span class="pull-right-container">
			   			<i class="fa fa-angle-left pull-right"></i>
			   		</span>
			   </a>
			   <ul class="treeview-menu" style="display: none;">
			      <li><a href="${pageContext.request.contextPath}/report"><i class="fa fa-pie-chart"></i>Chart</a></li>
			   </ul>
			</li>
        </ul>
    </section>
</aside>
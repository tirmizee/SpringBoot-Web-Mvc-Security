<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<security:authentication var="profile" property="principal" />
<header class="main-header">
    <a href="" class="logo">
        <span class="logo-mini"><b>T</b>MZ</span>
        <span class="logo-lg"><b>TIRMI</b>ZEE</span>
    </a>
    <nav class="navbar navbar-static-top">
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <li class="dropdown tasks-menu">
                    <a id="aLanguage" href="#" class="dropdown-toggle">
                        <i class="fa fa-language " style="height: 20px;font-size:20px"></i>
                        <span id="spLanguage" class="label label-warning">th</span>
                    </a>
                </li>
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="${pageContext.request.contextPath}/resources/libs/admin-lte2/img/user2-160x160.jpg" class="user-image" alt="User Image">
                        <span class="hidden-xs font-kanit-medium">${profile.username}</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="user-header">
                            <img src="${pageContext.request.contextPath}/resources/libs/admin-lte2/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                            <p>
                                ${profile.fistName}  ${profile.lastName}
                                <small>Member since Nov. 2012</small>
                            </p>
                        </li>
                        <li class="user-footer">
                            <form action="${pageContext.request.contextPath}/logout" method="post">
                                <div class="pull-left">
                                    <a href="${pageContext.request.contextPath}/profile" class="btn btn-default btn-flat">Profile</a>
                                </div>
                                <div class="pull-right">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <button type="submit" class="btn btn-default btn-flat">Sign out</button>
                                </div>
                            </form>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>
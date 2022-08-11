<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Metis Menu Plugin JavaScript -->
<script src="${pageContext.request.contextPath}/bower_components/metisMenu/dist/metisMenu.min.js"></script>

<div class="navbar-default sidebar" role="navigation">
	<div class="sidebar-nav navbar-collapse">
		<ul class="nav" id="side-menu">
			<li>
				<a href="javascript:void(0);" value2="${pageContext.request.contextPath}/redis/stringList/${serverName}/${dbIndex}">
					<i class="fa fa-dashboard fa-fw"></i>Redis管理
				</a>
			</li>
			<li>
				<a href="javascript:void(0);" value3="${pageContext.request.contextPath}/db/manage/index">
					<i class="fa fa-database fa-fw"></i>数据库查询
				</a>
			</li>
		</ul>
	</div>
	<!-- /.sidebar-collapse -->
</div>
<!-- /.navbar-static-side -->


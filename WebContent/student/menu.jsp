<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/student/css/menu.css">
</head>
<body>
	<div id="topmenu">
		<ul>
		<!-- 	<li><a href="">首页</a></li> -->
			<li><a href="javascript:void(0);" >我的班级</a>
				<ul>
					<li><a href="<%=basePath%>student/joinclass.jsp">加入班级</a></li>
					<li><a href="<%=basePath%>student/FindRequest">我的申请</a></li>
					<li><a href="<%=basePath%>student/FindJoinedClass">已加入班级</a></li>
				</ul></li>
			<li><a href="javascript:void(0);">我的留言</a>
			<ul>
			<li><a href="<%=basePath%>student/FindJoinedClass?flag=1">发表留言</a></li>
			<li><a href="<%=basePath%>student/FindMessage">查看留言</a></li>
			</ul>
			</li>
			<li>
			<a href="<%=basePath%>student/FindCourseFile">课件下载</a>
			</li>
			<li><a href="javascript:void(0);">我的信息</a>
				<ul>
					<li><a href="<%=basePath%>login/FindPersonalInfo">个人信息</a></li>
					<li><a href="<%=basePath%>updatepassword.jsp">修改密码</a></li>
				</ul></li>
			<li><a href="<%=basePath%>about.jsp">关于</a></li>
			<li><a href="<%=basePath%>login/LogoutAction">退出</a></li>
		</ul>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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
<title>关于</title>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/student/css/welcome.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/student/css/student.css">
<script type="text/javascript"
	src="<%=basePath%>/teacher/js/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/student/js/studentjs.js"></script>
</head>
<body>
<div id="top">
		<h2>信息交流平台</h2>
	</div>
	<div>
		<s:if test="#session.userrole==1">
			<jsp:include page="/teacher/menu.jsp" flush="false"></jsp:include>
		</s:if>
		<s:else>
			<jsp:include page="/student/menu.jsp" flush="false"></jsp:include>
		</s:else>
	</div>
	<div id="center">
		<fieldset>
		 <p>
		 	本系统主要用于学生和教师之间的信息交流以及教师与学生分享教学资料等信息。
		 学生可以通过本系统反应教学中应该做的改进，与教师留言。教师可以通过本系统对教学进行改进，与学生交流信息。
		 总之，本系统的主要目的就是促进师生之间的交流，提高教学质量。
		 </p>
		</fieldset>
	</div>
	<div id="bottom">
		<label> 版权所有：zenghu 2014 </label>
	</div>
</body>
</html>
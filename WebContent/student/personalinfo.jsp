<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
<title>个人信息</title>
<script type="text/javascript" src="<%=basePath%>/student/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=basePath%>/student/js/studentjs.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/student/css/welcome.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/student/css/student.css">
</head>
<body>
	<div id="top">
		<h2>信息交流平台</h2>
	</div>
	<div>
		<jsp:include page="/student/menu.jsp" flush="false"></jsp:include>
	</div>
	<div id="center">
		<s:set var="user" value="#request.user"></s:set>
		<fieldset>
			<legend style="color: red;font-weight: bold;">个人信息</legend>
			<s:form cssStyle="color:blue;">
				<s:textfield label="学号" value="%{#user.studentId}" readonly="true"></s:textfield>
				<s:textfield label="姓名" value="%{#user['studentName']}"
					readonly="true"></s:textfield>
				<s:textfield label="性别" value="%{#user['studentSex']}"
					readonly="true"></s:textfield>
				<s:date name="%{#user['studentBirthday']}" format="yyyy-MM-dd"
					var="birthday" />
				<s:textfield label="出生日期" value="%{#birthday}" readonly="true"></s:textfield>
				<s:textfield label="所在院系"
					value="%{#user['department']['departmentName']}" readonly="true"></s:textfield>
				<s:textfield label="专业"
					value="%{#user['discipline']['disciplineName']}" readonly="true"></s:textfield>
			</s:form>
		</fieldset>
	</div>
	<div id="bottom">
		<label> 版权所有：zenghu 2014 </label>
	</div>
</body>
</html>
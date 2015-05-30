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
<link rel="stylesheet" type="text/css" href="<%=basePath%>/teacher/css/welcome.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/teacher/css/teacher.css">
<script type="text/javascript" src="<%=basePath%>/teacher/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=basePath%>/teacher/js/teacherjs.js"></script>
</head>
<body>
	<div id="top">
		<h2>信息交流平台</h2>
	</div>
	<jsp:include page="/teacher/menu.jsp"></jsp:include>
	<div id="center">
	<s:set var="user" value="#request.user"></s:set>
		<fieldset>
			<legend style="color: red;font-weight: bold;">个人信息</legend>
			<div>
			<s:form cssStyle="color:blue;">
			<s:textfield label="教师编号" value="%{#user['teacherId']}" readonly="true"></s:textfield>
			<s:textfield label="姓名" value="%{#user['teacherName']}" readonly="true"></s:textfield>
			<s:textfield label="性别" value="%{#user['teacherSex']}" readonly="true"></s:textfield>
			<s:textfield label="职位" value="%{#user['position']}" readonly="true"></s:textfield>
			<s:textfield label="所在院系" value="%{#user['department']['departmentName']}" readonly="true"></s:textfield>
			<s:textarea label="个人描述" value="%{#user['courseDescribe']}" readonly="true" cols="20" rows="6" cssStyle="resize:none;"></s:textarea>	
			</s:form>
			</div>
		</fieldset>
	</div>
	<div id="bottom">
		<label> 版权所有：zenghu 2014 </label>
	</div>
</body>
</html>
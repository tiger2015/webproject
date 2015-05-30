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
<title>发布者信息</title>
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
		<jsp:include page="/student/menu.jsp" flush="false"></jsp:include>
	</div>
	<div id="center">
		<s:if test="#request['teacher']!=null">
			<fieldset>
				<legend style="color: red;font-weight: bold;">教师信息</legend>
				<s:form cssStyle="color:blue;">
					<s:textfield label="教师编号"
						value="%{#request['teacher']['teacherId']}" readonly="true"></s:textfield>
					<s:textfield label="姓名"
						value="%{#request['teacher']['teacherName']}" readonly="true"></s:textfield>
					<s:textfield label="性别"
						value="%{#request['teacher']['teacherSex']}" readonly="true"></s:textfield>
					<s:textfield label="职位" value="%{#request['teacher']['position']}"
						readonly="true"></s:textfield>
					<s:textfield label="所在院系"
						value="%{#request['teacher']['department']['departmentName']}"
						readonly="true"></s:textfield>
					<s:textarea label="个人描述"
						value="%{#request['teacher']['courseDescribe']}" readonly="true"
						cols="20" rows="6" cssStyle="resize:none;"></s:textarea>
				</s:form>
			</fieldset>
		</s:if>
		<s:else>
			<fieldset>
				<legend style="color: red;font-weight: bold;">学生信息</legend>
				<s:form>
					<s:textfield label="学号" value="%{#request['student']['studentId']}"
						readonly="true"></s:textfield>
					<s:textfield label="姓名"
						value="%{#request['student']['studentName']}" readonly="true"></s:textfield>
					<s:textfield label="性别"
						value="%{#request['student']['studentSex']}" readonly="true"></s:textfield>
					<s:date name="%{#request['student']['studentBirthday']}"
						format="yyyy-MM-dd" var="birthday" />
					<s:textfield label="出生日期" value="%{#birthday}" readonly="true"></s:textfield>
					<s:textfield label="所在院系"
						value="%{#request['student']['department']['departmentName']}" readonly="true"></s:textfield>
					<s:textfield label="专业"
						value="%{#request['student']['discipline']['disciplineName']}" readonly="true"></s:textfield>
				</s:form>
			</fieldset>
		</s:else>

	</div>
	<div id="bottom">
		<label> 版权所有：zenghu 2014 </label>
	</div>
</body>
</html>
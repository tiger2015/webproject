<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>新建班级</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/teacher/css/welcome.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/teacher/css/teacher.css">
<script type="text/javascript"
	src="<%=basePath%>/teacher/js/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/teacher/js/teacherjs.js"></script>
</head>
<body>
	<div id="top">
		<h2>信息交流平台</h2>
	</div>
	<jsp:include page="/teacher/menu.jsp"></jsp:include>
	<div id="center">
		<fieldset>
			<legend style="color: red;font-weight: bold;">新建班级</legend>
			<div>
				<s:form action="teacher/AddChatClass" method="post" cssStyle="color:blue;">
					<s:textfield label="班级编号" name="chatClass.classId" id="classid"></s:textfield>
					<s:textfield label="班级名称(最多15个字)" name="chatClass.className"></s:textfield>
					<s:textfield label="班级容量" name="chatClass.capacity"></s:textfield>
					<s:textarea label="班级描述(最多80个字)" rows="5" cols="30"
						name="chatClass.classDescribe" cssStyle="resize:none;" id="classdescribe"></s:textarea>
					<s:submit value="提交"></s:submit>
					<%-- <s:reset value="重置"></s:reset> --%>
				</s:form>
			</div>
		</fieldset>
	</div>
	<div id="bottom">
		<label> 版权所有：zenghu 2014 </label>
	</div>
</body>
</html>

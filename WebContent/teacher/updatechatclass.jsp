<%@page import="com.entity.ChatClass"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.dao.*" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	/* if(session.getAttribute("chatclassid")!=null){
	   String chatClassId=session.getAttribute("chatclassid").toString();
	   ChatClass chatClass=(ChatClass) (new ChatClassDAO().findByProperty("classId", chatClassId, 0,null)).get(0);   
	   request.setAttribute("chatclass", chatClass);
	}	 */
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>更新班级信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>teacher/css/welcome.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>teacher/css/teacher.css">
<script type="text/javascript"
	src="<%=basePath%>teacher/js/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="<%=basePath%>teacher/js/teacherjs.js"></script>
</head>
<body>
	<div id="top">
		<h2>信息交流平台</h2>
	</div>
	<jsp:include page="/teacher/menu.jsp"></jsp:include>
	<div id="center">
	<s:set name="chatClass" value="#request['chatClass']"></s:set>
		<fieldset>
			<legend style="color: red;font-weight: bold;">更新班级</legend>
			<div>
				<s:form action="teacher/UpdateChatClass" method="post" cssStyle="color:blue;">
					<s:textfield label="班级编号" name="chatClass.classId"  value="%{#chatClass['classId']}" readonly="true"></s:textfield>
					<s:textfield label="班级名称" name="chatClass.className" value="%{#chatClass['className']}"></s:textfield>
					<s:textfield label="加入人数" name="chatClass.total" value="%{#chatClass['total']}" readonly="true" ></s:textfield>
					<s:textfield label="班级容量" name="chatClass.capacity" value="%{#chatClass['capacity']}"></s:textfield>
					<s:textarea label="班级描述" rows="5" cols="30"
						name="chatClass.classDescribe" value="%{#chatClass['classDescribe']}" cssStyle="resize:none;"></s:textarea>
					<s:submit value="提交"></s:submit>
				</s:form>
			</div>
		</fieldset>
	</div>
	<div id="bottom">
		<label> 版权所有：zenghu 2014 </label>
	</div>
</body>
</html>

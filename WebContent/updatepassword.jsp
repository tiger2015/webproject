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
<title>修改密码</title>
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
			<legend style="color: red;font-weight: bold;">修改密码</legend>
			<s:form action="login/UpdatePassword" method="post" cssStyle="color:blue;">
				<s:set value="%{#request.oldPassword}" var="ps1"></s:set>	
				<s:set value="%{#request.newPassword}" var="ps2"></s:set>	
				<%--<s:password name="oldPassword" label="原密码" value="%{#ps1}"></s:password>
				--%>
			    <%--
				<table>
				<tr>
				<td>原密码:</td>
				<td>
				<label for="oldPassword"></label>
				<input type="password" name="oldPassword" value="${ps1}"></td>
				</tr>
				<tr>
				<td>新密码：</td>
				<td><input type="password" name="newPassword" value="${ps2}"></td>
				</tr>
				<tr>
				<td>确认密码：</td>
				<td><input type="password" name="confirmPassword"></td>
				</tr>
				<tr>
				<td></td>
				<td>
				<input type="submit" value="提交">
				</td>
				</tr>
				</table>		
				--%>
				<s:password name="oldPassword" label="原密码"></s:password>
				<s:password name="newPassword" label="新密码"></s:password>
				<s:password name="confirmPassword" label="确认密码"></s:password>
				<s:submit value="提交"></s:submit>
				<s:reset value="重置"></s:reset>
			</s:form>
		</fieldset>
	</div>
	<div id="bottom">
		<label> 版权所有：zenghu 2014 </label>
	</div>
</body>
</html>

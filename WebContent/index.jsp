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
<title>登录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<style type="text/css">
.form {
	margin-top: 200px;
}

.errorMessage {
	color: red;
	font-size: 12px;
}
</style>
<body style="background:#00aaff;">
	<center class="form">
	<label style="font-size: 45px;font-weight: bold;">欢迎登录信息交流系统</label>
	<br><br>
		<fieldset style="width: 240px; height: 150px; background: #00ff00;">
			<legend> 欢迎登录 </legend>
			<s:form action="login/LoginAction" method="post">
		    <!-- <label>用户名:</label><input type="text" name="userinfo.userId"> -->
		    
		   <s:textfield label="用户名" name="userinfo.userId"></s:textfield>
		   <!-- <label style="margin-top: 5px;">密&nbsp;&nbsp;码:</label><input type="password" style="margin-top: 5px;" name="userinfo.userPassword"> -->
		   <s:password label="密  码"  name="userinfo.userPassword"></s:password>
				<table>		
					<tr>
						<td><input type="submit" value="登录" /> <input type="reset"
							value="重置" /></td>
					</tr>					
				</table>
			</s:form>
			
		</fieldset>
	</center>
</body>
</html>

<%@page import="com.entity.ChatClass"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.dao.*" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String teacherId = request.getSession().getAttribute("userid")
			.toString();
	List<ChatClass> list = new ChatClassDAO()
			.findByProperty("teacherId", teacherId, 0,null);
	request.setAttribute("result", list);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>发布留言</title>
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
		<s:if test="#request.result.isEmpty">
	<b style="color:red;">你还未创建班级!</b>
	</s:if>
		<s:else>
			<fieldset>
				<legend style="color: red;font-weight: bold;">新留言</legend>
				<s:form action="teacher/AddMessage" method="post" cssStyle="color:blue;">
					<input id="messageid" name="message.messageId"
						style="display: none;">
					<label>选择班级:</label>
					<select class="select" name="message.chatClass.classId">
						<s:iterator value="#request.result" status="chatclass">
							<s:if test="#chatclass.first">
								<option value="<s:property value="classId"/>"
									selected="selected">
									<s:property value="className" />
								</option>
							</s:if>
							<s:else>
								<option value="<s:property value="classId"/>">
									<s:property value="className" />
								</option>
							</s:else>
						</s:iterator>
					</select>
					<s:textfield label="标题(不超过30个字)" name="message.title"
						cssClass="title"></s:textfield>
					<s:textarea label="内容(不超过100个字)" name="message.messageContent"
						wrap="true" cols="30" rows="5" cssStyle="resize:none;"></s:textarea>
					<s:submit value="提交"></s:submit>
				</s:form>
			</fieldset>
		</s:else>
	</div>
	<div id="bottom">
		<label> 版权所有：zenghu 2014 </label>
	</div>
</body>
</html>

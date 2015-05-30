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
<title>上传课件</title>
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
			<b style="color: red;">你还未创建班级!</b>
		</s:if>
		<s:else>
			<fieldset>
				<legend style="color: red; font-weight: bold;">上传课件</legend>
				<div>
					<s:form action="teacher/UploadFile" method="post"
						enctype="multipart/form-data" cssStyle="color:blue;">
						<div>
							<label style="color: blue;">选择班级:</label> <select name="classId">


								<s:iterator value="#request.result" status="chatclass">
									<s:if test="chatclass.first">
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
						</div>
						<input id="fileid" value="" style="display: none;" name="fileId">
						<s:file label="课件" name="course"></s:file>
						<s:label cssStyle="front-size:10px;color:red;"
							value="注意：文件格式为zip/rar/doc/excel/ppt/txt/pdf/java/html,大小不超过100M."></s:label>
						<%--
				   <s:textfield label="课件描述" name="desciption"></s:textfield>
					--%>
						<s:submit value="提交"></s:submit>
						<s:reset value="重置"></s:reset>
					</s:form>
				</div>

			</fieldset>
		</s:else>
	</div>
	<div id="bottom">
		<label> 版权所有：zenghu 2014 </label>
	</div>
</body>
</html>

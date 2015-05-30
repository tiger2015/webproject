<%@page import="oracle.net.aso.s"%>
<%@page import="com.util.MySplitePage"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String classId="";
	if(request.getAttribute("classid")!=null)
	 classId=request.getAttribute("classid").toString();
	MySplitePage splitePage=null;
	if(session.getAttribute("splitepage")==null)
		splitePage=new MySplitePage();
	else
		splitePage=(MySplitePage)session.getAttribute("splitepage");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>课件管理</title>
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

		<s:if test="#request.chatclass.isEmpty">
			<b style="color: red;">你还未创建班级!</b>
		</s:if>
		<s:else>
			<div>
				<label style="color: blue;">选择班级</label> <select name="classId"
					id="chatclassid">
					<s:set name="classid" value="#request.classid"></s:set>
					<s:iterator value="#request.chatclass" status="chatclass">
						<s:if test="classId==#classid">
							<option value="<s:property value="classId"/>" selected="selected">
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

			<fieldset>
				<legend style="color: red; font-weight: bold;">已上传课件</legend>
				<s:if test="#request.coursefile.isEmpty">
					<b style="color: red;">没有上传的课件!</b>
				</s:if>
				<s:elseif test="#request.coursefile==null">
				<b style="color: red;">没有上传的课件!</b>
				</s:elseif>
				<s:else>
					<s:if test="#request.coursefile!=null">
						<table class="showtable">
							<thead>
								<tr>
									<td>课件编号</td>
									<td>课件名称</td>
									<td>上传时间</td>
									<td></td>
									<td></td>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#request.coursefile">
									<tr>
										<td><s:property value="courseFileId" /></td>
										<td><s:property value="courseName" /></td>
										<td><s:date name="uploadDate"
												format="yyyy-MM-dd HH:mm:ss" var="format1" /> <s:property
												value="#format1" /></td>
										<td><s:url action="teacher/DownloadFile" var="url">
												<s:param name="fileId" value="courseFileId"></s:param>
												<s:param name="fileName" value="courseName"></s:param>
											</s:url> <s:a href="%{#url}">下载</s:a></td>
										<td><s:url action="teacher/DeleteCourseFile" var="url1">
												<s:param name="fileId" value="courseFileId"></s:param>
												<s:param name="fileName" value="courseName"></s:param>
											</s:url> <%-- <s:a href="%{#url1}">删除</s:a> --%> <a
											href="javascript:void(0);"
											onclick="deleteCoursefile('<s:property value="courseFileId"/>','<s:property value="courseName"/>','<s:property value="#session['splitepage']['currentPage']"/>','<s:property value="#request['classid']"/>')">删除</a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<a href="<%=basePath%>/teacher/FindCourseFile?classId=<%=classId%>&currentpage=<%=1%>">首页</a>
						<a href="<%=basePath%>/teacher/FindCourseFile?classId=<%=classId%>&currentpage=<%=splitePage.getCurrentPage()+1%>">下一页</a>
						<a href="<%=basePath%>/teacher/FindCourseFile?classId=<%=classId%>&currentpage=<%=splitePage.getCurrentPage()-1%>">上一页</a>
						<a href="<%=basePath%>/teacher/FindCourseFile?classId=<%=classId%>&currentpage=<%=splitePage.getTotalPage()%>">尾页</a>
						<label style="color:blue;">当前页:<%=splitePage.getCurrentPage()%> 总页数:<%=splitePage.getTotalPage()%></label>
					</s:if>
				</s:else>
			</fieldset>
		</s:else>
	</div>
	<div id="bottom">
		<label> 版权所有：zenghu 2014 </label>
	</div>
</body>
</html>

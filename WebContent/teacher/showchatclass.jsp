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
<title>班级详细信息</title>
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
	<jsp:include page="/teacher/menu.jsp" flush="false"></jsp:include>
	<div id="center">
		<fieldset>
			<legend style="color: red; font-weight: bold;">班级信息</legend>
			<s:if test="#request['students']==null">
				<b style="color: red;">还没有学生加入!</b>
			</s:if>
			<s:elseif test="#request['students'].isEmpty">
				<b style="color: red;">还没有学生加入!</b>
			</s:elseif>
			<s:else>
				<table class="showtable">
					<thead>
						<tr>
							<td>学号</td>
							<td>姓名</td>
							<td>性别</td>
							<td>出生日期</td>
							<td>所在院系</td>
							<td>专业</td>
							<td>加入时间</td>
							<td></td>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="#request['students']">
							<tr>
								<td><s:property
										value="studentClassId['student']['studentId']" /></td>
								<td><s:property
										value="studentClassId['student']['studentName']" /></td>
								<td><s:property
										value="studentClassId['student']['studentSex']" /></td>
								<td><s:date
										name="studentClassId['student']['studentBirthday']" var="date"
										format="yyyy-MM-dd" /> <s:property value="#date" /></td>
								<td><s:property
										value="studentClassId['student']['department']['departmentName']" />
								</td>
								<td><s:property
										value="studentClassId['student']['discipline']['disciplineName']" />
								</td>
								<td><s:date name="joinDate" var="date"
										format="yyyy-MM-dd HH:mm:ss" /> <s:property value="#date" />
								</td>
								<td>
									<%--<s:property value="#request.classid"/>
							<s:property value="studentClassId['student']['studentId']"/>
							 <s:url action="teacher/RemoveStudent" var="myurl"
									escapeAmp="true">
									<s:param name="classId" value="#request.classid"></s:param>
									<s:param name="studentId"
										value="studentClassId['student']['studentId']"></s:param>
								</s:url> 					
								<a href="${myurl}" id="delete">删除</a></td> --%> <a
									href="javascript:void(0);"
									onclick="deleteStudentClass('<s:property value="#request.classid"/>','<s:property value="studentClassId['student']['studentId']"/>')">删除</a>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<a href="teacher/FindDetailChatClass?classid=<%=classId%>&currentpage=<%=1%>">首页</a>
				<a href="teacher/FindDetailChatClass?classid=<%=classId%>&currentpage=<%=splitePage.getCurrentPage()+1%>">下一页</a>
				<a href="teacher/FindDetailChatClass?classid=<%=classId%>&currentpage=<%=splitePage.getCurrentPage()-1%>">上一页</a>
				<a href="teacher/FindDetailChatClass?classid=<%=classId%>&currentpage=<%=splitePage.getTotalPage()%>">尾页</a>
				<label style="color:blue;">当前页:<%=splitePage.getCurrentPage() %> 总页数:<%=splitePage.getTotalPage() %></label>
				<%-- 	<s:debug>daddad</s:debug>点击显示调试的信息 --%>
			</s:else>
		</fieldset>
	</div>
	<div id="bottom">
		<label> 版权所有：zenghu 2014 </label>
	</div>
</body>
</html>

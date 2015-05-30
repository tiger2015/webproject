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
<title>课件下载</title>
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
		<jsp:include page="/student/menu.jsp" flush="false"></jsp:include>
	</div>
	<div id="center">
		<s:if test="#request.result==null">
			<b style="color: red;">你还未加入任何班级!</b>
		</s:if>
		<s:elseif test="#request.result.isEmpty">
			<b style="color: red;">你还未加入任何班级!</b>
		</s:elseif>
		<s:else>
			<label style="color: blue;">选择班级</label>
			<select id="chatclassselect">
				<s:set name="classid" value="#request.classid"></s:set>
				<s:iterator value="#request.result" status="chatclass">
					<s:if test="['studentClassId']['chatClass']['classId']==#classid">
						<option selected="selected"
							value="<s:property value="studentClassId.chatClass['classId']"/>">
							<s:property value="studentClassId.chatClass['className']" />
						</option>
					</s:if>
					<s:else>
						<option
							value="<s:property value="studentClassId.chatClass['classId']"/>">
							<s:property value="studentClassId.chatClass['className']" />
						</option>
					</s:else>
				</s:iterator>
			</select>
			<fieldset>
				<legend style="color: red; font-weight: bold;">已上传课件</legend>
				<s:if test="#request.coursefile==null">
					<b style="color: red;">没有上传的课件!</b>
				</s:if>
				<s:else>
					<s:if test="#request.coursefile.isEmpty">
						<b style="color: red;">没有上传的课件!</b>
					</s:if>
					<s:else>
						<table class="showtable">
							<thead>
								<tr>
									<td>课件编号</td>
									<td>课件名称</td>
									<td>上传时间</td>
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
										<td><s:url action="student/DownloadFile" var="url">
												<s:param name="fileId" value="courseFileId"></s:param>
												<s:param name="fileName" value="courseName"></s:param>
											</s:url> <s:a href="%{#url}">下载</s:a> <!-- 	<a href="javascript:void(0);">下载</a> -->
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<a href="<%=basePath%>/student/FindCourseFile?classId=<%=classId%>&currentpage=<%=1%>">首页</a>
						<a href="<%=basePath%>/student/FindCourseFile?classId=<%=classId%>&currentpage=<%=splitePage.getCurrentPage()+1%>">下一页</a>
						<a href="<%=basePath%>/student/FindCourseFile?classId=<%=classId%>&currentpage=<%=splitePage.getCurrentPage()-1%>">上一页</a>
						<a href="<%=basePath%>/student/FindCourseFile?classId=<%=classId%>&currentpage=<%=splitePage.getTotalPage()%>">尾页</a>
						<label style="color:blue;">当前页:<%=splitePage.getCurrentPage()%> 总页数:<%=splitePage.getTotalPage()%></label>
					</s:else>
				</s:else>
			</fieldset>
		</s:else>
	</div>
	<div id="bottom">
		<label> 版权所有：zenghu 2014 </label>
	</div>
</body>
</html>

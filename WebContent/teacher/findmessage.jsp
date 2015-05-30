<%@page import="com.util.MySplitePage"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String classId="";
	if(request.getAttribute("classId")!=null)
		classId=request.getAttribute("classId").toString();
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
<title>查看留言</title>
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
		<s:elseif test="#request.result==null">
			<b style="color: red;">你还未创建班级!</b>
		</s:elseif>
		<s:else>
			<%-- 	<s:form action="teacher/FindMessage" method="post"> --%>
			<label style="color: blue;">选择班级</label>
			<select name="classId" id="findmessage">
				<s:set name="classid" value="#request.classId"></s:set>
				<s:iterator value="#request.result" status="chatclass">
					<s:if test="#classid==classId">
						<option value="<s:property value="classId"/>" selected="selected">
							<s:property value="className" />
						</option>
					</s:if>
					<%-- <s:if test="#chatclass.first">
							<option value="<s:property value="classId"/>" selected="selected">
								<s:property value="className" />
							</option>
						</s:if> --%>
					<s:else>
						<option value="<s:property value="classId"/>">
							<s:property value="className"></s:property>
						</option>
					</s:else>
				</s:iterator>
			</select>
			<!-- 	<input type="submit" value="查询"> -->
			<%-- 	</s:form> --%>
			<s:if test="#request.messages==null">
			</s:if>
			<s:else>
				<fieldset>
					<legend style="color: red; font-weight: bold;">查看留言</legend>
					<s:if test="#request.messages.isEmpty">
						<b style="color: red;">没有留言!</b>
					</s:if>
					<s:elseif test="#request.messages==null">
						<b style="color: red;">没有留言!</b>
					</s:elseif>
					<s:else>
						<table class="showtable">
							<thead>
								<tr>
									<td>标题</td>
									<td>发布日期</td>
									<td>发布人</td>
									<td>回复</td>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#request.messages">
									<tr>
										<td><s:url action="teacher/FindResponse" var="myurl"
												escapeAmp="true">
												<s:param name="messageid" value="messageId"></s:param>
											</s:url> <s:a href="%{#myurl}" escapeAmp="true">
												<s:property value="title" />
											</s:a></td>
										<td><s:date name="messageDate" var="format1"
												format="yyyy-MM-dd HH:mm:ss" /> <s:property
												value="#format1" /></td>
										<td><s:property value="userinfo['userId']" /></td>
										<td><s:property value="responseCount" /></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<a href="<%=basePath%>/teacher/FindMessage?classId=<%=classId%>&currentpage=<%=1%>">首页</a>
						<a href="<%=basePath%>/teacher/FindMessage?classId=<%=classId%>&currentpage=<%=splitePage.getCurrentPage()+1%>">下一页</a>
						<a href="<%=basePath%>/teacher/FindMessage?classId=<%=classId%>&currentpage=<%=splitePage.getCurrentPage()-1%>">上一页</a>
						<a href="<%=basePath%>/teacher/FindMessage?classId=<%=classId%>&currentpage=<%=splitePage.getTotalPage()%>">尾页</a>
						<label style="color:blue;">当前页:<%=splitePage.getCurrentPage()%> 总页数:<%=splitePage.getTotalPage()%></label>
					</s:else>
				</fieldset>
			</s:else>
		</s:else>
	</div>
	<div id="bottom">
		<label> 版权所有：zenghu 2014 </label>
	</div>
</body>
</html>

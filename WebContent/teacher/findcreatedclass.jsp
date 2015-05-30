<%@page import="com.util.MySplitePage"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	MySplitePage splitePage = null;
	if (session.getAttribute("splitepage") == null)
		splitePage = new MySplitePage();
	else
		splitePage = (MySplitePage) session.getAttribute("splitepage");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>已建班级</title>
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
		<s:if test="#request.result==null">
			<b style="color: red;">你还未创建班级!</b>
		</s:if>
		<s:else>
			<s:if test="#request.result.isEmpty">
				<b style="color: red;">你还未创建班级!</b>
			</s:if>
			<s:else>
				<fieldset>
					<legend style="color: red; font-weight: bold;">已创建班级</legend>
					<table class="showtable">
						<thead>
							<tr>
								<td>班级编号</td>
								<td>班级名称</td>
								<td>班级容量</td>
								<td>已加入人数</td>
								<td>创建时间</td>
								<td></td>
								<td></td>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#request.result">
								<tr>
									<td><s:property value="classId" /></td>
									<td><s:url action="teacher/FindDetailChatClass" var="url">
											<s:param name="classid" value="classId"></s:param>
										</s:url> <s:a href="%{#url}">
											<s:property value="className" />
										</s:a></td>
									<td><s:property value="capacity" /></td>
									<td><s:property value="total" /></td>
									<td><s:date name="createDate" var="format1"
											format="yyyy-MM-dd HH:mm:ss" /> <s:property value="#format1" />
									</td>
									<td>
										<%-- <s:url action="FindChatClassById"
											escapeAmp="false" var="url" namespace="teacher">
											<s:param name="chatclassid">
												<s:property value="classId" />
											</s:param>
										</s:url> <s:a href="%{url}">编辑</s:a> --%> <a
										href="javascript:void(0);"
										onclick="updateChatClass('<s:property value="classId" />')">编辑</a>
									</td>
									<td>
										<%-- <s:url action="teacher/DeleteChatClass"
									escapeAmp="false" var="url">
									<s:param name="chatclassid">
										<s:property value="classId" />
									</s:param>
								</s:url> <s:a href="%{url}">删除</s:a> --%> <a
										href="javascript:void(0);"
										onclick="deleteChatClass('<s:property value="classId"/>')">删除</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<a href="teacher/FindCreatedClass?currentpage=<%=1%>">首页</a> <a
						href="teacher/FindCreatedClass?currentpage=<%=splitePage.getCurrentPage() + 1%>">下一页</a>
					<a
						href="teacher/FindCreatedClass?currentpage=<%=splitePage.getCurrentPage() - 1%>">上一页</a>
					<a
						href="teacher/FindCreatedClass?currentpage=<%=splitePage.getTotalPage()%>">尾页</a>
					<label style="color:blue;">当前页:<%=splitePage.getCurrentPage()%>
						总页数:<%=splitePage.getTotalPage()%></label>
				</fieldset>
			</s:else>
		</s:else>
	</div>
	<div id="bottom">
		<label> 版权所有：zenghu 2014 </label>
	</div>
</body>
</html>

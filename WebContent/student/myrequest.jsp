<%@page import="com.util.MySplitePage"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<title>我的申请</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript"
	src="<%=basePath%>/student/js/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/student/js/studentjs.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/student/css/welcome.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/student/css/student.css">
</head>

<body>
	<div id="top">
		<h2>信息交流平台</h2>
	</div>
	<jsp:include page="/student/menu.jsp" flush="false"></jsp:include>
	<div id="center">
		<s:if test="#request.result.isEmpty">
			<b style="color: red;">你还没有任何申请!</b>
		</s:if>
		<s:elseif test="#request.result==null">
		<b style="color: red;">你还没有任何申请!</b>
		</s:elseif>
		<s:else>
			<fieldset>
				<legend style="color: red; font-weight: bold;">我的申请</legend>
				<table class="showtable">
					<thead>
						<tr>
							<td>班级编号</td>
							<td>班级名称</td>
							<td>创建教师</td>
							<td>创建时间</td>
							<td>班级容量</td>
							<td>已加入人数</td>
							<td>申请时间</td>
							<td>是否通过</td>
						</tr>
					</thead>
					<tbody>
						<s:iterator id="request" status="myrequest"
							value="#request.result">
							<tr>
								<td><s:property value="joinRequestId.chatClass['classId']" /></td>
								<td><s:property
										value="joinRequestId.chatClass['className']" /></td>
								<td><s:property
										value="joinRequestId.chatClass['teacher']['teacherName']" /></td>
								<td><s:date name="joinRequestId.chatClass['createDate']"
										var="format1" format="yyyy-MM-dd HH:mm:ss" /> <s:property
										value="#format1" /></td>
								<td><s:property value="joinRequestId.chatClass['capacity']" />
								</td>
								<td><s:property value="joinRequestId.chatClass['total']" /></td>

								<td><s:date name="requestDate" var="format1"
										format="yyyy-MM-dd hh:mm:ss" /> <s:property value="#format1" /></td>
								<td><s:if test="isAllow==0">
                        还未通过
            </s:if> <s:else>
                        已通过
            </s:else></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<a href="<%=basePath%>/student/FindRequest?currentpage=<%=1%>">首页</a>
				<a href="<%=basePath%>/student/FindRequest?currentpage=<%=splitePage.getCurrentPage()+1%>">下一页</a>
				<a href="<%=basePath%>/student/FindRequest?currentpage=<%=splitePage.getCurrentPage()-1%>">上一页</a>
				<a href="<%=basePath%>/student/FindRequest?currentpage=<%=splitePage.getTotalPage()%>">尾页</a>
				<label style="color:blue;">当前页:<%=splitePage.getCurrentPage()%> 总页数:<%=splitePage.getTotalPage() %></label>
			</fieldset>
		</s:else>
	</div>
	<div id="bottom">
		<label> 版权所有：zenghu 2014 </label>
	</div>
</body>
</html>

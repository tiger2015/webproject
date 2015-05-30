<%@page import="oracle.net.aso.s"%>
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
	String classId="";
	if(request.getAttribute("classid")!=null)
		classId=request.getAttribute("classid").toString();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>申请处理</title>
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
			<div>
				<label style="color: blue;">选择班级</label> <select
					id="chatclassselect">
					<s:set name="classid" value="#request.classid"></s:set>
					<s:iterator value="#request.result" status="chatclass">
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
			<div>
				<fieldset>
					<legend style="color: red; font-weight: bold;">申请信息</legend>
					<s:if test="#request.requests.isEmpty">
						<b style="color: red;">没有申请信息!</b>
					</s:if>
					<s:elseif test="#request.requests==null">
						<b style="color: red;">没有申请信息!</b>
					</s:elseif>
					<s:else>
						<s:if test="#request.requests!=null">
							<table class="showtable">
								<thead>
									<tr>
										<td>学号</td>
										<td>姓名</td>
										<td>申请日期</td>
										<td>是否通过</td>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="#request.requests">
									<tr>
										<td><s:property
												value="joinRequestId['student']['studentId']" /></td>
										<td><s:property
												value="joinRequestId['student']['studentName']" /></td>
										<td><s:date name="requestDate" var="format1"
												format="yyyy-MM-dd HH:mm:ss" /> <s:property
												value="#format1" /></td>
										<td><s:if test="isAllow==0">
												<s:url action="teacher/DealWithRequest" escapeAmp="true"
													var="url">
													<s:param name="classid" value="#classid"></s:param>
													<s:param name="userid"
														value="joinRequestId['student']['studentId']"></s:param>
													<s:param name="currentpage" value="#session['splitepage']['currentPage']"></s:param>				    
												</s:url>
												<s:a href="%{url}">加入</s:a>
											</s:if> <s:else>
							 已通过
							</s:else>
							</td>
							</tr>
									</s:iterator>
								</tbody>
							</table>
							<a
								href="teacher/FindJoinRequest?classid=<%=classId%>&currentpage=<%=1%>">首页</a>
							<a
								href="teacher/FindJoinRequest?classid=<%=classId%>&currentpage=<%=splitePage.getCurrentPage() + 1%>">下一页</a>
							<a
								href="teacher/FindJoinRequest?classid=<%=classId%>&currentpage=<%=splitePage.getCurrentPage() - 1%>">上一页</a>
							<a
								href="teacher/FindJoinRequest?classid=<%=classId%>&currentpage=<%=splitePage.getTotalPage()%>">尾页</a>
							<label style="color: blue;">当前页:<%=splitePage.getCurrentPage()%>
								总页数:<%=splitePage.getTotalPage()%></label>
						</s:if>
					</s:else>
				</fieldset>
			</div>
		</s:else>
	</div>
	<div id="bottom">
		<label> 版权所有：zenghu 2014 </label>
	</div>
</body>
</html>

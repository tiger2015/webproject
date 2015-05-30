<%@page import="oracle.net.aso.s"%>
<%@page import="com.util.MySplitePage"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	MySplitePage splitePage = (MySplitePage) session
			.getAttribute("splitepage");
	
	if(splitePage==null)
		splitePage=new MySplitePage();
	int currentpage=splitePage.getCurrentPage();
	String className="";
	if(request.getAttribute("condition")!=null)
		className=request.getAttribute("condition").toString();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>加入班级</title>
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
		<div>
			<form action="student/FindChatClass" method="post">
				<label style="color: blue;">班级查询 </label> <input type="text"
					id="classname" name="className" value="${request.condition}" /> <input
					type="submit" value="查询" id="searchclass" />
			</form>
		</div>

		<div>
			<s:if test="#request.result==null">
			<!--    <b style="color:red;">没有班级信息!</b>   -->
			</s:if>
			<s:else>
				
					<s:if test="#request.result.isEmpty">
                                        <b style="color:red;">没有班级信息!</b>    
					</s:if>
					<s:else>
					<fieldset>
					<legend style="color: red; font-weight: bold;">班级信息</legend>
						<table class="showtable">
							<thead>
								<tr>
									<td>班级编号</td>
									<td>班级名称</td>
									<td>创建教师</td>
									<td>创建日期</td>
									<td>容量</td>
									<td>加入人数</td>
									<td></td>
									<td></td>
								</tr>
							</thead>
							<tbody>
								<s:iterator id="chatclass" status="chatclass"
									value="#request.result">
									<tr>
										<td><s:property value="classId" /></td>
										<td><s:property value="className" /></td>
										<td><s:property value="teacher['teacherName']" /></td>
										<td><s:date name="createDate" var="format1"
												format="yyyy-MM-dd HH:mm:ss" /> <s:property
												value="#format1" /></td>
										<td><s:property value="capacity" /></td>
										<td><s:property value="total" /></td>
										<td><label class="descibe">查看</label>
											<div class="descibehide">
												<s:property value="classDescribe" />
											</div></td>
										<td><s:if test="capacity==total">
							人已满
							</s:if> <s:elseif test="state==0">
							已申请							
							</s:elseif> <s:elseif test="state==1">
							已加入
							</s:elseif> <s:else>
												<s:url action="student/JoinChatClass" var="myurl"
													escapeAmp="true">
													<s:param name="classid" value="classId"></s:param>
													<s:param name="studentid" value="#session.userid"></s:param>
													<s:param name="condition" value="#request.condition"></s:param>
													<s:param name="currentpage" value="#session['splitepage']['currentPage']"></s:param>
												</s:url>
												<s:a href="%{myurl}" escapeAmp="true">加入</s:a>
											</s:else></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<a
							href="student/FindChatClass?className=<%=className%>&currentpage=<%=1%>">首页</a>
						<a
							href="student/FindChatClass?className=<%=className%>&currentpage=<%=splitePage.getCurrentPage() + 1%>">下一页</a>
						<a
							href="student/FindChatClass?className=<%=className%>&currentpage=<%=splitePage.getCurrentPage() - 1%>">上一页</a>
						<a
							href="student/FindChatClass?className=<%=className%>&currentpage=<%=splitePage.getTotalPage()%>">尾页</a> 
							<label style="color:blue;">当前页:<%=splitePage.getCurrentPage()%> 总页数:<%=splitePage.getTotalPage()%></label>
					</fieldset>
					</s:else>
				
			</s:else>

		</div>
	</div>
	<div id="bottom">
		<label> 版权所有：zenghu 2014 </label>
	</div>
</body>
</html>

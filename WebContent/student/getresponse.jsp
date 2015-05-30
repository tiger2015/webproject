<%@page import="com.util.MySplitePage"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String messageId="";
	if(request.getAttribute("messageid")!=null)
		messageId=request.getAttribute("messageid").toString();
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
<title>回复留言</title>
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
		<fieldset>
			<legend style="color: red;font-weight: bold;">留言</legend>
			<div>
				<table>
					<tr>
						<td><label style="color:blue;">标题:<s:property
									value="%{#request['message']['title']}" />
						</label> <%-- <s:textfield label="标题"
								value="%{#request['message']['title']}" readonly="true"></s:textfield> --%>
						</td>
					</tr>
					<tr>
						<td><label style="color:blue;">留言人:<s:property
										value="%{#request['message']['userinfo']['userId']}" />
						<%-- <a href="javascript:void(0);"
								onclick="findUserinfo('<s:property value="%{#request['message']['userinfo']['userId']}"/>')"><s:property
										value="%{#request['message']['userinfo']['userId']}" /></a> --%></label> <%-- <s:textfield label="留言人"
								value="%{#request['message']['userinfo']['userId']}"
								readonly="true"></s:textfield> --%></td>
					</tr>
					<tr>
						<td><s:date name="#request['message']['messageDate']"
								var="mydate" format="yyyy-MM-dd hh:mm:ss" /> <%--  <s:textfield
								label="发布日期" value="%{#mydate}" readonly="true"></s:textfield> --%>
							<label style="color:blue;">发布日期:<s:property value="%{#mydate}" />
						</label>
						</td>
					</tr>
					<tr>
					<td><label style="color:blue;">留言内容:</label></td>
					</tr>
					<tr>
						<td>
						<textarea rows="5" cols="30" style="resize:none;"><s:property value="%{#request['message']['messageContent']}"/> </textarea>
						</td>
					</tr>
					<tr>
						<td><label id="look">查看回复</label> <label id="add">发表回复</label>
						</td>
					</tr>
				</table>
				<div id="responses">
					<fieldset>
						<legend>回复</legend>
						<s:if test="#request.responses.isEmpty">
						<b style="color:red;">没有回复!</b>    
						</s:if>
						<s:elseif test="#request。responses==null">
						<b style="color:red;">没有回复!</b>
						</s:elseif>
						<s:else>
							<div>
								<table>
									<s:iterator value="#request.responses" status="response">
										<tr>
											<td style="font-size: 12px; color: green;">回复<s:property
													value="#response.count"/>
											</td>
										</tr>
										<tr>
											<td>回复内容:<s:property value="responseContent" /></td>
										</tr>
										<tr>
											<td style="font-size: 12px; color: red;">回复时间:<s:date
													name="responseDate" format="yyyy-MM-dd HH:mm:ss" var="date" />
												<s:property value="#date" />回复人:<s:property
														value="userinfo['userId']"/>
														<%-- <a
												href="javascript:void(0);"
												onclick="findUserinfo('<s:property
													value="userinfo['userId']"/>')"><s:property
														value="userinfo['userId']"/></a> --%>
											</td>
										</tr>
									</s:iterator>
								</table>
								<a href="student/FindResponse?messageid=<%=messageId%>&currentpage=<%=1%>">首页</a>
								<a href="student/FindResponse?messageid=<%=messageId%>&currentpage=<%=splitePage.getCurrentPage()+1%>">下一页</a>
								<a href="student/FindResponse?messageid=<%=messageId%>&currentpage=<%=splitePage.getCurrentPage()-1%>">上一页</a>
								<a href="student/FindResponse?messageid=<%=messageId%>&currentpage=<%=splitePage.getTotalPage()%>">尾页</a>
								<label style="color:blue;">当前页:<%=splitePage.getCurrentPage() %> 总页数:<%=splitePage.getTotalPage() %></label>
							</div>
						</s:else>
					</fieldset>
					<label id="close">收起</label>
				</div>
				<div id="addresponse" class="descibehide">
					<fieldset>
						<legend>发表回复</legend>
						<s:form action="student/AddResponse" method="post" onsubmit="return checkresponsecontent();">
							<input type="text" style="display: none;"
								value="${request['message']['messageId']}"
								name="response.message.messageId">
							<input type="text" style="display: none;"
								value="${session['userid']}"
								name="response.userinfo.userId">
							<input type="text" style="display: none;" id="responseid"
								name="response.responseId" value="">
							<s:textarea label="回复内容(100字以内)" name="response.responseContent"
								cols="30" rows="5" id="content" cssStyle="resize:none;"></s:textarea>
							<s:submit value="发表" id="addresponse"></s:submit>
						</s:form>
						<label id="close1">收起</label>
					</fieldset>
				</div>
			</div>
		</fieldset>
	</div>
	<div id="bottom">
		<label> 版权所有：zenghu 2014 </label>
	</div>
</body>
</html>

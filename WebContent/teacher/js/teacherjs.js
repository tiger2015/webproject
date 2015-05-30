$(document).ready(function() {
	var id = createClassid();
	$("#classid").val(id);
	$("#classid").attr("readonly", "readonly");

	$(".descibe").mouseover(function() {
		$(this).next().removeClass("descibehide");
		$(this).next().addClass("describeshow");
	}).mouseout(function() {
		$(this).next().removeClass("describeshow");
		$(this).next().addClass("descibehide");
	});

	//查看申请
	$("#chatclassselect").change(function() {
		var classid = $("#chatclassselect").val();
		window.location.href = "teacher/FindJoinRequest?classid=" + classid;
	}).click(function(){
		var count=$(this).get(0).options.length;
	//	if(count==1)
		//$(this).get(0).selectedIndex=-1;				
	});
	
	//设置留言id
	
	$("#messageid").val(id);
	$("#messageid").attr("readonly", "readonly");
	
	
	$("#look").click(function() {
		$("#responses").show();
	});

	$("#add").click(function() {
		$("#content").val("");
		$("#addresponse").show();
	});
	
	$("#close").css("background", "green");
	$("#close").click(function() {
		$("#responses").hide();
	}).mouseover(function() {
		$(this).css("background", "red");
	}).mouseout(function() {
		$(this).css("background", "green");
	});

	$("#close1").css("background", "green");
	$("#close1").click(function() {
		$("#addresponse").hide();
	}).mouseover(function() {
		$(this).css("background", "red");
	}).mouseout(function() {
		$(this).css("background", "green");
	});
	
	$("#responseid").val(id);
	
	$("#fileid").val("10"+id);
	
	/*$("#delete").click(function(){
		//var url=$(this).attr("href");
		//alter(url);
		//window.location.href=url;
	});*/
	//查询课件
	$("#chatclassid").change(function() {
		var classid = $("#chatclassid").val();
		window.location.href = "teacher/FindCourseFile?classId=" + classid;
	}).click(function(){
		var count=$(this).get(0).options.length;
		//if(count==1)
		//$(this).get(0).selectedIndex=-1;				
	});
	//查找发布信息
	$("#findmessage").change(function() {
		var classid = $("#findmessage").val();
		window.location.href = "teacher/FindMessage?classId=" + classid;
	}).click(function(){
		var count=$(this).get(0).options.length;
		//if(count==1)
		//$(this).get(0).selectedIndex=-1;				
	});
	$("#content").val("");
});


//发表回复
function checkresponsecontent() {
	var info = $("#content").val();
	if (info == '' || $.trim(info) == '') {
		alert("回复不能为空！");
		return false;
	} else if (info.lenght > 100) {
		alert("回复内容不能超过100个字符！");
		return false;
	} else
		return true;

}
// 生成id号
function createClassid() {
	var id;
	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth() + 1;
	var day = today.getDate();
	var hour = today.getHours();
	var minite = today.getMinutes();
	var second = today.getSeconds();
	if (month < 10)
		month = "0" + month;
	else
		month = "" + month;
	if (day < 10)
		day = "0" + day;
	else
		day = day + "";
	if (hour < 10)
		hour = "0" + hour;
	else
		hour = "" + hour;
	if (minite < 10)
		minite = "0" + minite;
	else
		minite = "" + minite;
	if (second < 10)
		second = "0" + second;
	else
		second = "" + second;
	id = year + month + day + hour + minite + second;
	return id;
}
//删除课件
function deleteCoursefile(fileid,filename,page,classid){	
	if(window.confirm("确定删除？")){	
		window.location.href="teacher/DeleteCourseFile?fileId="+fileid+"&fileName="+filename+"&currentpage="+page+"&classid="+classid;		
	}
}
//删除交流班级
function deleteChatClass(chatclassid)
{
	if(window.confirm("确定删除？")){	
		window.location.href="teacher/DeleteChatClass?chatclassid="+chatclassid;		
	}
}
//删除班级中的学生
function deleteStudentClass(classid,studentid)
{
	if(window.confirm("确定删除？")){	
		window.location.href="teacher/RemoveStudent?classId="+classid+"&studentId="+studentid;		
	}
}

function getUserinfo(userid){
	window.location.href="teacher/FindUserinfo?userId="+userid;
	//alert(userid);
}

//
function updateChatClass(chatClassId){
	window.location.href="teacher/FindChatClassById?chatclassid="+chatClassId;
	
}
$(document).ready(function() {
	//显示班级信息
	$(".descibe").mouseover(function() {
		$(this).next().removeClass("descibehide");
		$(this).next().addClass("describeshow");

		/* $(this).parent().parent().next().children().has(".descibe").attr("",""); */
	}).mouseout(function() {
		$(this).next().removeClass("describeshow");
		$(this).next().addClass("descibehide");

		/* $(this).parent().parent().next().children().has(".descibe").removeClass("descibehide"); */
	});
	var id = createClassid();
	$("#messageid").val(id);
	$("#messageid").attr("readonly", "readonly");

	$("#look").click(function() {
		$("#responses").show();
	});

	//发表回复被点击
	$("#add").click(function() {
		$("#content").val("");
		$("#addresponse").show();
	});
	
	$("#close").css("background", "green");
	//收起回复
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

	// 选择班级
	$("#chatclassselect").change(function() {
		var classid = $("#chatclassselect").val();
		window.location.href = "student/FindCourseFile?classId=" + classid;
	}).click(function() {
		var count = $(this).get(0).options.length;
		/*if (count == 1)
			$(this).get(0).selectedIndex = -1;*/
	});
	// 选择班级查询信息
	$("#chatclassid").change(function() {
		var classid = $("#chatclassid").val();
		window.location.href = "student/FindMessage?classId=" + classid;
	}).click(function() {
		var count = $(this).get(0).options.length;
	/*	if (count == 1)
			$(this).get(0).selectedIndex = -1;*/
	});
});
// 发表回复
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

function findUserinfo(userId) {
	// alert(userId);

	window.location.href = "student/FindUserinfo?userId=" + userId;
}

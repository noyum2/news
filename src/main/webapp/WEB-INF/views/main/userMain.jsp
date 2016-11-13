<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script src="resources/js/jquery.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
<title>News Tracer</title>
<style>
.main_left_top{
	border:1px solid #368AFF;
	border-radius:4px;
	width:100%;
	background-color: white;
}
.main_left_bottom{
	border:1px solid #368AFF;
	border-radius:4px;
	width:100%;
	background-color: white;
}
.inner_main_left_top{
	background-color: #368AFF;
	
}
.drop_down_keyWord{
	background-color: white;
	display: none;
}
.drop_down_account{
	background-color: white;
	display: none;
}
.page_left{
	float:left;
	width:30%;
	position: fixed;
}
.page_right{
	float:right;
	width:60%;
	margin-right: 5%;
}
.newsfeed{
	width:500px;
	border:1px solid #368AFF;
	border-radius:4px;
	width:100%;
}
.newsfeed_title{
	background-color: #368AFF;

}
.newsfeed_contents{
	background-color: white;
	
}
</style>
<script>
function showKeyWord(){
	document.getElementById("drop_down_keyWord").style.display = (document.getElementById("drop_down_keyWord").style.display == "none") ? "inline" : "none";

}
function showAccount(){
	document.getElementById("drop_down_account").style.display = (document.getElementById("drop_down_account").style.display == "none") ? "inline" : "none";

}
function showInput(){
	document.getElementById('inputKeyWord').style.display='inline';
}
function checkEnter(){
	if(event.keyCode==13){
		var value=document.getElementById("inputKeyWord").value;
		$('#divKeyWord').append("<input type='text' name='keyword' value="+value+">");
		document.getElementById("inputKeyWord").value="";
	}
}
function showNewsFeedByKeyWord(keyword){
	var data = {};
	data["keyword"]=keyword;
	$.ajax({
		url : '/news/signin/aa',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data : data,
		success : function(result) {
			makeNewsList(result);
		},
		error : function(request, status, error) {
			alert("code:" + request.status + "\n"
					+ "error:" + error);
		}
	});
}
function makeNewsList(result){
	for (var i in result){
		$('#newsfeed').append("<div class='newsfeed_title'>"+result[i].getNewsTitle()+"</div><div id='newsfeed_contents' class='newsfeed_contents'>"+result[i].getNewsDescription()+"</div></div><br/");
	}
	
}
</script>
</head>
<body style="background-color: gray; margin:0px">
<c:import url="/WEB-INF/views/header&footer/header.jsp"></c:import>
<br/>
<div class="page_left">
	<div class="main_left_top">
		<div onclick="showKeyWord()" class="inner_main_left_top">
			<span style="color:white;"> + 내 키워드 보기</span>
		</div>
		<div id="drop_down_keyWord" class="drop_down_keyWord">
			<c:forEach items="${keywords}" var="list">
			<li onclick="showNewsFeedByKeyWord(${list.getContent()})">${list.getContent()}</li>
			</c:forEach>
			<span onclick="showInput()">+키워드추가</span><br/>
			<div ><form action="/news/mainPage/inputKeyword" method="POST"><div id="divKeyWord"></div><input type="submit"></form></div>
			<input type="text" id="inputKeyWord" style="display:none" onkeydown="checkEnter()">
		</div>
	</div>
	<br/>
	<div class="main_left_bottom">
		<div onclick="showAccount()" class="inner_main_left_top">
			<span style="color:white;"> + 내 정보 보기</span>
		</div>
		<div id="drop_down_account" class="drop_down_account">
			<li>윤현도</li>
			<li>윤현도</li>
			<li>윤현도</li>
			<li>윤현도</li>
			<li>윤현도</li>
		</div>
	</div>
</div>
<div class="page_right">
	<div id="newsfeed" class="newsfeed">
		<div class="newsfeed_title">
			title
		</div>
			<div id="newsfeed_contents" class="newsfeed_contents">
				contents<span>더보기</span>
			</div>
	</div>
	<br/>
</div>
</body>
</html>
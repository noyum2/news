<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
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
			<li>윤현도</li>
			<li>윤현도</li>
			<li>윤현도</li>
			<li>윤현도</li>
			<li>윤현도</li>
			<span>+키워드추가</span>
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
	<div class="newsfeed">
		<div class="newsfeed_title">
			title
		</div>
			<div class="newsfeed_contents">
				contents<span>더보기</span>
			</div>
		<br/>
	</div>
	
	
</div>
</body>
</html>
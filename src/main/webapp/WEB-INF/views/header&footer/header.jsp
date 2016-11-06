<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>News Tracer</title>
<style>
.head{
background-color:#368AFF;
margin: 0px;
height: 75px;
}
.head_title{
color: white;
margin: 0px;
float:left;
}
.login{
float:right;
}
</style>
</head>
<body style="background-color: gray; margin:0px">
<div class="head">
	<br/>
	<h2 class="head_title">NEWS TRACER</h2>
	<c:choose>
	<c:when test=${not empty sessionScope.user}>
		<p>${sessionScope.user.getName()}</p>
	</c:when>
	<c:otherwise>
		<div class="login">
		<input id="id" name="id" placeholder="아이디">
		<input id="pw" name="pw" placeholder="비밀번호">
		<button type="button" onclick="login()">제출</button>
	</div>
	</c:otherwise>
	</c:choose>
	
</div>
</body>
<script>
function login(){
	var id=document.getElementById("id").value;
	var pw=document.getElementById("pw").value;
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content"); // THIS WAS ADDED
	var data = {};
	var headers = {};
	data[csrfParameter] = csrfToken;
	data["id"] =id;
	data["pw"]=pw;
	headers[csrfHeader] = csrfToken;
	$.ajax({
		url : "/news/signin/",
		dataType : "json",
		type : "POST",
		headers : headers,
		data : data,
		success : function(data) {
			alert("data");
		},
		error : function(request, status, error) {
			alert("code:" + request.status + "\n" + "error:" + error);
		}

	});
}
</script>
</html>